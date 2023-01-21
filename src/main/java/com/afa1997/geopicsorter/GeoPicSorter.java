package com.afa1997.geopicsorter;

// Internals:
import com.afa1997.geopicsorter.features.PrefMan;
import com.afa1997.geopicsorter.frames.MainWindow;

// SQL exception:
import java.sql.SQLException;

// Main class that opens main window and loads default user preferences.
public class GeoPicSorter{
    public static void main(String[] args) throws SQLException {
        // If the settings database doesn't exist, create it and load default settings.
        new PrefMan().PREF_InitDefaults();
        
        // Displays the main window, which contains an introduction and the menus to use the program.
        new MainWindow().setVisible(true);
    }
}