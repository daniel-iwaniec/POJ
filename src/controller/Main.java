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
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.awt.EventQueue;
import view.MainView;

/**
 *
 * @author Daniel Iwaniec, Karol Gos.
 */
public class Main {

 private static MainView view;

 public static void defaultAction() {
  view = new MainView();

  Connection c;
  Statement stmt;
  try {
   Class.forName("org.postgresql.Driver");
   c = DriverManager.getConnection("jdbc:postgresql://localhost:5433/poj", "postgres", "postgres");
   c.setAutoCommit(false);

   stmt = c.createStatement();
   ResultSet rs = stmt.executeQuery("SELECT * FROM warehouse;");
   while (rs.next()) {
    int id = rs.getInt("id");
    String name = rs.getString("name");
    view.getLabel().setText(name);
   }
   rs.close();
   stmt.close();
   c.close();
  } catch (Exception e) {
   System.err.println(e.getClass().getName() + ": " + e.getMessage());
   System.exit(0);
  }

  view.setVisible(true);
 }

 /**
  * @param args the command line arguments
  */
 public static void main(String[] args) {
  EventQueue.invokeLater(new Runnable() {
   public void run() {
    defaultAction();
   }
  });

 }

}
