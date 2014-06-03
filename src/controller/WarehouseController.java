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
 * LIABILITY, WHETHER IN AN ACTION OF ONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package controller;

import java.util.List;
import database.Database;
import database.entity.Document;
import database.entity.WareRecord;
import database.entity.Warehouse;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import view.MainView;
import view.SelectItem;
import javax.swing.table.DefaultTableModel;

public class WarehouseController {

 protected static MainView view = MainView.getInstance();
 protected static Database database = Database.getInstance();

 public static void list() {
  DefaultTableModel table = (DefaultTableModel) view.getWarehouseListTable().getModel();
  table.setRowCount(0);

  List<Warehouse> warehouses = database.getWarehouses();
  for (Warehouse warehouse : warehouses) {
   table.addRow(new Object[]{warehouse.getId().toString(), warehouse.getName(), new DecimalFormat("#0.00zł").format((double) Math.round(warehouse.calculateValue() * 100) / 100)});
  }

  view.hideAllViews();
  view.getHeader().setText("Magazyn - lista");
  view.setIcon(MainView.BOX_ICON);

  view.getWarehouseListView().setVisible(true);
 }

 public static void addForm() {
  view.hideAllViews();
  view.getHeader().setText("Magazyn - dodaj");
  view.setIcon(MainView.BOX_ICON);

  for (ActionListener listener : view.getWarehouseFormButton().getActionListeners()) {
   view.getWarehouseFormButton().removeActionListener(listener);
  }
  view.getWarehouseFormButton().addActionListener(new java.awt.event.ActionListener() {
   @Override
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    try {
     WarehouseController.addAction();
    } catch (Exception ex) {
     Logger.getLogger(WarehouseController.class.getName()).log(Level.SEVERE, null, ex);
    }
   }
  });

