package gameoflifetdd.view

import gameoflifetdd.config.AppConfig
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.GridPane
import gameoflifetdd.config.NodeConfig
import javafx.geometry.Insets
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.BorderPane
import javafx.scene.layout.CornerRadii
import javafx.scene.layout.StackPane


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

    private val backButton = Button("back").apply {
        id = NodeConfig.BUTTON_BACK_ID
    }

    private val stopButton = Button("stop")

    private val gridSettings = GridPane().apply {
        alignment = Pos.CENTER
        add(backButton, 0, 0)
        add(stopButton, 1, 0)
    }

    init {
        val lefContainer = StackPane(gridCells).apply {
            padding = Insets(NodeConfig.GRID_PADDING)
            prefWidthProperty().bind(heightProperty())
            prefWidthProperty().bind(widthProperty().multiply(0.5))
        }
        left = lefContainer

        val rightContainer = StackPane(gridSettings).apply {
            padding = Insets(NodeConfig.GRID_PADDING)
        }
        right = rightContainer
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
