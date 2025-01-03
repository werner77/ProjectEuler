package com.behindmedia.euler.common

import java.awt.Font
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JTextArea

class TextWindow(private val title: String, rows: Int = 40, columns: Int = 40) : JFrame() {

    private val theText: JTextArea

    init {
        val theFrame = JFrame()
        theFrame.title = this.title
        theFrame.setSize(300, 300)
        theFrame.setLocation(550, 400)
        val mainPanel = JPanel()
        theText = JTextArea(rows, columns) //create the text area
        theText.font = Font("monospaced", Font.PLAIN, 12)
        mainPanel.add(theText) //add the text area to the panel
        theFrame.contentPane.add(mainPanel) //add the panel to the frame
        theFrame.pack()
        theFrame.isVisible = true
    }

    fun setText(string: String) {
        theText.text = string
    }
}