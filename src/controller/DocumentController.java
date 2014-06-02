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

import database.Database;
import database.entity.Document;
import database.entity.DocumentElement;
import database.entity.DocumentType;
import database.entity.Ware;
import database.entity.WareRecord;
import database.entity.Warehouse;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

import view.MainView;
import view.SelectItem;

public class DocumentController {

 protected static MainView view = MainView.getInstance();
 protected static Database database = Database.getInstance();

 public static void list(Integer documentTypeId) {
  DefaultTableModel table = (DefaultTableModel) view.getDocumentListTable().getModel();
  table.setRowCount(0);

  List<Document> documents = database.getDocumentsByDocumentTypeId(documentTypeId);
  for (Document document : documents) {
   table.addRow(new Object[]{
    document.getId().toString(),
    document.getNumber(),
    document.getWarehouse().getName(),
    new DecimalFormat("#0.00zł").format((double) Math.round(document.getTotalValue() * 100) / 100),
    new DecimalFormat("#0.00zł").format((double) Math.round(document.getTotalValueIncludingTax() * 100) / 100),});
  }

  view.hideAllViews();
  DocumentType documentType = database.getDocumentTypeById(documentTypeId);
  view.getHeader().setText(documentType.getName() + " (" + documentType.getSymbol() + ") - lista");
  view.setIcon(MainView.DOCUMENT_ICON);

  view.getDocumentListView().setVisible(true);
 }

