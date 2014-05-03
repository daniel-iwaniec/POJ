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

import java.util.LinkedList;
import java.util.List;

public class AbstractEntity {

 public static final Integer NULL_ID = 0;

 @SuppressWarnings("FieldMayBeFinal")
 protected List<String> validationErrors;

 public AbstractEntity() {
  this.validationErrors = new LinkedList<>();
 }

 public void filter() {
 }

 public Boolean validate() {
  this.filter();
  return true;
 }

 protected void addValidationError(String error) {
  validationErrors.add(error);
 }

 protected void clearValidationErrors() {
  validationErrors.clear();
 }

 public List<String> getValidationErrors() {
  return validationErrors;
 }

}
