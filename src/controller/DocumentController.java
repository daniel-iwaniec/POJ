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

 public static void addDocument(Integer documentTypeId) {
  view.hideAllViews();
  DocumentType documentType = database.getDocumentTypeById(documentTypeId);
  view.getHeader().setText(documentType.getName() + " (" + documentType.getSymbol() + ") - dodaj");
  view.setIcon(MainView.DOCUMENT_ICON);

  Document document;
  List<Document> documentList = database.getDocumentsByDocumentTypeId(documentTypeId, 1);
  if (documentList.size() < 1) {
   if (documentTypeId == DocumentType.PZ_ID || documentTypeId == DocumentType.WZ_ID) {
    for (ActionListener listener : view.getDocumentOutsidePreFormButton().getActionListeners()) {
     view.getDocumentOutsidePreFormButton().removeActionListener(listener);
    }

    view.getDocumentInsideTypeHidden().setText(documentTypeId.toString());
    view.getDocumentOutsideTypeHidden().setText(documentTypeId.toString());

    view.getDocumentOutsidePreFormButton().addActionListener(new java.awt.event.ActionListener() {
     @Override
     public void actionPerformed(java.awt.event.ActionEvent evt) {
      try {
       DocumentController.addDocumentPreFormAction();
      } catch (Exception ex) {
       Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
      }
     }
    });
   } else {
    for (ActionListener listener : view.getDocumentInsidePreFormButton().getActionListeners()) {
     view.getDocumentInsidePreFormButton().removeActionListener(listener);
    }

    view.getDocumentInsideTypeHidden().setText(documentTypeId.toString());
    view.getDocumentOutsideTypeHidden().setText(documentTypeId.toString());

    view.getDocumentInsidePreFormButton().addActionListener(new java.awt.event.ActionListener() {
     @Override
     public void actionPerformed(java.awt.event.ActionEvent evt) {
      try {
       DocumentController.addDocumentPreFormAction();
      } catch (Exception ex) {
       Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
      }
     }
    });
   }

   if (documentTypeId == DocumentType.PZ_ID || documentTypeId == DocumentType.WZ_ID) {
    List<Warehouse> warehouses = database.getWarehouses();
    view.getDocumentOutsidePreFormWarehouseSelect().removeAllItems();
    for (Warehouse warehouse : warehouses) {
     view.getDocumentOutsidePreFormWarehouseSelect().addItem(new SelectItem(warehouse.getId(), warehouse.getName()));
    }
   } else {
    List<Warehouse> warehouses = database.getWarehouses();
    view.getDocumentInsidePreFormWarehouseSelect().removeAllItems();
    for (Warehouse warehouse : warehouses) {
     view.getDocumentInsidePreFormWarehouseSelect().addItem(new SelectItem(warehouse.getId(), warehouse.getName()));
    }
   }

   if (documentTypeId == DocumentType.PZ_ID || documentTypeId == DocumentType.WZ_ID) {
    view.getDocumentOutsidePreFormButton().setText("Wybierz");
    view.getDocumentOutsidePreFormView().setVisible(true);
   } else {
    view.getDocumentInsidePreFormButton().setText("Wybierz");
    view.getDocumentInsidePreFormView().setVisible(true);
   }
  } else {
   if (documentTypeId == DocumentType.PZ_ID || documentTypeId == DocumentType.PW_ID) {
    document = documentList.get(0);
    DefaultTableModel table = (DefaultTableModel) view.getDocumentIncomeElementListTable().getModel();
    table.setRowCount(0);
    List<DocumentElement> documentElements = database.getDocumentElementsByDocumentId(document.getId());
    for (DocumentElement documentElement : documentElements) {
     table.addRow(new Object[]{
      documentElement.getId().toString(),
      documentElement.getWareName(),
      documentElement.getAmount().toString()
     });
    }
   } else {
    document = documentList.get(0);
    DefaultTableModel table = (DefaultTableModel) view.getDocumentOutcomeElementListTable().getModel();
    table.setRowCount(0);
    List<DocumentElement> documentElements = database.getDocumentElementsByDocumentId(document.getId());
    for (DocumentElement documentElement : documentElements) {
     table.addRow(new Object[]{
      documentElement.getId().toString(),
      documentElement.getWareName(),
      documentElement.getAmount().toString()
     });
    }
   }

   view.getDocumentIncomeTypeHidden().setText(documentTypeId.toString());
   view.getDocumentOutcomeTypeHidden().setText(documentTypeId.toString());

   if (documentTypeId == DocumentType.PZ_ID || documentTypeId == DocumentType.PW_ID) {
    for (ActionListener listener : view.getDocumentIncomeFormButton().getActionListeners()) {
     view.getDocumentIncomeFormButton().removeActionListener(listener);
    }
    for (ActionListener listener : view.getDocumentIncomeElementAddFormButton().getActionListeners()) {
     view.getDocumentIncomeElementAddFormButton().removeActionListener(listener);
    }
    for (ActionListener listener : view.getDocumentIncomeElementDeleteFormButton().getActionListeners()) {
     view.getDocumentIncomeElementDeleteFormButton().removeActionListener(listener);
    }
    for (ActionListener listener : view.getSelectWareFormReturnButton().getActionListeners()) {
     view.getSelectWareFormReturnButton().removeActionListener(listener);
    }

    view.getDocumentIncomeFormButton().addActionListener(new java.awt.event.ActionListener() {
     @Override
     public void actionPerformed(java.awt.event.ActionEvent evt) {
      try {
       Integer documentTypeId = Integer.parseInt(view.getDocumentIncomeTypeHidden().getText());
       if (documentTypeId == DocumentType.PZ_ID) {
        DocumentController.addPZAction();
       } else if (documentTypeId == DocumentType.PW_ID) {
        DocumentController.addPWAction();
       } else if (documentTypeId == DocumentType.WZ_ID) {
        DocumentController.addWZAction();
       } else if (documentTypeId == DocumentType.RW_ID) {
        DocumentController.addRWAction();
       }
      } catch (NumberFormatException ex) {
       Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
      }
     }
    });

    view.getDocumentIncomeElementAddFormButton().addActionListener(new java.awt.event.ActionListener() {
     @Override
     public void actionPerformed(java.awt.event.ActionEvent evt) {
      try {
       Integer documentTypeId = Integer.parseInt(view.getDocumentIncomeTypeHidden().getText());
       DocumentController.addDocumentElementForm(documentTypeId);
      } catch (NumberFormatException ex) {
       Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
      }
     }
    });

    view.getDocumentIncomeElementDeleteFormButton().addActionListener(new java.awt.event.ActionListener() {
     @Override
     public void actionPerformed(java.awt.event.ActionEvent evt) {
      try {
       Integer documentTypeId = Integer.parseInt(view.getDocumentIncomeTypeHidden().getText());
       DocumentController.deleteDocumentElementForm(documentTypeId);
      } catch (NumberFormatException ex) {
       Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
      }
     }
    });

    view.getSelectWareFormReturnButton().addActionListener(new java.awt.event.ActionListener() {
     @Override
     public void actionPerformed(java.awt.event.ActionEvent evt) {
      try {
       Integer documentTypeId = Integer.parseInt(view.getDocumentIncomeTypeHidden().getText());
       DocumentController.addDocument(documentTypeId);
      } catch (NumberFormatException ex) {
       Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
      }
     }
    });
   } else {
    for (ActionListener listener : view.getDocumentOutcomeFormButton().getActionListeners()) {
     view.getDocumentOutcomeFormButton().removeActionListener(listener);
    }
    for (ActionListener listener : view.getDocumentOutcomeElementAddFormButton().getActionListeners()) {
     view.getDocumentOutcomeElementAddFormButton().removeActionListener(listener);
    }
    for (ActionListener listener : view.getDocumentOutcomeElementDeleteFormButton().getActionListeners()) {
     view.getDocumentOutcomeElementDeleteFormButton().removeActionListener(listener);
    }
    for (ActionListener listener : view.getSelectWareFormReturnButton().getActionListeners()) {
     view.getSelectWareFormReturnButton().removeActionListener(listener);
    }

    view.getDocumentOutcomeFormButton().addActionListener(new java.awt.event.ActionListener() {
     @Override
     public void actionPerformed(java.awt.event.ActionEvent evt) {
      try {
       Integer documentTypeId = Integer.parseInt(view.getDocumentOutcomeTypeHidden().getText());
       if (documentTypeId == DocumentType.PZ_ID) {
        DocumentController.addPZAction();
       } else if (documentTypeId == DocumentType.PW_ID) {
        DocumentController.addPWAction();
       } else if (documentTypeId == DocumentType.WZ_ID) {
        DocumentController.addWZAction();
       } else if (documentTypeId == DocumentType.RW_ID) {
        DocumentController.addRWAction();
       }
      } catch (NumberFormatException ex) {
       Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
      }
     }
    });

    view.getDocumentOutcomeElementAddFormButton().addActionListener(new java.awt.event.ActionListener() {
     @Override
     public void actionPerformed(java.awt.event.ActionEvent evt) {
      try {
       Integer documentTypeId = Integer.parseInt(view.getDocumentOutcomeTypeHidden().getText());
       DocumentController.addDocumentElementForm(documentTypeId);
      } catch (NumberFormatException ex) {
       Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
      }
     }
    });

    view.getDocumentOutcomeElementDeleteFormButton().addActionListener(new java.awt.event.ActionListener() {
     @Override
     public void actionPerformed(java.awt.event.ActionEvent evt) {
      try {
       Integer documentTypeId = Integer.parseInt(view.getDocumentOutcomeTypeHidden().getText());
       DocumentController.deleteDocumentElementForm(documentTypeId);
      } catch (NumberFormatException ex) {
       Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
      }
     }
    });

    view.getSelectWareFormReturnButton().addActionListener(new java.awt.event.ActionListener() {
     @Override
     public void actionPerformed(java.awt.event.ActionEvent evt) {
      try {
       Integer documentTypeId = Integer.parseInt(view.getDocumentOutcomeTypeHidden().getText());
       DocumentController.addDocument(documentTypeId);
      } catch (NumberFormatException ex) {
       Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
      }
     }
    });
   }

   if (documentTypeId == DocumentType.PZ_ID || documentTypeId == DocumentType.PW_ID) {
    view.getDocumentIncomeFormWarehouseInput().setText(document.getWarehouse().getName());
    view.getDocumentIncomeFormButton().setText("Zatwierdź");
    view.getDocumentIncomeFormView().setVisible(true);
   } else {
    view.getDocumentOutcomeFormWarehouseInput().setText(document.getWarehouse().getName());
    view.getDocumentOutcomeFormButton().setText("Zatwierdź");
    view.getDocumentOutcomeFormView().setVisible(true);
   }
  }
 }

 public static void addDocumentPreFormAction() {
  Integer documentTypeId = Integer.parseInt(view.getDocumentInsideTypeHidden().getText());
  Object selectedWarehouse = view.getDocumentInsidePreFormWarehouseSelect().getSelectedItem();
  if (documentTypeId == DocumentType.PZ_ID || documentTypeId == DocumentType.WZ_ID) {
   selectedWarehouse = view.getDocumentOutsidePreFormWarehouseSelect().getSelectedItem();
  }
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
      new DecimalFormat("#0.00zł").format((double) Math.round(documentElement.getValue() * 100) / 100),
      new DecimalFormat("#0.00%").format((double) Math.round(documentElement.getTax() * 100) / 10000),
      new DecimalFormat("#0.00zł").format((double) Math.round(documentElement.getValueIncludingTax() * 100) / 100),
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
  if (documentElements.isEmpty()) {
   view.showErrorPopup("Nie można zatwierdzić dokumentu bez pozycji");
  } else {
   Ware ware;
   for (DocumentElement documentElement : documentElements) {
    ware = database.getWareByName(documentElement.getWareName());
    documentElement.setValue(ware.getValue());
    documentElement.setTax(ware.getTax());
    documentElement.setDocument(document);
    database.saveDocumentElement(documentElement);
   }

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
  if (documentElements.isEmpty()) {
   view.showErrorPopup("Nie można zatwierdzić dokumentu bez pozycji");
  } else {
   Ware ware;
   for (DocumentElement documentElement : documentElements) {
    ware = database.getWareByName(documentElement.getWareName());
    documentElement.setValue(ware.getValue());
    documentElement.setTax(ware.getTax());
    documentElement.setDocument(document);
    database.saveDocumentElement(documentElement);
   }

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

 public static void addWZAction() {
  Calendar now = Calendar.getInstance();
  Integer yearInteger = now.get(Calendar.YEAR);
  String year = String.valueOf(yearInteger);

  Document document = database.getDocumentsByDocumentTypeId(DocumentType.WZ_ID, 1).get(0);
  document.setBuffer(0);
  String documentNumber = document.getDocumentType().getSymbol() + "/" + document.getId() + "/" + document.getWarehouse().getId() + "/" + year;
  document.setNumber(documentNumber);

  List<DocumentElement> documentElements = database.getDocumentElementsByDocumentId(document.getId());
  if (documentElements.isEmpty()) {
   view.showErrorPopup("Nie można zatwierdzić dokumentu bez pozycji");
  } else {
   Boolean saveDocument = true;
   for (DocumentElement documentElement : documentElements) {
    List<WareRecord> wareRecords = database.getWareRecordsByWarehouseIdAndWareName(document.getWarehouse().getId(), documentElement.getWareName());
    Integer totalAmount = 0;
    for (WareRecord wareRecord : wareRecords) {
     totalAmount += wareRecord.getAmount();
    }

    if (totalAmount < documentElement.getAmount()) {
     view.showErrorPopup("Za mało towaru " + documentElement.getWareName() + " na magazynie " + document.getWarehouse().getName());
     saveDocument = false;
     break;
    }
   }

   if (saveDocument) {
    for (DocumentElement documentElement : documentElements) {
     List<WareRecord> wareRecords = database.getWareRecordsByWarehouseIdAndWareName(document.getWarehouse().getId(), documentElement.getWareName());

     Integer wareRecordIndex = 0;
     WareRecord wareRecord = wareRecords.get(wareRecordIndex);
     DocumentElement newDocumentElement = new DocumentElement();
     newDocumentElement.setDocument(document);
     newDocumentElement.setWareName(documentElement.getWareName());
     newDocumentElement.setAmount(0);

     for (Integer i = 1;;) {
      if (wareRecord.getAmount() > 0) {
       wareRecord.setAmount(wareRecord.getAmount() - 1);
       newDocumentElement.setAmount(newDocumentElement.getAmount() + 1);
       newDocumentElement.setValue(wareRecord.getValue());
       newDocumentElement.setTax(wareRecord.getTax());
       i++;
      } else {
       database.saveWareRecord(wareRecord);
       if (newDocumentElement.getAmount() > 0) {
        database.saveDocumentElement(newDocumentElement);
       }

       wareRecordIndex++;
       wareRecord = wareRecords.get(wareRecordIndex);
       newDocumentElement = new DocumentElement();
       newDocumentElement.setDocument(document);
       newDocumentElement.setWareName(documentElement.getWareName());
       newDocumentElement.setAmount(0);
      }

      if (i == documentElement.getAmount() + 1) {
       database.saveWareRecord(wareRecord);
       if (newDocumentElement.getAmount() > 0) {
        database.saveDocumentElement(newDocumentElement);
       }
       break;
      }
     }
     database.deleteDocumentElementById(documentElement.getId());
    }

    database.saveDocument(document);
    DocumentController.list(DocumentType.WZ_ID);
   }
  }
 }

 public static void addRWAction() {
  Calendar now = Calendar.getInstance();
  Integer yearInteger = now.get(Calendar.YEAR);
  String year = String.valueOf(yearInteger);

  Document document = database.getDocumentsByDocumentTypeId(DocumentType.RW_ID, 1).get(0);
  document.setBuffer(0);
  String documentNumber = document.getDocumentType().getSymbol() + "/" + document.getId() + "/" + document.getWarehouse().getId() + "/" + year;
  document.setNumber(documentNumber);

  List<DocumentElement> documentElements = database.getDocumentElementsByDocumentId(document.getId());
  if (documentElements.isEmpty()) {
   view.showErrorPopup("Nie można zatwierdzić dokumentu bez pozycji");
  } else {
   Boolean saveDocument = true;
   for (DocumentElement documentElement : documentElements) {
    List<WareRecord> wareRecords = database.getWareRecordsByWarehouseIdAndWareName(document.getWarehouse().getId(), documentElement.getWareName());
    Integer totalAmount = 0;
    for (WareRecord wareRecord : wareRecords) {
     totalAmount += wareRecord.getAmount();
    }

    if (totalAmount < documentElement.getAmount()) {
     view.showErrorPopup("Za mało towaru " + documentElement.getWareName() + " na magazynie " + document.getWarehouse().getName());
     saveDocument = false;
     break;
    }
   }

   if (saveDocument) {
    for (DocumentElement documentElement : documentElements) {
     List<WareRecord> wareRecords = database.getWareRecordsByWarehouseIdAndWareName(document.getWarehouse().getId(), documentElement.getWareName());

     Integer wareRecordIndex = 0;
     WareRecord wareRecord = wareRecords.get(wareRecordIndex);
     DocumentElement newDocumentElement = new DocumentElement();
     newDocumentElement.setDocument(document);
     newDocumentElement.setWareName(documentElement.getWareName());
     newDocumentElement.setAmount(0);

     for (Integer i = 1;;) {
      if (wareRecord.getAmount() > 0) {
       wareRecord.setAmount(wareRecord.getAmount() - 1);
       newDocumentElement.setAmount(newDocumentElement.getAmount() + 1);
       newDocumentElement.setValue(wareRecord.getValue());
       newDocumentElement.setTax(wareRecord.getTax());
       i++;
      } else {
       database.saveWareRecord(wareRecord);
       if (newDocumentElement.getAmount() > 0) {
        database.saveDocumentElement(newDocumentElement);
       }

       wareRecordIndex++;
       wareRecord = wareRecords.get(wareRecordIndex);
       newDocumentElement = new DocumentElement();
       newDocumentElement.setDocument(document);
       newDocumentElement.setWareName(documentElement.getWareName());
       newDocumentElement.setAmount(0);
      }

      if (i == documentElement.getAmount() + 1) {
       database.saveWareRecord(wareRecord);
       if (newDocumentElement.getAmount() > 0) {
        database.saveDocumentElement(newDocumentElement);
       }
       break;
      }
     }
     database.deleteDocumentElementById(documentElement.getId());
    }

    database.saveDocument(document);
    DocumentController.list(DocumentType.RW_ID);
   }
  }
 }

}
