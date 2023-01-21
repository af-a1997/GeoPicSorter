package com.afa1997.geopicsorter.frames.help;

// Internal:
import com.afa1997.geopicsorter.features.ShStrings;

// Contains brief information about GeoPicSorter.
public class AboutWindow extends javax.swing.JFrame {
    public AboutWindow() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        abt_title = new javax.swing.JLabel();
        abt_ver = new javax.swing.JLabel();
        abt_desc = new javax.swing.JLabel();
        abt_author = new javax.swing.JLabel();
        abt_repo_link = new javax.swing.JLabel();
        abt_btn_credits = new javax.swing.JButton();
        abt_btn_close = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("About GeoPicSorter");
        setResizable(false);

        abt_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        abt_title.setText("<html>\n<p style=\"font-size: 40px;\"><b style=\"color: #008000;\">Geo</b><b style=\"color: #000080;\">Pic</b><b style=\"color: #800000;\">Sorter</b></p>");

        abt_ver.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        abt_ver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        abt_ver.setText(ShStrings.PROG_NAME_FULL);

        abt_desc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        abt_desc.setText("<html><p style=\"text-align: center;\">GeoPicSorter is an automation tool that will help you organizing<br>geotagged pictures, based on groups of locations.</p>");

        abt_author.setFont(new java.awt.Font("sansserif", 0, 10)); // NOI18N
        abt_author.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        abt_author.setText("Designed by Aldo Franquez");

        abt_repo_link.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        abt_repo_link.setText("github.com/af-a1997/GeoPicSorter");

        abt_btn_credits.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stock_3d-favourites.png"))); // NOI18N
        abt_btn_credits.setText("Credits");
        abt_btn_credits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abt_btn_creditsActionPerformed(evt);
            }
        });

        abt_btn_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/stock_close.png"))); // NOI18N
        abt_btn_close.setText("Close");
        abt_btn_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abt_btn_closeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(abt_repo_link, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(abt_ver, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(abt_title, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(abt_desc, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                    .addComponent(abt_author, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(abt_btn_credits)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(abt_btn_close)
                        .addGap(9, 9, 9)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(abt_title, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(abt_ver, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(abt_desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(abt_author)
                .addGap(57, 57, 57)
                .addComponent(abt_repo_link, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(abt_btn_credits)
                    .addComponent(abt_btn_close))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Close about window.
    private void abt_btn_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abt_btn_closeActionPerformed
        this.dispose();
    }//GEN-LAST:event_abt_btn_closeActionPerformed

    // Open credits window.
    private void abt_btn_creditsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abt_btn_creditsActionPerformed
        Credits credits_window = new Credits();
        
        credits_window.setVisible(true);
    }//GEN-LAST:event_abt_btn_creditsActionPerformed

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
            java.util.logging.Logger.getLogger(AboutWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AboutWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AboutWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AboutWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AboutWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel abt_author;
    private javax.swing.JButton abt_btn_close;
    private javax.swing.JButton abt_btn_credits;
    private javax.swing.JLabel abt_desc;
    private javax.swing.JLabel abt_repo_link;
    private javax.swing.JLabel abt_title;
    private javax.swing.JLabel abt_ver;
    // End of variables declaration//GEN-END:variables
}
