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
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import Model.Magazyn;

public class Database {

 public static final String DRIVER = "org.sqlite.JDBC";
 public static final String DB_URL = "jdbc:sqlite:biblioteka.db";

 private Connection conn;
 private Statement stat;

 public Database() {
  try {
   Class.forName(Database.DRIVER);
  } catch (ClassNotFoundException e) {
   System.err.println("Brak sterownika JDBC");
   e.printStackTrace();
  }

  try {
   conn = DriverManager.getConnection(DB_URL);
   stat = conn.createStatement();
  } catch (SQLException e) {
   System.err.println("Problem z otwarciem polaczenia");
   e.printStackTrace();
  }

  createTables();
 }

 public boolean createTables() {
  String createMagazyn = "CREATE TABLE IF NOT EXISTS magazyn (id INTEGER PRIMARY KEY AUTOINCREMENT, nazwa varchar(255))";
  try {
   stat.execute(createMagazyn);
   stat.execute("DELETE FROM magazyn");
  } catch (SQLException e) {
   System.err.println("Blad przy tworzeniu tabeli");
   e.printStackTrace();
   return false;
  }
  return true;
 }

 public boolean insertMagazyn(String nazwa) {
  try {
   PreparedStatement prepStmt = conn.prepareStatement(
           "insert into magazyn values (NULL, ?);");
   prepStmt.setString(1, nazwa);
   prepStmt.execute();
  } catch (SQLException e) {
   System.err.println("Blad przy wstawianiu magazynu");
   e.printStackTrace();
   return false;
  }
  return true;
 }

 public List<Magazyn> selectMagazyny() {
  List<Magazyn> magazyny = new LinkedList<Magazyn>();
  try {
   ResultSet result = stat.executeQuery("SELECT * FROM magazyn");
   int id;
   String nazwa;
   while (result.next()) {
    id = result.getInt("id");
    nazwa = result.getString("nazwa");
    magazyny.add(new Magazyn(id, nazwa));
   }
  } catch (SQLException e) {
   e.printStackTrace();
   return null;
  }
  return magazyny;
 }

 public Magazyn selectMagazynById(int id) {
  Magazyn magazyn = null;
  try {
   PreparedStatement prepStmt = conn.prepareStatement("SELECT * FROM magazyn WHERE id = ?;");
   prepStmt.setInt(1, id);
   ResultSet result = prepStmt.executeQuery();
   String nazwa;
   while (result.next()) {
    id = result.getInt("id");
    nazwa = result.getString("nazwa");
    magazyn = new Magazyn(id, nazwa);
   }
  } catch (SQLException e) {
   e.printStackTrace();
   return null;
  }

  return magazyn;
 }

 public void closeConnection() {
  try {
   conn.close();
  } catch (SQLException e) {
   System.err.println("Problem z zamknieciem polaczenia");
   e.printStackTrace();
  }
 }
}
