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

import java.util.logging.Level;
import java.util.logging.Logger;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import controller.*;
import java.util.List;
import javax.swing.JDialog;

public class MainView extends javax.swing.JFrame {

 private JDialog errorPopupDialog;

 public static final String BOX_ICON = "box";
 public static final String HOME_ICON = "home";
 public static final String LICENSE_ICON = "license";

 private static MainView instance = null;

 public static MainView getInstance() {
  if (instance == null) {
   instance = new MainView();
  }
  return instance;
 }

 public MainView() {
  this.getContentPane().setBackground(Color.WHITE);
  this.setExtendedState(MainView.MAXIMIZED_BOTH);
  this.setUndecorated(true);
  this.setLocation(0, 0);

  UIManager.put("PopupMenu.border", BorderFactory.createLineBorder(Color.BLACK, 1));

  initComponents();

  this.hideAllViews();
  hiddenWarehouseId.setVisible(false);

  this.initializeErrorPopup();
 }

 private void initializeErrorPopup() {
  errorPopupDialog = new JDialog(this, "", true);
  errorPopupDialog.setUndecorated(true);

  this.ErrorPopup.setVisible(true);
  errorPopupDialog.getContentPane().add(this.ErrorPopup);
  errorPopupDialog.pack();

  errorPopupDialog.setSize(400, 280);
  errorPopupDialog.setLocationRelativeTo(this);
  errorPopupDialog.setVisible(false);
 }

 public javax.swing.JPanel getErrorPopupPanel() {
  return this.ErrorPopup;
 }

 public void showErrorPopup(String error) {
  this.licenseTextArea1.setText("");
  this.licenseTextArea1.append(error);
  this.getErrorPopupPanel().setVisible(true);
  this.getErrorPopup().setVisible(true);
 }

 public void showErrorPopup(List<String> errors) {
  this.licenseTextArea1.setText("");
  for (String error : errors) {
   this.licenseTextArea1.append(error + "\n");
  }
  this.getErrorPopupPanel().setVisible(true);
  this.getErrorPopup().setVisible(true);
 }

 public void showErrorPopup() {
  this.getErrorPopupPanel().setVisible(true);
  this.getErrorPopup().setVisible(true);
 }

 public void hideErrorPopup() {
  this.getErrorPopupPanel().setVisible(false);
  this.getErrorPopup().setVisible(false);
 }

 public JDialog getErrorPopup() {
  return errorPopupDialog;
 }

 public javax.swing.JLabel getHeader() {
  return this.Header;
 }

 public void setIcon(String name) {
  Header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/" + name + ".png")));
 }

 public javax.swing.JPanel getIndexView() {
  return this.Index;
 }

 public javax.swing.JPanel getLicenseView() {
  return this.License;
 }

 public javax.swing.JTextArea getLicenseTextArea() {
  return this.licenseTextArea;
 }

 public javax.swing.JPanel getWarehouseListView() {
  return this.WarehouseList;
 }

 public javax.swing.JPanel getWarehouseFormView() {
  return this.WarehouseForm;
 }

 public javax.swing.JPanel getSelectFormView() {
  return this.SelectForm;
 }

 public javax.swing.JPanel getWarehouseViewInformationsView() {
  return this.WarehouseViewInformations;
 }

 public javax.swing.JComboBox getSelectFormSelect() {
  return this.SelectFormSelect;
 }

 public javax.swing.JLabel getSelectFormHeader() {
  return this.jLabel17;
 }

 public javax.swing.JButton getSelectFormButton() {
  return this.jButton2;
 }

 public javax.swing.JTextField getHiddenWarehouseId() {
  return hiddenWarehouseId;
 }

 public final void hideAllViews() {
  this.WarehouseList.setVisible(false);
  this.WarehouseForm.setVisible(false);
  this.Index.setVisible(false);
  this.License.setVisible(false);
  this.SelectForm.setVisible(false);
  this.WarehouseViewInformations.setVisible(false);
  this.ErrorPopup.setVisible(false);
 }

 public javax.swing.JTable getWarehouseListTable() {
  return this.jTable1;
 }

 public javax.swing.JTextField getWarehouseFormNameInput() {
  return jTextField1;
 }

 public javax.swing.JTextField getWarehouseViewIDInput() {
  return jTextField3;
 }

 public javax.swing.JTextField getWarehouseViewNameInput() {
  return jTextField4;
 }

 public javax.swing.JTextField getWarehouseViewValueInput() {
  return jTextField2;
 }

 public javax.swing.JButton getWarehouseFormButton() {
  return this.jButton1;
 }