 public static void addDocument(final Integer documentTypeId) {
  view.hideAllViews();
  DocumentType documentType = database.getDocumentTypeById(documentTypeId);
  view.getHeader().setText(documentType.getName() + " (" + documentType.getSymbol() + ") - dodaj");
  view.setIcon(MainView.DOCUMENT_ICON);

  Document document;
  List<Document> documentList = database.getDocumentsByDocumentTypeId(documentTypeId, 1);
  if (documentList.size() < 1) {

   for (ActionListener listener : view.getDocumentPreFormButton().getActionListeners()) {
    view.getDocumentPreFormButton().removeActionListener(listener);
   }

   view.getDocumentPreFormButton().addActionListener(new java.awt.event.ActionListener() {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent evt) {
     try {
      DocumentController.addDocumentPreFormAction(documentTypeId);
     } catch (Exception ex) {
      Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
   });

   List<Warehouse> warehouses = database.getWarehouses();
   view.getDocumentPreFormWarehouseSelect().removeAllItems();
   for (Warehouse warehouse : warehouses) {
    view.getDocumentPreFormWarehouseSelect().addItem(new SelectItem(warehouse.getId(), warehouse.getName()));
   }

   view.getDocumentPreFormButton().setText("Wybierz");
   view.getDocumentPreFormView().setVisible(true);
  } else {
   document = documentList.get(0);

   DefaultTableModel table = (DefaultTableModel) view.getDocumentElementListTable().getModel();
   table.setRowCount(0);
   List<DocumentElement> documentElements = database.getDocumentElementsByDocumentId(document.getId());
   for (DocumentElement documentElement : documentElements) {
    table.addRow(new Object[]{
     documentElement.getId().toString(),
     documentElement.getWareName(),
     new DecimalFormat("#0.00zł").format((double) Math.round(documentElement.getTotalValue() * 100) / 100),
     new DecimalFormat("#0.00%").format((double) Math.round(documentElement.getTax() * 100) / 10000),
     new DecimalFormat("#0.00zł").format((double) Math.round(documentElement.getTotalValueIncludingTax() * 100) / 100),
     documentElement.getAmount().toString()
    });
   }

   for (ActionListener listener : view.getDocumentFormButton().getActionListeners()) {
    view.getDocumentFormButton().removeActionListener(listener);
   }
   for (ActionListener listener : view.getDocumentElementAddFormButton().getActionListeners()) {
    view.getDocumentElementAddFormButton().removeActionListener(listener);
   }
   for (ActionListener listener : view.getDocumentElementDeleteFormButton().getActionListeners()) {
    view.getDocumentElementDeleteFormButton().removeActionListener(listener);
   }
   for (ActionListener listener : view.getSelectWareFormReturnButton().getActionListeners()) {
    view.getSelectWareFormReturnButton().removeActionListener(listener);
   }

   view.getDocumentFormButton().addActionListener(new java.awt.event.ActionListener() {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent evt) {
     try {
      if (documentTypeId == DocumentType.PZ_ID) {
       DocumentController.addPZAction();
      } else if (documentTypeId == DocumentType.PW_ID) {
       DocumentController.addPWAction();
      } else if (documentTypeId == DocumentType.WZ_ID) {
       view.showErrorPopup("Funkcjonalność niedostępna");
      } else if (documentTypeId == DocumentType.RW_ID) {
       view.showErrorPopup("Funkcjonalność niedostępna");
      }
     } catch (Exception ex) {
      Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
   });

   view.getDocumentElementAddFormButton().addActionListener(new java.awt.event.ActionListener() {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent evt) {
     try {
      DocumentController.addDocumentElementForm(documentTypeId);
     } catch (Exception ex) {
      Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
   });

   view.getDocumentElementDeleteFormButton().addActionListener(new java.awt.event.ActionListener() {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent evt) {
     try {
      DocumentController.deleteDocumentElementForm(documentTypeId);
     } catch (Exception ex) {
      Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
   });

   view.getSelectWareFormReturnButton().addActionListener(new java.awt.event.ActionListener() {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent evt) {
     try {
      DocumentController.addDocument(documentTypeId);
     } catch (Exception ex) {
      Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
   });

   view.getDocumentFormWarehouseInput().setText(document.getWarehouse().getName());
   view.getDocumentFormButton().setText("Dodaj");
   view.getDocumentFormView().setVisible(true);
  }
 }

 public static void addDocumentPreFormAction(Integer documentTypeId) {
  Object selectedWarehouse = view.getDocumentPreFormWarehouseSelect().getSelectedItem();
  if (selectedWarehouse != null) {
   Integer id = ((SelectItem) selectedWarehouse).getId();
   Warehouse warehouse = database.getWarehouseById(id);

   DocumentType documentType = database.getDocumentTypeById(documentTypeId);
   Document document = new Document();
   document.setDocumentType(documentType);
   document.setBuffer(1);
   document.setWarehouse(warehouse);
   database.saveDocument(document);

   DocumentController.addDocument(documentTypeId);
  } else {
   view.showErrorPopup("Nie wybrano magazynu");
  }
 }

 public static void addDocumentElementForm(final Integer documentTypeId) {
  view.hideAllViews();
  DocumentType documentType = database.getDocumentTypeById(documentTypeId);
  view.getHeader().setText("Pozycja dokumentu " + documentType.getSymbol() + " - dodaj");
  view.setIcon(MainView.DOCUMENT_ICON);

  Document document = database.getDocumentsByDocumentTypeId(documentTypeId, 1).get(0);
  List<Ware> wares = database.getUniqueWaresForDocumentId(document.getId());
  view.getSelectWareFormSelect().removeAllItems();
  for (Ware ware : wares) {
   view.getSelectWareFormSelect().addItem(new SelectItem(ware.getId(), ware.getName()));
  }

  for (ActionListener listener : view.getSelectWareFormButton().getActionListeners()) {
   view.getSelectWareFormButton().removeActionListener(listener);
  }
  view.getSelectWareFormButton().addActionListener(new java.awt.event.ActionListener() {
   @Override
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    DocumentController.addDocumentElementAction(documentTypeId);
   }
  });

  view.getSelectWareFormAmountInput().setText("");
  view.getSelectWareFormHeader().setText("Wybierz towar");
  view.getSelectWareFormButton().setText("Dodaj");
  view.getSelectWareFormView().setVisible(true);
 }

 public static void addDocumentElementAction(Integer documentTypeId) {
  Object selectedWare = view.getSelectWareFormSelect().getSelectedItem();
  if (selectedWare != null) {
   Integer id = ((SelectItem) selectedWare).getId();

   Ware ware = database.getWareById(id);
   if (ware == null) {
    view.showErrorPopup("Wybrany towar nie istnieje");
   } else {
    Document document = database.getDocumentsByDocumentTypeId(documentTypeId, 1).get(0);
    DocumentElement documentElement = new DocumentElement();
    documentElement.setDocument(document);
    documentElement.setWareName(ware.getName());
    documentElement.setValue(ware.getValue());
    documentElement.setTax(ware.getTax());
    String amount = view.getSelectWareFormAmountInput().getText().trim();
    if (amount.equals("") || !amount.matches("^[0-9]*$")) {
     documentElement.setAmount(0);
    } else {
     documentElement.setAmount(Integer.parseInt(amount));
    }

    if (documentElement.validate()) {
     database.saveDocumentElement(documentElement);
     DocumentController.addDocument(documentTypeId);
    } else {
     view.showErrorPopup(documentElement.getValidationErrors());
    }
   }
  } else {
   view.showErrorPopup("Nie wybrano towaru");
  }
 }

 public static void deleteDocumentElementForm(final Integer documentTypeId) {
  view.hideAllViews();
  DocumentType documentType = database.getDocumentTypeById(documentTypeId);
  view.getHeader().setText("Pozycja dokumentu " + documentType.getSymbol() + " - usuń");
  view.setIcon(MainView.DOCUMENT_ICON);

  Document document = database.getDocumentsByDocumentTypeId(documentTypeId, 1).get(0);
  List<DocumentElement> documentElements = database.getDocumentElementsByDocumentId(document.getId());
  view.getSelectFormSelect().removeAllItems();
  for (DocumentElement documentElement : documentElements) {
   view.getSelectFormSelect().addItem(new SelectItem(documentElement.getId(), documentElement.getWareName()));
  }

  for (ActionListener listener : view.getSelectFormButton().getActionListeners()) {
   view.getSelectFormButton().removeActionListener(listener);
  }
  view.getSelectFormButton().addActionListener(new java.awt.event.ActionListener() {
   @Override
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    DocumentController.deleteDocumentElementAction(documentTypeId);
   }
  });

  for (ActionListener listener : view.getSelectFormReturnButton().getActionListeners()) {
   view.getSelectFormReturnButton().removeActionListener(listener);
  }
  view.getSelectFormReturnButton().addActionListener(new java.awt.event.ActionListener() {
   @Override
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    DocumentController.addDocument(documentTypeId);
   }
  });

  view.getSelectFormHeader().setText("Wybierz pozycję dokumentu");
  view.getSelectFormButton().setText("Usuń");
  view.getSelectFormView().setVisible(true);
  view.getSelectFormReturnButton().setVisible(true);
 }

 public static void deleteDocumentElementAction(Integer documentTypeId) {
  Object selectedDocumentElement = view.getSelectFormSelect().getSelectedItem();
  if (selectedDocumentElement != null) {
   Integer id = ((SelectItem) selectedDocumentElement).getId();

   DocumentElement documentElement = database.getDocumentElementById(id);
   if (documentElement == null) {
    view.showErrorPopup("Wybrana pozycja dokumentu nie istnieje");
   } else {
    database.deleteDocumentElementById(id);
   }

   DocumentController.addDocument(documentTypeId);
  } else {
   view.showErrorPopup("Nie wybrano pozycji dokumentu");
  }
 }

 public static void viewInformationsForm(final Integer documentTypeId) {
  view.hideAllViews();
  DocumentType documentType = database.getDocumentTypeById(documentTypeId);
  view.getHeader().setText(documentType.getName() + " (" + documentType.getSymbol() + ") - informacje");
  view.setIcon(MainView.DOCUMENT_ICON);

  List<Document> documents = database.getDocumentsByDocumentTypeId(documentTypeId);
  view.getSelectFormSelect().removeAllItems();
  for (Document document : documents) {
   view.getSelectFormSelect().addItem(new SelectItem(document.getId(), document.getNumber()));
  }

  for (ActionListener listener : view.getSelectFormButton().getActionListeners()) {
   view.getSelectFormButton().removeActionListener(listener);
  }
  view.getSelectFormButton().addActionListener(new java.awt.event.ActionListener() {
   @Override
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    DocumentController.viewInformationsAction(documentTypeId);
   }
  });

  view.getSelectFormHeader().setText("Wybierz dokument");
  view.getSelectFormButton().setText("Pokaż informacje");
  view.getSelectFormView().setVisible(true);
 }

 public static void viewInformationsAction(Integer documentTypeId) {
  Object selectedDocument = view.getSelectFormSelect().getSelectedItem();
  if (selectedDocument != null) {
   Integer id = ((SelectItem) selectedDocument).getId();

   Document document = database.getDocumentById(id);
   if (document == null) {
    view.showErrorPopup("Wybrany dokument nie istnieje");
   } else {
    view.hideAllViews();
    DocumentType documentType = database.getDocumentTypeById(documentTypeId);
    view.getHeader().setText(documentType.getName() + " (" + documentType.getSymbol() + ") - informacje");
    view.setIcon(MainView.DOCUMENT_ICON);

    view.getDocumentViewInformationsNumberInput().setText(document.getNumber());
    view.getDocumentViewInformationsWarehouseInput().setText(document.getWarehouse().getName());
    view.getDocumentViewInformationsNettoInput().setText(new DecimalFormat("#0.00zł").format((double) Math.round(document.getTotalValue() * 100) / 100));
    view.getDocumentViewInformationsBruttoInput().setText(new DecimalFormat("#0.00zł").format((double) Math.round(document.getTotalValueIncludingTax() * 100) / 100));

    DefaultTableModel table = (DefaultTableModel) view.getDocumentViewInformationsTable().getModel();
    table.setRowCount(0);
    List<DocumentElement> documentElements = database.getDocumentElementsByDocumentId(document.getId());
    for (DocumentElement documentElement : documentElements) {
     table.addRow(new Object[]{
      documentElement.getId().toString(),
      documentElement.getWareName(),
      new DecimalFormat("#0.00zł").format((double) Math.round(documentElement.getTotalValue() * 100) / 100),
      new DecimalFormat("#0.00%").format((double) Math.round(documentElement.getTax() * 100) / 10000),
      new DecimalFormat("#0.00zł").format((double) Math.round(documentElement.getTotalValueIncludingTax() * 100) / 100),
      documentElement.getAmount().toString()
     });
    }

    view.getDocumentViewInformationsView().setVisible(true);
   }
  } else {
   view.showErrorPopup("Nie wybrano dokumentu");
  }
 }

 public static void addPZAction() {
  Calendar now = Calendar.getInstance();
  Integer yearInteger = now.get(Calendar.YEAR);
  String year = String.valueOf(yearInteger);

  Document document = database.getDocumentsByDocumentTypeId(DocumentType.PZ_ID, 1).get(0);
  document.setBuffer(0);
  String documentNumber = document.getDocumentType().getSymbol() + "/" + document.getId() + "/" + document.getWarehouse().getId() + "/" + year;
  document.setNumber(documentNumber);

  List<DocumentElement> documentElements = database.getDocumentElementsByDocumentId(document.getId());
  WareRecord wareRecord = new WareRecord();
  wareRecord.setWarehouse(document.getWarehouse());
  for (DocumentElement documentElement : documentElements) {
   wareRecord.setWareName(documentElement.getWareName());
   wareRecord.setValue(documentElement.getValue());
   wareRecord.setTax(documentElement.getTax());
   wareRecord.setAmount(documentElement.getAmount());
   database.saveWareRecord(wareRecord);
  }

  database.saveDocument(document);

  DocumentController.list(DocumentType.PZ_ID);
 }

 public static void addPWAction() {
  Calendar now = Calendar.getInstance();
  Integer yearInteger = now.get(Calendar.YEAR);
  String year = String.valueOf(yearInteger);

  Document document = database.getDocumentsByDocumentTypeId(DocumentType.PW_ID, 1).get(0);
  document.setBuffer(0);
  String documentNumber = document.getDocumentType().getSymbol() + "/" + document.getId() + "/" + document.getWarehouse().getId() + "/" + year;
  document.setNumber(documentNumber);

  List<DocumentElement> documentElements = database.getDocumentElementsByDocumentId(document.getId());
  WareRecord wareRecord = new WareRecord();
  wareRecord.setWarehouse(document.getWarehouse());
  for (DocumentElement documentElement : documentElements) {
   wareRecord.setWareName(documentElement.getWareName());
   wareRecord.setValue(documentElement.getValue());
   wareRecord.setTax(documentElement.getTax());
   wareRecord.setAmount(documentElement.getAmount());
   database.saveWareRecord(wareRecord);
  }

  database.saveDocument(document);

  DocumentController.list(DocumentType.PW_ID);
 }

}
