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

import database.entity.Document;
import database.entity.DocumentElement;
import database.entity.DocumentType;
import database.entity.Ware;
import database.entity.WareRecord;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.entity.Warehouse;

public final class Database {

 public static final String DRIVER = "org.sqlite.JDBC";
 public static final String DB_URL = "jdbc:sqlite:database.db";

 private Connection connection;
 private Statement statement;

 private static Database instance = null;

 public static Database getInstance() {
  if (instance == null) {
   instance = new Database();
  }
  return instance;
 }

 public Database() {
  try {
   Class.forName(Database.DRIVER);

   this.connection = DriverManager.getConnection(Database.DB_URL);
   this.statement = this.connection.createStatement();

   initialize();
  } catch (ClassNotFoundException | SQLException ex) {
   Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
  }
 }

 public void initialize() {
  try {
   String createWarehouseTable = "CREATE TABLE IF NOT EXISTS warehouse (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20))";
   String createWareTable = "CREATE TABLE IF NOT EXISTS ware (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20), value DECIMAL(17,2), tax DECIMAL(17,2))";

   String createDocumentTypeTable = "CREATE TABLE IF NOT EXISTS document_type (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(50), symbol VARCHAR(5))";
   String deleteDocumentTypes = "DELETE FROM document_type;";
   String insertDocumentTypePZ = "INSERT INTO document_type (id, name, symbol) VALUES (1, 'Przyjęcie zewnętrzne', 'PZ')";
   String insertDocumentTypeWZ = "INSERT INTO document_type (id, name, symbol) VALUES (2, 'Wydanie zewnętrzne', 'WZ')";
   String insertDocumentTypePW = "INSERT INTO document_type (id, name, symbol) VALUES (3, 'Przyjęcie wewnętrzne', 'PW')";
   String insertDocumentTypeRW = "INSERT INTO document_type (id, name, symbol) VALUES (4, 'Rozchód wewnętrzny', 'RW')";

   String createDocumentTable = "CREATE TABLE IF NOT EXISTS document (id INTEGER PRIMARY KEY AUTOINCREMENT, document_type_id INTEGER, number VARCHAR(100), FOREIGN KEY(document_type_id) REFERENCES document_type(id))";
   String createDocumentElementTable = "CREATE TABLE IF NOT EXISTS document_element (id INTEGER PRIMARY KEY AUTOINCREMENT, document_id INTEGER, ware_name VARCHAR(20), value DECIMAL(17,2), tax DECIMAL(17,2), amount INTEGER, FOREIGN KEY(document_id) REFERENCES document(id))";

   String createWareRecordTable = "CREATE TABLE IF NOT EXISTS ware_record (id INTEGER PRIMARY KEY AUTOINCREMENT, warehouse_id INTEGER, ware_name VARCHAR(20), value DECIMAL(17,2), tax DECIMAL(17,2), amount INTEGER, FOREIGN KEY(warehouse_id) REFERENCES warehouse(id))";

   this.statement.execute(createWarehouseTable);
   this.statement.execute(createWareTable);

   this.statement.execute(createDocumentTypeTable);
   this.statement.execute(deleteDocumentTypes);
   this.statement.execute(insertDocumentTypePZ);
   this.statement.execute(insertDocumentTypeWZ);
   this.statement.execute(insertDocumentTypePW);
   this.statement.execute(insertDocumentTypeRW);

   this.statement.execute(createDocumentTable);
   this.statement.execute(createDocumentElementTable);

   this.statement.execute(createWareRecordTable);
  } catch (SQLException ex) {
   Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
  }
 }

 public void clearDatabase() {
  try {
   this.statement.execute("DROP TABLE IF EXISTS warehouse");
   this.statement.execute("DROP TABLE IF EXISTS ware");

   this.statement.execute("DROP TABLE IF EXISTS document_type");
   this.statement.execute("DROP TABLE IF EXISTS document");
   this.statement.execute("DROP TABLE IF EXISTS document_element");

   this.statement.execute("DROP TABLE IF EXISTS ware_record");

   this.initialize();
  } catch (SQLException ex) {
   Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
  }
 }

