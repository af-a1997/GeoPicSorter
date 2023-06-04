package com.afa1997.geopicsorter.features;

// This class contains various shared strings to be used across the project.
public class ShStrings {
    // Reminder: if using relative paths like in [METADATA_DB], the base path is that of the project's location.
    
    // Project related values.
    public static final String PROG_NAME = "GeoPicSorter";
    public static final String PROG_VER = "1.1.1";
    public static final String PROG_NAME_FULL = PROG_NAME + " " + PROG_VER;
    public static final String REPO_LOC = "https://github.com/af-a1997/GeoPicSorter";
    
    // Sorting status messsages.
    public static final String SORTING_STATUS_IDLE = "Not started";
    public static final String SORTING_STATUS_NO_PICS = "No pictures detected in this folder";
    public static final String SORTING_STATUS_DETECTING = "Detecting pictures...";
    public static final String SORTING_STATUS_FETCH_GEOTAGS = "Fetching geolocation data...";
    public static final String SORTING_STATUS_RG_WORKING = "Reverse geocoding in process...";
    public static final String SORTING_STATUS_RG_FAIL = "Reverse geocoding failed";
    public static final String SORTING_STATUS_RG_SUCCESS = "Reverse geocoding finished";
    public static final String SORTING_STATUS_ASSOC_PLACE_NAMES = "Associating pictures to location names...";
    public static final String SORTING_STATUS_GEN_SUBDIRS = "Creating sub-directories...";
    public static final String SORTING_STATUS_MOVING = "Moving pictures...";
    public static final String SORTING_STATUS_FINISHED = "Process finished, you may close this window";
    
    // Database pointers: define file name for geotag cache database and GeoPicSorter's settings.
    public static final String METADATA_DB_FN = "locations.db";
    public static final String METADATA_DB = "jdbc:sqlite:" + METADATA_DB_FN;
    public static final String SETTINGS_DB_FN = "prefs.db";
    public static final String SETTINGS_DB = "jdbc:sqlite:" + SETTINGS_DB_FN;
    
    // Folder names for pictures without requested info from the API, like street name, or has no geotags at all.
    public static final String FOLDER_NO_GEOTAG = "[Unknown]";
    public static final String FOLDER_NO_STREET = "[Streetless]";
    public static final String FOLDER_NO_STATE = "[Stateless]";
    public static final String FOLDER_NO_CITY = "[Cityless]";
    public static final String FOLDER_NO_COUNTRY = "[Countryless]";
    public static final String FOLDER_NO_CNTLESS = "[Outland]";
    
    // Various other things.
    public static final String[] API_NAMES = {"Geoapify", "LocationIQ"};
    public static final String[] SORTING_CRITERIAS = {"Street", "State", "City", "Country", "Continent"};
    public static final String[] SORTER_TBL_HEADINGS = {"File name","Latitude","Longitude","Location name"};
    public static final String[] PICTURE_EXTS = {"jpg", "jpeg"};
    // TODO: implement [Custom] organization criteria, will be added to the [SORTING_CRITERIAS] constant when it's made possible to designate custom areas and label them for the organizaation process. [Block] criteria will also be implemented but will certainly require further coding to detect and differentiate each block in an urban zone. Also, add more formats known to support Exif metadata and can be extracted with Metadata-extractor.
}