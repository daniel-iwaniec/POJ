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

import java.sql.DriverManager;
import java.sql.Connection;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.MainView;
import java.sql.Statement;
import java.sql.ResultSet;
import Model.Magazyn;

/**
 *
 * @author Daniel Iwaniec, Karol Gos.
 */
public class Main {

 protected static MainView view;
 protected static Connection db;


 public static void mainAction() throws SQLException, ClassNotFoundException {
  try {
   view = new MainView();

   Class.forName("org.postgresql.Driver");
   // Przerobić na jakąś klasę która robi za połączenie ('driver' jakiś)
   db = DriverManager.getConnection("jdbc:postgresql://localhost:5433/poj", "postgres", "postgres");
   db.setAutoCommit(false);

   Statement stmt;
   stmt = db.createStatement();
   ResultSet rs = stmt.executeQuery("SELECT * FROM warehouse;");
   while (rs.next()) {
    // Dekorator Magazyn
    Magazyn m = new Magazyn(rs);
     //wypisz dane do okienka
    //cos w stylu view.setKol1Wiersz1(m.getId();)
    m.getId();
    m.getName();
   }

   db.close();
   view.setVisible(true);
  } catch (SQLException | ClassNotFoundException e) {
   System.err.println(e.getMessage());
   System.exit(0);
  }
 }

 /**
  * @param args the command line arguments
  */
 public static void main(String[] args) {
  EventQueue.invokeLater(new Runnable() {
   @Override
   public void run() {
    try {
     mainAction();
    } catch (SQLException | ClassNotFoundException ex) {
     Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
   }
  });

 }

}
