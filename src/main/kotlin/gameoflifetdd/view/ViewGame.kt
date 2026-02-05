package gameoflifetdd.view

import gameoflifetdd.config.AppConfig
import gameoflifetdd.config.NodeConfig
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Group
import javafx.scene.control.Button
import javafx.scene.layout.*
import javafx.scene.shape.SVGPath
import kotlin.math.max


class ViewGame : BorderPane() {

    private val gridCells = GridPane().apply {
        alignment = Pos.TOP_LEFT
        background = Background(
            BackgroundFill(
                javafx.scene.paint.Color.WHITE,
                CornerRadii.EMPTY,
                Insets.EMPTY
            )
        )
        styleClass.add(NodeConfig.GRID_CELLS_CSS_CLASS)
    }

    private val stopButton = createIconButton("/icons/stop.svg")

    private val runButton = createIconButton("/icons/run.svg")

    private val backButton = createIconButton("/icons/back.svg")

    private val regenerateButton = createIconButton("/icons/regenerate.svg")

    private val importButton = createIconButton("/icons/import.svg")

    private val exportButton = createIconButton("/icons/export.svg")


    private val gridSettings = GridPane().apply {
        alignment = Pos.TOP_CENTER
        add(stopButton, 0, 0)
        add(runButton, 1, 0)
        add(backButton, 2, 0)
        add(regenerateButton, 0, 1)
        add(importButton, 1, 1)
        add(exportButton, 2, 1)
        vgap = 80.0
        hgap = 80.0
    }

    init {
        val lefContainer = StackPane(gridCells).apply {
            padding = Insets(NodeConfig.GRID_PADDING)
            prefWidthProperty().bind(heightProperty())
            prefWidthProperty().bind(widthProperty())
        }
        left = lefContainer

        val rightContainer = StackPane(gridSettings).apply {
            padding = Insets(NodeConfig.GRID_PADDING)
        }
        right = rightContainer
    }

    fun createIconButton(path: String): Button {

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
        }

        return button
    }


    fun fixButtonControler(buttonToFix : Button, controler : EventHandler<ActionEvent>) {
        buttonToFix.onAction = controler
    }

    fun changeNodeAt(col: Int, row: Int, newCell : CellUI) {
        val nodeToRemove = gridCells.children.find { node ->
            (GridPane.getColumnIndex(node) ?: 0) == col && (GridPane.getRowIndex(node) ?: 0) == row
        }
        nodeToRemove?.let { gridCells.children.remove(it) }
        gridCells.add(newCell, col, row)
    }


    fun getBackButton() : Button {
        return backButton
    }
}
