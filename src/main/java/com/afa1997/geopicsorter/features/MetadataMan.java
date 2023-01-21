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
        if (!Files.isDirectory(pictures_loc)) {
            throw new IllegalArgumentException("Invalid directory.");
        }

        List<String> temp_list;
        List<String> out_list = new ArrayList<>();
        int file_entry = 0;
        
        try (Stream<Path> walk = Files.walk(pictures_loc, 1)) {
            temp_list = walk
                .filter(p -> !Files.isDirectory(p))
                // convert path to string
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
        for (String fileExtension : ShStrings.PICTURE_EXTS) {
            if (file.endsWith(fileExtension)) {
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
        
        try{
            String base_command = "INSERT INTO pictures (location, filename) VALUES (";
            int detected_pictures = 0;
            store_locations_db = DriverManager.getConnection(ShStrings.METADATA_DB);
            StringBuilder writing_commands = new StringBuilder();
            writing_commands.append(base_command);
            
            // Build the INSERT query to insert various pictures with their locations in one go.
            for(int x = 0 ; x < ft ; x++){
                if(x == ft-1)
                    writing_commands.append("\"").append(pictures_location).append("\\\", \"").append(pictures_fns.get(x)).append("\")");
                else
                    writing_commands.append("\"").append(pictures_location).append("\\\", \"").append(pictures_fns.get(x)).append("\"), (");
                
                detected_pictures++;
            }
            
            // If at least one picture was detected, write the file names into the database.
            if(detected_pictures > 0){
                writing_commands.append(";");
                
                Statement write_locations_to_db = store_locations_db.createStatement();
                write_locations_to_db.executeUpdate(writing_commands.toString());
            }
            // TODO: add visual feedback for when no pictures are detected.
            else
                System.out.println(ShStrings.SORTING_STATUS_NO_PICS);
        } catch(SQLException sql_ex){
            sql_ex.getMessage();
        } finally {
            store_locations_db.close();
        }
        
        return pictures_fns;
    }
    
    // Gets latitude and longitude from each picture and writes it to the database.
    public List<String[]> MMIF_GetCoords(String pictures_location) throws SQLException {
        List<String[]> out_list_locs = new ArrayList<>();
        
        try{
            store_locations_db = DriverManager.getConnection(ShStrings.METADATA_DB);
            String select_command = "SELECT * FROM pictures;";
            
            // Acquire file names at the database.
            Statement st_sel_fns = store_locations_db.createStatement();
            ResultSet rs_sel_fns = st_sel_fns.executeQuery(select_command);
            
            // To create UPDATE command batch onto the database.
            Statement st_upd_loc = store_locations_db.createStatement();
            
            // Try to get latitude and longitude for each picture, and writes them into the database if appliable.
            while(rs_sel_fns.next()){
		try {
                    String lat, lng;
                    
                    //File current_picture = new File(pictures_location + rs_sel_fns.getString("filename"));
                    File current_picture = new File(rs_sel_fns.getString("location") + rs_sel_fns.getString("filename"));
		    md = ImageMetadataReader.readMetadata(current_picture);
                    
                    GpsDirectory addr = md.getFirstDirectoryOfType(GpsDirectory.class);
                    
                    // If there's geolocation data, retrieve it:
                    if(addr != null){
                        lat = String.valueOf(addr.getGeoLocation().getLatitude());
                        lng = String.valueOf(addr.getGeoLocation().getLongitude());
                    }
                    // Otherwise, send an empty string to indicate the picture has no coordinates registered:
                    else{
                        lat = "";
                        lng = "";
                    }
                    
                    // Add location info to the matching picture in the database, ONLY if both latitude and longitude are present, otherwise keeps the corresponding fields empty, having only one of the parameters makes no sense after all.
                    if(!lat.isEmpty() && !lng.isEmpty())
                        st_upd_loc.addBatch("UPDATE pictures SET latitude = " + lat + ", longitude = " + lng + " WHERE id = " + rs_sel_fns.getString("id") + ";");
                    
                    String[] out_list_locs_d = {rs_sel_fns.getString("filename"), lat, lng};
                    out_list_locs.add(out_list_locs_d);
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
        
        // TODO: List is used for first table recreation to show GPS coordinates, probably won't be necessary but it's going to be evaluated.
        return out_list_locs;
    }
}