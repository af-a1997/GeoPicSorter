package com.afa1997.geopicsorter.frames;

// Internals:
import com.afa1997.geopicsorter.features.ShStrings;
import com.afa1997.geopicsorter.features.PrefMan;
import com.afa1997.geopicsorter.frames.help.GetKey;
import com.afa1997.geopicsorter.frames.help.SortActExpl;

// SQL-related imports:
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

// Java utils:
import java.util.logging.Level;
import java.util.logging.Logger;

// GUI class for user preferences, to customize GeoPicSorter's behavior, appearance, etc.
public class Preferences extends javax.swing.JFrame {
    Connection conn_settings_db;
    static boolean reset_pref = false;
    
    // Default preferences for user interface.
    static int selection_sortact = 0;
    static boolean selection_rmbwkd = true;
    static String selection_apikey = new String();
    
    public Preferences() {
        // The following block tries to loads user preferences from database, these would be set in the JFrame elements further below.
        try {
            conn_settings_db = DriverManager.getConnection(ShStrings.SETTINGS_DB);
            
            Statement st_get_pref_sortact = conn_settings_db.createStatement();
            Statement st_get_pref_rmbwkd = conn_settings_db.createStatement();
            Statement st_get_pref_apikey = conn_settings_db.createStatement();
            
            ResultSet rs_val_sortact = st_get_pref_sortact.executeQuery("SELECT * FROM settings WHERE s_key = \"sort_act\";");
            ResultSet rs_val_rmbwkd = st_get_pref_rmbwkd.executeQuery("SELECT * FROM settings WHERE s_key = \"keep_last_work_dir\";");
            ResultSet rs_val_apikey = st_get_pref_apikey.executeQuery("SELECT * FROM settings WHERE s_key = \"api_key\";");
            
            selection_sortact = Integer.parseInt(rs_val_sortact.getString("s_value"));
            selection_rmbwkd = Boolean.parseBoolean(rs_val_rmbwkd.getString("s_value"));
            selection_apikey = rs_val_apikey.getString("s_value");
            
            conn_settings_db.close();
        } catch (SQLException ex) {
            Logger.getLogger(Preferences.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                conn_settings_db.close();
            } catch (SQLException ex) {
                Logger.getLogger(Preferences.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        initComponents();
        
        pref_org_sortact_picker.setSelectedIndex(selection_sortact);
        pref_org_txf_api_key_in.setText(selection_apikey);
        
        // If user chose to remember last work directory, use it when choosing folder from JFileChooser.
        if(selection_rmbwkd)
            pref_chk_remember_work_dir.setSelected(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pref_jtp = new javax.swing.JTabbedPane();
        pref_jtp_gen = new javax.swing.JPanel();
        pref_jtp_gen_ttl_lang = new javax.swing.JLabel();
        pref_jtp_gen_lang_desc = new javax.swing.JLabel();
        pref_jtp_gen_lang_picker = new javax.swing.JComboBox<>();
        pref_jtp_gen_ttl_behavior = new javax.swing.JLabel();
        pref_chk_remember_work_dir = new javax.swing.JCheckBox();
        pref_jtp_org = new javax.swing.JPanel();
        pref_org_ttl_rgapi = new javax.swing.JLabel();
        pref_org_rgapi_desc = new javax.swing.JLabel();
        pref_org_rgapi_picker = new javax.swing.JComboBox<>();
        pref_org_ttl_sortact = new javax.swing.JLabel();
        pref_org_sortact_picker = new javax.swing.JComboBox<>();
        pref_org_jb_help_sortact = new javax.swing.JButton();
        pref_org_jl_api_key_expl = new javax.swing.JLabel();
        pref_org_txf_api_key_in = new javax.swing.JTextField();
        pref_org_jb_help_get_api_key = new javax.swing.JButton();
        pref_container_btns = new javax.swing.JPanel();
        pref_canc = new javax.swing.JButton();
        pref_save = new javax.swing.JButton();
        pref_defaults = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(ShStrings.PROG_NAME + " preferences");
        setResizable(false);

        pref_jtp_gen_ttl_lang.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        pref_jtp_gen_ttl_lang.setText("Language");

        pref_jtp_gen_lang_desc.setText("<html>\nCurrently, the program is being developed in English, as it's<br>\na globally-spoken language, and will be of help expanding<br>\nthe development team in the future.\n<br><br>\nBecause of this, support for other languages will<br>\nbe implemented later on.");

        pref_jtp_gen_lang_picker.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "English", "Spanish", "Portuguese" }));
        pref_jtp_gen_lang_picker.setEnabled(false);

        pref_jtp_gen_ttl_behavior.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        pref_jtp_gen_ttl_behavior.setText("Behavior");

        pref_chk_remember_work_dir.setText("Remember last work directory");
        pref_chk_remember_work_dir.setToolTipText("<html>\nCheck if you want GeoPicSorter to remember the directory you've last used for sorting pictures.\n<br><br>\nIf unsure, leave this checked.");

        javax.swing.GroupLayout pref_jtp_genLayout = new javax.swing.GroupLayout(pref_jtp_gen);
        pref_jtp_gen.setLayout(pref_jtp_genLayout);
        pref_jtp_genLayout.setHorizontalGroup(
            pref_jtp_genLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pref_jtp_genLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pref_jtp_genLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pref_jtp_genLayout.createSequentialGroup()
                        .addGroup(pref_jtp_genLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pref_jtp_gen_lang_picker, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pref_jtp_genLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(pref_jtp_gen_lang_desc, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                            .addGroup(pref_jtp_genLayout.createSequentialGroup()
                                .addGroup(pref_jtp_genLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pref_jtp_gen_ttl_lang)
                                    .addComponent(pref_jtp_gen_ttl_behavior))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(pref_jtp_genLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(pref_chk_remember_work_dir)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pref_jtp_genLayout.setVerticalGroup(
            pref_jtp_genLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pref_jtp_genLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pref_jtp_gen_ttl_lang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pref_jtp_gen_lang_desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pref_jtp_gen_lang_picker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pref_jtp_gen_ttl_behavior)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pref_chk_remember_work_dir)
                .addContainerGap(230, Short.MAX_VALUE))
        );

        pref_jtp.addTab("General", pref_jtp_gen);

        pref_org_ttl_rgapi.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        pref_org_ttl_rgapi.setText("Reverse Geocoding API");

        pref_org_rgapi_desc.setText("<html>\nGeoPicSorter requires to make requests to an online API to fetch the<br>\nlocation names from it, an internet connection is required for this.<br>\nFor now, you may only be able to use Geoapify, but more options<br>\nwill be added in the future.");

        pref_org_rgapi_picker.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Geoapify", "LocationIQ" }));
        pref_org_rgapi_picker.setEnabled(false);

        pref_org_ttl_sortact.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        pref_org_ttl_sortact.setText("Sorting action");

        pref_org_sortact_picker.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Only copy", "Copy then delete originals (pseudo-move)", "Directly move pictures (during process)", "Pack and copy" }));

        pref_org_jb_help_sortact.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stock_help.png"))); // NOI18N
        pref_org_jb_help_sortact.setText("What is this?");
        pref_org_jb_help_sortact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pref_org_jb_help_sortactActionPerformed(evt);
            }
        });

