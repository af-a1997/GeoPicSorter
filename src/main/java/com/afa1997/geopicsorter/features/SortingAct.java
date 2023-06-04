package com.afa1997.geopicsorter.features;

// SQL-related imports:
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

// I/O related libraries:
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

// Java utils:
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Library to compress files into ZIP file:
import net.lingala.zip4j.ZipFile;

// GeoPicSorter class that handles picture re-allocation by sorting criteria, which is defined on the reverse geocoding classes when initiated (because it handles the JSON output from the API responses and make requests).
public class SortingAct {
    static Connection store_locations_db, settings_db_conn;
    
    public SortingAct(String output_location) throws FileNotFoundException, IOException, SQLException {
        List<File> pictures_to_compress = new ArrayList<>();
        
        try{
            store_locations_db = DriverManager.getConnection(ShStrings.METADATA_DB);
            settings_db_conn = DriverManager.getConnection(ShStrings.SETTINGS_DB);
            
            // Acquire file names at the database.
            Statement st_sel_fns = store_locations_db.createStatement();
            Statement save_loc_names = store_locations_db.createStatement();
            Statement st_get_prefs = settings_db_conn.createStatement();
            
            // Selects pictures list and loads file handling preference.
            ResultSet rs_sel_fns = st_sel_fns.executeQuery("SELECT * FROM pictures;");
            ResultSet rs_get_prefs_sortact = st_get_prefs.executeQuery("SELECT s_value FROM settings WHERE s_key = \"sort_act\";");
            int psa_v = rs_get_prefs_sortact.getInt("s_value");
            
            while(rs_sel_fns.next()){
                String orig_dir = rs_sel_fns.getString("location");
                if(orig_dir == null)
                    orig_dir = ShStrings.FOLDER_NO_GEOTAG;
                
                File dest_dir = new File(orig_dir + rs_sel_fns.getString("location_name"));
                File origin_pict = new File(orig_dir + rs_sel_fns.getString("filename"));
                File destination = new File(dest_dir + "\\" + rs_sel_fns.getString("filename"));
                
                // Create a folder for the location group if it doesn't exist.
                dest_dir.mkdirs();
                
                // If both the directory exist and is directory, do one of the following sorting actions based on user preference.
                if(dest_dir.isDirectory() && dest_dir.exists()){
                    /*
                        · 0 = Only copy the pictures to their matching destination folders.
                        · 1 = Copy the pictures then delete the unsorted copies.
                        · 2 = Move the pictures.
                        · 3 = Pack the unsorted copies in a ZIP file, copies them to the matching destination folders then delete the unsorted copies.
                    */
                    if(psa_v == 0 || psa_v == 1 || psa_v == 3){
                        try {
                            FileUtils.copyFile(origin_pict, destination);
                            
                            if(psa_v == 1)
                                FileUtils.delete(origin_pict);
                            else if(psa_v == 3)
                                pictures_to_compress.add(origin_pict);
                        } catch (IOException ex) {
                            Logger.getLogger(SortingAct.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else if(psa_v == 2){
                        try {
                            FileUtils.moveFile(origin_pict, destination);
                        } catch (IOException ex) {
                            Logger.getLogger(SortingAct.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            
            // After sorting the pictures, if the last sorting action was chosen, pack the unsorted copies and delete them after compression.
            if(psa_v == 3){
                new ZipFile(output_location + "\\" + "unsorted_pictures.zip").addFiles(pictures_to_compress);
                
                for(File picture_to_delete : pictures_to_compress)
                    FileUtils.delete(picture_to_delete);
            }
            
            save_loc_names.executeBatch();
        }
        catch(SQLException exc){    
            exc.getMessage();
        }
        finally{    
            store_locations_db.close();
            settings_db_conn.close();
        }
    }
}
