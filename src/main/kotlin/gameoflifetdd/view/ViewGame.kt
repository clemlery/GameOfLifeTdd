package gameoflifetdd.view

import gameoflifetdd.config.NodeConfig
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Group
import javafx.scene.control.Button
import javafx.scene.control.Slider
import javafx.beans.value.ChangeListener
import javafx.scene.layout.*
import javafx.scene.shape.SVGPath
import kotlin.math.max

class ViewGame : BorderPane() {

    private val gridCells = GridPane().apply {
        alignment = Pos.TOP_LEFT
        isGridLinesVisible = true
        styleClass.add(NodeConfig.GRID_CELLS_CSS_CLASS)
    }

    private val cellsUI : Array<Array<CellUI>> = arrayOf()

    private val stopButton = createIconButton("/icons/stop.svg", NodeConfig.BUTTON_STOP_ID)

    private val runButton = createIconButton("/icons/run.svg", NodeConfig.BUTTON_RUN_ID)

    private val backButton = createIconButton("/icons/back.svg", NodeConfig.BUTTON_BACK_ID)

    private val regenerateButton = createIconButton("/icons/regenerate.svg", NodeConfig.BUTTON_REGEN_ID)

    private val importButton = createIconButton("/icons/import.svg", NodeConfig.BUTTON_IMPORT_ID)

    private val exportButton = createIconButton("/icons/export.svg", NodeConfig.BUTTON_EXPORT_ID)

    private val speedSlider = Slider().apply {
        id = NodeConfig.SLIDER_SPEED_ID
        min = 20.0
        max = 500.0
    }

    private val nbCellsSlider = Slider().apply {
        id = NodeConfig.SLIDER_NB_CELLS_ID
    }

    private val gridSettings = GridPane().apply {
        alignment = Pos.TOP_CENTER
        add(stopButton, 0, 0)
        add(runButton, 1, 0)
        add(backButton, 2, 0)
        add(regenerateButton, 0, 1)
        add(importButton, 1, 1)
        add(exportButton, 2, 1)
        add(speedSlider, 0, 2, 3, 1)
        vgap = 80.0
        hgap = 80.0
    }

    private var cellPerfectWidth = 0.0
    private var cellPerfectHeight = 0.0

    init {
        val lefContainer = StackPane(gridCells).apply {
            padding = Insets(NodeConfig.GRID_PADDING)
            prefHeightProperty().bind(heightProperty())
            prefWidthProperty().bind(heightProperty())
        }
        center = lefContainer

        val rightContainer = StackPane(gridSettings).apply {
            padding = Insets(NodeConfig.GRID_PADDING)
        }
        right = rightContainer

        widthProperty().addListener { _, _, newWidth ->
            calculCellPerfectShape(gridCells.columnCount, gridCells.rowCount, newWidth.toDouble())
        }
    }

    fun createIconButton(path: String, buttonId : String): Button {

        val svgText = javaClass
            .getResource(path)
            ?.readText()
            ?: error("SVG not found: $path")

        val paths = Regex("d=\"([^\"]+)\"")
            .findAll(svgText)
            .map { match -> match.groupValues[1] }
            .toList()

        val svgNodes = paths.map { d ->
            SVGPath().apply {
                content = d
                styleClass.add("button-icon")
            }
        }

        val group = Group().apply {
            children.addAll(svgNodes)
        }

        val bounds = group.boundsInLocal

        val scaleFactor = 40 / max(bounds.width, bounds.height)

        group.scaleX = scaleFactor
        group.scaleY = scaleFactor

        val button = Button().apply {
            isPickOnBounds = true
            graphic = group
            alignment = Pos.CENTER
            styleClass.add("icon-button")
            id = buttonId
        }

        return button
    }


    fun fixButtonControler(buttonToFix : Button, controler : EventHandler<ActionEvent>) {
        buttonToFix.onAction = controler
    }

    fun fixSliderControler(sliderToFix: Slider, controler: ChangeListener<Number>) {
        sliderToFix.valueProperty().addListener(controler)
    }

    fun changeNodeAt(col: Int, row: Int, newCell : CellUI) {
        val nodeToRemove = gridCells.children.find { node ->
            (GridPane.getColumnIndex(node) ?: 0) == col && (GridPane.getRowIndex(node) ?: 0) == row
        }
        nodeToRemove?.let { gridCells.children.remove(it) }
        gridCells.add(newCell, col, row)
    }

    fun getProgressBarById(id: String) : Slider {
        return when(id) {
            NodeConfig.SLIDER_SPEED_ID -> speedSlider
            NodeConfig.SLIDER_NB_CELLS_ID -> nbCellsSlider
            else -> throw IllegalArgumentException("Id : $id doesn't exist")
        }
    }

    fun getButtonById(id : String) : Button {
        return when (id) {
            NodeConfig.BUTTON_STOP_ID -> stopButton
            NodeConfig.BUTTON_RUN_ID -> runButton
            NodeConfig.BUTTON_BACK_ID -> backButton
            NodeConfig.BUTTON_REGEN_ID -> regenerateButton
            NodeConfig.BUTTON_IMPORT_ID -> importButton
            NodeConfig.BUTTON_EXPORT_ID -> exportButton
            else -> throw IllegalArgumentException("Id : $id doesn't exist")
        }
    }

    fun calculCellPerfectShape(nbColumns: Int, nbRows: Int, newWidth : Double) {
        cellPerfectHeight = (newWidth / 2) / nbRows
        cellPerfectWidth = (newWidth / 2) / nbColumns
    }

    fun getPerfectCellWidth() = cellPerfectWidth

    fun getPerfectCellHeight() = cellPerfectHeight
}
