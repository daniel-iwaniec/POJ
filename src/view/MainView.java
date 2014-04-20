/*
 * The MIT License
 *
 * Copyright 2014 Daniel Iwaniec, Karol Gos.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package view;

/**
 *
 * @author Daniel Iwaniec, Karol Gos
 */
public class MainView extends javax.swing.JFrame {

 /**
  * Creates new form MainView
  */
 public MainView() {
  this.setExtendedState(this.MAXIMIZED_BOTH);
  this.setUndecorated(true);
  this.setLocation(0, 0);
  initComponents();
 }

 public javax.swing.JLabel getLabel() {
  return null;
 }

 public javax.swing.JTable getTable() {
  return this.jTable1;
 }

 /**
  * This method is called from within the constructor to initialize the form.
  * WARNING: Do NOT modify this code. The content of this method is always
  * regenerated by the Form Editor.
  */
 @SuppressWarnings("unchecked")
 // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
 private void initComponents() {

  jLabel1 = new javax.swing.JLabel();
  jPopupMenu1 = new javax.swing.JPopupMenu();
  jMenu1 = new javax.swing.JMenu();
  jMenuItem1 = new javax.swing.JMenuItem();
  jMenuItem2 = new javax.swing.JMenuItem();
  jMenuItem12 = new javax.swing.JMenuItem();
  jLabel2 = new javax.swing.JLabel();
  jScrollPane1 = new javax.swing.JScrollPane();
  jTable1 = new javax.swing.JTable();
  jMenuBar1 = new javax.swing.JMenuBar();
  jMenu2 = new javax.swing.JMenu();
  jMenuItem7 = new javax.swing.JMenuItem();
  jMenuItem8 = new javax.swing.JMenuItem();
  jMenu3 = new javax.swing.JMenu();
  jMenuItem3 = new javax.swing.JMenuItem();
  jMenuItem4 = new javax.swing.JMenuItem();
  jMenu4 = new javax.swing.JMenu();
  jMenu5 = new javax.swing.JMenu();
  jMenuItem10 = new javax.swing.JMenuItem();
  jMenuItem9 = new javax.swing.JMenuItem();
  jMenu6 = new javax.swing.JMenu();
  jMenuItem11 = new javax.swing.JMenuItem();
  jMenuItem13 = new javax.swing.JMenuItem();

  jLabel1.setText("jLabel1");

  jMenu1.setText("jMenu1");

  jMenuItem1.setText("jMenuItem1");

  jMenuItem2.setText("jMenuItem2");

  jMenuItem12.setText("jMenuItem12");

  setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
  setTitle("Magazyn");
  setAlwaysOnTop(true);
  setBackground(new java.awt.Color(255, 255, 255));
  setBounds(new java.awt.Rectangle(0, 0, 0, 0));
  setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
  setFont(new java.awt.Font("Myriad Pro", 0, 12)); // NOI18N
  setForeground(new java.awt.Color(0, 0, 0));
  setPreferredSize(new java.awt.Dimension(500, 500));

  jLabel2.setBackground(new java.awt.Color(255, 255, 255));
  jLabel2.setFont(new java.awt.Font("Myriad Pro", 1, 24)); // NOI18N
  jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
  jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/box.png"))); // NOI18N
  jLabel2.setText("MAGAZYNY");
  jLabel2.setToolTipText("");
  jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 2, 0, new java.awt.Color(204, 204, 204)));
  jLabel2.setIconTextGap(20);

  jTable1.setFont(new java.awt.Font("Myriad Pro", 0, 18)); // NOI18N
  jTable1.setModel(new javax.swing.table.DefaultTableModel(
   new Object [][] {

   },
   new String [] {
    "ID", "Nazwa"
   }
  ) {
   Class[] types = new Class [] {
    java.lang.Long.class, java.lang.String.class
   };
   boolean[] canEdit = new boolean [] {
    false, false
   };

   public Class getColumnClass(int columnIndex) {
    return types [columnIndex];
   }

   public boolean isCellEditable(int rowIndex, int columnIndex) {
    return canEdit [columnIndex];
   }
  });
  jTable1.setColumnSelectionAllowed(true);
  jTable1.setFillsViewportHeight(true);
  jTable1.setGridColor(new java.awt.Color(204, 204, 204));
  jTable1.setRowHeight(36);
  jTable1.setSelectionBackground(new java.awt.Color(204, 255, 255));
  jTable1.getTableHeader().setReorderingAllowed(false);
  jScrollPane1.setViewportView(jTable1);
  jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
  if (jTable1.getColumnModel().getColumnCount() > 0) {
   jTable1.getColumnModel().getColumn(0).setResizable(false);
   jTable1.getColumnModel().getColumn(1).setResizable(false);
  }

  jMenuBar1.setBorderPainted(false);

  jMenu2.setText("Aplikacja");

  jMenuItem7.setText("Start");
  jMenu2.add(jMenuItem7);

  jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
  jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/exit.png"))); // NOI18N
  jMenuItem8.setText("Zakończ");
  jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem8ActionPerformed(evt);
   }
  });
  jMenu2.add(jMenuItem8);

  jMenuBar1.add(jMenu2);

  jMenu3.setText("Magazyn");
  jMenu3.setBorderPainted(true);

  jMenuItem3.setText("Lista");
  jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem3ActionPerformed(evt);
   }
  });
  jMenu3.add(jMenuItem3);

  jMenuItem4.setText("Dodaj");
  jMenu3.add(jMenuItem4);

  jMenuBar1.add(jMenu3);

  jMenu4.setText("Dokument");

  jMenu5.setText("Wydanie zewnętrzne");

  jMenuItem10.setText("Lista");
  jMenu5.add(jMenuItem10);

  jMenuItem9.setText("Dodaj");
  jMenu5.add(jMenuItem9);

  jMenu4.add(jMenu5);

  jMenu6.setText("Przyjęcie zewnętrzne");

  jMenuItem11.setText("Lista");
  jMenu6.add(jMenuItem11);

  jMenuItem13.setText("Dodaj");
  jMenu6.add(jMenuItem13);

  jMenu4.add(jMenu6);

  jMenuBar1.add(jMenu4);

  setJMenuBar(jMenuBar1);

  javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
  getContentPane().setLayout(layout);
  layout.setHorizontalGroup(
   layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
   .addGroup(layout.createSequentialGroup()
    .addContainerGap()
    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
    .addContainerGap())
  );
  layout.setVerticalGroup(
   layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(layout.createSequentialGroup()
    .addContainerGap()
    .addComponent(jLabel2)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addContainerGap())
  );

  pack();
 }// </editor-fold>//GEN-END:initComponents

 private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
  // TODO add your handling code here:
 }//GEN-LAST:event_jMenuItem3ActionPerformed

 private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
  // TODO add your handling code here:
  this.dispose();
 }//GEN-LAST:event_jMenuItem8ActionPerformed

 /**
  * @param args the command line arguments
  */
 public static void main(String args[]) {
  /* Set the Nimbus look and feel */
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
   java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
  } catch (InstantiationException ex) {
   java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
  } catch (IllegalAccessException ex) {
   java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
  } catch (javax.swing.UnsupportedLookAndFeelException ex) {
   java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
  }
        //</editor-fold>

  /* Create and display the form */
  java.awt.EventQueue.invokeLater(new Runnable() {
   public void run() {
    new MainView().setVisible(true);
   }
  });
 }

 // Variables declaration - do not modify//GEN-BEGIN:variables
 private javax.swing.JLabel jLabel1;
 private javax.swing.JLabel jLabel2;
 private javax.swing.JMenu jMenu1;
 private javax.swing.JMenu jMenu2;
 private javax.swing.JMenu jMenu3;
 private javax.swing.JMenu jMenu4;
 private javax.swing.JMenu jMenu5;
 private javax.swing.JMenu jMenu6;
 private javax.swing.JMenuBar jMenuBar1;
 private javax.swing.JMenuItem jMenuItem1;
 private javax.swing.JMenuItem jMenuItem10;
 private javax.swing.JMenuItem jMenuItem11;
 private javax.swing.JMenuItem jMenuItem12;
 private javax.swing.JMenuItem jMenuItem13;
 private javax.swing.JMenuItem jMenuItem2;
 private javax.swing.JMenuItem jMenuItem3;
 private javax.swing.JMenuItem jMenuItem4;
 private javax.swing.JMenuItem jMenuItem7;
 private javax.swing.JMenuItem jMenuItem8;
 private javax.swing.JMenuItem jMenuItem9;
 private javax.swing.JPopupMenu jPopupMenu1;
 private javax.swing.JScrollPane jScrollPane1;
 private javax.swing.JTable jTable1;
 // End of variables declaration//GEN-END:variables
}
