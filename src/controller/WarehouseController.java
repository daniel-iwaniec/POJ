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
import database.entity.Warehouse;

import view.MainView;
import javax.swing.table.DefaultTableModel;

public class WarehouseController {

 protected static MainView view = MainView.getInstance();
 protected static Database database = Database.getInstance();

 public static void list() {
  DefaultTableModel table = (DefaultTableModel) view.getWarehouseListTable().getModel();
  table.setRowCount(0);

  List<Warehouse> warehouses = database.getWarehouses();
  for (Warehouse warehouse : warehouses) {
   table.addRow(new Object[]{warehouse.getId(), warehouse.getName(), false});
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

  view.getWarehouseFormNameInput().setText("");
  view.getWarehouseFormView().setVisible(true);
 }

 public static void addAction() throws java.lang.Exception {
  /**
   * @todo Walidacja
   */
  Warehouse newWarehouse = new Warehouse();
  newWarehouse.setName(view.getWarehouseFormNameInput().getText());
  database.saveWarehouse(newWarehouse);

  WarehouseController.list();
 }

}
