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
package controller;

import java.sql.Connection;
import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.MainView;
import database.Database;
import java.util.List;
import Model.Magazyn;

import java.awt.Color;
import javax.swing.table.DefaultTableModel;

public class Main {

 protected static MainView view;
 protected static Connection db;

 public static void mainAction() throws Exception {
  try {
   view = new MainView();

   Database database = new Database();
   database.insertMagazyn("Magazyn 1");
   database.insertMagazyn("Magazyn 7");
   database.insertMagazyn("Magazyn 616");

   List<Magazyn> magazyny = database.selectMagazyny();

   DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();
   for (Magazyn m : magazyny) {
    model.addRow(new Object[]{m.getId(), m.getName()});
   }

   database.closeConnection();

   view.getContentPane().setBackground(Color.WHITE);
   view.setVisible(true);
  } catch (Exception e) {
   System.err.println(e.getMessage());
   System.exit(0);
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
