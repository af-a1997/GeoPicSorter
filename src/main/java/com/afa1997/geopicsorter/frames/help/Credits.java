package com.afa1997.geopicsorter.frames.help;

// I developed this tool while using other open source third-party tools, list such tools and involved entities in development on this window.
public class Credits extends javax.swing.JFrame {
    public Credits() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        credits_btn_area = new javax.swing.JPanel();
        credits_btns_close = new javax.swing.JButton();
        credits_container = new javax.swing.JTabbedPane();
        credits_tab_coder = new javax.swing.JPanel();
        credits_tab_coder_cont = new javax.swing.JScrollPane();
        credits_tab_coder_list = new javax.swing.JTextArea();
        credits_tab_ext = new javax.swing.JPanel();
        credits_tab_ext_cont = new javax.swing.JScrollPane();
        credits_tab_ext_list = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Credits");
        setResizable(false);

        credits_btns_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stock_close.png"))); // NOI18N
        credits_btns_close.setText("Close");
        credits_btns_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                credits_btns_closeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout credits_btn_areaLayout = new javax.swing.GroupLayout(credits_btn_area);
        credits_btn_area.setLayout(credits_btn_areaLayout);
        credits_btn_areaLayout.setHorizontalGroup(
            credits_btn_areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, credits_btn_areaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(credits_btns_close)
                .addContainerGap())
        );
        credits_btn_areaLayout.setVerticalGroup(
            credits_btn_areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(credits_btn_areaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(credits_btns_close)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        credits_tab_coder_list.setEditable(false);
        credits_tab_coder_list.setBackground(new java.awt.Color(25, 0, 51));
        credits_tab_coder_list.setColumns(20);
        credits_tab_coder_list.setForeground(new java.awt.Color(255, 255, 255));
        credits_tab_coder_list.setRows(5);
        credits_tab_coder_list.setText("Aldo Franquez <https://github.com/af-a1997>");
        credits_tab_coder_cont.setViewportView(credits_tab_coder_list);

        javax.swing.GroupLayout credits_tab_coderLayout = new javax.swing.GroupLayout(credits_tab_coder);
        credits_tab_coder.setLayout(credits_tab_coderLayout);
        credits_tab_coderLayout.setHorizontalGroup(
            credits_tab_coderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(credits_tab_coder_cont, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );
        credits_tab_coderLayout.setVerticalGroup(
            credits_tab_coderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(credits_tab_coder_cont, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
        );

        credits_container.addTab("Written by", credits_tab_coder);

        credits_tab_ext_list.setEditable(false);
        credits_tab_ext_list.setBackground(new java.awt.Color(25, 0, 51));
        credits_tab_ext_list.setColumns(20);
        credits_tab_ext_list.setForeground(new java.awt.Color(255, 255, 255));
        credits_tab_ext_list.setRows(5);
        credits_tab_ext_list.setText("Geoapify Geocoding API <https://www.geoapify.com/>\nGoogle Maps Geocoding API <https://developers.google.com/maps/documentation/geocoding/start#reverse>\nMetadata-extractor <https://github.com/drewnoakes/metadata-extractor>\nJDBC <https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc>\nOkHttp <https://square.github.io/okhttp/>\norg.json <https://mvnrepository.com/artifact/org.json/json>\nApache Commons <https://search.maven.org/artifact/commons-io/commons-io/2.11.0/jar>\nzip4j <https://github.com/srikanth-lingala/zip4j>");
        credits_tab_ext_cont.setViewportView(credits_tab_ext_list);

        javax.swing.GroupLayout credits_tab_extLayout = new javax.swing.GroupLayout(credits_tab_ext);
        credits_tab_ext.setLayout(credits_tab_extLayout);
        credits_tab_extLayout.setHorizontalGroup(
            credits_tab_extLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(credits_tab_ext_cont, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );
        credits_tab_extLayout.setVerticalGroup(
            credits_tab_extLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(credits_tab_ext_cont, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
        );

        credits_container.addTab("3rd-party tools", credits_tab_ext);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(credits_btn_area, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(credits_container)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(credits_container)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(credits_btn_area, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Close credits window.
    private void credits_btns_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_credits_btns_closeActionPerformed
        this.dispose();
    }//GEN-LAST:event_credits_btns_closeActionPerformed

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Credits.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Credits.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Credits.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Credits.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Credits().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel credits_btn_area;
    private javax.swing.JButton credits_btns_close;
    private javax.swing.JTabbedPane credits_container;
    private javax.swing.JPanel credits_tab_coder;
    private javax.swing.JScrollPane credits_tab_coder_cont;
    private javax.swing.JTextArea credits_tab_coder_list;
    private javax.swing.JPanel credits_tab_ext;
    private javax.swing.JScrollPane credits_tab_ext_cont;
    private javax.swing.JTextArea credits_tab_ext_list;
    // End of variables declaration//GEN-END:variables

}
