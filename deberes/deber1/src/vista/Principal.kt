package vista

import clases.Datos
import clases.Files
import org.jdatepicker.DateModel
import org.jdatepicker.JDatePicker
import java.awt.*
import java.awt.event.*
import java.io.File
import java.io.FileWriter
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import javax.swing.*
import javax.swing.GroupLayout.Alignment
import javax.swing.LayoutStyle.ComponentPlacement
import javax.swing.filechooser.FileFilter
import javax.swing.table.DefaultTableModel
import javax.swing.table.TableModel
import kotlin.collections.ArrayList

class Principal : JFrame() {
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private fun initComponents() {
        val jPanel2 = JPanel()
        jPanel2.alignmentY = Component.TOP_ALIGNMENT
        jPanel2.alignmentX = Component.LEFT_ALIGNMENT
        jButtonAdd = JButton()
        jButtonAdd!!.isEnabled = true
        jButtonNew = JButton()
        jButtonNew!!.alignmentX = Component.CENTER_ALIGNMENT
        jButtonNew!!.isEnabled = false
        val jScrollPane2 = JScrollPane()
        jScrollPane2.font = Font("Tahoma", Font.PLAIN, 14)
        jTable1 = JTable()
        table = JTable()
        jButtonSave = JButton()
        jButtonSave!!.isEnabled = false
        jButtonDelete = JButton()
        jButtonDelete!!.isEnabled = false
        jMenuBar1 = JMenuBar()
        jMenu1 = JMenu()
        val jMenuItemCartas = JMenuItem()
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        val gbl_jPanel2 = GridBagLayout()
        gbl_jPanel2.columnWidths = intArrayOf(75, 150)
        gbl_jPanel2.rowHeights = intArrayOf(20, 20, 20, 20, 20)
        gbl_jPanel2.columnWeights = doubleArrayOf(0.0, 0.0)
        gbl_jPanel2.rowWeights = doubleArrayOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
        jPanel2.layout = gbl_jPanel2
        jTextFldCardName = JTextField()
        jTextFldCardName!!.horizontalAlignment = SwingConstants.LEFT
        jLabelCardName = JLabel()
        jLabelCardName!!.text = "Nombre:"
        val gbc_jLabelCardName = GridBagConstraints()
        gbc_jLabelCardName.anchor = GridBagConstraints.WEST
        gbc_jLabelCardName.gridx = 0
        gbc_jLabelCardName.gridy = 0
        jPanel2.add(jLabelCardName, gbc_jLabelCardName)
        jTextFldCardName!!.text = "Eng name"
        val gbc_jTextFldCardName = GridBagConstraints()
        gbc_jTextFldCardName.fill = GridBagConstraints.HORIZONTAL
        gbc_jTextFldCardName.gridx = 1
        gbc_jTextFldCardName.gridy = 0
        jPanel2.add(jTextFldCardName, gbc_jTextFldCardName)
        jLabelId = JLabel()
        jLabelId!!.text = "ID:"
        val gbc_jLabelId = GridBagConstraints()
        gbc_jLabelId.anchor = GridBagConstraints.WEST
        gbc_jLabelId.gridx = 0
        gbc_jLabelId.gridy = 1
        jPanel2.add(jLabelId, gbc_jLabelId)
        jTextFldId = JTextField()
        jTextFldId!!.horizontalAlignment = SwingConstants.LEFT
        jTextFldId!!.text = "Spa name"
        val gbc_jTextFldId = GridBagConstraints()
        gbc_jTextFldId.fill = GridBagConstraints.HORIZONTAL
        gbc_jTextFldId.gridx = 1
        gbc_jTextFldId.gridy = 1
        jPanel2.add(jTextFldId, gbc_jTextFldId)
        val gbc_panel = GridBagConstraints()
        gbc_panel.fill = GridBagConstraints.HORIZONTAL
        gbc_panel.gridx = 1
        gbc_panel.gridy = 2
        jPanel2.add(panel, gbc_panel)
        panel.layout = BoxLayout(panel, BoxLayout.X_AXIS)
        jButton1 = JButton()
        panel.add(jButton1)
        jButton1!!.text = "Obtener Datos"
        jButton1!!.addActionListener { evt: ActionEvent -> jButton1ActionPerformed() }
        jLabelLevel = JLabel()
        jLabelLevel!!.text = "Nivel/LINK:"
        val gbc_jLabelLevel = GridBagConstraints()
        gbc_jLabelLevel.anchor = GridBagConstraints.WEST
        gbc_jLabelLevel.gridx = 0
        gbc_jLabelLevel.gridy = 3
        jPanel2.add(jLabelLevel, gbc_jLabelLevel)
        jComboBoxLvl = JComboBox()
        jComboBoxLvl!!.model = DefaultComboBoxModel(arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"))
        val gbc_jComboBoxLvl = GridBagConstraints()
        gbc_jComboBoxLvl.fill = GridBagConstraints.HORIZONTAL
        gbc_jComboBoxLvl.gridx = 1
        gbc_jComboBoxLvl.gridy = 3
        jPanel2.add(jComboBoxLvl, gbc_jComboBoxLvl)
        jLabelTcg = JLabel()
        jLabelTcg!!.text = "TCG"
        val gbc_jLabelTcg = GridBagConstraints()
        gbc_jLabelTcg.anchor = GridBagConstraints.WEST
        gbc_jLabelTcg.gridx = 0
        gbc_jLabelTcg.gridy = 4
        jPanel2.add(jLabelTcg, gbc_jLabelTcg)
        chckbxDisponible = JCheckBox("Disponible")
        val gbc_chckbxDisponible = GridBagConstraints()
        gbc_chckbxDisponible.gridx = 1
        gbc_chckbxDisponible.gridy = 4
        jPanel2.add(chckbxDisponible, gbc_chckbxDisponible)
        jLabelPrecio = JLabel()
        jLabelPrecio!!.text = "Precio:"
        val gbc_jLabelPrecio = GridBagConstraints()
        gbc_jLabelPrecio.anchor = GridBagConstraints.WEST
        gbc_jLabelPrecio.gridx = 0
        gbc_jLabelPrecio.gridy = 5
        jPanel2.add(jLabelPrecio, gbc_jLabelPrecio)
        jButtonAdd!!.text = "Add"
        jButtonAdd!!.addActionListener { jButtonAddActionPerformed() }
        jButtonNew!!.text = "New"
        jButtonNew!!.addActionListener { evt: ActionEvent -> jButtonNewActionPerformed() }
        jButtonSaveEx.addActionListener { evt: ActionEvent -> jButtonSaveExActionPerformed() }
        jTable1!!.model = object : DefaultTableModel(arrayOf(), arrayOf(
                "Cartas"
        )) {
            val canEdit = booleanArrayOf(
                    false
            )
            override fun isCellEditable(rowIndex: Int, columnIndex: Int): Boolean {
                return canEdit[columnIndex]
            }
        }
        jTable1!!.setSelectionMode(ListSelectionModel.SINGLE_SELECTION)
        jTable1!!.showVerticalLines = false
        jTable1!!.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(evt: MouseEvent) {
                jTable1MouseClicked(evt)
            }
        })
        table!!.model = object : DefaultTableModel(arrayOf(), arrayOf(
                "Expansiones"
        )) {
            val canEdit = booleanArrayOf(
                    false
            )
            override fun isCellEditable(rowIndex: Int, columnIndex: Int): Boolean {
                return canEdit[columnIndex]
            }
        }
        table!!.setSelectionMode(ListSelectionModel.SINGLE_SELECTION)
        table!!.showVerticalLines = false
        table!!.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(evt: MouseEvent) {
                jTableMouseClicked(evt)
            }
        })
        jScrollPane2.setViewportView(jTable1)
        jButtonSave!!.text = "Save"
        jButtonSave!!.addActionListener { evt: ActionEvent -> jButtonSaveActionPerformed() }
        jButtonDelete!!.text = "X"
        jButtonDelete!!.addActionListener { evt: ActionEvent -> jButtonDeleteActionPerformed(evt) }
        jMenu1!!.text = "Config"
        jMenu1!!.actionCommand = "Ruta"
        jMenuItemCartas.text = "Cargar Cartas"
        jMenuItemCartas.addActionListener { evt -> jMenuItem1ActionPerformed() }
        val jMenuItemNew = JMenuItem("New Base")
        jMenuItemNew.addActionListener { evt: ActionEvent -> jMenuNewAction() }
        jMenu1!!.add(jMenuItemNew)
        jMenu1!!.add(jMenuItemCartas)
        jMenuBar1!!.add(jMenu1)
        jMenuItemEx = JMenuItem("Cargar Expansiones")
        jMenuItemEx!!.addActionListener { evt: ActionEvent -> jMenuLoadExpAction() }
        jMenu1!!.add(jMenuItemEx)
        jButtonAddEx.addActionListener { evt: ActionEvent -> jButtonAddExActionPerformed() }
        jButtonNew_1.addActionListener { evt: ActionEvent -> jButtonNew_1ActionPerformed(evt) }
        btnDltCrdFrmExp.addActionListener { evt: ActionEvent -> jBtnDltCrdFromExpActionPerformed() }
        jMenuBar = jMenuBar1
        val groupLayout = GroupLayout(contentPane)
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(jButtonAdd)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(jButtonSave)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(jButtonNew)
                                                .addGap(28)
                                                .addComponent(jButtonDelete, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE.toInt())
                                                .addComponent(btnAddCrdToExp))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                                        .addComponent(jScrollPane2, Alignment.LEADING, 0, 0, Short.MAX_VALUE.toInt())
                                                        .addComponent(jPanel2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE))
                                                .addGap(73)))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(jButtonAddEx, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(jButtonSaveEx, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(jButtonNew_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(btnDeleteExp)
                                                .addGap(36))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                                        .addComponent(jScrollPane2_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE.toInt())
                                                        .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE.toInt()))
                                                .addGap(18)))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btnDltCrdFrmExp)
                                        .addComponent(jScrollPane2_1_1, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        )
        btnDeleteExp.isEnabled = false
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(btnDltCrdFrmExp)
                                                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE.toInt())
                                                .addComponent(jScrollPane2_1_1, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                                                        .addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE.toInt())
                                                        .addComponent(jPanel2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE.toInt()))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                                                .addComponent(jButtonAdd)
                                                                                .addComponent(jButtonSave)
                                                                                .addComponent(jButtonNew))
                                                                        .addComponent(jButtonDelete))
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE.toInt())
                                                                .addPreferredGap(ComponentPlacement.RELATED))
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                                        .addComponent(jButtonAddEx)
                                                                        .addComponent(jButtonSaveEx)
                                                                        .addComponent(jButtonNew_1)
                                                                        .addComponent(btnDeleteExp)
                                                                        .addComponent(btnAddCrdToExp))
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPane2_1, 0, 0, Short.MAX_VALUE.toInt())))))
                                .addGap(10))
        )
        btnDeleteExp.addActionListener { e -> jButtonDeleteExpansionActionPerformed(e) }
        table_1 = JTable()
        table_1!!.showVerticalLines = false
        table_1!!.setSelectionMode(ListSelectionModel.SINGLE_SELECTION)
        jScrollPane2_1_1.setViewportView(table_1)
        table!!.showVerticalLines = false
        table!!.setSelectionMode(ListSelectionModel.SINGLE_SELECTION)
        jScrollPane2_1.setViewportView(table)
        jTextFldPrecio = JTextField()
        btnAddCrdToExp.addActionListener { evt: ActionEvent -> jBtnAddCrdToExpActionPerformed(evt) }
        jTextFldPrecio!!.text = "0"
        val gbc_jTextFldPrecio = GridBagConstraints()
        gbc_jTextFldPrecio.fill = GridBagConstraints.HORIZONTAL
        gbc_jTextFldPrecio.gridx = 1
        gbc_jTextFldPrecio.gridy = 5
        jPanel2.add(jTextFldPrecio, gbc_jTextFldPrecio)
        val gbl_panel_1 = GridBagLayout()
        gbl_panel_1.columnWidths = intArrayOf(75, 200)
        gbl_panel_1.rowHeights = intArrayOf(20, 20, 60, 20, 20)
        gbl_panel_1.columnWeights = doubleArrayOf(0.0, 1.0)
        gbl_panel_1.rowWeights = doubleArrayOf(0.0, 0.0, 0.0, 0.0, 0.0)
        gbl_panel_1.columnWeights = doubleArrayOf(Double.MIN_VALUE)
        gbl_panel_1.rowWeights = doubleArrayOf(Double.MIN_VALUE)
        panel_1.layout = gbl_panel_1
        val gbc_lblNewLabel = GridBagConstraints()
        gbc_lblNewLabel.anchor = GridBagConstraints.EAST
        gbc_lblNewLabel.gridx = 0
        gbc_lblNewLabel.gridy = 0
        panel_1.add(lblNewLabel, gbc_lblNewLabel)
        jTxttFldNameEx = JTextField()
        val gbc_jTxttFldNameEx = GridBagConstraints()
        gbc_jTxttFldNameEx.fill = GridBagConstraints.HORIZONTAL
        gbc_jTxttFldNameEx.gridx = 1
        gbc_jTxttFldNameEx.gridy = 0
        panel_1.add(jTxttFldNameEx, gbc_jTxttFldNameEx)
        jTxttFldNameEx!!.columns = 10
        val gbc_lblNewLabel_1 = GridBagConstraints()
        gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST
        gbc_lblNewLabel_1.gridx = 0
        gbc_lblNewLabel_1.gridy = 1
        panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1)
        jTxtFldIDEx = JTextField()
        val gbc_jTxtFldIDEx = GridBagConstraints()
        gbc_jTxtFldIDEx.fill = GridBagConstraints.HORIZONTAL
        gbc_jTxtFldIDEx.gridx = 1
        gbc_jTxtFldIDEx.gridy = 1
        panel_1.add(jTxtFldIDEx, gbc_jTxtFldIDEx)
        jTxtFldIDEx!!.columns = 10
        val gbc_lblNewLabel_2 = GridBagConstraints()
        gbc_lblNewLabel_2.fill = GridBagConstraints.VERTICAL
        gbc_lblNewLabel_2.gridx = 0
        gbc_lblNewLabel_2.gridy = 2
        panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2)
        val gbc_lblNewLabel_3 = GridBagConstraints()
        gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST
        gbc_lblNewLabel_3.gridx = 0
        gbc_lblNewLabel_3.gridy = 3
        panel_1.add(lblNewLabel_3, gbc_lblNewLabel_3)
        val gbc_jDatePicker = GridBagConstraints()
        gbc_jDatePicker.fill = GridBagConstraints.HORIZONTAL
        gbc_jDatePicker.gridx = 1
        gbc_jDatePicker.gridy = 2
        panel_1.add(jDatePick, gbc_jDatePicker)
        jTxtFldPrice = JTextField()
        val gbc_jTxtFldPrice = GridBagConstraints()
        gbc_jTxtFldPrice.fill = GridBagConstraints.HORIZONTAL
        gbc_jTxtFldPrice.gridx = 1
        gbc_jTxtFldPrice.gridy = 3
        panel_1.add(jTxtFldPrice, gbc_jTxtFldPrice)
        jTxtFldPrice!!.columns = 10
        val gbc_lblNewLabel_4 = GridBagConstraints()
        gbc_lblNewLabel_4.gridx = 0
        gbc_lblNewLabel_4.gridy = 4
        panel_1.add(lblNewLabel_4, gbc_lblNewLabel_4)
        val gbc_chckbxNewCheckBox = GridBagConstraints()
        gbc_chckbxNewCheckBox.gridx = 1
        gbc_chckbxNewCheckBox.gridy = 4
        chckbxNewCheckBox.horizontalAlignment = SwingConstants.LEFT
        panel_1.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox)
        contentPane.layout = groupLayout
        pack()
    }

    private fun jButtonAddActionPerformed() {
        if (fl.addCard(jTextFldCardName!!.text,
                        jTextFldId!!.text, jComboBoxLvl!!.selectedItem.toString().toInt(),
                        chckbxDisponible!!.isSelected, jTextFldPrecio!!.text.toDouble()) == 0) {
            fl.writeFileCards(fl.cartas)
            JOptionPane.showMessageDialog(this, "Añadida con éxito")
            jTable1!!.model = tableModel(fl.getCardsKeys(), "Expansiones")
        } else {
            JOptionPane.showMessageDialog(this, "La carta ya está registrada")
        }
    }

    private fun jButtonAddExActionPerformed() {
        val date = LocalDate.of(jDatePick.model.year, jDatePick.model.month + 1, jDatePick.model.day)
        if (fl.addExpansion(jTxttFldNameEx!!.text,
                        jTxtFldIDEx!!.text,
                        date, jTxtFldPrice!!.text.toDouble(),
                        chckbxNewCheckBox.isSelected) == 0) {
            fl.writeFileExpansions(fl.expansiones)
            JOptionPane.showMessageDialog(this, "Añadida con éxito")
            table!!.model = tableModel(fl.getExpKeys(), "Expansiones")
        } else {
            JOptionPane.showMessageDialog(this, "La carta ya está registrada")
        }
    }

    private fun jMenuItem1ActionPerformed() {
        fl.rutaCartas = loadChooser(this, ".cards")
        fl.readFile(fl.rutaCartas)
        jTable1!!.model = tableModel(fl.getCardsKeys(), "Cartas")
    }

    private fun jMenuNewAction() {
        saveChooser(this)
        fl.readFile(fl.rutaCartas)
        fl.readFileExpansiones(fl.rutaExpansiones)
    }

    private fun jMenuLoadExpAction() {
        fl.rutaExpansiones = loadChooser(this, ".expansiones")
        fl.readFileExpansiones(fl.rutaExpansiones)
        table!!.model = tableModel(fl.getExpKeys(), "Expansiones")
    }

    private fun jTable1MouseClicked(evt: MouseEvent) {
        if (evt.clickCount == 2 && !evt.isConsumed) {
            evt.consume()
            oldCardName = jTable1!!.getValueAt(jTable1!!.selectedRow, 0) as String
            val lista = fl.readCard(oldCardName) as List<Any?>
            jTextFldCardName!!.text = lista[0] as String?
            jTextFldId!!.text = lista[1] as String?
            jComboBoxLvl!!.selectedItem = lista[2].toString() + ""
            chckbxDisponible!!.isSelected = (lista[3] as Boolean?)!!
            jTextFldPrecio!!.text = lista[4].toString() + ""
            desactivar(arrayOf(jButtonSave, jButtonNew, jButtonDelete), true)
            jButtonAdd!!.isEnabled = false
        }
    }

    private fun convertToDateViaInstant(dateToConvert: LocalDate?): Date {
        return Date.from(dateToConvert!!.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant())
    }

    private fun jTableMouseClicked(evt: MouseEvent) {
        if (evt.clickCount == 2 && !evt.isConsumed) {
            evt.consume()
            oldExpansionName= table!!.getValueAt(table!!.selectedRow, 0) as String
            val lista = fl.readExp(oldExpansionName) as List<Any?>
            val calendar = Calendar.getInstance()
            calendar.time = convertToDateViaInstant(lista[2] as LocalDate?)
            val dateModel = jDatePick.model as DateModel<Calendar>
            dateModel.value = calendar
            jTxttFldNameEx!!.text = lista[0] as String?
            jTxtFldIDEx!!.text = lista[1] as String?
            chckbxNewCheckBox.isSelected = (lista[3] as Boolean?)!!
            jTxtFldPrice!!.text = lista[4].toString()
            table_1!!.model = tableModel(lista[5] as List<*>?, "Cartas en $oldExpansionName")
            desactivar(arrayOf(jButtonSaveEx, jButtonNew_1, btnDeleteExp, btnDltCrdFrmExp), true)
            jButtonAddEx.isEnabled = false
        }
    }

    private fun jButtonSaveActionPerformed() {
        fl.updateCard(oldCardName, jTextFldCardName!!.text,
                jTextFldId!!.text, jComboBoxLvl!!.selectedItem.toString().toInt(),
                chckbxDisponible!!.isSelected, jTextFldPrecio!!.text.toDouble())
        fl.updateCardForAll(oldCardName, jTextFldCardName!!.text)
        fl.writeFileCards(fl.cartas)
        JOptionPane.showMessageDialog(this, "Carta actualizada con éxito")
        jTable1!!.model = tableModel(fl.getCardsKeys(), "Expansiones")
        table_1!!.model = tableModel(fl.readExp(oldExpansionName)[5] as List<*>?, "Cartas en $oldExpansionName")
        oldCardName = jTextFldCardName!!.text
    }

    private fun jButtonSaveExActionPerformed() {
        val date = LocalDate.of(jDatePick.model.year, jDatePick.model.month, jDatePick.model.day)
        fl.updateExpansion(oldExpansionName, jTxttFldNameEx!!.text,
                jTxtFldIDEx!!.text,
                date, jTextFldPrecio!!.text.toDouble(),
                chckbxDisponible!!.isSelected,
                fl.readExp(oldExpansionName)[5] as MutableList<*>
        )
        fl.writeFileExpansions(fl.expansiones)
        JOptionPane.showMessageDialog(this, "Expansion actualizada con éxito")
        oldExpansionName = jTxttFldNameEx!!.text
        table!!.model = tableModel(fl.getExpKeys(), "Expansiones")
        table_1!!.model = tableModel(fl.readExp(oldExpansionName)[5] as List<*>?, "Cartas en $oldExpansionName")
    }

    private fun jButtonNewActionPerformed() {
        jTextFldCardName!!.text = ""
        jTextFldId!!.text = ""
        jComboBoxLvl!!.selectedIndex = 0
        jTextFldPrecio!!.text = ""
        desactivar(arrayOf(jButtonDelete, jButtonNew, jButtonSave), false)
        jButtonAdd!!.isEnabled = true
    }

    private fun jButtonNew_1ActionPerformed(evt: ActionEvent) {
        jTxttFldNameEx!!.text = ""
        jTxtFldIDEx!!.text = ""
        jTxtFldPrice!!.text = ""
        desactivar(arrayOf(btnDeleteExp, btnDltCrdFrmExp, jButtonNew_1, jButtonSaveEx), false)
        jButtonAddEx.isEnabled = true
    }

    private fun jBtnDltCrdFromExpActionPerformed() {
        val oldExp = fl.readExp(oldExpansionName) as MutableList<*>
        val oldExpCards = oldExp[5] as MutableList<String>
        oldExpCards.remove(table_1!!.getValueAt(table_1!!.selectedRow, 0))
        fl.updateExpansion(oldExpansionName, oldExpansionName,
                (oldExp[1] as String?)!!,(oldExp[2] as LocalDate?)!!,
                (oldExp[4] as Double?)!!,(oldExp[3] as Boolean?)!!,
                oldExpCards)
        fl.writeFileExpansions(fl.expansiones)
        table_1!!.model = tableModel(fl.readExp(oldExpansionName)[5] as List<*>?, "Cartas en $oldExpansionName")
    }

    private fun jButtonDeleteActionPerformed(evt: ActionEvent) { //GEN-FIRST:event_jButtonDeleteActionPerformed
        fl.deleteCard(oldCardName)
        fl.writeFileCards(fl.cartas)
        jTable1!!.model = tableModel(fl.getCardsKeys(), "Expansiones")
        if (fl.rutaExpansiones!=""){
            fl.deleteCardFromAll(oldCardName)
            fl.writeFileExpansions(fl.expansiones)
            table_1!!.model = tableModel(fl.readExp(oldExpansionName)[5] as List<*>?, "Cartas en $oldExpansionName")
        }
        JOptionPane.showMessageDialog(this, "Eliminada")
        jButtonNewActionPerformed()
    }

    private fun jButtonDeleteExpansionActionPerformed(evt: ActionEvent) { //GEN-FIRST:event_jButtonDeleteActionPerformed
        fl.deleteExpansion((table!!.getValueAt(table!!.selectedRow, 0) as String))
        fl.writeFileExpansions(fl.expansiones)
        JOptionPane.showMessageDialog(this, "Eliminada")
        table!!.model = tableModel(fl.getExpKeys(), "Expansiones")
        jButtonNew_1ActionPerformed(evt)
    }

    private fun jButton1ActionPerformed() { //GEN-FIRST:event_jButton1ActionPerformed
        val data = Datos().strToJson() as List<Any?>
        jTextFldCardName!!.text = data[0] as String?
        jTextFldId!!.text = data[1].toString() + ""
    }

    private fun jBtnAddCrdToExpActionPerformed(evt: ActionEvent) { //GEN-FIRST:event_jButton1ActionPerforme
        val date = LocalDate.of(jDatePick.model.year, jDatePick.model.month + 1, jDatePick.model.day)
        val li = fl.readExp(oldExpansionName)[5] as ArrayList<String>
        if (li.contains(oldCardName)) {
            JOptionPane.showMessageDialog(this, "La carta ya esta agregada")
        } else {
            li.add(oldCardName)
            fl.updateExpansion(oldExpansionName, jTxttFldNameEx!!.text,
                    jTxtFldIDEx!!.text, date, jTxtFldPrice!!.text.toDouble(), chckbxNewCheckBox.isSelected,
                    li)
            fl.writeFileExpansions(fl.expansiones)
            JOptionPane.showMessageDialog(this, "Añadida con éxito")
            fl.readFileExpansiones(fl.rutaExpansiones)
            table!!.model = tableModel(fl.getExpKeys(), "Expansiones")
            table_1!!.model = tableModel(fl.readExp(oldExpansionName)[5] as List<*>?, "Cartas en $oldExpansionName")
        }
    }

    private fun desactivar(jCampos: Array<Any?>, enabled: Boolean) {
        for (`object` in jCampos) {
            when (`object`!!.javaClass.toString()) {
                "class javax.swing.JTextArea" -> (`object` as JTextArea?)!!.isEditable = enabled
                "class javax.swing.JTextField" -> (`object` as JTextField?)!!.isEditable = enabled
                "class javax.swing.JComboBox" -> (`object` as JComboBox<*>?)!!.setEnabled(enabled)
                "class javax.swing.JButton" -> (`object` as JButton?)!!.isEnabled = enabled
                else -> {
                }
            }
        }
    }

    private fun tableModel(listaDeKeys: List<*>?, nameColumn: String): TableModel {
        val tableModel: DefaultTableModel = object : DefaultTableModel() {
            override fun isCellEditable(row: Int, column: Int): Boolean {
                return false
            }
        }
        tableModel.addColumn(nameColumn, listaDeKeys!!.toTypedArray())
        return tableModel
    }

    private fun saveChooser(parent: Component?) {
        val jFileCh = JFileChooser("user.home")
        jFileCh.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
        if (jFileCh.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
            try {
                val ruta = jFileCh.selectedFile.absolutePath
                val fw = FileWriter("$ruta\\datos.cards")
                val fw2 = FileWriter("$ruta\\datos.expansiones")
                fl.rutaCartas = "$ruta\\datos.cards"
                fl.rutaExpansiones = "$ruta\\datos.expansiones"
                fw.close()
                fw2.close()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    fun loadChooser(parent: Component?, tipo: String): String {
        var ruta = ""
        val jFileCh = JFileChooser()
        jFileCh.fileFilter = filtroParaFChoser(tipo)
        if (jFileCh.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            ruta = jFileCh.selectedFile.absolutePath
        }
        return ruta
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private var jButton1: JButton? = null
    private var jButtonAdd: JButton? = null
    private var jButtonDelete: JButton? = null
    private var jButtonNew: JButton? = null
    private var jButtonSave: JButton? = null
    private var jComboBoxLvl: JComboBox<String>? = null
    private var jLabelPrecio: JLabel? = null
    private var jLabelCardName: JLabel? = null
    private var jLabelLevel: JLabel? = null
    private var jLabelId: JLabel? = null
    private var jLabelTcg: JLabel? = null
    private var jMenu1: JMenu? = null
    private var jMenuBar1: JMenuBar? = null
    private var jTable1: JTable? = null
    private var jTextFldPrecio: JTextField? = null
    private var jTextFldCardName: JTextField? = null
    private var jTextFldId: JTextField? = null
    private val panel = JPanel()
    private var chckbxDisponible: JCheckBox? = null
    private val panel_1 = JPanel()
    private val btnAddCrdToExp = JButton("Add ->")
    private val lblNewLabel = JLabel("Nombre:")
    private val lblNewLabel_1 = JLabel("ID:")
    private val lblNewLabel_2 = JLabel("Fecha de Salida:")
    private val lblNewLabel_3 = JLabel("Precio:")
    private val lblNewLabel_4 = JLabel("Disponible TCG:")
    private var jTxttFldNameEx: JTextField? = null
    private var jTxtFldIDEx: JTextField? = null
    private var jTxtFldPrice: JTextField? = null
    private val chckbxNewCheckBox = JCheckBox("")
    private val jButtonAddEx = JButton()
    private val jButtonSaveEx = JButton()
    private val jButtonNew_1 = JButton()
    private val jDatePick = JDatePicker()
    private val jScrollPane2_1 = JScrollPane()
    private var table: JTable? = null
    private val jScrollPane2_1_1 = JScrollPane()
    private var jMenuItemEx: JMenuItem? = null
    private val btnDltCrdFrmExp = JButton("Quitar Carta")
    private var table_1: JTable? = null
    private val btnDeleteExp = JButton("Borrar Exp")

    companion object {
        var fl = Files()
        var oldCardName = ""
        var oldExpansionName = ""

        @JvmStatic
        fun main(args: Array<String>) {

            /* Create and display the form */EventQueue.invokeLater { Principal().isVisible = true }
        }

        private fun filtroParaFChoser(tipo: String): FileFilter {
            return object : FileFilter() {
                override fun accept(f: File): Boolean {
                    return if (f.isDirectory) {
                        true
                    } else {
                        f.name.toLowerCase().endsWith(tipo)
                    }
                }
                override fun getDescription(): String {
                    return tipo.toUpperCase() + " files (*" + tipo + ")"
                }
            }
        }
    }

    init {
        jButtonNew_1.text = "New"
        jButtonNew_1.isEnabled = false
        jButtonSaveEx.text = "Save"
        jButtonSaveEx.isEnabled = false
        jButtonAddEx.text = "Add"
        jButtonAddEx.isEnabled = true
        initComponents()
    }
}