 /**
  * This method is called from within the constructor to initialize the form.
  * WARNING: Do NOT modify this code. The content of this method is always
  * regenerated by the Form Editor.
  */
 @SuppressWarnings("unchecked")
 // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
 private void initComponents() {

  Header = new javax.swing.JLabel();
  jLayeredPane1 = new javax.swing.JLayeredPane();
  WarehouseList = new javax.swing.JPanel();
  jScrollPane2 = new javax.swing.JScrollPane();
  jTable1 = new javax.swing.JTable();
  WarehouseForm = new javax.swing.JPanel();
  jButton1 = new javax.swing.JButton();
  jTextField1 = new javax.swing.JTextField();
  jLabel1 = new javax.swing.JLabel();
  hiddenWarehouseId = new javax.swing.JTextField();
  Index = new javax.swing.JPanel();
  jLabel2 = new javax.swing.JLabel();
  jLabel3 = new javax.swing.JLabel();
  jLabel4 = new javax.swing.JLabel();
  jLabel5 = new javax.swing.JLabel();
  jLabel6 = new javax.swing.JLabel();
  jLabel7 = new javax.swing.JLabel();
  jLabel8 = new javax.swing.JLabel();
  jLabel9 = new javax.swing.JLabel();
  jLabel10 = new javax.swing.JLabel();
  jLabel11 = new javax.swing.JLabel();
  License = new javax.swing.JPanel();
  jLabel12 = new javax.swing.JLabel();
  jLabel13 = new javax.swing.JLabel();
  jLabel14 = new javax.swing.JLabel();
  jScrollPane1 = new javax.swing.JScrollPane();
  licenseTextArea = new javax.swing.JTextArea();
  SelectForm = new javax.swing.JPanel();
  jButton2 = new javax.swing.JButton();
  jLabel17 = new javax.swing.JLabel();
  SelectFormSelect = new javax.swing.JComboBox();
  WarehouseViewInformations = new javax.swing.JPanel();
  jTextField2 = new javax.swing.JTextField();
  jLabel18 = new javax.swing.JLabel();
  jLabel19 = new javax.swing.JLabel();
  jTextField3 = new javax.swing.JTextField();
  jLabel20 = new javax.swing.JLabel();
  jTextField4 = new javax.swing.JTextField();
  ErrorPopup = new javax.swing.JPanel();
  jLabel23 = new javax.swing.JLabel();
  jScrollPane3 = new javax.swing.JScrollPane();
  licenseTextArea1 = new javax.swing.JTextArea();
  jButton3 = new javax.swing.JButton();
  jMenuBar1 = new javax.swing.JMenuBar();
  jMenu2 = new javax.swing.JMenu();
  jMenuItem7 = new javax.swing.JMenuItem();
  jMenuItem2 = new javax.swing.JMenuItem();
  jMenuItem8 = new javax.swing.JMenuItem();
  jMenu3 = new javax.swing.JMenu();
  jMenuItem3 = new javax.swing.JMenuItem();
  jMenuItem6 = new javax.swing.JMenuItem();
  jMenuItem4 = new javax.swing.JMenuItem();
  jMenuItem1 = new javax.swing.JMenuItem();
  jMenuItem5 = new javax.swing.JMenuItem();
  jMenu1 = new javax.swing.JMenu();
  jMenuItem12 = new javax.swing.JMenuItem();
  jMenuItem14 = new javax.swing.JMenuItem();
  jMenuItem16 = new javax.swing.JMenuItem();
  jMenuItem17 = new javax.swing.JMenuItem();
  jMenuItem18 = new javax.swing.JMenuItem();
  jMenu4 = new javax.swing.JMenu();
  jMenu5 = new javax.swing.JMenu();
  jMenuItem10 = new javax.swing.JMenuItem();
  jMenuItem9 = new javax.swing.JMenuItem();
  jMenuItem19 = new javax.swing.JMenuItem();
  jMenuItem20 = new javax.swing.JMenuItem();
  jMenu6 = new javax.swing.JMenu();
  jMenuItem11 = new javax.swing.JMenuItem();
  jMenuItem13 = new javax.swing.JMenuItem();
  jMenuItem22 = new javax.swing.JMenuItem();
  jMenuItem23 = new javax.swing.JMenuItem();
  jMenuItem24 = new javax.swing.JMenuItem();
  jMenu7 = new javax.swing.JMenu();
  jMenuItem25 = new javax.swing.JMenuItem();
  jMenuItem26 = new javax.swing.JMenuItem();
  jMenuItem27 = new javax.swing.JMenuItem();
  jMenuItem28 = new javax.swing.JMenuItem();
  jMenuItem29 = new javax.swing.JMenuItem();
  jMenu8 = new javax.swing.JMenu();
  jMenuItem30 = new javax.swing.JMenuItem();
  jMenuItem31 = new javax.swing.JMenuItem();
  jMenuItem32 = new javax.swing.JMenuItem();
  jMenuItem33 = new javax.swing.JMenuItem();
  jMenuItem34 = new javax.swing.JMenuItem();
  jMenu9 = new javax.swing.JMenu();
  jMenuItem35 = new javax.swing.JMenuItem();

  setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
  setTitle("Magazyn");
  setAlwaysOnTop(true);
  setBackground(new java.awt.Color(255, 255, 255));
  setBounds(new java.awt.Rectangle(0, 0, 0, 0));
  setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
  setFont(new java.awt.Font("Myriad Pro", 0, 12)); // NOI18N
  setForeground(new java.awt.Color(0, 0, 0));

  Header.setBackground(new java.awt.Color(255, 255, 255));
  Header.setFont(new java.awt.Font("Myriad Pro", 1, 24)); // NOI18N
  Header.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
  Header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/box.png"))); // NOI18N
  Header.setText("MAGAZYNY");
  Header.setToolTipText("");
  Header.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 2, 0, new java.awt.Color(204, 204, 204)));
  Header.setIconTextGap(20);

  jLayeredPane1.setOpaque(true);

  WarehouseList.setBackground(new java.awt.Color(255, 255, 255));

  jScrollPane2.setPreferredSize(new java.awt.Dimension(452, 427));

  jTable1.setFont(new java.awt.Font("Myriad Pro", 0, 18)); // NOI18N
  jTable1.setModel(new javax.swing.table.DefaultTableModel(
   new Object [][] {

   },
   new String [] {
    "ID", "Nazwa", "Wartość"
   }
  ) {
   Class[] types = new Class [] {
    java.lang.String.class, java.lang.String.class, java.lang.String.class
   };
   boolean[] canEdit = new boolean [] {
    false, false, false
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
  jScrollPane2.setViewportView(jTable1);
  jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
  if (jTable1.getColumnModel().getColumnCount() > 0) {
   jTable1.getColumnModel().getColumn(0).setResizable(false);
   jTable1.getColumnModel().getColumn(1).setResizable(false);
   jTable1.getColumnModel().getColumn(2).setResizable(false);
  }

  javax.swing.GroupLayout WarehouseListLayout = new javax.swing.GroupLayout(WarehouseList);
  WarehouseList.setLayout(WarehouseListLayout);
  WarehouseListLayout.setHorizontalGroup(
   WarehouseListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WarehouseListLayout.createSequentialGroup()
    .addContainerGap()
    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
    .addContainerGap())
  );
  WarehouseListLayout.setVerticalGroup(
   WarehouseListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(WarehouseListLayout.createSequentialGroup()
    .addContainerGap()
    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
    .addContainerGap())
  );

  WarehouseForm.setBackground(new java.awt.Color(255, 255, 255));
  WarehouseForm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
  WarehouseForm.setPreferredSize(new java.awt.Dimension(452, 427));

  jButton1.setFont(new java.awt.Font("Myriad Pro", 0, 18)); // NOI18N
  jButton1.setText("Dodaj");
  jButton1.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jButton1ActionPerformed(evt);
   }
  });

  jTextField1.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N

  jLabel1.setFont(new java.awt.Font("Myriad Pro", 0, 18)); // NOI18N
  jLabel1.setLabelFor(jTextField1);
  jLabel1.setText("Nazwa magazynu");

  hiddenWarehouseId.setText("Hidden ID");

  javax.swing.GroupLayout WarehouseFormLayout = new javax.swing.GroupLayout(WarehouseForm);
  WarehouseForm.setLayout(WarehouseFormLayout);
  WarehouseFormLayout.setHorizontalGroup(
   WarehouseFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(WarehouseFormLayout.createSequentialGroup()
    .addContainerGap()
    .addGroup(WarehouseFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
     .addGroup(WarehouseFormLayout.createSequentialGroup()
      .addGap(0, 399, Short.MAX_VALUE)
      .addComponent(jButton1))
     .addComponent(jTextField1)
     .addGroup(WarehouseFormLayout.createSequentialGroup()
      .addComponent(jLabel1)
      .addGap(0, 0, Short.MAX_VALUE))
     .addComponent(hiddenWarehouseId))
    .addContainerGap())
  );
  WarehouseFormLayout.setVerticalGroup(
   WarehouseFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WarehouseFormLayout.createSequentialGroup()
    .addContainerGap()
    .addComponent(jLabel1)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(hiddenWarehouseId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 436, Short.MAX_VALUE)
    .addComponent(jButton1)
    .addContainerGap())
  );

  Index.setBackground(new java.awt.Color(255, 255, 255));
  Index.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
  Index.setPreferredSize(new java.awt.Dimension(452, 427));

  jLabel2.setFont(new java.awt.Font("Myriad Pro", 1, 24)); // NOI18N
  jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
  jLabel2.setText("Politechnika Świętokrzyska");
  jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

  jLabel3.setFont(new java.awt.Font("Myriad Pro", 1, 24)); // NOI18N
  jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
  jLabel3.setText("Programowanie obiektowe Java - Projekt");

  jLabel4.setFont(new java.awt.Font("Myriad Pro", 0, 20)); // NOI18N
  jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
  jLabel4.setText("Semetr letni 2014r.");

  jLabel5.setFont(new java.awt.Font("Myriad Pro", 0, 20)); // NOI18N
  jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
  jLabel5.setText("Autorzy:");

  jLabel6.setFont(new java.awt.Font("Myriad Pro", 0, 20)); // NOI18N
  jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
  jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/bullet.png"))); // NOI18N
  jLabel6.setText("Daniel Iwaniec");

  jLabel7.setFont(new java.awt.Font("Myriad Pro", 0, 20)); // NOI18N
  jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
  jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/bullet.png"))); // NOI18N
  jLabel7.setText("Karol Gos");

  jLabel8.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
  jLabel8.setText("Copyright 2014 Daniel Iwaniec, Karol Gos.");

  jLabel9.setFont(new java.awt.Font("Myriad Pro", 1, 16)); // NOI18N
  jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
  jLabel9.setText("Temat projektu: Aplikacja magazynowa.");

  jLabel10.setFont(new java.awt.Font("Myriad Pro", 0, 16)); // NOI18N
  jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
  jLabel10.setText("Projekt obejmuje:");

  jLabel11.setFont(new java.awt.Font("Myriad Pro", 0, 16)); // NOI18N
  jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
  jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/bullet.png"))); // NOI18N
  jLabel11.setText("Obsługę magazynów.");

  javax.swing.GroupLayout IndexLayout = new javax.swing.GroupLayout(Index);
  Index.setLayout(IndexLayout);
  IndexLayout.setHorizontalGroup(
   IndexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
   .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
   .addGroup(IndexLayout.createSequentialGroup()
    .addContainerGap()
    .addGroup(IndexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
     .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
     .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
     .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
     .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
     .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
     .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
     .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
     .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    .addContainerGap())
  );
  IndexLayout.setVerticalGroup(
   IndexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(IndexLayout.createSequentialGroup()
    .addGap(5, 5, 5)
    .addComponent(jLabel2)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jLabel3)
    .addGap(18, 18, 18)
    .addComponent(jLabel4)
    .addGap(18, 18, 18)
    .addComponent(jLabel5)
    .addGap(18, 18, 18)
    .addComponent(jLabel6)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jLabel7)
    .addGap(18, 18, 18)
    .addComponent(jLabel9)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jLabel10)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jLabel11)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 242, Short.MAX_VALUE)
    .addComponent(jLabel8)
    .addContainerGap())
  );

  License.setBackground(new java.awt.Color(255, 255, 255));
  License.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
  License.setPreferredSize(new java.awt.Dimension(452, 427));

  jLabel12.setFont(new java.awt.Font("Myriad Pro", 1, 24)); // NOI18N
  jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
  jLabel12.setText("Politechnika Świętokrzyska");
  jLabel12.setVerticalAlignment(javax.swing.SwingConstants.TOP);

  jLabel13.setFont(new java.awt.Font("Myriad Pro", 1, 24)); // NOI18N
  jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
  jLabel13.setText("Programowanie obiektowe Java - Projekt");

  jLabel14.setFont(new java.awt.Font("Myriad Pro", 0, 20)); // NOI18N
  jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
  jLabel14.setText("The MIT License");

  jScrollPane1.setBorder(null);

  licenseTextArea.setEditable(false);
  licenseTextArea.setBackground(new java.awt.Color(245, 245, 245));
  licenseTextArea.setColumns(20);
  licenseTextArea.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
  licenseTextArea.setRows(5);
  licenseTextArea.setBorder(null);
  jScrollPane1.setViewportView(licenseTextArea);

  javax.swing.GroupLayout LicenseLayout = new javax.swing.GroupLayout(License);
  License.setLayout(LicenseLayout);
  LicenseLayout.setHorizontalGroup(
   LicenseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
   .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LicenseLayout.createSequentialGroup()
    .addContainerGap()
    .addGroup(LicenseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
     .addComponent(jScrollPane1)
     .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    .addContainerGap())
  );
  LicenseLayout.setVerticalGroup(
   LicenseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(LicenseLayout.createSequentialGroup()
    .addGap(5, 5, 5)
    .addComponent(jLabel12)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jLabel13)
    .addGap(18, 18, 18)
    .addComponent(jLabel14)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
    .addContainerGap())
  );

  SelectForm.setBackground(new java.awt.Color(255, 255, 255));
  SelectForm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
  SelectForm.setPreferredSize(new java.awt.Dimension(452, 427));

  jButton2.setFont(new java.awt.Font("Myriad Pro", 0, 18)); // NOI18N
  jButton2.setText("OK");
  jButton2.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jButton2ActionPerformed(evt);
   }
  });

  jLabel17.setFont(new java.awt.Font("Myriad Pro", 0, 18)); // NOI18N
  jLabel17.setLabelFor(jTextField1);
  jLabel17.setText("Wybierz");

  SelectFormSelect.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  SelectFormSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

  javax.swing.GroupLayout SelectFormLayout = new javax.swing.GroupLayout(SelectForm);
  SelectForm.setLayout(SelectFormLayout);
  SelectFormLayout.setHorizontalGroup(
   SelectFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(SelectFormLayout.createSequentialGroup()
    .addContainerGap()
    .addGroup(SelectFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
     .addGroup(SelectFormLayout.createSequentialGroup()
      .addGap(0, 423, Short.MAX_VALUE)
      .addComponent(jButton2))
     .addGroup(SelectFormLayout.createSequentialGroup()
      .addComponent(jLabel17)
      .addGap(0, 0, Short.MAX_VALUE))
     .addComponent(SelectFormSelect, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    .addContainerGap())
  );
  SelectFormLayout.setVerticalGroup(
   SelectFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SelectFormLayout.createSequentialGroup()
    .addContainerGap()
    .addComponent(jLabel17)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(SelectFormSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 451, Short.MAX_VALUE)
    .addComponent(jButton2)
    .addContainerGap())
  );

  WarehouseViewInformations.setBackground(new java.awt.Color(255, 255, 255));
  WarehouseViewInformations.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
  WarehouseViewInformations.setPreferredSize(new java.awt.Dimension(452, 427));

  jTextField2.setEditable(false);
  jTextField2.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N

  jLabel18.setFont(new java.awt.Font("Myriad Pro", 0, 18)); // NOI18N
  jLabel18.setLabelFor(jTextField1);
  jLabel18.setText("Nazwa magazynu");

  jLabel19.setFont(new java.awt.Font("Myriad Pro", 0, 18)); // NOI18N
  jLabel19.setLabelFor(jTextField1);
  jLabel19.setText("ID magazynu");

  jTextField3.setEditable(false);
  jTextField3.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N

  jLabel20.setFont(new java.awt.Font("Myriad Pro", 0, 18)); // NOI18N
  jLabel20.setLabelFor(jTextField1);
  jLabel20.setText("Wartość magazynu");

  jTextField4.setEditable(false);
  jTextField4.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N

  javax.swing.GroupLayout WarehouseViewInformationsLayout = new javax.swing.GroupLayout(WarehouseViewInformations);
  WarehouseViewInformations.setLayout(WarehouseViewInformationsLayout);
  WarehouseViewInformationsLayout.setHorizontalGroup(
   WarehouseViewInformationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(WarehouseViewInformationsLayout.createSequentialGroup()
    .addContainerGap()
    .addGroup(WarehouseViewInformationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
     .addComponent(jTextField2)
     .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING)
     .addGroup(WarehouseViewInformationsLayout.createSequentialGroup()
      .addGroup(WarehouseViewInformationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
       .addComponent(jLabel19)
       .addComponent(jLabel18)
       .addComponent(jLabel20))
      .addGap(0, 286, Short.MAX_VALUE))
     .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING))
    .addContainerGap())
  );
  WarehouseViewInformationsLayout.setVerticalGroup(
   WarehouseViewInformationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WarehouseViewInformationsLayout.createSequentialGroup()
    .addContainerGap()
    .addComponent(jLabel19)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addGap(18, 18, 18)
    .addComponent(jLabel18)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addGap(18, 18, 18)
    .addComponent(jLabel20)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addContainerGap(240, Short.MAX_VALUE))
  );

  ErrorPopup.setBackground(new java.awt.Color(255, 255, 255));
  ErrorPopup.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
  ErrorPopup.setPreferredSize(new java.awt.Dimension(452, 427));

  jLabel23.setFont(new java.awt.Font("Myriad Pro", 0, 20)); // NOI18N
  jLabel23.setForeground(new java.awt.Color(204, 0, 0));
  jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
  jLabel23.setText("Wystąpiły następujące błędy");

  jScrollPane3.setBorder(null);

  licenseTextArea1.setEditable(false);
  licenseTextArea1.setBackground(new java.awt.Color(245, 245, 245));
  licenseTextArea1.setColumns(20);
  licenseTextArea1.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
  licenseTextArea1.setRows(5);
  licenseTextArea1.setBorder(null);
  jScrollPane3.setViewportView(licenseTextArea1);

  jButton3.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jButton3.setText("OK");
  jButton3.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jButton3ActionPerformed(evt);
   }
  });

  javax.swing.GroupLayout ErrorPopupLayout = new javax.swing.GroupLayout(ErrorPopup);
  ErrorPopup.setLayout(ErrorPopupLayout);
  ErrorPopupLayout.setHorizontalGroup(
   ErrorPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(ErrorPopupLayout.createSequentialGroup()
    .addContainerGap()
    .addGroup(ErrorPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
     .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
     .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
     .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ErrorPopupLayout.createSequentialGroup()
      .addGap(0, 0, Short.MAX_VALUE)
      .addComponent(jButton3)))
    .addContainerGap())
  );
  ErrorPopupLayout.setVerticalGroup(
   ErrorPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(ErrorPopupLayout.createSequentialGroup()
    .addContainerGap()
    .addComponent(jLabel23)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jButton3)
    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
  );

  javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
  jLayeredPane1.setLayout(jLayeredPane1Layout);
  jLayeredPane1Layout.setHorizontalGroup(
   jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addComponent(WarehouseList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addContainerGap()
     .addComponent(WarehouseForm, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
     .addContainerGap()))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addContainerGap()
     .addComponent(Index, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
     .addContainerGap()))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addContainerGap()
     .addComponent(License, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
     .addContainerGap()))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addContainerGap()
     .addComponent(SelectForm, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
     .addContainerGap()))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addContainerGap()
     .addComponent(WarehouseViewInformations, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
     .addContainerGap()))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addContainerGap()
     .addComponent(ErrorPopup, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
     .addContainerGap()))
  );
  jLayeredPane1Layout.setVerticalGroup(
   jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addComponent(WarehouseList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addGap(10, 10, 10)
     .addComponent(WarehouseForm, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
     .addContainerGap()))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addGap(10, 10, 10)
     .addComponent(Index, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
     .addContainerGap()))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addContainerGap()
     .addComponent(License, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
     .addGap(21, 21, 21)))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addContainerGap()
     .addComponent(SelectForm, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
     .addGap(21, 21, 21)))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addContainerGap()
     .addComponent(WarehouseViewInformations, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
     .addGap(21, 21, 21)))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addGap(21, 21, 21)
     .addComponent(ErrorPopup, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
     .addContainerGap()))
  );
  jLayeredPane1.setLayer(WarehouseList, javax.swing.JLayeredPane.DEFAULT_LAYER);

  WarehouseList.getAccessibleContext().setAccessibleName("WarehouseList");
  jLayeredPane1.setLayer(WarehouseForm, javax.swing.JLayeredPane.DEFAULT_LAYER);
  WarehouseForm.getAccessibleContext().setAccessibleName("WarehouseForm");
  jLayeredPane1.setLayer(Index, javax.swing.JLayeredPane.DEFAULT_LAYER);
  Index.getAccessibleContext().setAccessibleName("Index");
  jLayeredPane1.setLayer(License, javax.swing.JLayeredPane.DEFAULT_LAYER);
  License.getAccessibleContext().setAccessibleName("License");
  jLayeredPane1.setLayer(SelectForm, javax.swing.JLayeredPane.DEFAULT_LAYER);
  SelectForm.getAccessibleContext().setAccessibleName("SelectForm");
  jLayeredPane1.setLayer(WarehouseViewInformations, javax.swing.JLayeredPane.DEFAULT_LAYER);
  jLayeredPane1.setLayer(ErrorPopup, javax.swing.JLayeredPane.DEFAULT_LAYER);
  ErrorPopup.getAccessibleContext().setAccessibleName("ErrorPopup");

  jMenuBar1.setBorderPainted(false);

  jMenu2.setText("Aplikacja");

  jMenuItem7.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/home_small.png"))); // NOI18N
  jMenuItem7.setText("Informacje o projekcie");
  jMenuItem7.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem7ActionPerformed(evt);
   }
  });
  jMenu2.add(jMenuItem7);

  jMenuItem2.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/license_menu.png"))); // NOI18N
  jMenuItem2.setText("Licencja");
  jMenuItem2.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem2ActionPerformed(evt);
   }
  });
  jMenu2.add(jMenuItem2);

  jMenuItem8.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/exit.png"))); // NOI18N
  jMenuItem8.setText("Zakończ");
  jMenuItem8.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem8ActionPerformed(evt);
   }
  });
  jMenu2.add(jMenuItem8);

  jMenuBar1.add(jMenu2);

  jMenu3.setText("Magazyn");
  jMenu3.setBorderPainted(true);

  jMenuItem3.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/list.png"))); // NOI18N
  jMenuItem3.setText("Lista");
  jMenuItem3.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem3ActionPerformed(evt);
   }
  });
  jMenu3.add(jMenuItem3);

  jMenuItem6.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/view.png"))); // NOI18N
  jMenuItem6.setText("Informacje");
  jMenuItem6.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem6ActionPerformed(evt);
   }
  });
  jMenu3.add(jMenuItem6);

  jMenuItem4.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/add.png"))); // NOI18N
  jMenuItem4.setText("Dodaj");
  jMenuItem4.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem4ActionPerformed(evt);
   }
  });
  jMenu3.add(jMenuItem4);

  jMenuItem1.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/edit.png"))); // NOI18N
  jMenuItem1.setText("Edytuj");
  jMenuItem1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem1ActionPerformed(evt);
   }
  });
  jMenu3.add(jMenuItem1);

  jMenuItem5.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/delete.png"))); // NOI18N
  jMenuItem5.setText("Usuń");
  jMenuItem5.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem5ActionPerformed(evt);
   }
  });
  jMenu3.add(jMenuItem5);

  jMenuBar1.add(jMenu3);

  jMenu1.setText("Towar");

  jMenuItem12.setText("jMenuItem12");
  jMenu1.add(jMenuItem12);

  jMenuItem14.setText("jMenuItem14");
  jMenu1.add(jMenuItem14);

  jMenuItem16.setText("jMenuItem16");
  jMenu1.add(jMenuItem16);

  jMenuItem17.setText("jMenuItem17");
  jMenu1.add(jMenuItem17);

  jMenuItem18.setText("jMenuItem18");
  jMenu1.add(jMenuItem18);

  jMenuBar1.add(jMenu1);

  jMenu4.setText("Dokument");

  jMenu5.setText("Wydanie zewnętrzne");

  jMenuItem10.setText("Lista");
  jMenu5.add(jMenuItem10);

  jMenuItem9.setText("Dodaj");
  jMenu5.add(jMenuItem9);

  jMenuItem19.setText("jMenuItem19");
  jMenu5.add(jMenuItem19);

  jMenuItem20.setText("jMenuItem20");
  jMenu5.add(jMenuItem20);

  jMenu4.add(jMenu5);

  jMenu6.setText("Przyjęcie zewnętrzne");

  jMenuItem11.setText("Lista");
  jMenu6.add(jMenuItem11);

  jMenuItem13.setText("Dodaj");
  jMenu6.add(jMenuItem13);

  jMenuItem22.setText("jMenuItem22");
  jMenu6.add(jMenuItem22);

  jMenuItem23.setText("jMenuItem23");
  jMenu6.add(jMenuItem23);

  jMenuItem24.setText("jMenuItem24");
  jMenu6.add(jMenuItem24);

  jMenu4.add(jMenu6);

  jMenu7.setText("Rozchód wewnętrzny");

  jMenuItem25.setText("jMenuItem25");
  jMenu7.add(jMenuItem25);

  jMenuItem26.setText("jMenuItem26");
  jMenu7.add(jMenuItem26);

  jMenuItem27.setText("jMenuItem27");
  jMenu7.add(jMenuItem27);

  jMenuItem28.setText("jMenuItem28");
  jMenu7.add(jMenuItem28);

  jMenuItem29.setText("jMenuItem29");
  jMenu7.add(jMenuItem29);

  jMenu4.add(jMenu7);

  jMenu8.setText("Przychód wewnętrzny");

  jMenuItem30.setText("jMenuItem30");
  jMenu8.add(jMenuItem30);

  jMenuItem31.setText("jMenuItem31");
  jMenu8.add(jMenuItem31);

  jMenuItem32.setText("jMenuItem32");
  jMenu8.add(jMenuItem32);

  jMenuItem33.setText("jMenuItem33");
  jMenu8.add(jMenuItem33);

  jMenuItem34.setText("jMenuItem34");
  jMenu8.add(jMenuItem34);

  jMenu4.add(jMenu8);

  jMenuBar1.add(jMenu4);

  jMenu9.setText("Dane");

  jMenuItem35.setText("Wartość magazynów");
  jMenu9.add(jMenuItem35);

  jMenuBar1.add(jMenu9);

  setJMenuBar(jMenuBar1);

  javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
  getContentPane().setLayout(layout);
  layout.setHorizontalGroup(
   layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addComponent(Header, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
   .addGroup(layout.createSequentialGroup()
    .addContainerGap()
    .addComponent(jLayeredPane1)
    .addContainerGap())
  );
  layout.setVerticalGroup(
   layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(layout.createSequentialGroup()
    .addContainerGap()
    .addComponent(Header)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
    .addComponent(jLayeredPane1)
    .addContainerGap())
  );

  Header.getAccessibleContext().setAccessibleName("Header");

  pack();
 }// </editor-fold>//GEN-END:initComponents

 private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
  WarehouseController.list();
 }//GEN-LAST:event_jMenuItem3ActionPerformed

 private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
  try {
   WarehouseController.addAction();
  } catch (Exception ex) {
   Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
  }
 }//GEN-LAST:event_jButton1ActionPerformed

 private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
  WarehouseController.addForm();
 }//GEN-LAST:event_jMenuItem4ActionPerformed

 private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
  main.Main.mainAction();
 }//GEN-LAST:event_jMenuItem7ActionPerformed

 private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
  this.dispose();
 }//GEN-LAST:event_jMenuItem8ActionPerformed

 private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
  WarehouseController.deleteForm();
 }//GEN-LAST:event_jMenuItem5ActionPerformed

 private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
  main.Main.licenseAction();
 }//GEN-LAST:event_jMenuItem2ActionPerformed

 private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
  // TODO add your handling code here:
 }//GEN-LAST:event_jButton2ActionPerformed

 private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
  WarehouseController.editSelectForm();
 }//GEN-LAST:event_jMenuItem1ActionPerformed

 private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
  WarehouseController.viewInformationsForm();
 }//GEN-LAST:event_jMenuItem6ActionPerformed

 private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
  this.hideErrorPopup();
 }//GEN-LAST:event_jButton3ActionPerformed

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
   @Override
   public void run() {
    new MainView().setVisible(true);
   }
  });
 }

 // Variables declaration - do not modify//GEN-BEGIN:variables
 private javax.swing.JPanel ErrorPopup;
 private javax.swing.JLabel Header;
 private javax.swing.JPanel Index;
 private javax.swing.JPanel License;
 private javax.swing.JPanel SelectForm;
 private javax.swing.JComboBox SelectFormSelect;
 private javax.swing.JPanel WarehouseForm;
 private javax.swing.JPanel WarehouseList;
 private javax.swing.JPanel WarehouseViewInformations;
 private javax.swing.JTextField hiddenWarehouseId;
 private javax.swing.JButton jButton1;
 private javax.swing.JButton jButton2;
 private javax.swing.JButton jButton3;
 private javax.swing.JLabel jLabel1;
 private javax.swing.JLabel jLabel10;
 private javax.swing.JLabel jLabel11;
 private javax.swing.JLabel jLabel12;
 private javax.swing.JLabel jLabel13;
 private javax.swing.JLabel jLabel14;
 private javax.swing.JLabel jLabel17;
 private javax.swing.JLabel jLabel18;
 private javax.swing.JLabel jLabel19;
 private javax.swing.JLabel jLabel2;
 private javax.swing.JLabel jLabel20;
 private javax.swing.JLabel jLabel23;
 private javax.swing.JLabel jLabel3;
 private javax.swing.JLabel jLabel4;
 private javax.swing.JLabel jLabel5;
 private javax.swing.JLabel jLabel6;
 private javax.swing.JLabel jLabel7;
 private javax.swing.JLabel jLabel8;
 private javax.swing.JLabel jLabel9;
 private javax.swing.JLayeredPane jLayeredPane1;
 private javax.swing.JMenu jMenu1;
 private javax.swing.JMenu jMenu2;
 private javax.swing.JMenu jMenu3;
 private javax.swing.JMenu jMenu4;
 private javax.swing.JMenu jMenu5;
 private javax.swing.JMenu jMenu6;
 private javax.swing.JMenu jMenu7;
 private javax.swing.JMenu jMenu8;
 private javax.swing.JMenu jMenu9;
 private javax.swing.JMenuBar jMenuBar1;
 private javax.swing.JMenuItem jMenuItem1;
 private javax.swing.JMenuItem jMenuItem10;
 private javax.swing.JMenuItem jMenuItem11;
 private javax.swing.JMenuItem jMenuItem12;
 private javax.swing.JMenuItem jMenuItem13;
 private javax.swing.JMenuItem jMenuItem14;
 private javax.swing.JMenuItem jMenuItem16;
 private javax.swing.JMenuItem jMenuItem17;
 private javax.swing.JMenuItem jMenuItem18;
 private javax.swing.JMenuItem jMenuItem19;
 private javax.swing.JMenuItem jMenuItem2;
 private javax.swing.JMenuItem jMenuItem20;
 private javax.swing.JMenuItem jMenuItem22;
 private javax.swing.JMenuItem jMenuItem23;
 private javax.swing.JMenuItem jMenuItem24;
 private javax.swing.JMenuItem jMenuItem25;
 private javax.swing.JMenuItem jMenuItem26;
 private javax.swing.JMenuItem jMenuItem27;
 private javax.swing.JMenuItem jMenuItem28;
 private javax.swing.JMenuItem jMenuItem29;
 private javax.swing.JMenuItem jMenuItem3;
 private javax.swing.JMenuItem jMenuItem30;
 private javax.swing.JMenuItem jMenuItem31;
 private javax.swing.JMenuItem jMenuItem32;
 private javax.swing.JMenuItem jMenuItem33;
 private javax.swing.JMenuItem jMenuItem34;
 private javax.swing.JMenuItem jMenuItem35;
 private javax.swing.JMenuItem jMenuItem4;
 private javax.swing.JMenuItem jMenuItem5;
 private javax.swing.JMenuItem jMenuItem6;
 private javax.swing.JMenuItem jMenuItem7;
 private javax.swing.JMenuItem jMenuItem8;
 private javax.swing.JMenuItem jMenuItem9;
 private javax.swing.JScrollPane jScrollPane1;
 private javax.swing.JScrollPane jScrollPane2;
 private javax.swing.JScrollPane jScrollPane3;
 private javax.swing.JTable jTable1;
 private javax.swing.JTextField jTextField1;
 private javax.swing.JTextField jTextField2;
 private javax.swing.JTextField jTextField3;
 private javax.swing.JTextField jTextField4;
 private javax.swing.JTextArea licenseTextArea;
 private javax.swing.JTextArea licenseTextArea1;
 // End of variables declaration//GEN-END:variables
}
