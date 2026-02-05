package gameoflifetdd.view

import gameoflifetdd.Main
import gameoflifetdd.config.AppConfig
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.GridPane
import javafx.scene.layout.StackPane
import gameoflifetdd.config.NodeConfig
import javafx.geometry.Insets
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import kotlin.math.floor


class ViewGame : StackPane() {

    private val gridCells = GridPane().apply {
        padding = Insets(NodeConfig.GRID_PADDING)
        alignment = Pos.TOP_LEFT
        background = Background(
            BackgroundFill(
                javafx.scene.paint.Color.WHITE,
                CornerRadii.EMPTY,
                Insets.EMPTY
            )
        )
    }

    private val backButton = Button("back").apply {
        id = NodeConfig.BUTTON_BACK_ID
    }

    init {
        this.children.addAll(
            gridCells,
            backButton
        )
        this.alignment = Pos.CENTER
        gridCells.apply {
            minWidth = floor(Main.getSceneWidth() / 2)
            maxWidth = floor(Main.getSceneWidth() / 2)
            minHeight = AppConfig.INITIAL_HEIGHT - NodeConfig.GRID_PADDING * 2
            maxHeight = AppConfig.INITIAL_HEIGHT - NodeConfig.GRID_PADDING * 2
        }
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
