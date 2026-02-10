package gameoflifetdd.controler

import gameoflifetdd.GameEngine
import gameoflifetdd.model.CellState
import gameoflifetdd.view.CellGrid
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue

class ControlerCellGridResize(val cellGrid: CellGrid, val game: GameEngine) : ChangeListener<Number> {

    override fun changed(observable: ObservableValue<out Number?>?, oldHeight: Number?, newHeight: Number?) {
        val newCellSize = newHeight!!.toInt() / game.getGridWidth()
        cellGrid.cellWidth = newCellSize.toDouble()
        cellGrid.cellHeight = newCellSize.toDouble()
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