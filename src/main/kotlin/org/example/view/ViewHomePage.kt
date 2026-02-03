package jeudelavietdd.org.example.view

import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox

class ViewHomePage : BorderPane() {

    private val title = Label("Game Of Life - Conway")

    private val gridInput : GridPane = GridPane()

    // Input grid width
    private val labelWidth = Label("Grid width")
    private val inputWidth = TextField()
    private val vboxWidth = VBox()

    // Input grid height
    private val labelHeight = Label("Grid height")
    private val inputHeight = TextField()
    private val vboxHeight = VBox()

    // Input number of cells
    private val labelNbCells = Label("Number of cells")
    private val inputNbCells = TextField()
    private val vboxNbCells = VBox()

    // Button to launch game
    private val startButton = Button()

    init {
        // Adding childre for Width vbox
        vboxWidth.children.addAll(
            labelWidth,
            inputWidth
        )

        // Adding children for Height vbox
        vboxHeight.children.addAll(
            labelHeight,
            inputHeight
        )

        // Adding children for NbCells vbox
        vboxNbCells.children.addAll(
            labelNbCells,
            inputNbCells
        )

        gridInput.add(vboxWidth, 0, 0)
        gridInput.add(vboxHeight, 1, 0)
        gridInput.add(vboxNbCells,0, 1)
        gridInput.alignment = Pos.CENTER

        this.top = title
        this.center = gridInput
        this.bottom = startButton

        BorderPane.setAlignment(title, Pos.CENTER)
    }

}