 public void saveWarehouse(Warehouse warehouse) {
  try {
   if (!warehouse.validate()) {
    return;
   }

   if (warehouse.getId().equals(Warehouse.NULL_ID)) {
    PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO warehouse VALUES (NULL, ?);");
    preparedStatement.setString(1, warehouse.getName());
    preparedStatement.execute();
   } else {
    PreparedStatement preparedStatement = this.connection.prepareStatement("UPDATE warehouse SET name = ? WHERE id = ?;");
    preparedStatement.setString(1, warehouse.getName());
    preparedStatement.setInt(2, warehouse.getId());
    preparedStatement.execute();
   }
  } catch (SQLException ex) {
   Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
  }
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

 public Warehouse getWarehouseById(Integer id) {
  Warehouse warehouse = null;
  try {
   PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM warehouse WHERE id = ?;");
   preparedStatement.setInt(1, id);
   ResultSet result = preparedStatement.executeQuery();
   while (result.next()) {
    warehouse = new Warehouse(result.getInt("id"), result.getString("name"));
   }
  } catch (SQLException exception) {
   System.err.println(exception.getMessage());
   return null;
  }

  return warehouse;
 }

 public void deleteWarehouseById(Integer id) {
  Warehouse warehouse = null;
  try {
   PreparedStatement preparedStatement = this.connection.prepareStatement("DELETE FROM warehouse WHERE id = ?;");
   preparedStatement.setInt(1, id);
   preparedStatement.execute();
  } catch (SQLException exception) {
   System.err.println(exception.getMessage());
  }
 }

 public Boolean isWarehouseNameUnique(Warehouse warehouse) {
  try {
   PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM warehouse WHERE name = ? AND id != ? LIMIT 1;");
   preparedStatement.setString(1, warehouse.getName());
   preparedStatement.setInt(2, warehouse.getId());
   ResultSet result = preparedStatement.executeQuery();

   return !result.next();
  } catch (SQLException exception) {
   System.err.println(exception.getMessage());
   return false;
  }
 }

 public void saveWare(Ware ware) {
  try {
   if (!ware.validate()) {
    return;
   }

   if (ware.getId().equals(Ware.NULL_ID)) {
    PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO ware VALUES (NULL, ?, ?, ?);");
    preparedStatement.setString(1, ware.getName());
    preparedStatement.setDouble(2, ware.getValue());
    preparedStatement.setDouble(3, ware.getTax());
    preparedStatement.execute();
   } else {
    PreparedStatement preparedStatement = this.connection.prepareStatement("UPDATE ware SET name = ?, value = ?, tax = ? WHERE id = ?;");
    preparedStatement.setString(1, ware.getName());
    preparedStatement.setDouble(2, ware.getValue());
    preparedStatement.setDouble(3, ware.getTax());
    preparedStatement.setInt(4, ware.getId());
    preparedStatement.execute();
   }
  } catch (SQLException ex) {
   Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
  }
 }

 public List<Ware> getWares() {
  List<Ware> wares = new LinkedList<>();
  try {
   ResultSet result = this.statement.executeQuery("SELECT * FROM ware");
   while (result.next()) {
    wares.add(new Ware(result.getInt("id"), result.getString("name"), result.getDouble("value"), result.getDouble("tax")));
   }
  } catch (SQLException exception) {
   System.err.println(exception.getMessage());
   return null;
  }
  return wares;
 }

 public Boolean isWareNameUnique(Ware ware) {
  try {
   PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM ware WHERE name = ? AND id != ? LIMIT 1;");
   preparedStatement.setString(1, ware.getName());
   preparedStatement.setInt(2, ware.getId());
   ResultSet result = preparedStatement.executeQuery();

   return !result.next();
  } catch (SQLException exception) {
   System.err.println(exception.getMessage());
   return false;
  }
 }

 public Ware getWareById(Integer id) {
  Ware ware = null;
  try {
   PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM ware WHERE id = ?;");
   preparedStatement.setInt(1, id);
   ResultSet result = preparedStatement.executeQuery();
   while (result.next()) {
    ware = new Ware(result.getInt("id"), result.getString("name"), result.getDouble("value"), result.getDouble("tax"));
   }
  } catch (SQLException exception) {
   System.err.println(exception.getMessage());
   return null;
  }

  return ware;
 }

 public void deleteWareById(Integer id) {
  Ware ware = null;
  try {
   PreparedStatement preparedStatement = this.connection.prepareStatement("DELETE FROM ware WHERE id = ?;");
   preparedStatement.setInt(1, id);
   preparedStatement.execute();
  } catch (SQLException exception) {
   System.err.println(exception.getMessage());
  }
 }

 public void closeConnection() {
  try {
   this.connection.close();
  } catch (SQLException exception) {
   System.err.println(exception.getMessage());
  }
 }

 public DocumentType getDocumentTypeById(Integer id) {
  DocumentType documentType = null;
  try {
   PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM document_type WHERE id = ?;");
   preparedStatement.setInt(1, id);
   ResultSet result = preparedStatement.executeQuery();
   while (result.next()) {
    documentType = new DocumentType(result.getInt("id"), result.getString("name"), result.getString("symbol"));
   }
  } catch (SQLException exception) {
   System.err.println(exception.getMessage());
   return null;
  }

  return documentType;
 }

 public List<DocumentType> getDocumentTypes() {
  List<DocumentType> documentTypes = new LinkedList<>();
  try {
   ResultSet result = this.statement.executeQuery("SELECT * FROM document_type");
   while (result.next()) {
    documentTypes.add(new DocumentType(result.getInt("id"), result.getString("name"), result.getString("symbol")));
   }
  } catch (SQLException exception) {
   System.err.println(exception.getMessage());
   return null;
  }
  return documentTypes;
 }

 public Document getDocumentById(Integer id) {
  Document document = null;
  try {
   PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM document WHERE id = ?;");
   preparedStatement.setInt(1, id);
   ResultSet result = preparedStatement.executeQuery();
   while (result.next()) {
    document = new Document(result.getInt("id"), result.getInt("document_type_id"), result.getString("number"));
   }
  } catch (SQLException exception) {
   System.err.println(exception.getMessage());
   return null;
  }

  return document;
 }

 public List<Document> getDocuments() {
  List<Document> documents = new LinkedList<>();
  try {
   ResultSet result = this.statement.executeQuery("SELECT * FROM document");
   while (result.next()) {
    documents.add(new Document(result.getInt("id"), result.getInt("document_type_id"), result.getString("number")));
   }
  } catch (SQLException exception) {
   System.err.println(exception.getMessage());
   return null;
  }
  return documents;
 }

 public Boolean isDocumentNumberUnique(Document document) {
  try {
   PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM document WHERE number = ? AND id != ? LIMIT 1;");
   preparedStatement.setString(1, document.getNumber());
   preparedStatement.setInt(2, document.getId());
   ResultSet result = preparedStatement.executeQuery();

   return !result.next();
  } catch (SQLException exception) {
   System.err.println(exception.getMessage());
   return false;
  }
 }

 public void saveDocument(Document document) {
  try {
   if (!document.validate()) {
    return;
   }

   /**
    * @todo save document elements, save ware records, modify warehouses state
    * etc.
    */
   if (document.getId().equals(Ware.NULL_ID)) {
    PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO document VALUES (NULL, ?, ?);");
    preparedStatement.setInt(1, document.getDocumentType().getId());
    preparedStatement.setString(2, document.getNumber());
    preparedStatement.execute();
   }
  } catch (SQLException ex) {
   Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
  }
 }

 public DocumentElement getDocumentElementById(Integer id) {
  DocumentElement documentElement = null;
  try {
   PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM document_element WHERE id = ?;");
   preparedStatement.setInt(1, id);
   ResultSet result = preparedStatement.executeQuery();
   while (result.next()) {
    documentElement = new DocumentElement(result.getInt("id"), result.getInt("document_id"), result.getString("ware_name"), result.getDouble("value"), result.getDouble("tax"), result.getInt("amount"));
   }
  } catch (SQLException exception) {
   System.err.println(exception.getMessage());
   return null;
  }

  return documentElement;
 }

 public List<DocumentElement> getDocumentElements() {
  List<DocumentElement> documentElements = new LinkedList<>();
  try {
   ResultSet result = this.statement.executeQuery("SELECT * FROM document_element");
   while (result.next()) {
    documentElements.add(new DocumentElement(result.getInt("id"), result.getInt("document_id"), result.getString("ware_name"), result.getDouble("value"), result.getDouble("tax"), result.getInt("amount")));
   }
  } catch (SQLException exception) {
   System.err.println(exception.getMessage());
   return null;
  }
  return documentElements;
 }

 public List<DocumentElement> getDocumentElementsByDocumentId(Integer documentId) {
  List<DocumentElement> documentElements = new LinkedList<>();
  try {
   PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM document_element WHERE document_id = ?");
   preparedStatement.setInt(1, documentId);
   ResultSet result = preparedStatement.executeQuery();
   while (result.next()) {
    documentElements.add(new DocumentElement(result.getInt("id"), result.getInt("document_id"), result.getString("ware_name"), result.getDouble("value"), result.getDouble("tax"), result.getInt("amount")));
   }
  } catch (SQLException exception) {
   System.err.println(exception.getMessage());
   return null;
  }
  return documentElements;
 }

 public void saveDocumentElement(DocumentElement documentElement) {
  try {
   if (!documentElement.validate()) {
    return;
   }

   if (documentElement.getId().equals(Ware.NULL_ID)) {
    PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO document_element VALUES (NULL, ?, ?, ?, ?, ?);");
    preparedStatement.setInt(1, documentElement.getDocument().getId());
    preparedStatement.setString(2, documentElement.getWareName());
    preparedStatement.setDouble(3, documentElement.getValue());
    preparedStatement.setDouble(4, documentElement.getTax());
    preparedStatement.setInt(5, documentElement.getAmount());
    preparedStatement.execute();
   }
  } catch (SQLException ex) {
   Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
  }
 }

 public WareRecord getWareRecordById(Integer id) {
  WareRecord wareRecord = null;
  try {
   PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM ware_record WHERE id = ?;");
   preparedStatement.setInt(1, id);
   ResultSet result = preparedStatement.executeQuery();
   while (result.next()) {
    wareRecord = new WareRecord(result.getInt("id"), result.getInt("warehouse_id"), result.getString("ware_name"), result.getDouble("value"), result.getDouble("tax"), result.getInt("amount"));
   }
  } catch (SQLException exception) {
   System.err.println(exception.getMessage());
   return null;
  }

  return wareRecord;
 }

 public List<WareRecord> getWarehouseRecords() {
  List<WareRecord> wareRecords = new LinkedList<>();
  try {
   ResultSet result = this.statement.executeQuery("SELECT * FROM ware_record");
   while (result.next()) {
    wareRecords.add(new WareRecord(result.getInt("id"), result.getInt("warehouse_id"), result.getString("ware_name"), result.getDouble("value"), result.getDouble("tax"), result.getInt("amount")));
   }
  } catch (SQLException exception) {
   System.err.println(exception.getMessage());
   return null;
  }
  return wareRecords;
 }

 public List<WareRecord> getWareRecordsByWarehouseId(Integer warehouseId) {
  List<WareRecord> wareRecords = new LinkedList<>();
  try {
   PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM ware_record WHERE warehouse_id = ?");
   preparedStatement.setInt(1, warehouseId);
   ResultSet result = preparedStatement.executeQuery();
   while (result.next()) {
    wareRecords.add(new WareRecord(result.getInt("id"), result.getInt("warehouse_id"), result.getString("ware_name"), result.getDouble("value"), result.getDouble("tax"), result.getInt("amount")));
   }
  } catch (SQLException exception) {
   System.err.println(exception.getMessage());
   return null;
  }
  return wareRecords;
 }

 public void saveWareRecord(WareRecord wareRecord) {
  try {
   if (!wareRecord.validate()) {
    return;
   }

   if (wareRecord.getId().equals(Ware.NULL_ID)) {
    PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO ware_record VALUES (NULL, ?, ?, ?, ?, ?);");
    preparedStatement.setInt(1, wareRecord.getWarehouse().getId());
    preparedStatement.setString(2, wareRecord.getWareName());
    preparedStatement.setDouble(3, wareRecord.getValue());
    preparedStatement.setDouble(4, wareRecord.getTax());
    preparedStatement.setInt(5, wareRecord.getAmount());
    preparedStatement.execute();
   }
  } catch (SQLException ex) {
   Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
  }
 }

}
