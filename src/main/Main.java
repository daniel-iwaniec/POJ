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
 * LIABILITY, WHETHER IN AN ACTION OF ONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package main;

import java.awt.EventQueue;
import database.Database;
import view.MainView;

public class Main {

 protected static MainView view = MainView.getInstance();
 protected static Database database = Database.getInstance();

 public static void mainAction() {
  view.getHeader().setText("Informacje o projekcie");
  view.setIcon(MainView.HOME_ICON);

  view.hideAllViews();
  view.getIndexView().setVisible(true);
 }

 public static void licenseAction() {
  view.getHeader().setText("Licencja");
  view.setIcon(MainView.LICENSE_ICON);

  view.getLicenseTextArea().setText("");

  view.getLicenseTextArea().append("Permission is hereby granted, free of charge, to any person obtaining a copy\n");
  view.getLicenseTextArea().append("of this software and associated documentation files (the \"Software\"), to deal\n");
  view.getLicenseTextArea().append("in the Software without restriction, including without limitation the rights\n");
  view.getLicenseTextArea().append("to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n");
  view.getLicenseTextArea().append("copies of the Software, and to permit persons to whom the Software is\n");
  view.getLicenseTextArea().append("furnished to do so, subject to the following conditions:\n");
  view.getLicenseTextArea().append("\n");
  view.getLicenseTextArea().append("The above copyright notice and this permission notice shall be included in\n");
  view.getLicenseTextArea().append("all copies or substantial portions of the Software.\n");
  view.getLicenseTextArea().append("\n");
  view.getLicenseTextArea().append("THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n");
  view.getLicenseTextArea().append("IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n");
  view.getLicenseTextArea().append("FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n");
  view.getLicenseTextArea().append("AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n");
  view.getLicenseTextArea().append("LIABILITY, WHETHER IN AN ACTION OF ONTRACT, TORT OR OTHERWISE, ARISING FROM,\n");
  view.getLicenseTextArea().append("OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN\n");
  view.getLicenseTextArea().append("THE SOFTWARE.\n");

  view.hideAllViews();
  view.getLicenseView().setVisible(true);
 }

 public static void clearDatabaseAction() {
  database.clearDatabase();
  view.hideAllViews();
  view.getIndexView().setVisible(true);
 }

 public static void main(String[] args) {
  EventQueue.invokeLater(new Runnable() {
   @Override
   public void run() {
    mainAction();
    view.setVisible(true);
   }
  });
 }

}
