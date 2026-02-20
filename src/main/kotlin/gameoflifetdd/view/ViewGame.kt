package gameoflifetdd.view

import gameoflifetdd.config.AppConfig
import gameoflifetdd.config.NodeConfig
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Slider
import javafx.beans.value.ChangeListener
import javafx.scene.control.Label
import javafx.scene.layout.*

class ViewGame : StackPane() {

    val cellGrid = CellGrid(
        0.0,
        NodeConfig.GRID_BACKGROUND_COLOR
    ).apply {
        styleClass.add(NodeConfig.GRID_CELLS_CSS_CLASS)
        toFront()
    }

    private val patternLabel = Label("3enginecordershiprake.cells").apply {
        font = AppConfig.TEXT_FONT_SMALL
        alignment = Pos.BOTTOM_LEFT
        padding = Insets(20.0, 0.0, 20.0, 0.0)
    }
    private val bookmarkButton = Util.createIconButton("/icons/game/bookmark.svg", NodeConfig.BUTTON_BOOKMARK_ID).apply {
    }
    private val patternBar = BorderPane().apply {
        left = patternLabel
        right = bookmarkButton
        isVisible = true
        maxWidthProperty().bind(cellGrid.widthProperty())
        alignment = Pos.CENTER
    }

    private val rightContainer = StackPane()

    private var continueButton = Util.createIconButton("/icons/game/run.svg", NodeConfig.BUTTON_CONTINUE_ID)

    private val clearButton = Util.createIconButton("/icons/game/clear.svg", NodeConfig.BUTTON_CLEAR_ID)

    private val regenerateButton = Util.createIconButton("/icons/game/regenerate.svg", NodeConfig.BUTTON_REGEN_ID)

    private val backButton = Util.createIconButton("/icons/game/back.svg", NodeConfig.BUTTON_BACK_ID)

    private val importButton = Util.createIconButton("/icons/game/import.svg", NodeConfig.BUTTON_IMPORT_ID)

    private val exportButton = Util.createIconButton("/icons/game/export.svg", NodeConfig.BUTTON_EXPORT_ID)

    private val speedLabel = Label("Speed").apply {
        font = AppConfig.TEXT_FONT_SMALL
    }
    private val speedSlider = Slider().apply {
        id = NodeConfig.SLIDER_SPEED_ID
        min = 20.0
        max = 500.0
    }

    private val nbCellsLabel = Label("Number of cells").apply {
        font = AppConfig.TEXT_FONT_SMALL
    }
    private val nbCellsSlider = Slider().apply {
        id = NodeConfig.SLIDER_NB_CELLS_ID
    }

    private val gridSettings = GridPane().apply {
        alignment = Pos.TOP_CENTER
        add(continueButton, 0, 0)
        add(clearButton, 1, 0)
        add(regenerateButton, 2, 0)
        add(backButton, 0, 1)
        add(importButton, 1, 1)
        add(exportButton, 2, 1)
        add(VBox(speedLabel, speedSlider), 0, 2, 3, 1)
        add(VBox(nbCellsLabel, nbCellsSlider), 0, 3, 3, 1)
        vgap = 80.0
        hgap = 80.0
    }

    init {
        BorderPane.setAlignment(patternLabel, Pos.BOTTOM_LEFT)

        val mainContainer = BorderPane()
        mainContainer.center = VBox(patternBar, cellGrid).apply {
            alignment = Pos.CENTER
        }

        rightContainer.children.add(gridSettings)
        rightContainer.apply {
            id = NodeConfig.RIGHT_CONTAINER_ID
            padding = Insets(NodeConfig.GRID_PADDING)
        }
        mainContainer.right = rightContainer

        setMargin(mainContainer.center, Insets(
            NodeConfig.GRID_CELLS_UP_MARGIN,
            0.0, 0.0,
            NodeConfig.GRID_CELLS_LEFT_MARGIN)
        )
        styleClass.add(NodeConfig.VIEW_GAME_CSS_CLASS)

        setAlignment(cellGrid, Pos.CENTER)
        children.add(mainContainer)
    }

    fun fixButtonControler(buttonToFix : Button, controler : EventHandler<ActionEvent>) {
        buttonToFix.onAction = controler
    }

    fun fixSliderControler(sliderToFix: Slider, controler: ChangeListener<Number>) {
        sliderToFix.valueProperty().addListener(controler)
    }

    fun getSliderById(id: String) : Slider {
        return when(id) {
            NodeConfig.SLIDER_SPEED_ID -> speedSlider
            NodeConfig.SLIDER_NB_CELLS_ID -> nbCellsSlider
            else -> throw IllegalArgumentException("Id : $id doesn't exist")
        }
    }

    fun getButtonById(id : String) : Button {
        return when (id) {
            NodeConfig.BUTTON_CONTINUE_ID -> continueButton
            NodeConfig.BUTTON_CLEAR_ID -> clearButton
            NodeConfig.BUTTON_REGEN_ID -> regenerateButton
            NodeConfig.BUTTON_BACK_ID -> backButton
            NodeConfig.BUTTON_IMPORT_ID -> importButton
            NodeConfig.BUTTON_EXPORT_ID -> exportButton
            else -> throw IllegalArgumentException("Id : $id doesn't exist")
        }
    }

    fun setSliderNbCellsMax(max: Double) {
        nbCellsSlider.max = max
    }

    fun clearGrid() {
        cellGrid.clearCanvas()
    }

    fun toggleIcon(state: Boolean) {
        continueButton = if (state) {
            Util.changeButtonIcon("/icons/game/stop.svg", continueButton)
        } else {
            Util.changeButtonIcon("/icons/game/run.svg", continueButton)
        }
    }
}
