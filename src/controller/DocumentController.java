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
import database.entity.DocumentType;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import view.MainView;

public class DocumentController {

 protected static MainView view = MainView.getInstance();
 protected static Database database = Database.getInstance();

 public static void listPZ() {
  DefaultTableModel table = (DefaultTableModel) view.getDocumentListTable().getModel();
  table.setRowCount(0);

  List<Document> documents = database.getDocumentsByDocumentTypeId(DocumentType.PZ_ID);
  for (Document document : documents) {
   table.addRow(new Object[]{
    document.getId().toString(),
    document.getNumber()
   });
  }

  view.hideAllViews();
  view.getHeader().setText("Przyjęcie zewnętrzne - lista");
  view.setIcon(MainView.DOCUMENT_ICON);

  view.getDocumentListView().setVisible(true);
 }

 public static void listWZ() {
  DefaultTableModel table = (DefaultTableModel) view.getDocumentListTable().getModel();
  table.setRowCount(0);

  List<Document> documents = database.getDocumentsByDocumentTypeId(DocumentType.WZ_ID);
  for (Document document : documents) {
   table.addRow(new Object[]{
    document.getId().toString(),
    document.getNumber()
   });
  }

  view.hideAllViews();
  view.getHeader().setText("Przyjęcie zewnętrzne - lista");
  view.setIcon(MainView.DOCUMENT_ICON);

  view.getDocumentListView().setVisible(true);
 }

 public static void listPW() {
  DefaultTableModel table = (DefaultTableModel) view.getDocumentListTable().getModel();
  table.setRowCount(0);

  List<Document> documents = database.getDocumentsByDocumentTypeId(DocumentType.PW_ID);
  for (Document document : documents) {
   table.addRow(new Object[]{
    document.getId().toString(),
    document.getNumber()
   });
  }

  view.hideAllViews();
  view.getHeader().setText("Przyjęcie zewnętrzne - lista");
  view.setIcon(MainView.DOCUMENT_ICON);

  view.getDocumentListView().setVisible(true);
 }

 public static void listRW() {
  DefaultTableModel table = (DefaultTableModel) view.getDocumentListTable().getModel();
  table.setRowCount(0);

  List<Document> documents = database.getDocumentsByDocumentTypeId(DocumentType.RW_ID);
  for (Document document : documents) {
   table.addRow(new Object[]{
    document.getId().toString(),
    document.getNumber()
   });
  }

  view.hideAllViews();
  view.getHeader().setText("Przyjęcie zewnętrzne - lista");
  view.setIcon(MainView.DOCUMENT_ICON);

  view.getDocumentListView().setVisible(true);
 }

}
