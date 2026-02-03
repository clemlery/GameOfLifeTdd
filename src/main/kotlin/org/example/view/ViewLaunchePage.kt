package jeudelavietdd.org.example.view

import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox

class ViewLaunchePage : StackPane() {

    private val borderPage : BorderPane = BorderPane()
    private val gridInput : GridPane = GridPane()

    // Input grid width
    private val labelWidth = Label("Grid width")
    private val inputWidth = TextField()
    private val vboxWidth = VBox().children.addAll(
        labelWidth,
        inputWidth
    )

    // Input grid height
    private val labelHeight = Label("Grid height")
    private val inputHeight = TextField()
    private val vboxHeight = VBox().children.addAll(
        labelHeight,
        inputHeight
    )

    // Input number of cells
    private val labelNbCells = Label("Number of cells")
    private val inputNbCells = TextField()
    private val vboxNbCells = VBox().children.addAll(
        labelNbCells,
        inputNbCells
    )



}