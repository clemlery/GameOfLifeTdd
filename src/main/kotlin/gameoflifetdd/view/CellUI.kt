package gameoflifetdd.view

import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import gameoflifetdd.model.CellState
import javafx.event.ActionEvent
import javafx.event.EventHandler

class CellUI(cellState: CellState, val perfectWidth : Double, val perfectHeight : Double, val x : Int, val y : Int) : Rectangle() {

    init {
        val color = if (cellState == CellState.ALIVE) {
            Color.BLACK
        } else Color.WHITE
        width = perfectWidth
        height = perfectHeight
        fill = color
        toBack()
    }

    fun updateCellColor(newCellState: CellState) {
        fill = if (newCellState == CellState.ALIVE) {
            Color.BLACK
        } else {
            Color.WHITE
        }
    }

    fun fixCellControler(controler : EventHandler<ActionEvent>) {
        this.onMouseClicked = controler
    }
}
