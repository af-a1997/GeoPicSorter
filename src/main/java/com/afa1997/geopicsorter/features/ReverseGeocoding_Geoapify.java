package com.afa1997.geopicsorter.features;

// SQL-related imports:
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

// OkHttp's libraries for making API requests and handling responses:
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// I/O Exception:
import java.io.IOException;

// JSON output handling:
import org.json.JSONArray;
import org.json.JSONObject;

// Lists:
import java.util.ArrayList;
import java.util.List;

// GeoPicSorter class that interacts with the Geoapify Reverse Geocoding API to fetch location names.
public class ReverseGeocoding_Geoapify {
    static Connection store_locations_db, settings_db;
    static String geoapify_api_key = new String();
    
    public List<String[]> RG_SRC0_GetLocNames() throws SQLException {
        int processed_pictures = 0;
        int total_pictures;
        
        List<String[]> return_locations_to_table = new ArrayList<>();
                    
        // Keywords related to sorting criterias, most of them are actual API body response keys (in the JSON object) but a few like [continent] aren't, they're here for ease of readability and make the logic below easier to mantain/update.
        String org_crit_str;
        
        try{
            store_locations_db = DriverManager.getConnection(ShStrings.METADATA_DB);
            settings_db = DriverManager.getConnection(ShStrings.SETTINGS_DB);
            
            // Acquire file names at the pictures list database, and the API key stored at the settings database.
            Statement st_sel_fns = store_locations_db.createStatement();
            Statement save_loc_names = store_locations_db.createStatement();
            Statement get_api_key = settings_db.createStatement();
            Statement get_sort_pref = settings_db.createStatement();
            
            // Gets the list of pictures in the database.
            ResultSet rs_sel_fns = st_sel_fns.executeQuery("SELECT * FROM pictures;");
            
            // Gets the API key stored in the settings.
            ResultSet rs_api_key = get_api_key.executeQuery("SELECT s_value FROM settings WHERE s_key = \"api_key\";");
            geoapify_api_key = rs_api_key.getString("s_value");
            
            // Get sorting criteria value from preferences.
            ResultSet rs_sort_crit = get_sort_pref.executeQuery("SELECT s_value FROM settings WHERE s_key = \"organization_criteria\";");
            switch (rs_sort_crit.getInt("s_value")){
                case 0:
                    org_crit_str = "street";
                    break;
                case 1:
                    org_crit_str = "city";
                    break;
                case 2:
                    org_crit_str = "state";
                    break;
                case 3:
                    org_crit_str = "country";
                    break;
                case 4:
                    org_crit_str = "continent";
                    break;

                default:
                    org_crit_str = "street";
            }
            
            // Counts total of pictures to log into console.
            // TODO: remove and implement proper progress display, maybe logging to file too.
            Statement st_cnt_fns = store_locations_db.createStatement();
            ResultSet rs_cnt_fns = st_cnt_fns.executeQuery("SELECT MAX(id) AS total_pics_on_tbl FROM pictures;");
            total_pictures = rs_cnt_fns.getInt("total_pics_on_tbl");
            
            rs_sel_fns.getInt("id");
            
            // An API response for each location is required, so we loop through each of the picture's relevant data.
            while(rs_sel_fns.next()){
                // If there's both latitude and longitude info, interact with the API, if not, flag them as having unknown location by chosen criteria (for example, of unknown street).
                if(rs_sel_fns.getString("latitude") != null && rs_sel_fns.getString("longitude") != null){
                    String api_request_url = "https://api.geoapify.com/v1/geocode/reverse?lat=" + rs_sel_fns.getString("latitude") + "&lon=" + rs_sel_fns.getString("longitude") + "&format=json&apiKey=" + geoapify_api_key;

                    // Send a request and receive a response from the API, which will be in JSON format.
                    OkHttpClient okhttp_cli = new OkHttpClient().newBuilder().build();
                    Request send_loc = new Request.Builder().url(api_request_url).method("GET", null).build();
                    Response reverse_geocoded_info = okhttp_cli.newCall(send_loc).execute();

                    // Get the body of the response, which contains the JSON I need to get the location names.
                    String api_response_to_string = reverse_geocoded_info.body().string();
                    
                    // Parses the API response body into a JSON object to extract required data.
                    JSONObject api_response_obj = new JSONObject(api_response_to_string);
                    JSONArray api_response_arr = api_response_obj.getJSONArray("results");
                    
                    if(!org_crit_str.equals("continent")){
                        // Checks if specified key in above switch/case structure exists, if so, retrieve its value.
                        if(api_response_arr.getJSONObject(0).has(org_crit_str)){
                            String location_name = api_response_arr.getJSONObject(0).getString(org_crit_str);
                            
                            save_loc_names.addBatch("UPDATE pictures SET location_name = \"" + location_name + "\" WHERE id = " + rs_sel_fns.getString("id") + ";");

                            return_locations_to_table.add(new String[]{rs_sel_fns.getString("filename"), rs_sel_fns.getString("latitude"), rs_sel_fns.getString("longitude"), location_name});
                        }
                        // There might be cases in which the API doesn't come with the key for the chosen criteria (for example, the JSON response body has no [street] key) so, flag such pictures as "unknown".
                        else{
                            String non_ex_var;
                            
                            switch(org_crit_str){
                                case "street":
                                    non_ex_var = ShStrings.FOLDER_NO_STREET;
                                    break;
                                case "city":
                                    non_ex_var = ShStrings.FOLDER_NO_CITY;
                                    break;
                                case "state":
                                    non_ex_var = ShStrings.FOLDER_NO_STATE;
                                    break;
                                case "country":
                                    non_ex_var = ShStrings.FOLDER_NO_COUNTRY;
                                    break;
                                    
                                default:
                                    non_ex_var = ShStrings.FOLDER_NO_CITY;
                            }
                            
                            save_loc_names.addBatch("UPDATE pictures SET location_name = \"" + non_ex_var + "\" WHERE id = " + rs_sel_fns.getString("id") + ";");

                            return_locations_to_table.add(new String[]{rs_sel_fns.getString("filename"), rs_sel_fns.getString("latitude"), rs_sel_fns.getString("longitude"), "--"});
                        }
                    }
                    // TODO: find if there's a more efficient, acurrate and/or more complete solution to getting continent name, for now I'm getting it from the timezone>name value.
                    else{
                        String get_timezone_val = api_response_arr.getJSONObject(0).getJSONObject("timezone").getString("name");
                        String get_continent_name = new String();
                        int slash_pos = get_timezone_val.indexOf("/");
                        
                        if(slash_pos != -1)
                            get_continent_name = get_timezone_val.substring(0, slash_pos);
                        
                        save_loc_names.addBatch("UPDATE pictures SET location_name = \"" + get_continent_name + "\" WHERE id = " + rs_sel_fns.getString("id") + ";");

                        return_locations_to_table.add(new String[]{rs_sel_fns.getString("filename"), rs_sel_fns.getString("latitude"), rs_sel_fns.getString("longitude"), get_continent_name});
                    }
                
                    // The API has a limit of 5 calls per second for free users, so wait between each call to avoid getting an error due to overcoming that limit, see < https://www.geoapify.com/pricing > for details on the free plan and pricing on subscription-based plans. Please don't lower this value below 250, so you can stay within the request threshold.
                    Thread.sleep(250);
                }
                    
                // No matter the criteria, if either or both latitude and longitude info is missing, then put the "geotag-less" pictures on a dedicated folder.
                else if(rs_sel_fns.getString("latitude") == null || rs_sel_fns.getString("longitude") == null){
                    save_loc_names.addBatch("UPDATE pictures SET location_name = \"" + ShStrings.FOLDER_NO_GEOTAG + "\" WHERE id = " + rs_sel_fns.getString("id") + ";");

                    return_locations_to_table.add(new String[]{rs_sel_fns.getString("filename"), "--", "--", "--"});
                }
                
                // Placeholder to monitor progress via console, until proper visual progress is implemented.
                processed_pictures++;
                System.out.println("Processed picture: " + processed_pictures + " of " + total_pictures);
            }
            
            save_loc_names.executeBatch();
        }
        catch(SQLException | IOException | InterruptedException exc){    
            exc.getMessage();
        }
        finally{
            store_locations_db.close();
            settings_db.close();
        }
        
        // The list I've been building here is used to recreate the table in the sorting process window.
        return return_locations_to_table;
    }
}