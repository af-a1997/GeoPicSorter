package com.afa1997.geopicsorter.frames;

// Internals:
import com.afa1997.geopicsorter.features.ShStrings;
import com.afa1997.geopicsorter.features.MetadataMan;
import com.afa1997.geopicsorter.features.ReverseGeocoding_Geoapify;
import com.afa1997.geopicsorter.features.SortingAct;

// Exceptions:
import java.io.IOException;

// SQL-related imports:
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

// Java utils:
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Table model used when adding the info to the table:
import javax.swing.table.DefaultTableModel;

// UI class that handles the base of the picture sorting process and calls the other actions in order.
public class SorterProcess extends javax.swing.JFrame {
    Connection conn_settings_db;
    String retrieve_location = "";
    
    int org_crit_v = 0;
    
    public SorterProcess() {
        initComponents();
        
        try {
            conn_settings_db = DriverManager.getConnection(ShStrings.SETTINGS_DB);
            
            Statement get_org_crit_v = conn_settings_db.createStatement();
            ResultSet rtn_org_crit_v = get_org_crit_v.executeQuery("SELECT * FROM settings WHERE s_key = \"organization_criteria\";");
            
            org_crit_v = rtn_org_crit_v.getInt("s_value");
        } catch (SQLException ex) {
            Logger.getLogger(SorterProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                conn_settings_db.close();
            } catch (SQLException ex) {
                Logger.getLogger(SorterProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public SorterProcess(String loc_whole_folder){
        initComponents();
        
        try {
            conn_settings_db = DriverManager.getConnection(ShStrings.SETTINGS_DB);
            
            Statement get_org_crit_v = conn_settings_db.createStatement();
            ResultSet rtn_org_crit_v = get_org_crit_v.executeQuery("SELECT * FROM settings WHERE s_key = \"organization_criteria\";");
            
            org_crit_v = rtn_org_crit_v.getInt("s_value");
        } catch (SQLException ex) {
            Logger.getLogger(SorterProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                conn_settings_db.close();
            } catch (SQLException ex) {
                Logger.getLogger(SorterProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        retrieve_location = loc_whole_folder;
        
        System.out.println("Selected location with pictures: " + retrieve_location);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jt_actions = new javax.swing.JToolBar();
        jt_start_sorting = new javax.swing.JButton();
        jt_abort_sorting = new javax.swing.JButton();
        jt_sep0 = new javax.swing.JToolBar.Separator();
        jt_set_crit = new javax.swing.JButton();
        jt_sep1 = new javax.swing.JToolBar.Separator();
        jt_close_sorter_window = new javax.swing.JButton();
        jt_sep2 = new javax.swing.JToolBar.Separator();
        jl_warn_sortproc = new javax.swing.JLabel();
        jsc_tbl_cont = new javax.swing.JScrollPane();
        pictures_listing = new javax.swing.JTable();
        status_bar = new javax.swing.JPanel();
        status_indicator = new javax.swing.JLabel();
        status_visual = new javax.swing.JProgressBar();
        status_flavor_text = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sorting process");
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jt_actions.setRollover(true);

        jt_start_sorting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stock_media-play.png"))); // NOI18N
        jt_start_sorting.setText("Start");
        jt_start_sorting.setToolTipText("Start organization process");
        jt_start_sorting.setFocusable(false);
        jt_start_sorting.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jt_start_sorting.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jt_start_sorting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jt_start_sortingActionPerformed(evt);
            }
        });
        jt_actions.add(jt_start_sorting);

        jt_abort_sorting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stock_stop.png"))); // NOI18N
        jt_abort_sorting.setToolTipText("Abort organizing process");
        jt_abort_sorting.setEnabled(false);
        jt_abort_sorting.setFocusable(false);
        jt_abort_sorting.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jt_abort_sorting.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jt_actions.add(jt_abort_sorting);
        jt_actions.add(jt_sep0);

        jt_set_crit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stock_filters.png"))); // NOI18N
        jt_set_crit.setText("Set criteria...");
        jt_set_crit.setToolTipText("Choose the sorting criteria, such as street, city, etc.");
        jt_set_crit.setFocusable(false);
        jt_set_crit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jt_set_crit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jt_set_crit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jt_set_critActionPerformed(evt);
            }
        });
        jt_actions.add(jt_set_crit);
        jt_actions.add(jt_sep1);

        jt_close_sorter_window.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stock_close.png"))); // NOI18N
        jt_close_sorter_window.setToolTipText("Close this window and cancel organization process prompt");
        jt_close_sorter_window.setFocusable(false);
        jt_close_sorter_window.setHideActionText(true);
        jt_close_sorter_window.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jt_close_sorter_window.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jt_close_sorter_window.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jt_close_sorter_windowActionPerformed(evt);
            }
        });
        jt_actions.add(jt_close_sorter_window);
        jt_actions.add(jt_sep2);

        jl_warn_sortproc.setForeground(new java.awt.Color(51, 51, 0));
        jl_warn_sortproc.setText("Please note that the program may seem like it's not working while sorting, let it finish");
        jt_actions.add(jl_warn_sortproc);

        jsc_tbl_cont.setAutoscrolls(true);

        pictures_listing.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "File name", "Latitude", "Longitude", "Location name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jsc_tbl_cont.setViewportView(pictures_listing);

        status_bar.setMaximumSize(new java.awt.Dimension(32767, 95));

        status_indicator.setText(ShStrings.SORTING_STATUS_IDLE);

        status_visual.setString(ShStrings.SORTING_STATUS_IDLE);
        status_visual.setStringPainted(true);

        javax.swing.GroupLayout status_barLayout = new javax.swing.GroupLayout(status_bar);
        status_bar.setLayout(status_barLayout);
        status_barLayout.setHorizontalGroup(
            status_barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(status_barLayout.createSequentialGroup()
                .addComponent(status_indicator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(status_visual, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        status_barLayout.setVerticalGroup(
            status_barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(status_indicator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(status_visual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        status_flavor_text.setText("Status:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jt_actions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jsc_tbl_cont, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(status_flavor_text)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(status_bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jt_actions, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsc_tbl_cont, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(status_bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(status_flavor_text, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Close sorter window, when pressing the [X] from the toolbar.
    private void jt_close_sorter_windowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_close_sorter_windowActionPerformed
        this.dispose();
    }//GEN-LAST:event_jt_close_sorter_windowActionPerformed

    // When the window is opened, proceeds to populate the database with the picture names and location data for each picture, before the reverse geocoding process.
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        List<String> mm_wt_fns;
        
        status_indicator.setText(ShStrings.SORTING_STATUS_DETECTING);
        
        try {
            MetadataMan mm_i = new MetadataMan(true);
            
            // Get file names to add them to the list.
            mm_wt_fns = mm_i.MMIF_WriteFileNames(retrieve_location);

            // Creates an object that will contain the list of file names, which is then used to populate the table. Also makes the cells non-editable.
            DefaultTableModel dtm_pictures_list = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column){
                    return false;
                }
            };
            
            pictures_listing.setModel(dtm_pictures_list);
            
            dtm_pictures_list.setColumnIdentifiers(ShStrings.SORTER_TBL_HEADINGS);
            
            // Creates an object for each file, all of them will have a row.
            for(String mm_l : mm_wt_fns){
                Object[] obj_pictures_list = new Object[1];
                
                obj_pictures_list[0] = mm_l;
                
                dtm_pictures_list.addRow(obj_pictures_list);
            }
            
            status_indicator.setText(ShStrings.SORTING_STATUS_IDLE);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(SorterProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened

    // When the user clicks the [Start] button, begins sorting process.
    private void jt_start_sortingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_start_sortingActionPerformed
        try {
            // Disables the button to prevent running the sorting process again, specially useful to prevent possible issues if the pictures were moved to the sub-folders.
            jt_start_sorting.setEnabled(false);
            
            // Tries to get latitude and longitude metadata from all loaded pictures, and shows it on the table.
            MetadataMan mm_i = new MetadataMan(false);
        
            status_indicator.setText(ShStrings.SORTING_STATUS_FETCH_GEOTAGS);
            
            List<String[]> mm_gps_locs = mm_i.MMIF_GetCoords(retrieve_location);
            
            DefaultTableModel dtm_pictures_list = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column){
                    return false;
                }
            };
            
            pictures_listing.setModel(dtm_pictures_list);
            
            dtm_pictures_list.setColumnIdentifiers(ShStrings.SORTER_TBL_HEADINGS);
            dtm_pictures_list.setRowCount(0);
            
            for(String[] mm_l : mm_gps_locs){
                Object[] obj_pictures_list = new Object[3];
                
                obj_pictures_list[0] = mm_l[0];
                obj_pictures_list[1] = mm_l[1];
                obj_pictures_list[2] = mm_l[2];
                
                dtm_pictures_list.addRow(obj_pictures_list);
            }
        
            status_indicator.setText(ShStrings.SORTING_STATUS_RG_WORKING);
            
            // Make requests to API in order to retrieve location names, and stores them onto database, then display them on the table.
            ReverseGeocoding_Geoapify rg_src0 = new ReverseGeocoding_Geoapify();
            
            List<String[]> final_table = rg_src0.RG_SRC0_GetLocNames(org_crit_v);
            dtm_pictures_list.setRowCount(0);
            for(String[] final_table_recreation : final_table){
                Object[] obj_pictures_list = new Object[4];
                
                obj_pictures_list[0] = final_table_recreation[0];
                obj_pictures_list[1] = final_table_recreation[1];
                obj_pictures_list[2] = final_table_recreation[2];
                obj_pictures_list[3] = final_table_recreation[3];
                
                dtm_pictures_list.addRow(obj_pictures_list);
            }
        
            status_indicator.setText(ShStrings.SORTING_STATUS_RG_SUCCESS);
            
            // Begins sorting process.
            try {
                SortingAct sorting_act_proc = new SortingAct(retrieve_location);
            } catch (IOException ex) {
                Logger.getLogger(SorterProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            status_indicator.setText(ShStrings.SORTING_STATUS_MOVING);
        } catch (SQLException ex) {
            Logger.getLogger(SorterProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        status_indicator.setText(ShStrings.SORTING_STATUS_FINISHED);
    }//GEN-LAST:event_jt_start_sortingActionPerformed

    // Opens a new window to define sorting criteria.
    private void jt_set_critActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_set_critActionPerformed
        new SortingCriteria().setVisible(true);
    }//GEN-LAST:event_jt_set_critActionPerformed

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
            java.util.logging.Logger.getLogger(SorterProcess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SorterProcess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SorterProcess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SorterProcess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SorterProcess().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jl_warn_sortproc;
    private javax.swing.JScrollPane jsc_tbl_cont;
    private javax.swing.JButton jt_abort_sorting;
    private javax.swing.JToolBar jt_actions;
    private javax.swing.JButton jt_close_sorter_window;
    private javax.swing.JToolBar.Separator jt_sep0;
    private javax.swing.JToolBar.Separator jt_sep1;
    private javax.swing.JToolBar.Separator jt_sep2;
    private javax.swing.JButton jt_set_crit;
    private javax.swing.JButton jt_start_sorting;
    private javax.swing.JTable pictures_listing;
    private javax.swing.JPanel status_bar;
    private javax.swing.JLabel status_flavor_text;
    private javax.swing.JLabel status_indicator;
    private javax.swing.JProgressBar status_visual;
    // End of variables declaration//GEN-END:variables
}