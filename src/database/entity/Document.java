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

public class Document extends AbstractEntity {

 private Integer id = AbstractEntity.NULL_ID;
 private Integer documentTypeId = AbstractEntity.NULL_ID;
 private String number = "";

 private DocumentType documentType = null;

 public Document() {
  super();
 }

 public Document(Integer id, DocumentType documentType, String number) {
  super();
  this.id = id;
  this.documentType = documentType;
  this.number = number;
 }

 public Integer getId() {
  return this.id;
 }

 public void setId(Integer id) {
  this.id = id;
 }

 public void setDocumentTypeId(Integer documentTypeId) {
  this.documentTypeId = documentTypeId;
 }

 public Integer getDocumentTypeId() {
  return this.documentTypeId;
 }

 public String getNumber() {
  return this.number;
 }

 public void setNumber(String number) {
  this.number = number;
 }

 public DocumentType getDocumentType() {
  if (this.documentType == null) {
   Database database = Database.getInstance();
   //DocumentType documentType = database.getDocumentTypeById(this.documentTypeId);
   //this.documentTypeId = documentType.getId();
  }
  
  return this.documentType;
 }

 public void setDocumentType(DocumentType documentType) {
  this.documentType = documentType;
  this.documentTypeId = documentType.getId();
 }

 @Override
 public void filter() {
  this.number = this.number.trim();
 }

 @Override
 public Boolean validate() {
  this.filter();
  return validationErrors.isEmpty();
 }

}
