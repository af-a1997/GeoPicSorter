package com.afa1997.geopicsorter.frames;

// Internals:
import com.afa1997.geopicsorter.features.MetadataMan;
import com.afa1997.geopicsorter.features.ShStrings;
import com.afa1997.geopicsorter.frames.help.AboutWindow;
import com.afa1997.geopicsorter.frames.help.GetKey;

// SQL-related imports:
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

// Java Utils:
import java.util.logging.Level;
import java.util.logging.Logger;

// Swing GUI elements:
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

// Files:
import java.io.File;
import java.io.IOException;

// Main window, used to start interacting with GeoPicSorter, accessing preferences, etc.
public class MainWindow extends JFrame {
    static Connection conn_to_settings_db;
    
    public MainWindow() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mw_jp_container = new javax.swing.JPanel();
        mw_intro_text = new javax.swing.JLabel();
        mw_sec_api_key_ttl = new javax.swing.JLabel();
        mw_sec_api_key_body = new javax.swing.JLabel();
        mw_sec_api_key_foot = new javax.swing.JLabel();
        mw_btn_prefs = new javax.swing.JButton();
        mw_foot_note_rebrand = new javax.swing.JLabel();
        mw_mb = new javax.swing.JMenuBar();
        mw_mb_program = new javax.swing.JMenu();
        mw_mb_program_open = new javax.swing.JMenu();
        mw_mb_program_open_folder = new javax.swing.JMenuItem();
        mw_mb_program_open_pics = new javax.swing.JMenuItem();
        mw_mb_program_sep0 = new javax.swing.JPopupMenu.Separator();
        mw_mb_program_quit = new javax.swing.JMenuItem();
        mw_mb_edit = new javax.swing.JMenu();
        mw_mb_edit_prefs = new javax.swing.JMenuItem();
        mw_mb_help = new javax.swing.JMenu();
        mw_mb_help_cont = new javax.swing.JMenuItem();
        mw_mb_help_sep0 = new javax.swing.JPopupMenu.Separator();
        mw_mb_help_repo = new javax.swing.JMenuItem();
        mw_mb_help_sep1 = new javax.swing.JPopupMenu.Separator();
        mw_mb_help_about = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(ShStrings.PROG_NAME_FULL);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(620, 420));
        setResizable(false);

        mw_intro_text.setText("<html>\nWelcome to <b style=\"color: #008000;\">Geo</b><b style=\"color: #000080;\">Pic</b><b style=\"color: #800000;\">Sorter</b>!<br><br>\n\nThe goal of this tool is to raise awareness about picture geotagging and automating picture<br>\nsorting within folders based on geolocation groups such as street, city and others that<br>\nwill be implemented on the future.<br><br>\n\n<b style=\"color: #008000;\">Geo</b><b style=\"color: #000080;\">Pic</b><b style=\"color: #800000;\">Sorter</b> <u style=\"color: red;\">requires</u> an internet connection in order to work, because it sends requests<br>\nto Geoapify's reverse geocoding API. Please make sure your computer is connected<br>\nto the internet before beginning.<br><br>\n\nTo get started, press [<span style=\"color: #CDA64A; font-family: monospace;\">Control + O</span>] or go to [<span style=\"color: #5D96CC; font-family: monospace;\"><u>Program</u> &gt; <u>Open...</u> &gt; <u>Folder</u></span>].");
        mw_intro_text.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        mw_sec_api_key_ttl.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        mw_sec_api_key_ttl.setForeground(new java.awt.Color(204, 153, 0));
        mw_sec_api_key_ttl.setText("Important!");

        mw_sec_api_key_body.setText("<html>\nBefore using the tool, please consider signing up to Geoapify and supply your own API key.<br>\n<b style=\"color: #008000;\">Geo</b><b style=\"color: #000080;\">Pic</b><b style=\"color: #800000;\">Sorter</b> is supplied with a key for quick access/testing, and in the event more than<br>\none user makes use of it, there'll likely be unexpected issues.<br>");
        mw_sec_api_key_body.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        mw_sec_api_key_foot.setText("<html>\nGetting a key for yourself is easy, and is free.\n<br><br>\nFor instructions and/or changing the key, see:");

        mw_btn_prefs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/gnome-settings.png"))); // NOI18N
        mw_btn_prefs.setText("Preferences");
        mw_btn_prefs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mw_btn_prefsActionPerformed(evt);
            }
        });

        mw_foot_note_rebrand.setText("<html>\n<b>Also</b>, the software is being re-written, you'll find more information at the homepage and<br>\nthe repository. I recommend to keep an eye on either site.");
        mw_foot_note_rebrand.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout mw_jp_containerLayout = new javax.swing.GroupLayout(mw_jp_container);
        mw_jp_container.setLayout(mw_jp_containerLayout);
        mw_jp_containerLayout.setHorizontalGroup(
            mw_jp_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mw_jp_containerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mw_jp_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mw_intro_text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mw_sec_api_key_ttl)
                    .addComponent(mw_sec_api_key_body, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mw_jp_containerLayout.createSequentialGroup()
                        .addComponent(mw_sec_api_key_foot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mw_btn_prefs))
                    .addComponent(mw_foot_note_rebrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mw_jp_containerLayout.setVerticalGroup(
            mw_jp_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mw_jp_containerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mw_intro_text, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mw_sec_api_key_ttl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mw_jp_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mw_jp_containerLayout.createSequentialGroup()
                        .addComponent(mw_sec_api_key_body, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mw_sec_api_key_foot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(mw_btn_prefs))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mw_foot_note_rebrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        mw_mb_program.setMnemonic(0);
        mw_mb_program.setText("Program");

        mw_mb_program_open.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stock_navigator.png"))); // NOI18N
        mw_mb_program_open.setText("Open...");

        mw_mb_program_open_folder.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mw_mb_program_open_folder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stock_open.png"))); // NOI18N
        mw_mb_program_open_folder.setText("Folder");
        mw_mb_program_open_folder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mw_mb_program_open_folderActionPerformed(evt);
            }
        });
        mw_mb_program_open.add(mw_mb_program_open_folder);

        mw_mb_program_open_pics.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mw_mb_program_open_pics.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stock_preview-four-pages.png"))); // NOI18N
        mw_mb_program_open_pics.setText("Picture(s)");
        mw_mb_program_open_pics.setEnabled(false);
        mw_mb_program_open.add(mw_mb_program_open_pics);

        mw_mb_program.add(mw_mb_program_open);
        mw_mb_program.add(mw_mb_program_sep0);

        mw_mb_program_quit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mw_mb_program_quit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stock_close.png"))); // NOI18N
        mw_mb_program_quit.setText("Quit");
        mw_mb_program_quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mw_mb_program_quitActionPerformed(evt);
            }
        });
        mw_mb_program.add(mw_mb_program_quit);

        mw_mb.add(mw_mb_program);

        mw_mb_edit.setText("Edit");

        mw_mb_edit_prefs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mw_mb_edit_prefs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/gnome-settings.png"))); // NOI18N
        mw_mb_edit_prefs.setText("Preferences");
        mw_mb_edit_prefs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mw_mb_edit_prefsActionPerformed(evt);
            }
        });
        mw_mb_edit.add(mw_mb_edit_prefs);

        mw_mb.add(mw_mb_edit);

        mw_mb_help.setText("Help");

        mw_mb_help_cont.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        mw_mb_help_cont.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stock_help.png"))); // NOI18N
        mw_mb_help_cont.setText("Help contents");
        mw_mb_help_cont.setEnabled(false);
        mw_mb_help.add(mw_mb_help_cont);
        mw_mb_help.add(mw_mb_help_sep0);

        mw_mb_help_repo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/github.png"))); // NOI18N
        mw_mb_help_repo.setText("Visit repository");
        mw_mb_help_repo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mw_mb_help_repoActionPerformed(evt);
            }
        });
        mw_mb_help.add(mw_mb_help_repo);
        mw_mb_help.add(mw_mb_help_sep1);

        mw_mb_help_about.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stock_about.png"))); // NOI18N
        mw_mb_help_about.setText("About");
        mw_mb_help_about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mw_mb_help_aboutActionPerformed(evt);
            }
        });
        mw_mb_help.add(mw_mb_help_about);

        mw_mb.add(mw_mb_help);

        setJMenuBar(mw_mb);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mw_jp_container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mw_jp_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Open browser with GeoPicSorter's repository site.
    private void mw_mb_help_repoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mw_mb_help_repoActionPerformed
        // TODO: find fix for multiplatform solution. Solution to open website in Windows from: < https://stackoverflow.com/a/28807079 >.
        Runtime rt = Runtime.getRuntime();
        
        try {
            rt.exec("rundll32 url.dll,FileProtocolHandler " + ShStrings.REPO_LOC);
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mw_mb_help_repoActionPerformed

    // Closes GeoPicSorter completely.
    private void mw_mb_program_quitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mw_mb_program_quitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mw_mb_program_quitActionPerformed

    // Open a folder for sorting pictures, send the output path to the sorting process window.
    private void mw_mb_program_open_folderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mw_mb_program_open_folderActionPerformed
        JFileChooser jfc_folder = new JFileChooser();
        String jfc_starting_addr = ".";
        
        try{
            conn_to_settings_db = DriverManager.getConnection(ShStrings.SETTINGS_DB);
            
            Statement st_last_dir = conn_to_settings_db.createStatement();
            
            ResultSet rs_last_dir = st_last_dir.executeQuery("SELECT * FROM settings WHERE s_key = \"last_work_dir\";");
            
            jfc_starting_addr = rs_last_dir.getString("s_value");
        } catch (SQLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                conn_to_settings_db.close();
            } catch (SQLException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        File jfc_starting_folder_loc = new File(jfc_starting_addr);
        
        // Check if the last used directory exists, if not then open file chooser to default location.
        if(jfc_starting_folder_loc.exists() && jfc_starting_folder_loc.isDirectory())
            jfc_folder.setCurrentDirectory(new File(jfc_starting_addr));
        else
            jfc_folder.setCurrentDirectory(new File("."));
        
        // To ensure user can only see and pick directories on the file chooser.
        jfc_folder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc_folder.setAcceptAllFileFilterUsed(false);
        
        // Open a file chooser, if a folder was selected, then do what's on the first part, otherwise do nothing.
        if (jfc_folder.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
            String sel_dir = jfc_folder.getSelectedFile().getAbsolutePath();
            
            try {
                MetadataMan mm_i = new MetadataMan(true);
                
                // Get picture file names and put them into the locations database.
                mm_i.MMIF_WriteFileNames(sel_dir);
                
                // Attempt to get coordinates from the pictures in this folder.
                int mm_gps_locs = mm_i.MMIF_GetCoords(sel_dir);
                
                // If there are no geotagged pictures, return a message and don't open sorting process window.
                if(mm_gps_locs == 0){
                    JFrame warn_no_geotags = new JFrame();
                    
                    JOptionPane.showMessageDialog(warn_no_geotags, "This folder doesn't contain any geotagged pictures, please try another folder.", "No geotagged pictures found", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    SorterProcess srt_prc = new SorterProcess(sel_dir);

                    // Save last work directory address if the setting to remember it is set to true, otherwise this does nothing.
                    try{
                        conn_to_settings_db = DriverManager.getConnection(ShStrings.SETTINGS_DB);

                        Statement st_save_last_dir = conn_to_settings_db.createStatement();
                        Statement st_get_last_dir_status = conn_to_settings_db.createStatement();
                        ResultSet get_remember_dir_pref = st_get_last_dir_status.executeQuery("SELECT s_value FROM settings WHERE s_key = \"keep_last_work_dir\";");

                        if(Boolean.parseBoolean(get_remember_dir_pref.getString("s_value")))
                            st_save_last_dir.executeUpdate("UPDATE settings SET s_value = \"" + sel_dir + "\" WHERE s_key = \"last_work_dir\";");

                    } catch (SQLException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    finally{
                        try {
                            conn_to_settings_db.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    srt_prc.setVisible(true);

                    // TODO: this approach keeps the main window disabled after opening the sorting process, and we don't want this... it should be available again when closing the sorter process window.
                    //this.setEnabled(false);
                }
            } catch (SQLException | IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_mw_mb_program_open_folderActionPerformed

    // Opens about window.
    private void mw_mb_help_aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mw_mb_help_aboutActionPerformed
        new AboutWindow().setVisible(true);
    }//GEN-LAST:event_mw_mb_help_aboutActionPerformed

    // Opens preferences window.
    private void mw_mb_edit_prefsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mw_mb_edit_prefsActionPerformed
        new Preferences().setVisible(true);
    }//GEN-LAST:event_mw_mb_edit_prefsActionPerformed

    // Button at the home screen to open preferences.
    private void mw_btn_prefsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mw_btn_prefsActionPerformed
        new Preferences().setVisible(true);
    }//GEN-LAST:event_mw_btn_prefsActionPerformed

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton mw_btn_prefs;
    private javax.swing.JLabel mw_foot_note_rebrand;
    private javax.swing.JLabel mw_intro_text;
    private javax.swing.JPanel mw_jp_container;
    private javax.swing.JMenuBar mw_mb;
    private javax.swing.JMenu mw_mb_edit;
    private javax.swing.JMenuItem mw_mb_edit_prefs;
    private javax.swing.JMenu mw_mb_help;
    private javax.swing.JMenuItem mw_mb_help_about;
    private javax.swing.JMenuItem mw_mb_help_cont;
    private javax.swing.JMenuItem mw_mb_help_repo;
    private javax.swing.JPopupMenu.Separator mw_mb_help_sep0;
    private javax.swing.JPopupMenu.Separator mw_mb_help_sep1;
    private javax.swing.JMenu mw_mb_program;
    private javax.swing.JMenu mw_mb_program_open;
    private javax.swing.JMenuItem mw_mb_program_open_folder;
    private javax.swing.JMenuItem mw_mb_program_open_pics;
    private javax.swing.JMenuItem mw_mb_program_quit;
    private javax.swing.JPopupMenu.Separator mw_mb_program_sep0;
    private javax.swing.JLabel mw_sec_api_key_body;
    private javax.swing.JLabel mw_sec_api_key_foot;
    private javax.swing.JLabel mw_sec_api_key_ttl;
    // End of variables declaration//GEN-END:variables
}
