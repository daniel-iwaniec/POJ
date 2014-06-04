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
import database.entity.*;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import view.MainView;

public class StatisticsController {

 protected static MainView view = MainView.getInstance();
 protected static Database database = Database.getInstance();

 public static void WarehouseStatistics() {
  view.hideAllViews();
  view.getHeader().setText("Statystyki - wartość magazynów");
  view.setIcon(MainView.STATISTICS_ICON);

  Double netto = 0.0;
  Double brutto = 0.0;
  Integer amount = 0;
  List<Warehouse> warehouses = database.getWarehouses();
  for (Warehouse warehouse : warehouses) {
   List<WareRecord> wareRecords = database.getWareRecordsByWarehouseId(warehouse.getId());
   for (WareRecord wareRecord : wareRecords) {
    netto += wareRecord.getTotalValue();
    brutto += wareRecord.getTotalValueIncludingTax();
    amount += wareRecord.getAmount();
   }
  }

  view.getWarehouseStatisticsNettoInput().setText(new DecimalFormat("#0.00zł").format((double) Math.round(netto * 100) / 100));
  view.getWarehouseStatisticsTaraInput().setText(new DecimalFormat("#0.00zł").format((double) Math.round((brutto - netto) * 100) / 100));
  view.getWarehouseStatisticsBruttoInput().setText(new DecimalFormat("#0.00zł").format((double) Math.round(brutto * 100) / 100));
  view.getWarehouseStatisticsAmountInput().setText(amount.toString() + " sztuk");

  view.getWarehouseStatisticsView().setVisible(true);
 }

 public static void WareStatistics() {
  view.hideAllViews();
  view.getHeader().setText("Statystyki - wartość towarów");
  view.setIcon(MainView.STATISTICS_ICON);

  WareRecord statisticWareRecord;
  List<WareRecord> wareRecords;
  List<WareRecord> statisticWareRecords = new LinkedList<>();

  List<Warehouse> warehouses = database.getWarehouses();
  for (Warehouse warehouse : warehouses) {
   wareRecords = database.getWareRecordsByWarehouseId(warehouse.getId());
   for (WareRecord wareRecord : wareRecords) {
    if (statisticWareRecords.contains(wareRecord)) {
     statisticWareRecord = statisticWareRecords.get(statisticWareRecords.indexOf(wareRecord));

     statisticWareRecord.setAmount(statisticWareRecord.getAmount() + wareRecord.getAmount());
     statisticWareRecord.setTax(statisticWareRecord.getTax() + wareRecord.getTotalValueIncludingTax());
     statisticWareRecord.setValue(statisticWareRecord.getValue() + wareRecord.getTotalValue());

     statisticWareRecords.set(statisticWareRecords.indexOf(wareRecord), statisticWareRecord);
    } else {
     wareRecord.setTax(wareRecord.getTotalValueIncludingTax());
     wareRecord.setValue(wareRecord.getTotalValue());
     statisticWareRecords.add(wareRecord);
    }
   }
  }

  DefaultTableModel table = (DefaultTableModel) view.getWareRecordStatisticsTable().getModel();
  table.setRowCount(0);
  for (WareRecord wareRecord : statisticWareRecords) {
   table.addRow(new Object[]{
    wareRecord.getWareName(),
    new DecimalFormat("#0.00zł").format((double) Math.round(wareRecord.getValue() * 100) / 100),
    new DecimalFormat("#0.00zł").format((double) Math.round((wareRecord.getTax() - wareRecord.getValue()) * 100) / 100),
    new DecimalFormat("#0.00zł").format((double) Math.round(wareRecord.getTax() * 100) / 100),
    wareRecord.getAmount()
   });
  }

  view.getWareStatisticsView().setVisible(true);
 }
}
