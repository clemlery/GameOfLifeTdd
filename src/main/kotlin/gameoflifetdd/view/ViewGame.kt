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
import javafx.scene.input.MouseEvent
import javafx.scene.layout.*

class ViewGame() : BorderPane() {

    private val gridCells = GridPane().apply {
        alignment = Pos.CENTER
        isGridLinesVisible = true
        styleClass.add(NodeConfig.GRID_CELLS_CSS_CLASS)
    }

    var cellsMatrixUI : Array<Array<CellUI>> = arrayOf()

    private var leftContainer = StackPane()


    private var continueButton = Util.createIconButton("/icons/run.svg", NodeConfig.BUTTON_CONTINUE_ID)

    private val clearButton = Util.createIconButton("/icons/clear.svg", NodeConfig.BUTTON_CLEAR_ID)

    private val regenerateButton = Util.createIconButton("/icons/regenerate.svg", NodeConfig.BUTTON_REGEN_ID)

    private val backButton = Util.createIconButton("/icons/back.svg", NodeConfig.BUTTON_BACK_ID)

    private val importButton = Util.createIconButton("/icons/import.svg", NodeConfig.BUTTON_IMPORT_ID)

    private val exportButton = Util.createIconButton("/icons/export.svg", NodeConfig.BUTTON_EXPORT_ID)

    private val speedLabel = Label("Speed").apply {
        font = AppConfig.TEXT_FONT
    }
    private val speedSlider = Slider().apply {
        id = NodeConfig.SLIDER_SPEED_ID
        min = 20.0
        max = 500.0
    }

    private val nbCellsLabel = Label("Number of cells").apply {
        font = AppConfig.TEXT_FONT
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
        leftContainer = StackPane(gridCells).apply {
            alignment = Pos.CENTER
            prefHeightProperty().bind(heightProperty())
            prefWidthProperty().bind(heightProperty())
            maxWidthProperty().bind(
                this@ViewGame.heightProperty()
                .subtract(NodeConfig.GRID_CELLS_UP_MARGIN * 2)
                .subtract(NodeConfig.BUTTONS_WIDTH * 2)
            )
            maxHeightProperty().bind(
                this@ViewGame.heightProperty()
                .subtract(NodeConfig.GRID_CELLS_UP_MARGIN * 2)
                .subtract(NodeConfig.BUTTONS_WIDTH * 2)
            )
        }
        center = leftContainer

        val rightContainer = StackPane(gridSettings).apply {
            padding = Insets(NodeConfig.GRID_PADDING)
        }
        right = rightContainer

        setMargin(center, Insets(
            NodeConfig.GRID_CELLS_UP_MARGIN,
            0.0, 0.0,
            NodeConfig.GRID_CELLS_LEFT_MARGIN))
    }

    fun fixButtonControler(buttonToFix : Button, controler : EventHandler<ActionEvent>) {
        buttonToFix.onAction = controler
    }

    fun fixSliderControler(sliderToFix: Slider, controler: ChangeListener<Number>) {
        sliderToFix.valueProperty().addListener(controler)
    }

    fun fixGridPaneControler(gridPaneToFix: GridPane, controler: EventHandler<MouseEvent>) {
        gridPaneToFix.onMouseDragged = controler
    }

    fun addCellUIToGrid(x : Int, y : Int, cellToAdd : CellUI) {
        gridCells.add(cellToAdd, x, y)
    }

    fun getGridCells() = gridCells

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

    fun updateCellsShape(height: Double) {
        val cellsMatrixUIWidth = cellsMatrixUI.size
        val cellsMatrixUIHeight = cellsMatrixUI[0].size
        val newWidth = height / cellsMatrixUIWidth
        val newHeight = height / cellsMatrixUIHeight

        for (x in 0 until cellsMatrixUIWidth) {
            for (y in 0 until cellsMatrixUIHeight) {
                cellsMatrixUI[x][y].updateShape(
                    newWidth,
                    newHeight
                )
            }
        }
    }

    fun setSliderNbCellsMax(max: Double) {
        nbCellsSlider.max = max
    }

    fun clearGrid() {
        if (gridCells.children.isNotEmpty()) {
            gridCells.children.clear()
        }
    }

    fun toggleIcon(state: Boolean) {
        continueButton = if (state) {
            Util.changeButtonIcon("/icons/stop.svg", continueButton)
        } else {
            Util.changeButtonIcon("/icons/run.svg", continueButton)
        }
    }
}
