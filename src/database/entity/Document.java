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
package database.entity;

import database.Database;
import java.util.List;

public class Document extends AbstractEntity {

 private final Database database = Database.getInstance();

 private Integer id = AbstractEntity.NULL_ID;
 private Warehouse warehouse = null;
 private DocumentType documentType = null;
 private String number = "";
 private Integer buffer = 1;

 public Document() {
  super();
 }

 public Document(Integer id, Integer warehouseId, Integer documentTypeId, String number, Integer buffer) {
  super();
  this.id = id;
  this.warehouse = database.getWarehouseById(warehouseId);
  this.documentType = database.getDocumentTypeById(documentTypeId);
  this.number = number;
  this.buffer = buffer;
 }

 public Document(Integer id, Warehouse warehouse, DocumentType documentType, String number, Integer buffer) {
  super();
  this.id = id;
  this.warehouse = warehouse;
  this.documentType = documentType;
  this.number = number;
  this.buffer = buffer;
 }

 public Integer getId() {
  return this.id;
 }

 public void setId(Integer id) {
  this.id = id;
 }

 public Warehouse getWarehouse() {
  return this.warehouse;
 }

 public void setWarehouse(Warehouse warehouse) {
  this.warehouse = warehouse;
 }

 public String getNumber() {
  return this.number;
 }

 public void setNumber(String number) {
  this.number = number;
 }

 public DocumentType getDocumentType() {
  return this.documentType;
 }

 public void setDocumentType(DocumentType documentType) {
  this.documentType = documentType;
 }

 public Integer getBuffer() {
  return this.buffer;
 }

 public void setBuffer(Integer buffer) {
  this.buffer = buffer;
 }

 public Double getTotalValue() {
  List<DocumentElement> documentElements = database.getDocumentElementsByDocumentId(this.id);
  Double totalValue = 0.0;
  for (DocumentElement documentElement : documentElements) {
   totalValue += documentElement.getTotalValue();
  }

  return totalValue;
 }

 public Double getTotalValueIncludingTax() {
  List<DocumentElement> documentElements = database.getDocumentElementsByDocumentId(this.id);
  Double totalValueIncludingTax = 0.0;
  for (DocumentElement documentElement : documentElements) {
   totalValueIncludingTax += documentElement.getTotalValueIncludingTax();
  }

  return totalValueIncludingTax;
 }

 @Override
 public void filter() {
  this.number = this.number.trim();
 }

 @Override
 public Boolean validate() {
  this.filter();

  if (documentType == null) {
   validationErrors.add("Dokument nie został przypisany do żadnego typu dokumentu");
  }

  if (buffer == 0) {
   if (warehouse == null) {
    validationErrors.add("Dokument nie został przypisany do żadnego magazynu");
   }
  }

  if (!database.isDocumentNumberUnique(this)) {
   validationErrors.add("Numer dokumentu musi być unikalny");
  }

  return validationErrors.isEmpty();
 }

}
