package gameoflifetdd.view

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.GridPane
import javafx.scene.layout.StackPane
import gameoflifetdd.config.NodeConfig
import javafx.geometry.Insets
import kotlin.math.floor


class ViewGame : StackPane() {

    private var gridCells = GridPane().apply {
        padding = Insets(NodeConfig.GRID_PADDING)
        alignment = Pos.TOP_LEFT
        minWidth = floor(this.width / 2)
        maxWidth = floor(this.width / 2)
        minHeight = this.height - NodeConfig.GRID_PADDING * 2
        maxHeight = this.height - NodeConfig.GRID_PADDING * 2
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
        gridCells.width
    }

    fun fixButtonControler(buttonToFix : Button, controler : EventHandler<ActionEvent>) {
        buttonToFix.onAction = controler
    }

    fun getGridCells() : GridPane {
        return gridCells
    }

    fun setGridCells(newGrid : GridPane) {
        this.children.remove(gridCells)
        gridCells = newGrid.apply {
            padding = Insets(NodeConfig.GRID_PADDING)
            alignment = Pos.TOP_LEFT
            minWidth = floor(this.width / 2)
            maxWidth = floor(this.width / 2)
            minHeight = this.height - NodeConfig.GRID_PADDING * 2
            maxHeight = this.height - NodeConfig.GRID_PADDING * 2
        }
        this.children.add(gridCells)
    }

    fun getBackButton() : Button {
        return backButton
    }

}
