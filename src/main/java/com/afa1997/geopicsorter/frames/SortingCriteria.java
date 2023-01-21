package com.afa1997.geopicsorter.frames;

// Internal:
import com.afa1997.geopicsorter.features.ShStrings;

// SQL-related imports:
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SortingCriteria extends javax.swing.JFrame {
    static Connection conn_settings_db;
    
    public SortingCriteria() {
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
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sorting criteria");
        setResizable(false);

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
        jl_footnote.setText("<html>\nSorting by location&gt;date is a future planned feature, along with defining<br>\ncustom sorting criterias and sorting by block and place.");

        jb_custom_rgn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stock_crop.png"))); // NOI18N
        jb_custom_rgn.setText("Customize regions");
        jb_custom_rgn.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp_toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jl_footnote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 48, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jl_place_lev)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcb_sort_crit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jl_title)
                                    .addComponent(jl_desc))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jck_date_sub_sort)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jb_custom_rgn)))
                        .addContainerGap())))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 146, Short.MAX_VALUE)
                        .addComponent(jl_footnote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jp_toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jb_custom_rgn)
                            .addComponent(jck_date_sub_sort))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Save changes
    private void jb_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_saveActionPerformed
        try {
            conn_settings_db = DriverManager.getConnection(ShStrings.SETTINGS_DB);
            
            Statement st_sc_save = conn_settings_db.createStatement();
            
            st_sc_save.addBatch("UPDATE settings SET s_value = \"" + jcb_sort_crit.getSelectedIndex() + "\" WHERE s_key = \"organization_criteria\";");
            st_sc_save.addBatch("UPDATE settings SET s_value = \"" + jck_date_sub_sort.isSelected() + "\" WHERE s_key = \"sub_by_date\";");
            
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
    private javax.swing.JButton jb_save;
    private javax.swing.JComboBox<String> jcb_sort_crit;
    private javax.swing.JCheckBox jck_date_sub_sort;
    private javax.swing.JLabel jl_desc;
    private javax.swing.JLabel jl_footnote;
    private javax.swing.JLabel jl_place_lev;
    private javax.swing.JLabel jl_title;
    private javax.swing.JPanel jp_toolbar;
    // End of variables declaration//GEN-END:variables
}
