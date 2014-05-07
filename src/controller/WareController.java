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
import database.entity.Ware;
import database.entity.Warehouse;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import view.MainView;
import view.SelectItem;
import javax.swing.table.DefaultTableModel;

public class WareController {

 protected static MainView view = MainView.getInstance();
 protected static Database database = Database.getInstance();

 public static void list() {
  DefaultTableModel table = (DefaultTableModel) view.getWareListTable().getModel();
  table.setRowCount(0);

  List<Ware> wares = database.getWares();
  for (Ware ware : wares) {
   table.addRow(new Object[]{
    ware.getId().toString(),
    ware.getName(),
    new DecimalFormat("#0.00zł").format((double) Math.round(ware.getValue() * 100) / 100),
    new DecimalFormat("#0.00%").format((double) Math.round(ware.getTax()) / 100),
    new DecimalFormat("#0.00zł").format((double) Math.round(ware.getValueIncludingTax() * 100) / 100)
   });
  }

  view.hideAllViews();
  view.getHeader().setText("Towar - lista");
  view.setIcon(MainView.WARE_ICON);

  view.getWareListView().setVisible(true);
 }

 public static void addForm() {
  view.hideAllViews();
  view.getHeader().setText("Towar - dodaj");
  view.setIcon(MainView.WARE_ICON);

  for (ActionListener listener : view.getWareFormButton().getActionListeners()) {
   view.getWareFormButton().removeActionListener(listener);
  }
  view.getWareFormButton().addActionListener(new java.awt.event.ActionListener() {
   @Override
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    try {
     WareController.addAction();
    } catch (Exception ex) {
     Logger.getLogger(WareController.class.getName()).log(Level.SEVERE, null, ex);
    }
   }
  });

