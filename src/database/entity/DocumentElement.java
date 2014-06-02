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

public class DocumentElement extends AbstractEntity {

 private final Database database = Database.getInstance();

 private Integer id = AbstractEntity.NULL_ID;
 private Document document = null;

 private String wareName = "";
 private Double value = 0.00;
 private Double tax = 0.00;
 private Integer amount = 0;

 public DocumentElement() {
  super();
 }

 public DocumentElement(Integer id, Document document, String wareName, Double value, Double tax, Integer amount) {
  super();
  this.id = id;
  this.document = document;
  this.wareName = wareName;
  this.value = value;
  this.tax = tax;
  this.amount = amount;
 }

 public DocumentElement(Integer id, Integer documentId, String wareName, Double value, Double tax, Integer amount) {
  super();
  this.id = id;
  this.document = database.getDocumentById(documentId);
  this.wareName = wareName;
  this.value = value;
  this.tax = tax;
  this.amount = amount;
 }

 public Integer getId() {
  return this.id;
 }

 public void setId(Integer id) {
  this.id = id;
 }

 public Document getDocument() {
  return this.document;
 }

 public void setDocument(Document document) {
  this.document = document;
 }

 public String getWareName() {
  return this.wareName;
 }

 public void setWareName(String wareName) {
  this.wareName = wareName;
 }

 public Double getValue() {
  return this.value;
 }

 public void setValue(Double value) {
  this.value = value;
 }

 public Double getTotalValue() {
  return this.value * this.amount;
 }

 public Double getTax() {
  return this.tax;
 }

 public void setTax(Double tax) {
  this.tax = tax;
 }

 public Double getValueIncludingTax() {
  return this.getValue() + (this.getValue() * (this.getTax() / 100));
 }

 public Double getTotalValueIncludingTax() {
  return this.getTotalValue() + (this.getTotalValue() * (this.getTax() / 100));
 }

 public void setAmount(Integer amount) {
  this.amount = amount;
 }

 public Integer getAmount() {
  return this.amount;
 }

 @Override
 public void filter() {
  this.wareName = this.wareName.trim();
  this.value = (double) Math.round(this.value * 100) / 100;
  this.tax = (double) Math.round(this.tax * 100) / 100;
 }

 @Override
 public Boolean validate() {
  this.filter();

  if (document == null) {
   validationErrors.add("Pozycja dokumentu nie została przypisana do dokumentu");
  }

  if ("".equals(wareName)) {
   validationErrors.add("Nazwa towaru nie może być pusta");
  }

  if (wareName.length() > 20) {
   validationErrors.add("Nazwa towaru może zawierać max 20 znaków");
  }

  if (!wareName.matches("^[a-zA-Z0-9ęóąśłżźćńĘÓĄŚŁŻŹĆŃ ]*$")) {
   validationErrors.add("Nazwa towaru może zawierać tylko alfanumeryczne znaki");
  }

  if (value.isNaN() || !(value > 0.0)) {
   validationErrors.add("Wartość musi być większa od zera");
  }

  if (value > 999999999999999.99) {
   validationErrors.add("Maksymalna wartość to 999999999999999.99");
  }

  if (tax.isNaN() || !(tax >= 0.0)) {
   validationErrors.add("Procentowa wysokość podatku musi być większa od zera lub równa zeru");
  }

  if (tax > 999999999999999.99) {
   validationErrors.add("Maksymalna procentowa wysokość podatku to 999999999999999.99");
  }

  if (!(amount > 0)) {
   validationErrors.add("Ilość towaru musi być liczbą całkowitą większą od zera");
  }

  if (amount > 999999999) {
   validationErrors.add("Maksymalna ilość towaru to 999999999");
  }

  return validationErrors.isEmpty();
 }

}
