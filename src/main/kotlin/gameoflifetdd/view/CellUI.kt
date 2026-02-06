package gameoflifetdd.view

import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import gameoflifetdd.model.CellState

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

    fun updateCellColor() {
        TODO()
    }
}
