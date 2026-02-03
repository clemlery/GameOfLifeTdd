package jeudelavietdd.org.example.view

import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import model.CellState

class Cell(val cellState: CellState) : Rectangle() {
    init {
        val color = if (cellState == CellState.ALIVE) {
            Color.BLACK
        } else Color.WHITE
        this.width = 10.0
        this.height = 10.0
        fill = color
    }
}