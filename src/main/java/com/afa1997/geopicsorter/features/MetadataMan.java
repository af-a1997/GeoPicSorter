package com.afa1997.geopicsorter.features;

// SQL-related imports
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

// Metadata-extractor, used to get the latitude and longitude info from the pictures:
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;

// I/O related libraries:
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// Java utils:
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// GeoPicSorter class that loads picture locations and fetches their geotags.
public class MetadataMan {
    static Connection store_locations_db;
    static DatabaseMetaData store_locations_dbmd;
    static Metadata md = new Metadata();
    
    // Upon class instancing, check if database exists, if not then create it, otherwise clear its contents by re-creating the table.
    public MetadataMan(boolean reset_db) throws SQLException {
        final String ACT_CREATE_PICS_TBL = "CREATE TABLE IF NOT EXISTS pictures (id INTEGER PRIMARY KEY AUTOINCREMENT, location TEXT NOT NULL, filename TEXT NOT NULL, latitude REAL, longitude REAL, location_name TEXT);";
        
        File check_db_existance = new File(ShStrings.METADATA_DB_FN);
        
        // Create the database if it doesn't exist.
        if(!check_db_existance.exists() && !check_db_existance.isDirectory()){
            try{
                store_locations_db = DriverManager.getConnection(ShStrings.METADATA_DB);

                if(store_locations_db != null)
                    store_locations_dbmd = store_locations_db.getMetaData();
                
                Statement table_creation = store_locations_db.createStatement();
                table_creation.execute(ACT_CREATE_PICS_TBL);
            } catch(SQLException sql_ex){
                sql_ex.getMessage();
            } finally {
                store_locations_db.close();
            }
        }
        // Re-create the table inside the database if it exists, this cleans any previously registered files and resets the auto increment count.
        else{
            // [reset_db] prevents that the table is re-created when the class is instanced and GeoPicSorter is writing the GPS coordinates into the matching cells.
            if(reset_db){
                try{
                    store_locations_db = DriverManager.getConnection(ShStrings.METADATA_DB);

                    Statement table_recreation = store_locations_db.createStatement();

                    table_recreation.executeUpdate("DROP TABLE pictures;");
                    table_recreation.executeUpdate(ACT_CREATE_PICS_TBL);
                } catch(SQLException sql_ex){
                    sql_ex.getMessage();
                } finally {
                    store_locations_db.close();
                }
            }
            else System.out.println(ShStrings.SORTING_STATUS_FETCH_GEOTAGS);
        }
    }

    // The following two methods are used to get a list of files with one or more extensions. Credit to base code at: < https://mkyong.com/java/how-to-find-files-with-certain-extension-only/ >, I modified it a little to adapt it to GeoPicSorter and return only filenames.
    public static List<String> picturesListGen(Path pictures_loc) throws IOException {
        if (!Files.isDirectory(pictures_loc))
            throw new IllegalArgumentException("Invalid directory.");
        
        List<String> temp_list;
        List<String> out_list = new ArrayList<>();
        int file_entry = 0;
        
        try (Stream<Path> walk = Files.walk(pictures_loc, 1)) {
            temp_list = walk
                .filter(p -> !Files.isDirectory(p))
                .map(p -> p.toString())
                .filter(f -> pictureHasValidFormat(f))
                .collect(Collectors.toList());
        }
        
        for(String transfer_e : temp_list){
            out_list.add(transfer_e.substring(temp_list.get(file_entry).lastIndexOf("\\") + 1));
            
            file_entry++;
        }
        
        return out_list;
    }
    private static boolean pictureHasValidFormat(String file) {
        boolean result = false;
        for (String file_ex : ShStrings.PICTURE_EXTS) {
            if (file.endsWith(file_ex)) {
                result = true;
                break;
            }
        }
        return result;
    }
    
    /*
     * Methods dedicated to operating with pictures on a single folder.
     * TODO: create methods for operating with pictures on different folders.
     */
    
    // Write the file names to the database.
    public List<String> MMIF_WriteFileNames(String pictures_location) throws SQLException, IOException {
        List<String> pictures_fns = picturesListGen(Paths.get(pictures_location));
        
        int ft = pictures_fns.size();
        
        if(ft == 0) return null;
        
        try{
            store_locations_db = DriverManager.getConnection(ShStrings.METADATA_DB);
            
            Statement send_location_list_to_db = store_locations_db.createStatement();
            
            // Queries for storing the picture names.
            for(int x = 0 ; x < ft ; x++)
                send_location_list_to_db.addBatch("INSERT INTO pictures (location, filename) VALUES (\"" +  pictures_location + "\\" + "\", \"" + pictures_fns.get(x) + "\");");
            
            send_location_list_to_db.executeBatch();
        } catch(SQLException sql_ex){
            sql_ex.getMessage();
        } finally {
            store_locations_db.close();
        }
        
        return pictures_fns;
    }
    
    // Gets latitude and longitude from each picture and writes it to the database.
    public int MMIF_GetCoords(String pictures_location) throws SQLException {
        // Counter used to keep track of geotagged pictures total, if it's zero, the window for starting the sorting process won't open.
        int geotagged_pictures = 0;
        
        try{
            store_locations_db = DriverManager.getConnection(ShStrings.METADATA_DB);
            
            // Acquire file names at the database.
            String select_command = "SELECT * FROM pictures;";
            Statement st_sel_fns = store_locations_db.createStatement();
            ResultSet rs_sel_fns = st_sel_fns.executeQuery(select_command);
            
            // To create UPDATE command batch onto the database.
            Statement st_upd_loc = store_locations_db.createStatement();
            
            String lat, lng;
            
            // Navigate through the pictures list to attempt getting coordinates that may be present in each of the pictures.
            while(rs_sel_fns.next()){
		try {
                    File current_picture = new File(rs_sel_fns.getString("location") + rs_sel_fns.getString("filename"));
                    
                    // Get metadata from picture.
		    md = ImageMetadataReader.readMetadata(current_picture);
                    
                    // If there's geolocation data in the current picture, retrieve it and store it on the database, for use at the sorting process.
                    if(md.containsDirectoryOfType(GpsDirectory.class)){
                        GpsDirectory addr = md.getFirstDirectoryOfType(GpsDirectory.class);
                        
                        if(addr.containsTag(GpsDirectory.TAG_LATITUDE) && addr.containsTag(GpsDirectory.TAG_LONGITUDE)){
                            GeoLocation gl_data = addr.getGeoLocation();

                            lat = String.valueOf(gl_data.getLatitude());
                            lng = String.valueOf(gl_data.getLongitude());
                            
                            st_upd_loc.addBatch("UPDATE pictures SET latitude = " + lat + ", longitude = " + lng + " WHERE id = " + rs_sel_fns.getInt("id") + ";");
                            
                            geotagged_pictures++;
                        }
                    }
		} catch (ImageProcessingException | IOException e) {
                    e.getMessage();
		}
            }
            
            st_upd_loc.executeBatch();
        } catch(SQLException sql_ex){
            sql_ex.getMessage();
        } finally {
            store_locations_db.close();
        }
        
        return geotagged_pictures;
    }
}