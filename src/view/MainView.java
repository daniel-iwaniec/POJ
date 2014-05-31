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
 public static final String WARE_ICON = "ware";
 public static final String HOME_ICON = "home";
 public static final String LICENSE_ICON = "license";
 public static final String DOCUMENT_ICON = "document";

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
  this.hiddenWarehouseId.setVisible(false);
  this.hiddenWareId.setVisible(false);

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

 public javax.swing.JPanel getWareListView() {
  return this.WareList;
 }

 public javax.swing.JPanel getWarehouseFormView() {
  return this.WarehouseForm;
 }

 public javax.swing.JPanel getWareFormView() {
  return this.WareForm;
 }

 public javax.swing.JPanel getWareViewInformationsView() {
  return this.WareViewInformations;
 }

 public javax.swing.JTextField getWareViewInformationsNameInput() {
  return this.jTextField6;
 }

 public javax.swing.JFormattedTextField getWareViewInformationsValueInput() {
  return this.jFormattedTextField3;
 }

 public javax.swing.JFormattedTextField getWareViewInformationsTaxInput() {
  return this.jFormattedTextField4;
 }

 public javax.swing.JFormattedTextField getWareViewInformationsValueWithTaxInput() {
  return this.jFormattedTextField5;
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

 public javax.swing.JPanel getDocumentListView() {
  return this.DocumentList;
 }

 public final void hideAllViews() {
  this.WarehouseList.setVisible(false);
  this.WarehouseForm.setVisible(false);
  this.Index.setVisible(false);
  this.License.setVisible(false);
  this.SelectForm.setVisible(false);
  this.WarehouseViewInformations.setVisible(false);
  this.ErrorPopup.setVisible(false);
  this.WareList.setVisible(false);
  this.WareForm.setVisible(false);
  this.WareViewInformations.setVisible(false);
  this.DocumentList.setVisible(false);
 }

 public javax.swing.JTable getWarehouseListTable() {
  return this.jTable1;
 }

 public javax.swing.JTable getWareListTable() {
  return this.jTable2;
 }

 public javax.swing.JTable getDocumentListTable() {
  return this.jTable3;
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

 public javax.swing.JButton getWareFormButton() {
  return this.jButton4;
 }

 public javax.swing.JTextField getWareFormNameInput() {
  return jTextField5;
 }

 public javax.swing.JTextField getWareFormValueInput() {
  return jFormattedTextField1;
 }

 public javax.swing.JTextField getWareFormTaxInput() {
  return jFormattedTextField2;
 }

 public javax.swing.JTextField getWareFormHiddenIdInput() {
  return hiddenWareId;
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
  jLabel27 = new javax.swing.JLabel();
  jLabel28 = new javax.swing.JLabel();
  jLabel29 = new javax.swing.JLabel();
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
  WareList = new javax.swing.JPanel();
  jScrollPane4 = new javax.swing.JScrollPane();
  jTable2 = new javax.swing.JTable();
  WareForm = new javax.swing.JPanel();
  jButton4 = new javax.swing.JButton();
  jTextField5 = new javax.swing.JTextField();
  jLabel15 = new javax.swing.JLabel();
  hiddenWareId = new javax.swing.JTextField();
  jLabel16 = new javax.swing.JLabel();
  jLabel21 = new javax.swing.JLabel();
  jFormattedTextField1 = new javax.swing.JFormattedTextField();
  jFormattedTextField2 = new javax.swing.JFormattedTextField();
  WareViewInformations = new javax.swing.JPanel();
  jTextField6 = new javax.swing.JTextField();
  jLabel22 = new javax.swing.JLabel();
  jLabel24 = new javax.swing.JLabel();
  jLabel25 = new javax.swing.JLabel();
  jFormattedTextField3 = new javax.swing.JFormattedTextField();
  jFormattedTextField4 = new javax.swing.JFormattedTextField();
  jLabel26 = new javax.swing.JLabel();
  jFormattedTextField5 = new javax.swing.JFormattedTextField();
  DocumentList = new javax.swing.JPanel();
  jScrollPane5 = new javax.swing.JScrollPane();
  jTable3 = new javax.swing.JTable();
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
  jMenu6 = new javax.swing.JMenu();
  jMenuItem11 = new javax.swing.JMenuItem();
  jMenuItem13 = new javax.swing.JMenuItem();
  jMenuItem22 = new javax.swing.JMenuItem();
  jMenu7 = new javax.swing.JMenu();
  jMenuItem25 = new javax.swing.JMenuItem();
  jMenuItem26 = new javax.swing.JMenuItem();
  jMenuItem27 = new javax.swing.JMenuItem();
  jMenu8 = new javax.swing.JMenu();
  jMenuItem30 = new javax.swing.JMenuItem();
  jMenuItem31 = new javax.swing.JMenuItem();
  jMenuItem32 = new javax.swing.JMenuItem();
  jMenu9 = new javax.swing.JMenu();
  jMenuItem15 = new javax.swing.JMenuItem();
  jMenuItem35 = new javax.swing.JMenuItem();
  jMenu10 = new javax.swing.JMenu();
  jMenuItem36 = new javax.swing.JMenuItem();

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
   jTable1.getColumnModel().getColumn(2).setHeaderValue("Wartość");
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
    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
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

  jLabel27.setFont(new java.awt.Font("Myriad Pro", 0, 16)); // NOI18N
  jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
  jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/bullet.png"))); // NOI18N
  jLabel27.setText("Obsługę towarów.");

  jLabel28.setFont(new java.awt.Font("Myriad Pro", 0, 16)); // NOI18N
  jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
  jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/bullet.png"))); // NOI18N
  jLabel28.setText("Obsługę dokumentów PZ, WZ, PW, RW.");

  jLabel29.setFont(new java.awt.Font("Myriad Pro", 0, 16)); // NOI18N
  jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
  jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/bullet.png"))); // NOI18N
  jLabel29.setText("Statystyki magazynów.");

  javax.swing.GroupLayout IndexLayout = new javax.swing.GroupLayout(Index);
  Index.setLayout(IndexLayout);
  IndexLayout.setHorizontalGroup(
   IndexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
   .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
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
     .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
     .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
     .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
     .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jLabel27)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jLabel28)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jLabel29)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 286, Short.MAX_VALUE)
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

  WareList.setBackground(new java.awt.Color(255, 255, 255));

  jScrollPane4.setPreferredSize(new java.awt.Dimension(452, 427));

  jTable2.setFont(new java.awt.Font("Myriad Pro", 0, 18)); // NOI18N
  jTable2.setModel(new javax.swing.table.DefaultTableModel(
   new Object [][] {

   },
   new String [] {
    "ID", "Nazwa", "Netto", "Podatek", "Brutto"
   }
  ) {
   Class[] types = new Class [] {
    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
   };
   boolean[] canEdit = new boolean [] {
    false, false, false, false, false
   };

   public Class getColumnClass(int columnIndex) {
    return types [columnIndex];
   }

   public boolean isCellEditable(int rowIndex, int columnIndex) {
    return canEdit [columnIndex];
   }
  });
  jTable2.setColumnSelectionAllowed(true);
  jTable2.setFillsViewportHeight(true);
  jTable2.setGridColor(new java.awt.Color(204, 204, 204));
  jTable2.setRowHeight(36);
  jTable2.setSelectionBackground(new java.awt.Color(204, 255, 255));
  jTable2.getTableHeader().setReorderingAllowed(false);
  jScrollPane4.setViewportView(jTable2);
  jTable2.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
  if (jTable2.getColumnModel().getColumnCount() > 0) {
   jTable2.getColumnModel().getColumn(0).setResizable(false);
   jTable2.getColumnModel().getColumn(1).setResizable(false);
   jTable2.getColumnModel().getColumn(2).setResizable(false);
   jTable2.getColumnModel().getColumn(3).setResizable(false);
   jTable2.getColumnModel().getColumn(4).setResizable(false);
  }

  javax.swing.GroupLayout WareListLayout = new javax.swing.GroupLayout(WareList);
  WareList.setLayout(WareListLayout);
  WareListLayout.setHorizontalGroup(
   WareListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
  );
  WareListLayout.setVerticalGroup(
   WareListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
  );

  WareForm.setBackground(new java.awt.Color(255, 255, 255));
  WareForm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
  WareForm.setPreferredSize(new java.awt.Dimension(452, 427));

  jButton4.setFont(new java.awt.Font("Myriad Pro", 0, 18)); // NOI18N
  jButton4.setText("Dodaj");
  jButton4.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jButton4ActionPerformed(evt);
   }
  });

  jTextField5.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N

  jLabel15.setFont(new java.awt.Font("Myriad Pro", 0, 18)); // NOI18N
  jLabel15.setLabelFor(jTextField1);
  jLabel15.setText("Nazwa towaru");

  hiddenWareId.setText("Hidden ID");

  jLabel16.setFont(new java.awt.Font("Myriad Pro", 0, 18)); // NOI18N
  jLabel16.setLabelFor(jTextField1);
  jLabel16.setText("Cena netto towaru");

  jLabel21.setFont(new java.awt.Font("Myriad Pro", 0, 18)); // NOI18N
  jLabel21.setLabelFor(jTextField1);
  jLabel21.setText("Procentowa wysokość podatku");

  jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
  jFormattedTextField1.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jFormattedTextField1ActionPerformed(evt);
   }
  });

  jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
  jFormattedTextField2.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jFormattedTextField2.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jFormattedTextField2ActionPerformed(evt);
   }
  });

  javax.swing.GroupLayout WareFormLayout = new javax.swing.GroupLayout(WareForm);
  WareForm.setLayout(WareFormLayout);
  WareFormLayout.setHorizontalGroup(
   WareFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(WareFormLayout.createSequentialGroup()
    .addContainerGap()
    .addGroup(WareFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
     .addGroup(WareFormLayout.createSequentialGroup()
      .addGap(0, 399, Short.MAX_VALUE)
      .addComponent(jButton4))
     .addComponent(jTextField5)
     .addComponent(hiddenWareId)
     .addGroup(WareFormLayout.createSequentialGroup()
      .addGroup(WareFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
       .addComponent(jLabel15)
       .addComponent(jLabel16)
       .addComponent(jLabel21))
      .addGap(0, 0, Short.MAX_VALUE))
     .addComponent(jFormattedTextField1)
     .addComponent(jFormattedTextField2))
    .addContainerGap())
  );
  WareFormLayout.setVerticalGroup(
   WareFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WareFormLayout.createSequentialGroup()
    .addContainerGap()
    .addComponent(jLabel15)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addGap(18, 18, 18)
    .addComponent(jLabel16)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addGap(18, 18, 18)
    .addComponent(jLabel21)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 308, Short.MAX_VALUE)
    .addComponent(hiddenWareId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jButton4)
    .addContainerGap())
  );

  WareViewInformations.setBackground(new java.awt.Color(255, 255, 255));
  WareViewInformations.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
  WareViewInformations.setPreferredSize(new java.awt.Dimension(452, 427));

  jTextField6.setEditable(false);
  jTextField6.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N

  jLabel22.setFont(new java.awt.Font("Myriad Pro", 0, 18)); // NOI18N
  jLabel22.setLabelFor(jTextField1);
  jLabel22.setText("Nazwa towaru");

  jLabel24.setFont(new java.awt.Font("Myriad Pro", 0, 18)); // NOI18N
  jLabel24.setLabelFor(jTextField1);
  jLabel24.setText("Cena netto towaru");

  jLabel25.setFont(new java.awt.Font("Myriad Pro", 0, 18)); // NOI18N
  jLabel25.setLabelFor(jTextField1);
  jLabel25.setText("Procentowa wysokość podatku");

  jFormattedTextField3.setEditable(false);
  jFormattedTextField3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
  jFormattedTextField3.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jFormattedTextField3.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jFormattedTextField3ActionPerformed(evt);
   }
  });

  jFormattedTextField4.setEditable(false);
  jFormattedTextField4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
  jFormattedTextField4.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jFormattedTextField4.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jFormattedTextField4ActionPerformed(evt);
   }
  });

  jLabel26.setFont(new java.awt.Font("Myriad Pro", 0, 18)); // NOI18N
  jLabel26.setLabelFor(jTextField1);
  jLabel26.setText("Wartość brutto");

  jFormattedTextField5.setEditable(false);
  jFormattedTextField5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
  jFormattedTextField5.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jFormattedTextField5.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jFormattedTextField5ActionPerformed(evt);
   }
  });

  javax.swing.GroupLayout WareViewInformationsLayout = new javax.swing.GroupLayout(WareViewInformations);
  WareViewInformations.setLayout(WareViewInformationsLayout);
  WareViewInformationsLayout.setHorizontalGroup(
   WareViewInformationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(WareViewInformationsLayout.createSequentialGroup()
    .addContainerGap()
    .addGroup(WareViewInformationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
     .addComponent(jTextField6)
     .addComponent(jFormattedTextField3)
     .addComponent(jFormattedTextField4)
     .addGroup(WareViewInformationsLayout.createSequentialGroup()
      .addGroup(WareViewInformationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
       .addComponent(jLabel22)
       .addComponent(jLabel24)
       .addComponent(jLabel25)
       .addComponent(jLabel26))
      .addGap(0, 239, Short.MAX_VALUE))
     .addComponent(jFormattedTextField5))
    .addContainerGap())
  );
  WareViewInformationsLayout.setVerticalGroup(
   WareViewInformationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WareViewInformationsLayout.createSequentialGroup()
    .addContainerGap()
    .addComponent(jLabel22)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addGap(18, 18, 18)
    .addComponent(jLabel24)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addGap(18, 18, 18)
    .addComponent(jLabel25)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addGap(18, 18, 18)
    .addComponent(jLabel26)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jFormattedTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addContainerGap(308, Short.MAX_VALUE))
  );

  DocumentList.setBackground(new java.awt.Color(255, 255, 255));

  jScrollPane5.setPreferredSize(new java.awt.Dimension(452, 427));

  jTable3.setFont(new java.awt.Font("Myriad Pro", 0, 18)); // NOI18N
  jTable3.setModel(new javax.swing.table.DefaultTableModel(
   new Object [][] {

   },
   new String [] {
    "ID", "Numer"
   }
  ) {
   Class[] types = new Class [] {
    java.lang.String.class, java.lang.String.class
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
  jTable3.setFillsViewportHeight(true);
  jTable3.setGridColor(new java.awt.Color(204, 204, 204));
  jTable3.setRowHeight(36);
  jTable3.setSelectionBackground(new java.awt.Color(204, 255, 255));
  jTable3.getTableHeader().setReorderingAllowed(false);
  jScrollPane5.setViewportView(jTable3);
  jTable3.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
  if (jTable3.getColumnModel().getColumnCount() > 0) {
   jTable3.getColumnModel().getColumn(0).setResizable(false);
   jTable3.getColumnModel().getColumn(1).setResizable(false);
  }

  javax.swing.GroupLayout DocumentListLayout = new javax.swing.GroupLayout(DocumentList);
  DocumentList.setLayout(DocumentListLayout);
  DocumentListLayout.setHorizontalGroup(
   DocumentListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DocumentListLayout.createSequentialGroup()
    .addContainerGap()
    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
    .addContainerGap())
  );
  DocumentListLayout.setVerticalGroup(
   DocumentListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(DocumentListLayout.createSequentialGroup()
    .addContainerGap()
    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
    .addContainerGap())
  );

  javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
  jLayeredPane1.setLayout(jLayeredPane1Layout);
  jLayeredPane1Layout.setHorizontalGroup(
   jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addComponent(WarehouseList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addContainerGap()
     .addComponent(WarehouseForm, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
     .addContainerGap()))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addContainerGap()
     .addComponent(Index, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
     .addContainerGap()))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addContainerGap()
     .addComponent(License, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
     .addContainerGap()))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addContainerGap()
     .addComponent(SelectForm, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
     .addContainerGap()))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addContainerGap()
     .addComponent(WarehouseViewInformations, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
     .addContainerGap()))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addContainerGap()
     .addComponent(ErrorPopup, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
     .addContainerGap()))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addContainerGap()
     .addComponent(WareList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
     .addContainerGap()))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addContainerGap()
     .addComponent(WareForm, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
     .addContainerGap()))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addContainerGap()
     .addComponent(WareViewInformations, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
     .addGap(30, 30, 30)))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addContainerGap()
     .addComponent(DocumentList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
     .addContainerGap()))
  );
  jLayeredPane1Layout.setVerticalGroup(
   jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addComponent(WarehouseList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addGap(10, 10, 10)
     .addComponent(WarehouseForm, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
     .addContainerGap()))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addGap(10, 10, 10)
     .addComponent(Index, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
     .addContainerGap()))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addContainerGap()
     .addComponent(License, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
     .addGap(21, 21, 21)))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addContainerGap()
     .addComponent(SelectForm, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
     .addGap(21, 21, 21)))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addContainerGap()
     .addComponent(WarehouseViewInformations, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
     .addGap(21, 21, 21)))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addGap(21, 21, 21)
     .addComponent(ErrorPopup, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
     .addContainerGap()))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addGap(10, 10, 10)
     .addComponent(WareList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
     .addContainerGap()))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addContainerGap()
     .addComponent(WareForm, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
     .addGap(21, 21, 21)))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addContainerGap()
     .addComponent(WareViewInformations, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
     .addGap(132, 132, 132)))
   .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jLayeredPane1Layout.createSequentialGroup()
     .addGap(10, 10, 10)
     .addComponent(DocumentList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
  jLayeredPane1.setLayer(WareList, javax.swing.JLayeredPane.DEFAULT_LAYER);
  jLayeredPane1.setLayer(WareForm, javax.swing.JLayeredPane.DEFAULT_LAYER);
  WareForm.getAccessibleContext().setAccessibleName("WareForm");
  jLayeredPane1.setLayer(WareViewInformations, javax.swing.JLayeredPane.DEFAULT_LAYER);
  jLayeredPane1.setLayer(DocumentList, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

  jMenuItem12.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/list.png"))); // NOI18N
  jMenuItem12.setText("Lista");
  jMenuItem12.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem12ActionPerformed(evt);
   }
  });
  jMenu1.add(jMenuItem12);

  jMenuItem14.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/view.png"))); // NOI18N
  jMenuItem14.setText("Informacje");
  jMenuItem14.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem14ActionPerformed(evt);
   }
  });
  jMenu1.add(jMenuItem14);

  jMenuItem16.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/add.png"))); // NOI18N
  jMenuItem16.setText("Dodaj");
  jMenuItem16.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem16ActionPerformed(evt);
   }
  });
  jMenu1.add(jMenuItem16);

  jMenuItem17.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/edit.png"))); // NOI18N
  jMenuItem17.setText("Edytuj");
  jMenuItem17.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem17ActionPerformed(evt);
   }
  });
  jMenu1.add(jMenuItem17);

  jMenuItem18.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/exit.png"))); // NOI18N
  jMenuItem18.setText("Usuń");
  jMenuItem18.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem18ActionPerformed(evt);
   }
  });
  jMenu1.add(jMenuItem18);

  jMenuBar1.add(jMenu1);

  jMenu4.setText("Dokument");

  jMenu5.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/outside_income.png"))); // NOI18N
  jMenu5.setText("Przyjęcie zewnętrzne (PZ)");
  jMenu5.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N

  jMenuItem10.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/list.png"))); // NOI18N
  jMenuItem10.setText("Lista");
  jMenuItem10.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem10ActionPerformed(evt);
   }
  });
  jMenu5.add(jMenuItem10);

  jMenuItem9.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/view.png"))); // NOI18N
  jMenuItem9.setText("Informacje");
  jMenuItem9.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem9ActionPerformed(evt);
   }
  });
  jMenu5.add(jMenuItem9);

  jMenuItem19.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/add.png"))); // NOI18N
  jMenuItem19.setText("Dodaj");
  jMenuItem19.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem19ActionPerformed(evt);
   }
  });
  jMenu5.add(jMenuItem19);

  jMenu4.add(jMenu5);

  jMenu6.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/outside_outcome.png"))); // NOI18N
  jMenu6.setText("Wydanie zewnętrzne (WZ)");
  jMenu6.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N

  jMenuItem11.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/list.png"))); // NOI18N
  jMenuItem11.setText("Lista");
  jMenuItem11.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem11ActionPerformed(evt);
   }
  });
  jMenu6.add(jMenuItem11);

  jMenuItem13.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/view.png"))); // NOI18N
  jMenuItem13.setText("Informacje");
  jMenuItem13.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem13ActionPerformed(evt);
   }
  });
  jMenu6.add(jMenuItem13);

  jMenuItem22.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/add.png"))); // NOI18N
  jMenuItem22.setText("Dodaj");
  jMenuItem22.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem22ActionPerformed(evt);
   }
  });
  jMenu6.add(jMenuItem22);

  jMenu4.add(jMenu6);

  jMenu7.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/inside_income.png"))); // NOI18N
  jMenu7.setText("Przychód wewnętrzny (PW)");
  jMenu7.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N

  jMenuItem25.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/list.png"))); // NOI18N
  jMenuItem25.setText("Lista");
  jMenuItem25.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem25ActionPerformed(evt);
   }
  });
  jMenu7.add(jMenuItem25);

  jMenuItem26.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/view.png"))); // NOI18N
  jMenuItem26.setText("Informacje");
  jMenuItem26.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem26ActionPerformed(evt);
   }
  });
  jMenu7.add(jMenuItem26);

  jMenuItem27.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/add.png"))); // NOI18N
  jMenuItem27.setText("Dodaj");
  jMenuItem27.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem27ActionPerformed(evt);
   }
  });
  jMenu7.add(jMenuItem27);

  jMenu4.add(jMenu7);

  jMenu8.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/inside_outcome.png"))); // NOI18N
  jMenu8.setText("Rozchód wewnętrzny (RW)");
  jMenu8.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N

  jMenuItem30.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/list.png"))); // NOI18N
  jMenuItem30.setText("Lista");
  jMenuItem30.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem30ActionPerformed(evt);
   }
  });
  jMenu8.add(jMenuItem30);

  jMenuItem31.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/view.png"))); // NOI18N
  jMenuItem31.setText("Informacje");
  jMenuItem31.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem31ActionPerformed(evt);
   }
  });
  jMenu8.add(jMenuItem31);

  jMenuItem32.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/add.png"))); // NOI18N
  jMenuItem32.setText("Dodaj");
  jMenuItem32.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem32.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem32ActionPerformed(evt);
   }
  });
  jMenu8.add(jMenuItem32);

  jMenu4.add(jMenu8);

  jMenuBar1.add(jMenu4);

  jMenu9.setText("Dane");

  jMenuItem15.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/chart_bar.png"))); // NOI18N
  jMenuItem15.setText("Wartość magazynów");
  jMenuItem15.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem15ActionPerformed(evt);
   }
  });
  jMenu9.add(jMenuItem15);

  jMenuItem35.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/chart_pie.png"))); // NOI18N
  jMenuItem35.setText("Wartość towarów");
  jMenuItem35.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem35.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem35ActionPerformed(evt);
   }
  });
  jMenu9.add(jMenuItem35);

  jMenuBar1.add(jMenu9);

  jMenu10.setText("Administracja");

  jMenuItem36.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
  jMenuItem36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/icon/refresh_database.png"))); // NOI18N
  jMenuItem36.setText("Wyczyść bazę danych");
  jMenuItem36.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
  jMenuItem36.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jMenuItem36ActionPerformed(evt);
   }
  });
  jMenu10.add(jMenuItem36);

  jMenuBar1.add(jMenu10);

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

 private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
  WareController.addForm();
 }//GEN-LAST:event_jMenuItem16ActionPerformed

 private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
  WareController.deleteForm();
 }//GEN-LAST:event_jMenuItem18ActionPerformed

 private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
  WareController.list();
 }//GEN-LAST:event_jMenuItem12ActionPerformed

 private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
  // TODO add your handling code here:
 }//GEN-LAST:event_jButton4ActionPerformed

 private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
  // TODO add your handling code here:
 }//GEN-LAST:event_jFormattedTextField1ActionPerformed

 private void jFormattedTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField2ActionPerformed
  // TODO add your handling code here:
 }//GEN-LAST:event_jFormattedTextField2ActionPerformed

 private void jFormattedTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField3ActionPerformed
  // TODO add your handling code here:
 }//GEN-LAST:event_jFormattedTextField3ActionPerformed

 private void jFormattedTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField4ActionPerformed
  // TODO add your handling code here:
 }//GEN-LAST:event_jFormattedTextField4ActionPerformed

 private void jFormattedTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField5ActionPerformed
  // TODO add your handling code here:
 }//GEN-LAST:event_jFormattedTextField5ActionPerformed

 private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
  WareController.viewInformationsForm();
 }//GEN-LAST:event_jMenuItem14ActionPerformed

 private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
  WareController.editSelectForm();
 }//GEN-LAST:event_jMenuItem17ActionPerformed

 private void jMenuItem32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem32ActionPerformed
  this.showErrorPopup("Funkcjonalność niedostępna");
 }//GEN-LAST:event_jMenuItem32ActionPerformed

 private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
  this.showErrorPopup("Funkcjonalność niedostępna");
 }//GEN-LAST:event_jMenuItem26ActionPerformed

 private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
  DocumentController.listPZ();
 }//GEN-LAST:event_jMenuItem10ActionPerformed

 private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
  this.showErrorPopup("Funkcjonalność niedostępna");
 }//GEN-LAST:event_jMenuItem9ActionPerformed

 private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
  this.showErrorPopup("Funkcjonalność niedostępna");
 }//GEN-LAST:event_jMenuItem19ActionPerformed

 private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
  DocumentController.listWZ();
 }//GEN-LAST:event_jMenuItem11ActionPerformed

 private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
  this.showErrorPopup("Funkcjonalność niedostępna");
 }//GEN-LAST:event_jMenuItem13ActionPerformed

 private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
  this.showErrorPopup("Funkcjonalność niedostępna");
 }//GEN-LAST:event_jMenuItem22ActionPerformed

 private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
  DocumentController.listPW();
 }//GEN-LAST:event_jMenuItem25ActionPerformed

 private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
  this.showErrorPopup("Funkcjonalność niedostępna");
 }//GEN-LAST:event_jMenuItem27ActionPerformed

 private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
  DocumentController.listRW();
 }//GEN-LAST:event_jMenuItem30ActionPerformed

 private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed
  this.showErrorPopup("Funkcjonalność niedostępna");
 }//GEN-LAST:event_jMenuItem31ActionPerformed

 private void jMenuItem35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem35ActionPerformed
  this.showErrorPopup("Funkcjonalność niedostępna");
 }//GEN-LAST:event_jMenuItem35ActionPerformed

 private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
  this.showErrorPopup("Funkcjonalność niedostępna");
 }//GEN-LAST:event_jMenuItem15ActionPerformed

 private void jMenuItem36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem36ActionPerformed
  main.Main.clearDatabaseAction();
 }//GEN-LAST:event_jMenuItem36ActionPerformed

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
 private javax.swing.JPanel DocumentList;
 private javax.swing.JPanel ErrorPopup;
 private javax.swing.JLabel Header;
 private javax.swing.JPanel Index;
 private javax.swing.JPanel License;
 private javax.swing.JPanel SelectForm;
 private javax.swing.JComboBox SelectFormSelect;
 private javax.swing.JPanel WareForm;
 private javax.swing.JPanel WareList;
 private javax.swing.JPanel WareViewInformations;
 private javax.swing.JPanel WarehouseForm;
 private javax.swing.JPanel WarehouseList;
 private javax.swing.JPanel WarehouseViewInformations;
 private javax.swing.JTextField hiddenWareId;
 private javax.swing.JTextField hiddenWarehouseId;
 private javax.swing.JButton jButton1;
 private javax.swing.JButton jButton2;
 private javax.swing.JButton jButton3;
 private javax.swing.JButton jButton4;
 private javax.swing.JFormattedTextField jFormattedTextField1;
 private javax.swing.JFormattedTextField jFormattedTextField2;
 private javax.swing.JFormattedTextField jFormattedTextField3;
 private javax.swing.JFormattedTextField jFormattedTextField4;
 private javax.swing.JFormattedTextField jFormattedTextField5;
 private javax.swing.JLabel jLabel1;
 private javax.swing.JLabel jLabel10;
 private javax.swing.JLabel jLabel11;
 private javax.swing.JLabel jLabel12;
 private javax.swing.JLabel jLabel13;
 private javax.swing.JLabel jLabel14;
 private javax.swing.JLabel jLabel15;
 private javax.swing.JLabel jLabel16;
 private javax.swing.JLabel jLabel17;
 private javax.swing.JLabel jLabel18;
 private javax.swing.JLabel jLabel19;
 private javax.swing.JLabel jLabel2;
 private javax.swing.JLabel jLabel20;
 private javax.swing.JLabel jLabel21;
 private javax.swing.JLabel jLabel22;
 private javax.swing.JLabel jLabel23;
 private javax.swing.JLabel jLabel24;
 private javax.swing.JLabel jLabel25;
 private javax.swing.JLabel jLabel26;
 private javax.swing.JLabel jLabel27;
 private javax.swing.JLabel jLabel28;
 private javax.swing.JLabel jLabel29;
 private javax.swing.JLabel jLabel3;
 private javax.swing.JLabel jLabel4;
 private javax.swing.JLabel jLabel5;
 private javax.swing.JLabel jLabel6;
 private javax.swing.JLabel jLabel7;
 private javax.swing.JLabel jLabel8;
 private javax.swing.JLabel jLabel9;
 private javax.swing.JLayeredPane jLayeredPane1;
 private javax.swing.JMenu jMenu1;
 private javax.swing.JMenu jMenu10;
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
 private javax.swing.JMenuItem jMenuItem15;
 private javax.swing.JMenuItem jMenuItem16;
 private javax.swing.JMenuItem jMenuItem17;
 private javax.swing.JMenuItem jMenuItem18;
 private javax.swing.JMenuItem jMenuItem19;
 private javax.swing.JMenuItem jMenuItem2;
 private javax.swing.JMenuItem jMenuItem22;
 private javax.swing.JMenuItem jMenuItem25;
 private javax.swing.JMenuItem jMenuItem26;
 private javax.swing.JMenuItem jMenuItem27;
 private javax.swing.JMenuItem jMenuItem3;
 private javax.swing.JMenuItem jMenuItem30;
 private javax.swing.JMenuItem jMenuItem31;
 private javax.swing.JMenuItem jMenuItem32;
 private javax.swing.JMenuItem jMenuItem35;
 private javax.swing.JMenuItem jMenuItem36;
 private javax.swing.JMenuItem jMenuItem4;
 private javax.swing.JMenuItem jMenuItem5;
 private javax.swing.JMenuItem jMenuItem6;
 private javax.swing.JMenuItem jMenuItem7;
 private javax.swing.JMenuItem jMenuItem8;
 private javax.swing.JMenuItem jMenuItem9;
 private javax.swing.JScrollPane jScrollPane1;
 private javax.swing.JScrollPane jScrollPane2;
 private javax.swing.JScrollPane jScrollPane3;
 private javax.swing.JScrollPane jScrollPane4;
 private javax.swing.JScrollPane jScrollPane5;
 private javax.swing.JTable jTable1;
 private javax.swing.JTable jTable2;
 private javax.swing.JTable jTable3;
 private javax.swing.JTextField jTextField1;
 private javax.swing.JTextField jTextField2;
 private javax.swing.JTextField jTextField3;
 private javax.swing.JTextField jTextField4;
 private javax.swing.JTextField jTextField5;
 private javax.swing.JTextField jTextField6;
 private javax.swing.JTextArea licenseTextArea;
 private javax.swing.JTextArea licenseTextArea1;
 // End of variables declaration//GEN-END:variables
}
