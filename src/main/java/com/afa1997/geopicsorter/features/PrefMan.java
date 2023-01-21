package com.afa1997.geopicsorter.features;

// Internal:
import com.afa1997.geopicsorter.GeoPicSorter;

// File handling:
import java.nio.file.*;

// Logging:
import java.util.logging.Level;
import java.util.logging.Logger;

// SQL-related imports:
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// Lists:
import java.util.ArrayList;
import java.util.List;

// This class manages user preferences for GeoPicSorter, such as language, used API and organization criteria.
public class PrefMan {
    Connection store_prefs_db;
    static List<String[]> default_settings_gen = new ArrayList<>();
    static final String PREF_RESET_ACT0 = "DROP TABLE IF EXISTS settings;";
    static final String PREF_RESET_ACT1 = "CREATE TABLE IF NOT EXISTS settings (s_key TEXT PRIMARY KEY, s_value TEXT);";
    
    public PrefMan(){
        /*
            Description of each setting in order:

            · Language of the program.
            · Organization criteria for pictures (as shown in [frames.SortingCriteria]).
            · Action to run when sorting pictures (see [frames.help.SortActExpl] for details).
            · Reverse geocoding API, there's plans to give the user the options to switch between Geoapify and LocationIQ for online options, and an offline reverse geocoding tool that should be used by default.
            · API key needed for the reverse geocoding, a public, free one is supplied, but the user is highly encouraged to supply their own (it's free).
            · Sort pictures inside location folders by date (optional). An option to order by date>location is also planned.
            · Last directory from which the user has loaded pictures, default is the program's base directory.
        */
        
        // Clear entries because these can get duplicated, if this isn't done, running the SQL query further below results in a SQLException (duplicate PKs).
        if(!default_settings_gen.isEmpty())
            default_settings_gen.clear();
        
        default_settings_gen.add(new String[]{"language", "en"});
        default_settings_gen.add(new String[]{"organization_criteria", "0"});
        default_settings_gen.add(new String[]{"sort_act", "0"});
        default_settings_gen.add(new String[]{"rg_api", "geoapify"});
        default_settings_gen.add(new String[]{"api_key", "0f885492e6b641c2a93406bc729a2d23"});
        default_settings_gen.add(new String[]{"sub_by_date", "false"});
        default_settings_gen.add(new String[]{"last_work_dir", "."});
        default_settings_gen.add(new String[]{"keep_last_work_dir", "true"});
    }
    
    // Used when creating settings file for the first time or re-creating it in case of damage/deletion.
    public void PREF_InitDefaults() throws SQLException{
        Path f_settings_db = Paths.get(ShStrings.SETTINGS_DB_FN);
        
        if(!Files.exists(f_settings_db)){
            try {
                store_prefs_db = DriverManager.getConnection(ShStrings.SETTINGS_DB);
                Statement settings_tbl_st = store_prefs_db.createStatement();

                    DatabaseMetaData store_settings_dbmd;

                    if(store_prefs_db != null)
                        store_settings_dbmd = store_prefs_db.getMetaData();

                // First reset settings to default by re-creating the table, then inserting the default values.
                settings_tbl_st.executeUpdate(PREF_RESET_ACT0);
                settings_tbl_st.executeUpdate(PREF_RESET_ACT1);

                for(String[] curr_setting : default_settings_gen)
                    settings_tbl_st.addBatch("INSERT INTO settings VALUES (\"" + curr_setting[0] + "\", \"" + curr_setting[1] + "\");");

                settings_tbl_st.executeBatch();

            } catch (SQLException ex) {
                Logger.getLogger(GeoPicSorter.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                store_prefs_db.close();
            }
        }
    }
    
    // Restore preferences to defaults, done when clicking [Reset] button on preferences window, then saving the changes. This function is called under such condition to make sure the user wants to restore the default values.
    public void PREF_RestoreDefaults() throws SQLException {
        try {
            store_prefs_db = DriverManager.getConnection(ShStrings.SETTINGS_DB);
            Statement settings_tbl_st = store_prefs_db.createStatement();

            settings_tbl_st.executeUpdate(PREF_RESET_ACT0);
            settings_tbl_st.executeUpdate(PREF_RESET_ACT1);

            for(String[] curr_setting : default_settings_gen){
                System.out.println("Key = " + curr_setting[0] + " | Value = " + curr_setting[1]);
                
                settings_tbl_st.addBatch("INSERT INTO settings VALUES (\"" + curr_setting[0] + "\", \"" + curr_setting[1] + "\");");
            }

            settings_tbl_st.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(GeoPicSorter.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            store_prefs_db.close();
        }
    }
}
