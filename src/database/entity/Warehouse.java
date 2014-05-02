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
import java.util.LinkedList;
import java.util.List;

public class Warehouse {

 public static final Integer NULL_ID = 0;

 private Integer id = Warehouse.NULL_ID;
 private String name = "";

 @SuppressWarnings("FieldMayBeFinal")
 private List<String> validationErrors;

 public Warehouse() {
  this.validationErrors = new LinkedList<>();
 }

 public Warehouse(Integer id, String name) {
  this();
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

 public Boolean validate() {
  /**
   * @ todo lepsza walidacja
   */

  String trimmedName = this.name.trim();
  if ("".equals(trimmedName)) {
   validationErrors.add("Nazwa magazynu nie może być pusta");
  }

  if (trimmedName.length() > 255) {
   validationErrors.add("Nazwa magazynu może zawierać maksymalnie 255 znaków");
  }

  Database database = Database.getInstance();
  if (!database.isNameUnique(this)) {
   validationErrors.add("Nazwa magazynu musi być unikalna");
  }

  return validationErrors.isEmpty();
 }

 private void addValidationError(String error) {
  validationErrors.add(error);
 }

 private void clearValidationErrors() {
  validationErrors.clear();
 }

 public List<String> getValidationErrors() {
  return validationErrors;
 }

}