  view.getWarehouseFormNameInput().setText("");
  view.getWarehouseFormButton().setText("Dodaj");
  view.getWarehouseFormView().setVisible(true);
 }

 public static void addAction() throws java.lang.Exception {
  Warehouse newWarehouse = new Warehouse();
  newWarehouse.setName(view.getWarehouseFormNameInput().getText());

  if (newWarehouse.validate()) {
   database.saveWarehouse(newWarehouse);
   WarehouseController.list();
  } else {
   view.showErrorPopup(newWarehouse.getValidationErrors());
  }
 }

 public static void deleteForm() {
  view.hideAllViews();
  view.getHeader().setText("Magazyn - usuń");
  view.setIcon(MainView.BOX_ICON);

  List<Warehouse> warehouses = database.getWarehouses();
  view.getSelectFormSelect().removeAllItems();
  for (Warehouse warehouse : warehouses) {
   view.getSelectFormSelect().addItem(new SelectItem(warehouse.getId(), warehouse.getName()));
  }

  for (ActionListener listener : view.getSelectFormButton().getActionListeners()) {
   view.getSelectFormButton().removeActionListener(listener);
  }
  view.getSelectFormButton().addActionListener(new java.awt.event.ActionListener() {
   @Override
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    WarehouseController.deleteAction();
   }
  });

  view.getSelectFormHeader().setText("Wybierz magazyn");
  view.getSelectFormButton().setText("Usuń");
  view.getSelectFormView().setVisible(true);
 }

 public static void deleteAction() {
  Object selectedWarehouse = view.getSelectFormSelect().getSelectedItem();
  if (selectedWarehouse != null) {
   Integer id = ((SelectItem) selectedWarehouse).getId();

   Warehouse warehouse = database.getWarehouseById(id);
   if (warehouse == null) {
    view.showErrorPopup("Wybrany magazyn nie istnieje");
   } else {
    List<Document> documents = database.getDocumentsByWarehouseId(warehouse.getId());
    if (documents.size() > 0) {
     view.showErrorPopup("Magazyn nie może zostać usunięty,\nponieważ został już powiązany z dokumentami");
    } else {
     database.deleteWarehouseById(id);
     WarehouseController.list();
    }
   }
  } else {
   view.showErrorPopup("Nie wybrano magazynu");
  }
 }

 public static void editSelectForm() {
  view.hideAllViews();
  view.getHeader().setText("Magazyn - edytuj");
  view.setIcon(MainView.BOX_ICON);

  List<Warehouse> warehouses = database.getWarehouses();
  view.getSelectFormSelect().removeAllItems();
  for (Warehouse warehouse : warehouses) {
   view.getSelectFormSelect().addItem(new SelectItem(warehouse.getId(), warehouse.getName()));
  }

  for (ActionListener listener : view.getSelectFormButton().getActionListeners()) {
   view.getSelectFormButton().removeActionListener(listener);
  }
  view.getSelectFormButton().addActionListener(new java.awt.event.ActionListener() {
   @Override
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    WarehouseController.editForm();
   }
  });

  view.getSelectFormHeader().setText("Wybierz magazyn");
  view.getSelectFormButton().setText("Edytuj");
  view.getSelectFormView().setVisible(true);
 }

 public static void editForm() {
  Object selectedWarehouse = view.getSelectFormSelect().getSelectedItem();
  if (selectedWarehouse != null) {
   Integer id = ((SelectItem) selectedWarehouse).getId();

   Warehouse warehouse = database.getWarehouseById(id);
   if (warehouse == null) {
    view.showErrorPopup("Wybrany magazyn nie istnieje");
   } else {
    List<Document> documents = database.getDocumentsByWarehouseId(warehouse.getId());
    if (documents.size() > 0) {
     view.showErrorPopup("Wybrany magazyn nie może zostać edytowany,\nponieważ został już powiązany z dokumentami");
    } else {
     view.hideAllViews();
     view.getHeader().setText("Magazyn - edytuj");
     view.setIcon(MainView.BOX_ICON);

     for (ActionListener listener : view.getWarehouseFormButton().getActionListeners()) {
      view.getWarehouseFormButton().removeActionListener(listener);
     }
     view.getWarehouseFormButton().addActionListener(new java.awt.event.ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent evt) {
       WarehouseController.editAction();
      }
     });

     view.getHiddenWarehouseId().setText(warehouse.getId().toString());
     view.getWarehouseFormNameInput().setText(warehouse.getName());
     view.getWarehouseFormButton().setText("Edytuj");
     view.getWarehouseFormView().setVisible(true);
    }
   }
  } else {
   view.showErrorPopup("Nie wybrano magazynu");
  }
 }

 public static void editAction() {
  Warehouse editedWarehouse = new Warehouse();
  editedWarehouse.setId(Integer.parseInt(view.getHiddenWarehouseId().getText()));
  editedWarehouse.setName(view.getWarehouseFormNameInput().getText());

  editedWarehouse.setName(view.getWarehouseFormNameInput().getText());

  if (editedWarehouse.validate()) {
   database.saveWarehouse(editedWarehouse);
   WarehouseController.list();
  } else {
   view.showErrorPopup(editedWarehouse.getValidationErrors());
  }
 }

 public static void viewInformationsForm() {
  view.hideAllViews();
  view.getHeader().setText("Magazyn - informacje");
  view.setIcon(MainView.BOX_ICON);

  List<Warehouse> warehouses = database.getWarehouses();
  view.getSelectFormSelect().removeAllItems();
  for (Warehouse warehouse : warehouses) {
   view.getSelectFormSelect().addItem(new SelectItem(warehouse.getId(), warehouse.getName()));
  }

  for (ActionListener listener : view.getSelectFormButton().getActionListeners()) {
   view.getSelectFormButton().removeActionListener(listener);
  }
  view.getSelectFormButton().addActionListener(new java.awt.event.ActionListener() {
   @Override
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    WarehouseController.viewInformationsAction();
   }
  });

  view.getSelectFormHeader().setText("Wybierz magazyn");
  view.getSelectFormButton().setText("Pokaż informacje");
  view.getSelectFormView().setVisible(true);
 }

 public static void viewInformationsAction() {
  Object selectedWarehouse = view.getSelectFormSelect().getSelectedItem();
  if (selectedWarehouse != null) {
   Integer id = ((SelectItem) selectedWarehouse).getId();

   Warehouse warehouse = database.getWarehouseById(id);
   if (warehouse == null) {
    view.showErrorPopup("Wybrany magazyn nie istnieje");
   } else {
    view.hideAllViews();
    view.getHeader().setText("Magazyn - informacje");
    view.setIcon(MainView.BOX_ICON);

    DefaultTableModel table = (DefaultTableModel) view.getWareRecordListTable().getModel();
    table.setRowCount(0);

    List<WareRecord> wareRecords = database.getWareRecordsByWarehouseIdOnlyExisting(warehouse.getId());
    for (WareRecord wareRecord : wareRecords) {
     table.addRow(new Object[]{
      wareRecord.getId().toString(),
      wareRecord.getWareName(),
      new DecimalFormat("#0.00zł").format((double) Math.round(wareRecord.getValue() * 100) / 100),
      new DecimalFormat("#0.00%").format((double) Math.round(wareRecord.getTax() * 100) / 10000),
      new DecimalFormat("#0.00zł").format((double) Math.round(wareRecord.getValueIncludingTax() * 100) / 100),
      wareRecord.getAmount().toString()
     });
    }

    view.getWarehouseViewNameInput().setText(warehouse.getName());
    view.getWarehouseViewValueInput().setText(new DecimalFormat("#0.00zł").format((double) Math.round(warehouse.calculateValue() * 100) / 100));

    view.getWarehouseViewInformationsView().setVisible(true);
   }
  } else {
   view.showErrorPopup("Nie wybrano magazynu");
  }

 }

}