        pref_org_jl_api_key_expl.setText("<html>\nThe API may require you to supply a key in order to make the requests,<br>\nwhile GeoPicSorter is supplied with a public use key, it's meant to be<br>\nused for quick access, <u style=\"color: #aa0;\">please supply your own key</u>!");

        pref_org_txf_api_key_in.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        pref_org_txf_api_key_in.setForeground(new java.awt.Color(102, 102, 0));

        pref_org_jb_help_get_api_key.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stock_help.png"))); // NOI18N
        pref_org_jb_help_get_api_key.setText("How to get a key");
        pref_org_jb_help_get_api_key.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pref_org_jb_help_get_api_keyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pref_jtp_orgLayout = new javax.swing.GroupLayout(pref_jtp_org);
        pref_jtp_org.setLayout(pref_jtp_orgLayout);
        pref_jtp_orgLayout.setHorizontalGroup(
            pref_jtp_orgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pref_jtp_orgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pref_jtp_orgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pref_jtp_orgLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(pref_org_jl_api_key_expl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(26, Short.MAX_VALUE))
                    .addGroup(pref_jtp_orgLayout.createSequentialGroup()
                        .addGroup(pref_jtp_orgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pref_org_txf_api_key_in)
                            .addComponent(pref_org_rgapi_picker, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pref_org_sortact_picker, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pref_jtp_orgLayout.createSequentialGroup()
                                .addGroup(pref_jtp_orgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pref_org_ttl_rgapi)
                                    .addGroup(pref_jtp_orgLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(pref_org_rgapi_desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(pref_org_ttl_sortact))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pref_jtp_orgLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(pref_jtp_orgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pref_org_jb_help_get_api_key, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(pref_org_jb_help_sortact, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addContainerGap())))
        );
        pref_jtp_orgLayout.setVerticalGroup(
            pref_jtp_orgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pref_jtp_orgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pref_org_ttl_rgapi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pref_org_rgapi_desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pref_org_rgapi_picker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pref_org_jl_api_key_expl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pref_org_txf_api_key_in, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pref_org_jb_help_get_api_key)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pref_org_ttl_sortact)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pref_org_sortact_picker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pref_org_jb_help_sortact)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        pref_jtp.addTab("Organizing", pref_jtp_org);

