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

public class Warehouse extends AbstractEntity {

 private Integer id = AbstractEntity.NULL_ID;
 private String name = "";

 public Warehouse() {
  super();
 }

 public Warehouse(Integer id, String name) {
  super();
  this.id = id;
  this.name = name;
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

 public Double calculateValue() {
  /**
   * @todo calculate value
   */
  return 0.0;
 }

 @Override
 public void filter() {
  this.name = this.name.trim();
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

  if (!name.matches("^[a-zA-Z0-9ęóąśłżźćńĘÓĄŚŁŻŹĆŃ]*$")) {
   validationErrors.add("Nazwa może zawierać tylko alfanumeryczne znaki");
  }

  Database database = Database.getInstance();
  if (!database.isNameUnique(this)) {
   validationErrors.add("Nazwa musi być unikalna");
  }

  return validationErrors.isEmpty();
 }

}
