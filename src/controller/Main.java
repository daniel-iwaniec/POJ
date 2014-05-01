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
/**
 * @todo Dodawanie magazynu Usuwanie magazynu Edycja magazynu
 *
 * ekran główny release (installer?) lepsze repo (git flow?)
 *
 * ikonki lepszy wygląd
 *
 * więcej danych do listy magazynu
 *
 * Podzielić warstwy widoku na podwidoki (osobne pliki) Osobne kontrolery
 *
 */
package controller;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

import database.Database;
import database.entity.Warehouse;

import view.MainView;
import javax.swing.table.DefaultTableModel;

public class Main {

 protected static MainView view = new MainView();
 protected static Database database = new Database();

 public static void mainAction() throws Exception {
  try {
   Warehouse newWarehouse = new Warehouse();

   newWarehouse.setName("Magazyn 1");
   database.saveWarehouse(newWarehouse);

   newWarehouse.setName("Magazyn 7");
   database.saveWarehouse(newWarehouse);

   newWarehouse.setName("Magazyn 616");
   database.saveWarehouse(newWarehouse);

   List<Warehouse> warehouses = database.getWarehouses();
   DefaultTableModel table = (DefaultTableModel) view.getTable().getModel();
   for (Warehouse warehouse : warehouses) {
    table.addRow(new Object[]{warehouse.getId(), warehouse.getName()});
   }

   database.closeConnection();
   view.setVisible(true);
  } catch (Exception e) {
   System.err.println(e.getMessage());
   System.exit(0);
  }
 }

 public static void addWarehouse(String name) throws Exception {
  Warehouse newWarehouse = new Warehouse();
  newWarehouse.setName(name);
  database.saveWarehouse(newWarehouse);

  DefaultTableModel table = (DefaultTableModel) view.getTable().getModel();
  Integer tableRowCount = table.getRowCount();
  while (tableRowCount <= 0) {
   table.removeRow(tableRowCount);
   tableRowCount--;
  }

  List<Warehouse> warehouses = database.getWarehouses();
  for (Warehouse warehouse : warehouses) {
   table.addRow(new Object[]{warehouse.getId(), warehouse.getName()});
  }
 }

 public static void main(String[] args) {
  EventQueue.invokeLater(new Runnable() {
   @Override
   public void run() {
    try {
     mainAction();
    } catch (Exception ex) {
     Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
   }
  });

 }

}
