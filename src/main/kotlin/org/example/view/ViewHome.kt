package jeudelavietdd.org.example.view

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import jeudelavietdd.org.example.config.AppConfig
import jeudelavietdd.org.example.config.NodeConfig

class ViewHome : StackPane() {

    private val mainPage = BorderPane()
    private val title = Label("Game Of Life - Conway").apply {
        font = AppConfig.TITLE_FONT
        alignment = Pos.CENTER
    }

    private val gridInput : GridPane = GridPane()

    // Input grid width
    private val labelWidth = Label("Grid width").apply {
        font = AppConfig.TEXT_FONT
    }
    private val inputWidth = TextField().apply {
        maxWidth = 150.0
    }
    private val vboxWidth = VBox()

    // Input grid height
    private val labelHeight = Label("Grid height").apply {
        font = AppConfig.TEXT_FONT
    }
    private val inputHeight = TextField().apply {
        maxWidth = 150.0
    }
    private val vboxHeight = VBox()

    // Input number of cells
    private val labelNbCells = Label("Number of cells").apply {
        font = AppConfig.TEXT_FONT
    }
    private val inputNbCells = TextField().apply {
        maxWidth = 150.0
    }
    private val vboxNbCells = VBox()

    // Button to launch game
    private val startButton = Button("Start").apply {
        font = AppConfig.TEXT_FONT
        id = NodeConfig.BUTTON_START_ID
    }

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
        gridInput.vgap = 50.0
        gridInput.hgap = 50.0

        mainPage.top = title
        mainPage.center = gridInput
        mainPage.bottom = startButton
        mainPage.padding = Insets(30.0, 0.0, 60.0, 0.0)

        BorderPane.setAlignment(title, Pos.CENTER)
        BorderPane.setAlignment(startButton, Pos.CENTER)

        this.children.add(mainPage)
    }

    fun fixButtonControler(buttonToFix : Button, controler : EventHandler<ActionEvent>) {
        buttonToFix.onAction = controler
    }

    fun getStartButton() : Button {
        return startButton
    }

}