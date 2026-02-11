package gameoflifetdd.controler

import gameoflifetdd.GameEngine
import gameoflifetdd.model.CellState
import gameoflifetdd.view.CellGrid
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue

class ControlerCellGridResize(val cellGrid: CellGrid, val game: GameEngine) : ChangeListener<Number> {

    override fun changed(observable: ObservableValue<out Number?>?, oldHeight: Number?, newHeight: Number?) {
        val newCellSize = newHeight!!.toDouble() / game.getGridWidth()
        println("====================== ControlerCellGridResize ======================")
        println("CenterContainer new height")
        println("Grid dimensions: ${game.getGridWidth()} x ${game.getGridHeight()}")
        println("Cell size: $newCellSize")
        println("Canvas size: ${cellGrid.width} x ${cellGrid.height}")
        cellGrid.width = (newCellSize * game.getGridWidth())
        cellGrid.height = (newCellSize * game.getGridHeight())
        cellGrid.cellWidth = newCellSize
        cellGrid.cellHeight = newCellSize
        cellGrid.clearCanvas()
        cellGrid.cellsMatrix.forEach { cellsColumns ->
            cellsColumns.forEach { cell ->
                val cellX = (cell.first / cellGrid.cellWidth).toInt()
                val cellY = (cell.second / cellGrid.cellHeight).toInt()
                val state = game.getCellAt(cellX, cellY).state

                if (state == CellState.ALIVE) cellGrid.drawAliveCell(cell.first, cell.second)
                else cellGrid.drawDeadCell(cell.first, cell.second)
            }
        }
    }
}