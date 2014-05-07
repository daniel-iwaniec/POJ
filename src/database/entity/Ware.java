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

public class Ware extends AbstractEntity {

 private Integer id = AbstractEntity.NULL_ID;
 private String name = "";
 private Double value = 0.00;
 private Double tax = 0.00;

 public Ware() {
  super();
 }

 public Ware(Integer id, String name, Double value, Double tax) {
  super();
  this.id = id;
  this.name = name;
  this.value = value;
  this.tax = tax;
 }

 public Integer getId() {
  return this.id;
 }

 public void setId(Integer id) {
  this.id = id;
 }

 public String getName() {
  return this.name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public Double getValue() {
  return this.value;
 }

 public void setValue(Double value) {
  this.value = value;
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

 @Override
 public void filter() {
  this.name = this.name.trim();
  this.value = (double) Math.round(this.value * 100) / 100;
  this.tax = (double) Math.round(this.tax * 100) / 100;
 }

 @Override
 public Boolean validate() {
  this.filter();

  if ("".equals(name)) {
   validationErrors.add("Nazwa nie może być pusta");
  }

  if (name.length() > 20) {
   validationErrors.add("Nazwa może zawierać max 20 znaków");
  }

  if (!name.matches("^[a-zA-Z0-9ęóąśłżźćńĘÓĄŚŁŻŹĆŃ ]*$")) {
   validationErrors.add("Nazwa może zawierać tylko alfanumeryczne znaki");
  }

  Database database = Database.getInstance();
  if (!database.isWareNameUnique(this)) {
   validationErrors.add("Nazwa musi być unikalna");
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

  return validationErrors.isEmpty();
 }

}
