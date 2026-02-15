package gameoflifetdd.view

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
import gameoflifetdd.config.AppConfig
import gameoflifetdd.config.NodeConfig

class ViewHome() : StackPane() {

    private val mainPage = BorderPane()
    private val title = Label("Game Of Life").apply {
        font = AppConfig.TITLE_FONT
        alignment = Pos.CENTER
    }

    private val gridInput : GridPane = GridPane()

    // Input grid width
    private val labelWidth = Label("Grid width").apply {
        font = AppConfig.TEXT_FONT_SMALL
    }
    private val inputWidth = TextField().apply {
        maxWidth = NodeConfig.TEXT_FIELDS_MAX_WIDTH
        minHeight = NodeConfig.TEXT_FIELDS_MIN_HEIGHT
        id = NodeConfig.TEXT_FIELD_WIDTH
        background = NodeConfig.TEXT_FIELDS_BACKGROUND_COLOR
        prefWidth = Double.MAX_VALUE
    }
    private val vboxWidth = VBox()

    // Input grid height
    private val labelHeight = Label("Grid height").apply {
        font = AppConfig.TEXT_FONT_SMALL
    }
    private val inputHeight = TextField().apply {
        maxWidth = NodeConfig.TEXT_FIELDS_MAX_WIDTH
        minHeight = NodeConfig.TEXT_FIELDS_MIN_HEIGHT
        id = NodeConfig.TEXT_FIELD_HEIGHT
        background = NodeConfig.TEXT_FIELDS_BACKGROUND_COLOR
        prefWidth = Double.MAX_VALUE
    }
    private val vboxHeight = VBox()

    // Input number of cells
    private val labelNbCells = Label("Number of cells").apply {
        font = AppConfig.TEXT_FONT_SMALL
    }
    private val inputNbCells = TextField().apply {
        maxWidth = NodeConfig.TEXT_FIELDS_MAX_WIDTH
        minHeight = NodeConfig.TEXT_FIELDS_MIN_HEIGHT
        id = NodeConfig.TEXT_FIELD_NB_OF_CELLS
        background = NodeConfig.TEXT_FIELDS_BACKGROUND_COLOR
        prefWidth = Double.MAX_VALUE
    }
    private val vboxNbCells = VBox()

    // Button to launch game
    private val startButton = Button("Start").apply {
        font = AppConfig.TEXT_FONT_SMALL
        id = NodeConfig.BUTTON_START_ID
        minHeight = 40.0
        maxWidth = NodeConfig.BUTTON_MAX_WIDTH
        styleClass.add(NodeConfig.BUTTON_STANDARD_CSS_CLASS)
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
        gridInput.vgap = 80.0
        gridInput.hgap = 150.0

        mainPage.top = title
        mainPage.center = gridInput
        mainPage.bottom = startButton
        mainPage.padding = Insets(30.0, 0.0, 60.0, 0.0)

        BorderPane.setAlignment(title, Pos.CENTER)
        BorderPane.setAlignment(startButton, Pos.CENTER)
        BorderPane.setMargin(gridInput, Insets(100.0, 0.0, 0.0, 0.0))
        BorderPane.setMargin(startButton,Insets(150.0, 0.0, 0.0, 0.0))
        this.children.add(mainPage)
    }

    fun fixButtonControler(buttonToFix : Button, controler : EventHandler<ActionEvent>) {
        buttonToFix.onAction = controler
    }

    fun getStartButton() : Button {
        return startButton
    }

    fun getTextFieldInputById(id : String) : TextField {
        return when(id) {
            NodeConfig.TEXT_FIELD_WIDTH -> inputWidth
            NodeConfig.TEXT_FIELD_HEIGHT -> inputHeight
            NodeConfig.TEXT_FIELD_NB_OF_CELLS -> inputNbCells
            else -> throw IllegalArgumentException()
        }
    }

}
