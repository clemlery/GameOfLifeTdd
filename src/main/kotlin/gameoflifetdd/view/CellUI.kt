package gameoflifetdd.view

import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import gameoflifetdd.model.CellState
import javafx.event.EventHandler
import javafx.scene.input.MouseEvent

class CellUI(cellState: CellState, val x : Int, val y : Int) : Rectangle() {

    init {
        val color = if (cellState == CellState.ALIVE) {
            Color.BLACK
        } else Color.WHITE


        fill = color
        toBack()
    }

    fun updateColor(newState: CellState) {
        fill = if (newState == CellState.ALIVE) {
            Color.BLACK
        } else {
            Color.WHITE
        }
    }

    fun updateShape(newWidth: Double, newHeight: Double) {
        width = newWidth
        height = newHeight
    }

    fun fixCellControler(controler : EventHandler<MouseEvent>) {
        this.onMouseClicked = controler
    }
}
