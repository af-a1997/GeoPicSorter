package com.afa1997.geopicsorter.frames;

// Internal:
import com.afa1997.geopicsorter.features.ShStrings;

// Logging:
import java.util.logging.Level;
import java.util.logging.Logger;

// Swing elements:
import javax.swing.JFileChooser;
import javax.swing.JFrame;

// SQL-related imports:
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

// Pop-up to customize sorting process.
public class SortingCriteria extends JFrame {
    public static final String OUTDIR_DEF = "Chosen directory";
    public static final String OUTDIR_OTHER = "Custom...";
    static Connection conn_settings_db;
    static String destination_dir = "";
    
    public SortingCriteria(){
        initComponents();
        
        try {
            conn_settings_db = DriverManager.getConnection(ShStrings.SETTINGS_DB);
            
            Statement st_sorting_pref = conn_settings_db.createStatement();
            ResultSet get_sorting_pref = st_sorting_pref.executeQuery("SELECT s_value FROM settings WHERE s_key = \"organization_criteria\"");
            
            jcb_sort_crit.setSelectedIndex(Integer.parseInt(get_sorting_pref.getString("s_value")));
            
            conn_settings_db.close();
        } catch (SQLException ex) {
            Logger.getLogger(SortingCriteria.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                conn_settings_db.close();
            } catch (SQLException ex) {
                Logger.getLogger(SortingCriteria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public SortingCriteria(String pictures_destination_path) {
        initComponents();
        
        try {
            conn_settings_db = DriverManager.getConnection(ShStrings.SETTINGS_DB);
            
            Statement st_sorting_pref = conn_settings_db.createStatement();
            ResultSet get_sorting_pref = st_sorting_pref.executeQuery("SELECT s_value FROM settings WHERE s_key = \"organization_criteria\"");
            
            jcb_sort_crit.setSelectedIndex(Integer.parseInt(get_sorting_pref.getString("s_value")));
        } catch (SQLException ex) {
            Logger.getLogger(SortingCriteria.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                conn_settings_db.close();
            } catch (SQLException ex) {
                Logger.getLogger(SortingCriteria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        destination_dir = pictures_destination_path;
        
        jtf_outdir_path.setText(destination_dir);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbg_out_dir = new javax.swing.ButtonGroup();
        jl_title = new javax.swing.JLabel();
        jl_desc = new javax.swing.JLabel();
        jl_place_lev = new javax.swing.JLabel();
        jp_toolbar = new javax.swing.JPanel();
        jb_save = new javax.swing.JButton();
        jb_canc = new javax.swing.JButton();
        jcb_sort_crit = new javax.swing.JComboBox<>();
        jck_date_sub_sort = new javax.swing.JCheckBox();
        jl_footnote = new javax.swing.JLabel();
        jb_custom_rgn = new javax.swing.JButton();
        jl_outdir_desc = new javax.swing.JLabel();
        jrb_outdir_chosen = new javax.swing.JRadioButton();
        jrb_outdir_custom = new javax.swing.JRadioButton();
        jb_outdir_browse = new javax.swing.JButton();
        jtf_outdir_path = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sorting criteria");
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        jl_title.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jl_title.setText("Configure sorting criteria");

        jl_desc.setText("Here you can define how you need your pictures to be sorted.");

        jl_place_lev.setText("Place level:");

        jb_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stock_save.png"))); // NOI18N
        jb_save.setText("Save");
        jb_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_saveActionPerformed(evt);
            }
        });

        jb_canc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stock_delete.png"))); // NOI18N
        jb_canc.setText("Cancel");
        jb_canc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_cancActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_toolbarLayout = new javax.swing.GroupLayout(jp_toolbar);
        jp_toolbar.setLayout(jp_toolbarLayout);
        jp_toolbarLayout.setHorizontalGroup(
            jp_toolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_toolbarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jb_save)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jb_canc)
                .addContainerGap())
        );
        jp_toolbarLayout.setVerticalGroup(
            jp_toolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_toolbarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jp_toolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_save)
                    .addComponent(jb_canc))
                .addContainerGap())
        );

        jcb_sort_crit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Street", "City", "State", "Country", "Continent" }));

        jck_date_sub_sort.setText("Create sub-folders for different dates");
        jck_date_sub_sort.setEnabled(false);

        jl_footnote.setForeground(new java.awt.Color(102, 102, 0));
        jl_footnote.setText("<html>\nSome of the features are currently disabled because these will be<br>\nimplemented at a later update.");

        jb_custom_rgn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stock_crop.png"))); // NOI18N
        jb_custom_rgn.setText("Customize regions");
        jb_custom_rgn.setEnabled(false);

        jl_outdir_desc.setText("Output directory:");

        jbg_out_dir.add(jrb_outdir_chosen);
        jrb_outdir_chosen.setSelected(true);
        jrb_outdir_chosen.setText(OUTDIR_DEF);
        jrb_outdir_chosen.setActionCommand("");
        jrb_outdir_chosen.setName("same"); // NOI18N
        jrb_outdir_chosen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_outdir_chosenActionPerformed(evt);
            }
        });

        jbg_out_dir.add(jrb_outdir_custom);
        jrb_outdir_custom.setText(OUTDIR_OTHER);
        jrb_outdir_custom.setActionCommand("");
        jrb_outdir_custom.setName("custom"); // NOI18N
        jrb_outdir_custom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_outdir_customActionPerformed(evt);
            }
        });

        jb_outdir_browse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stock_open.png"))); // NOI18N
        jb_outdir_browse.setText("Browse...");
        jb_outdir_browse.setEnabled(false);
        jb_outdir_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_outdir_browseActionPerformed(evt);
            }
        });

        jtf_outdir_path.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp_toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jl_place_lev)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcb_sort_crit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jck_date_sub_sort)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jb_custom_rgn))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtf_outdir_path, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jb_outdir_browse))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jl_title)
                            .addComponent(jl_desc)
                            .addComponent(jl_footnote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jl_outdir_desc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jrb_outdir_chosen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jrb_outdir_custom)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jl_title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jl_desc)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_place_lev)
                    .addComponent(jcb_sort_crit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_custom_rgn)
                    .addComponent(jck_date_sub_sort))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_outdir_desc)
                    .addComponent(jrb_outdir_chosen)
                    .addComponent(jrb_outdir_custom))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_outdir_path, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jb_outdir_browse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jl_footnote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp_toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Save changes.
    private void jb_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_saveActionPerformed
        try {
            conn_settings_db = DriverManager.getConnection(ShStrings.SETTINGS_DB);
            
            Statement st_sc_save = conn_settings_db.createStatement();
            
            st_sc_save.addBatch("UPDATE settings SET s_value = \"" + jcb_sort_crit.getSelectedIndex() + "\" WHERE s_key = \"organization_criteria\";");
            st_sc_save.addBatch("UPDATE settings SET s_value = \"" + jck_date_sub_sort.isSelected() + "\" WHERE s_key = \"sub_by_date\";");
            
            if(jrb_outdir_custom.isSelected())
                st_sc_save.addBatch("UPDATE settings SET s_value = \"" + destination_dir + "\" WHERE s_key = \"output_dir\";");
            else
                st_sc_save.addBatch("UPDATE settings SET s_value = null WHERE s_key = \"output_dir\";");
            
            st_sc_save.executeBatch();
            
            conn_settings_db.close();
        } catch (SQLException ex) {
            Logger.getLogger(SortingCriteria.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.dispose();
    }//GEN-LAST:event_jb_saveActionPerformed

    // Cancel changes
    private void jb_cancActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_cancActionPerformed
        this.dispose();
    }//GEN-LAST:event_jb_cancActionPerformed

    // If user chooses to output sorted pictures to a different folder, lets them pick it.
    private void jb_outdir_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_outdir_browseActionPerformed
        JFileChooser jfc_outdir_picker = new JFileChooser(".");
        
        jfc_outdir_picker.setAcceptAllFileFilterUsed(false);
        jfc_outdir_picker.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        if (jfc_outdir_picker.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String sel_dir = jfc_outdir_picker.getSelectedFile().getAbsolutePath();
            jtf_outdir_path.setText(sel_dir);
            
            destination_dir = sel_dir;
        }
    }//GEN-LAST:event_jb_outdir_browseActionPerformed

    // Controls the browse output directory button availability.
    private void jrb_outdir_customActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_outdir_customActionPerformed
        jb_outdir_browse.setEnabled(true);
    }//GEN-LAST:event_jrb_outdir_customActionPerformed

    private void jrb_outdir_chosenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_outdir_chosenActionPerformed
        jb_outdir_browse.setEnabled(false);
    }//GEN-LAST:event_jrb_outdir_chosenActionPerformed

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
            java.util.logging.Logger.getLogger(SortingCriteria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SortingCriteria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SortingCriteria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SortingCriteria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SortingCriteria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jb_canc;
    private javax.swing.JButton jb_custom_rgn;
    private javax.swing.JButton jb_outdir_browse;
    private javax.swing.JButton jb_save;
    private javax.swing.ButtonGroup jbg_out_dir;
    private javax.swing.JComboBox<String> jcb_sort_crit;
    private javax.swing.JCheckBox jck_date_sub_sort;
    private javax.swing.JLabel jl_desc;
    private javax.swing.JLabel jl_footnote;
    private javax.swing.JLabel jl_outdir_desc;
    private javax.swing.JLabel jl_place_lev;
    private javax.swing.JLabel jl_title;
    private javax.swing.JPanel jp_toolbar;
    private javax.swing.JRadioButton jrb_outdir_chosen;
    private javax.swing.JRadioButton jrb_outdir_custom;
    private javax.swing.JTextField jtf_outdir_path;
    // End of variables declaration//GEN-END:variables
}
