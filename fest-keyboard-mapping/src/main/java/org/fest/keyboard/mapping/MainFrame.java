/*
 * Created on Mar 31, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2010 the original author or authors.
 */
package org.fest.keyboard.mapping;

import static org.fest.keyboard.mapping.CharMapping.newCharMapping;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Understands the application's main window.
 *
 * @author Alex Ruiz
 */
public class MainFrame extends javax.swing.JFrame {

  private static final long serialVersionUID = 1L;

  private final CharMappingTable mappingTable;
  private AboutDialog aboutDialog;

  /**
   * Creates a new </code>{@link MainFrame}</code>.
   */
  public MainFrame() {
    mappingTable = new CharMappingTable();
    initComponents();
    mappingScrollPane.setViewportView(mappingTable);
    setUpForMacOS();
    setIconImage(new javax.swing.ImageIcon(getClass().getResource("/fest16.png")).getImage()); // NOI18N
    mappingTable.addSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) return;
        updateDeleteSelectedRowsMenu();
      }
    });
    saveMappingFileMenu.addActionListener(new SaveMappingFileActionListener(this, mappingTable.getModel()));
  }

  private void setUpForMacOS() {
    helpMenu.setVisible(!MacSupport.instance().isMacOS());
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    charLabel = new javax.swing.JLabel();
    charTextField = new javax.swing.JTextField();
    mappingScrollPane = new javax.swing.JScrollPane();
    menuBar = new javax.swing.JMenuBar();
    fileMenu = new javax.swing.JMenu();
    saveMappingFileMenu = new javax.swing.JMenuItem();
    editMenu = new javax.swing.JMenu();
    deleteSelectedRowsMenu = new javax.swing.JMenuItem();
    helpMenu = new javax.swing.JMenu();
    aboutMenuItem = new javax.swing.JMenuItem();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("FEST Keyboard Mapping Tool");
    setLocationByPlatform(true);
    setMinimumSize(new java.awt.Dimension(260, 240));
    setName("mainFrame"); // NOI18N

    charLabel.setDisplayedMnemonic('E');
    charLabel.setLabelFor(charTextField);
    charLabel.setText("Enter the character to map:");

    charTextField.setDocument(new MaxLengthDocument());
    charTextField.setName("charTextField"); // NOI18N
    charTextField.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        charEntered(evt);
      }
    });

    fileMenu.setMnemonic('F');
    fileMenu.setText("File");

    saveMappingFileMenu.setMnemonic('S');
    saveMappingFileMenu.setText("Save As Mapping File");
    saveMappingFileMenu.setEnabled(false);
    fileMenu.add(saveMappingFileMenu);

    menuBar.add(fileMenu);

    editMenu.setMnemonic('E');
    editMenu.setText("Edit");
    editMenu.setName(""); // NOI18N

    deleteSelectedRowsMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
    deleteSelectedRowsMenu.setMnemonic('D');
    deleteSelectedRowsMenu.setText("Delete Selected Rows");
    deleteSelectedRowsMenu.setEnabled(false);
    deleteSelectedRowsMenu.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        deleteSelectedRows(evt);
      }
    });
    editMenu.add(deleteSelectedRowsMenu);

    menuBar.add(editMenu);

    helpMenu.setMnemonic('H');
    helpMenu.setText("Help");

    aboutMenuItem.setMnemonic('A');
    aboutMenuItem.setText("About");
    aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        showAboutWindow(evt);
      }
    });
    helpMenu.add(aboutMenuItem);

    menuBar.add(helpMenu);

    setJMenuBar(menuBar);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(mappingScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
          .addComponent(charTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
          .addComponent(charLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(mappingScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(charLabel)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(charTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void charEntered(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_charEntered
    try {
      mappingTable.getModel().addOrReplace(newCharMapping(evt));
      mappingTable.scrollAndSelectLastRow();
      updateUI();
    } catch (MappingNotFoundError ignored) {}
  }//GEN-LAST:event_charEntered

  private void showAboutWindow(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showAboutWindow
    showAboutWindow();
  }//GEN-LAST:event_showAboutWindow

  private void deleteSelectedRows(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSelectedRows
    mappingTable.deleteSelection();
    updateUI();
    if (mappingTable.getRowCount() == 0) clearAndGiveFocusToCharTextField();
  }//GEN-LAST:event_deleteSelectedRows

  private void updateUI() {
    saveMappingFileMenu.setEnabled(mappingTable.getRowCount() > 0);
    updateDeleteSelectedRowsMenu();
  }

  private void updateDeleteSelectedRowsMenu() {
    deleteSelectedRowsMenu.setEnabled(mappingTable.getSelectedRowCount() > 0);
  }

  void showAboutWindow() {
    if (aboutDialog == null) aboutDialog = new AboutDialog(this, true);
    aboutDialog.setLocationRelativeTo(this);
    aboutDialog.setVisible(true);
  }

  private void clearAndGiveFocusToCharTextField() {
    charTextField.setText("");
    giveFocusToCharTextField();
  }

  void giveFocusToCharTextField() {
    charTextField.requestFocusInWindow();
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JMenuItem aboutMenuItem;
  private javax.swing.JLabel charLabel;
  private javax.swing.JTextField charTextField;
  private javax.swing.JMenuItem deleteSelectedRowsMenu;
  private javax.swing.JMenu editMenu;
  private javax.swing.JMenu fileMenu;
  private javax.swing.JMenu helpMenu;
  private javax.swing.JScrollPane mappingScrollPane;
  private javax.swing.JMenuBar menuBar;
  private javax.swing.JMenuItem saveMappingFileMenu;
  // End of variables declaration//GEN-END:variables
}