        pref_canc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stock_delete.png"))); // NOI18N
        pref_canc.setText("Cancel");
        pref_canc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pref_cancActionPerformed(evt);
            }
        });

        pref_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stock_save.png"))); // NOI18N
        pref_save.setText("Save");
        pref_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pref_saveActionPerformed(evt);
            }
        });

        pref_defaults.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stock_undo.png"))); // NOI18N
        pref_defaults.setText("Reset");
        pref_defaults.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pref_defaultsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pref_container_btnsLayout = new javax.swing.GroupLayout(pref_container_btns);
        pref_container_btns.setLayout(pref_container_btnsLayout);
        pref_container_btnsLayout.setHorizontalGroup(
            pref_container_btnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pref_container_btnsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pref_defaults)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pref_save)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pref_canc)
                .addContainerGap())
        );
        pref_container_btnsLayout.setVerticalGroup(
            pref_container_btnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pref_container_btnsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pref_container_btnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pref_canc)
                    .addComponent(pref_save)
                    .addComponent(pref_defaults))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pref_jtp)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pref_container_btns, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pref_jtp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pref_container_btns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Cancel changes to preferences.
    private void pref_cancActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pref_cancActionPerformed
        this.dispose();
        
        // During runtime, it might happen the user clicks on [Reset] but cancel saving preferences, the window may be disposed, but even so, the reset flag stays true when it should reset to false.
        reset_pref = false;
    }//GEN-LAST:event_pref_cancActionPerformed

    // Save changes to preferences.
    private void pref_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pref_saveActionPerformed
        try {
            conn_settings_db = DriverManager.getConnection(ShStrings.SETTINGS_DB);
            
            Statement statement_settings_ch = conn_settings_db.createStatement();

            // If an order to reset settings has been issued, reset to defaults upon saving, otherwise save changes the user has done.
            if(reset_pref){
                PrefMan sm_i = new PrefMan();

                sm_i.PREF_RestoreDefaults();
            }
            else{
                // Save sorting action selection:
                statement_settings_ch.executeUpdate("UPDATE settings SET s_value = \"" + pref_org_sortact_picker.getSelectedIndex() + "\" WHERE s_key = \"sort_act\";");
                // Save last work directory preference:
                statement_settings_ch.executeUpdate("UPDATE settings SET s_value = \"" + pref_chk_remember_work_dir.isSelected() + "\" WHERE s_key = \"keep_last_work_dir\";");
                // Save API key:
                statement_settings_ch.executeUpdate("UPDATE settings SET s_value = \"" + pref_org_txf_api_key_in.getText() + "\" WHERE s_key = \"api_key\";");
                
                // If user didn't ask to remember last work location, reset the path value to default (where GeoPicSorter's executable is located at).
                if(!pref_chk_remember_work_dir.isSelected())
                    statement_settings_ch.executeUpdate("UPDATE settings SET s_value = \".\" WHERE s_key = \"last_work_dir\";");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Preferences.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                conn_settings_db.close();
            } catch (SQLException ex) {
                Logger.getLogger(Preferences.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
        // Close window after saving settings.
        this.dispose();
    }//GEN-LAST:event_pref_saveActionPerformed

    // Sets flag to reset preferences to default, the defaults are saved when clicking on [Save].
    private void pref_defaultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pref_defaultsActionPerformed
        pref_chk_remember_work_dir.setSelected(true);
        pref_org_sortact_picker.setSelectedIndex(0);
        
        // TODO: add listener to detect when an option is changed after clicking [Reset], so that this flag resets to false.
        reset_pref = true;
    }//GEN-LAST:event_pref_defaultsActionPerformed

    // Explain in a new window what are the sorting actions.
    private void pref_org_jb_help_sortactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pref_org_jb_help_sortactActionPerformed
        SortActExpl i_sae = new SortActExpl();
        
        i_sae.setVisible(true);
    }//GEN-LAST:event_pref_org_jb_help_sortactActionPerformed

    // Explain in a new window how to get an API key from Geoapify.
    private void pref_org_jb_help_get_api_keyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pref_org_jb_help_get_api_keyActionPerformed
        GetKey i_gk = new GetKey();

        i_gk.setVisible(true);
    }//GEN-LAST:event_pref_org_jb_help_get_api_keyActionPerformed

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
            java.util.logging.Logger.getLogger(Preferences.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Preferences.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Preferences.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Preferences.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Preferences().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton pref_canc;
    private javax.swing.JCheckBox pref_chk_remember_work_dir;
    private javax.swing.JPanel pref_container_btns;
    private javax.swing.JButton pref_defaults;
    private javax.swing.JTabbedPane pref_jtp;
    private javax.swing.JPanel pref_jtp_gen;
    private javax.swing.JLabel pref_jtp_gen_lang_desc;
    private javax.swing.JComboBox<String> pref_jtp_gen_lang_picker;
    private javax.swing.JLabel pref_jtp_gen_ttl_behavior;
    private javax.swing.JLabel pref_jtp_gen_ttl_lang;
    private javax.swing.JPanel pref_jtp_org;
    private javax.swing.JButton pref_org_jb_help_get_api_key;
    private javax.swing.JButton pref_org_jb_help_sortact;
    private javax.swing.JLabel pref_org_jl_api_key_expl;
    private javax.swing.JLabel pref_org_rgapi_desc;
    private javax.swing.JComboBox<String> pref_org_rgapi_picker;
    private javax.swing.JComboBox<String> pref_org_sortact_picker;
    private javax.swing.JLabel pref_org_ttl_rgapi;
    private javax.swing.JLabel pref_org_ttl_sortact;
    private javax.swing.JTextField pref_org_txf_api_key_in;
    private javax.swing.JButton pref_save;
    // End of variables declaration//GEN-END:variables
}