  view.getWareFormNameInput().setText("");
  view.getWareFormValueInput().setText("0,00");
  view.getWareFormTaxInput().setText("0,00");
  view.getWareFormButton().setText("Dodaj");
  view.getWareFormView().setVisible(true);
 }

 public static void addAction() throws java.lang.Exception {
  Ware newWare = new Ware();
  newWare.setName(view.getWareFormNameInput().getText());
  newWare.setValue(Double.parseDouble(view.getWareFormValueInput().getText().replace(",", ".").trim()));
  newWare.setTax(Double.parseDouble(view.getWareFormTaxInput().getText().replace(",", ".").trim()));

  if (newWare.validate()) {
   database.saveWare(newWare);
   WareController.list();
  } else {
   view.showErrorPopup(newWare.getValidationErrors());
  }
 }

 public static void deleteForm() {
  view.hideAllViews();
  view.getHeader().setText("Towar - usuń");
  view.setIcon(MainView.WARE_ICON);

  List<Ware> wares = database.getWares();
  view.getSelectFormSelect().removeAllItems();
  for (Ware ware : wares) {
   view.getSelectFormSelect().addItem(new SelectItem(ware.getId(), ware.getName()));
  }

  for (ActionListener listener : view.getSelectFormButton().getActionListeners()) {
   view.getSelectFormButton().removeActionListener(listener);
  }
  view.getSelectFormButton().addActionListener(new java.awt.event.ActionListener() {
   @Override
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    WareController.deleteAction();
   }
  });

  view.getSelectFormHeader().setText("Wybierz towar");
  view.getSelectFormButton().setText("Usuń");
  view.getSelectFormView().setVisible(true);
 }

 public static void deleteAction() {
  Object selectedWare = view.getSelectFormSelect().getSelectedItem();
  if (selectedWare != null) {
   Integer id = ((SelectItem) selectedWare).getId();

   Ware ware = database.getWareById(id);
   if (ware == null) {
    view.showErrorPopup("Wybrany towar nie istnieje");
   } else {
    database.deleteWareById(id);
   }

   WareController.list();
  }
 }

 public static void editSelectForm() {
  view.hideAllViews();
  view.getHeader().setText("Towar - edytuj");
  view.setIcon(MainView.WARE_ICON);

  List<Ware> wares = database.getWares();
  view.getSelectFormSelect().removeAllItems();
  for (Ware ware : wares) {
   view.getSelectFormSelect().addItem(new SelectItem(ware.getId(), ware.getName()));
  }

  for (ActionListener listener : view.getSelectFormButton().getActionListeners()) {
   view.getSelectFormButton().removeActionListener(listener);
  }
  view.getSelectFormButton().addActionListener(new java.awt.event.ActionListener() {
   @Override
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    WareController.editForm();
   }
  });

  view.getSelectFormHeader().setText("Wybierz towar");
  view.getSelectFormButton().setText("Edytuj");
  view.getSelectFormView().setVisible(true);
 }

 public static void editForm() {
  Object selectedWare = view.getSelectFormSelect().getSelectedItem();
  if (selectedWare != null) {
   Integer id = ((SelectItem) selectedWare).getId();

   Ware ware = database.getWareById(id);
   if (ware == null) {
    view.showErrorPopup("Wybrany towar nie istnieje");
   } else {
    view.hideAllViews();
    view.getHeader().setText("Towar - edytuj");
    view.setIcon(MainView.WARE_ICON);

    for (ActionListener listener : view.getWareFormButton().getActionListeners()) {
     view.getWareFormButton().removeActionListener(listener);
    }
    view.getWareFormButton().addActionListener(new java.awt.event.ActionListener() {
     @Override
     public void actionPerformed(java.awt.event.ActionEvent evt) {
      WareController.editAction();
     }
    });

    view.getWareFormHiddenIdInput().setText(ware.getId().toString());

    view.getWareFormNameInput().setText(ware.getName());
    view.getWareFormValueInput().setText(new DecimalFormat("#0.00zł").format((double) Math.round(ware.getValue() * 100) / 100));
    view.getWareFormTaxInput().setText(new DecimalFormat("#0.00zł").format((double) Math.round(ware.getTax() * 100) / 100));

    view.getWareFormButton().setText("Edytuj");
    view.getWareFormView().setVisible(true);
   }
  }
 }

 public static void editAction() {
  Ware editedWare = new Ware();
  editedWare.setId(Integer.parseInt(view.getWareFormHiddenIdInput().getText()));
  editedWare.setName(view.getWareFormNameInput().getText());
  editedWare.setValue(Double.parseDouble(view.getWareFormValueInput().getText().replace(",", ".")));
  editedWare.setTax(Double.parseDouble(view.getWareFormTaxInput().getText().replace(",", ".")));

  if (editedWare.validate()) {
   database.saveWare(editedWare);
   WareController.list();
  } else {
   view.showErrorPopup(editedWare.getValidationErrors());
  }
 }

 public static void viewInformationsForm() {
  view.hideAllViews();
  view.getHeader().setText("Towar - informacje");
  view.setIcon(MainView.WARE_ICON);

  List<Ware> wares = database.getWares();
  view.getSelectFormSelect().removeAllItems();
  for (Ware ware : wares) {
   view.getSelectFormSelect().addItem(new SelectItem(ware.getId(), ware.getName()));
  }

  for (ActionListener listener : view.getSelectFormButton().getActionListeners()) {
   view.getSelectFormButton().removeActionListener(listener);
  }
  view.getSelectFormButton().addActionListener(new java.awt.event.ActionListener() {
   @Override
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    WareController.viewInformationsAction();
   }
  });

  view.getSelectFormHeader().setText("Wybierz towar");
  view.getSelectFormButton().setText("Pokaż informacje");
  view.getSelectFormView().setVisible(true);
 }

 public static void viewInformationsAction() {
  Object selectedWare = view.getSelectFormSelect().getSelectedItem();
  if (selectedWare != null) {
   Integer id = ((SelectItem) selectedWare).getId();

   Ware ware = database.getWareById(id);
   if (ware == null) {
    view.showErrorPopup("Wybrany towar nie istnieje");
   } else {
    view.hideAllViews();
    view.getHeader().setText("Towar - informacje");
    view.setIcon(MainView.WARE_ICON);

    view.getWareViewInformationsNameInput().setText(ware.getName());
    view.getWareViewInformationsValueInput().setText(new DecimalFormat("#0.00zł").format((double) Math.round(ware.getValue() * 100) / 100));
    view.getWareViewInformationsTaxInput().setText(new DecimalFormat("#0.00%").format((double) Math.round(ware.getTax() * 100) / 100));
    view.getWareViewInformationsValueWithTaxInput().setText(new DecimalFormat("#0.00zł").format((double) Math.round(ware.getValueIncludingTax() * 100) / 100));

    view.getWareViewInformationsView().setVisible(true);
   }
  }

 }

}
