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

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.sql.SQLException;

import database.entity.Warehouse;

public final class Database {

 public static final String DRIVER = "org.sqlite.JDBC";
 public static final String DB_URL = "jdbc:sqlite:magazyn.db";

 private Connection connection;
 private Statement statement;
 private PreparedStatement preparedStatement;

 public Database() {
  try {
   Class.forName(Database.DRIVER);
  } catch (ClassNotFoundException exception) {
   System.err.println(exception.getMessage());
  }

  try {
   this.connection = DriverManager.getConnection(Database.DB_URL);
   this.statement = this.connection.createStatement();
  } catch (SQLException exception) {
   System.err.println(exception.getMessage());
  }

  this.initialize();
 }

 public void initialize() {
  String createWarehouseTable = "CREATE TABLE IF NOT EXISTS warehouse (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(255))";
  try {
   this.statement.execute(createWarehouseTable);

   // Remove from production
   statement.execute("DELETE FROM warehouse");
  } catch (SQLException exception) {
   System.err.println(exception.getMessage());
  }
 }

 public boolean saveWarehouse(Warehouse warehouse) {
  try {
   if (!warehouse.validate()) {
    return false;
   }

   if (warehouse.getId().equals(Warehouse.NULL_ID)) {
    PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO warehouse VALUES (NULL, ?);");
    preparedStatement.setString(1, warehouse.getName());
    preparedStatement.execute();
   } else {
    this.preparedStatement = this.connection.prepareStatement("UPDATE warehouse SET name = ? WHERE id = ?;");
    this.preparedStatement.setString(1, warehouse.getName());
    this.preparedStatement.setInt(2, warehouse.getId());
    this.preparedStatement.execute();
   }
  } catch (SQLException exception) {
   System.err.println(exception.getMessage());
   return false;
  }
  return true;
 }

 public List<Warehouse> getWarehouses() {
  List<Warehouse> warehouses = new LinkedList<>();
  try {
   ResultSet result = this.statement.executeQuery("SELECT * FROM warehouse");
   while (result.next()) {
    warehouses.add(new Warehouse(result.getInt("id"), result.getString("name")));
   }
  } catch (SQLException exception) {
   System.err.println(exception.getMessage());
   return null;
  }
  return warehouses;
 }

 public Warehouse selectWarehouseById(Integer id) {
  Warehouse warehouse = null;
  try {
   this.preparedStatement = this.connection.prepareStatement("SELECT * FROM warehouse WHERE id = ?;");
   this.preparedStatement.setInt(1, id);
   ResultSet result = this.preparedStatement.executeQuery();
   while (result.next()) {
    warehouse = new Warehouse(result.getInt("id"), result.getString("name"));
   }
  } catch (SQLException exception) {
   System.err.println(exception.getMessage());
   return null;
  }

  return warehouse;
 }

 public void closeConnection() {
  try {
   this.connection.close();
  } catch (SQLException exception) {
   System.err.println(exception.getMessage());
  }
 }
}
