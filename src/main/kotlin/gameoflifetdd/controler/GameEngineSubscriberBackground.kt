package gameoflifetdd.controler

import gameoflifetdd.GameEngine
import gameoflifetdd.GameObserver
import gameoflifetdd.model.CellState
import gameoflifetdd.model.Grid
import gameoflifetdd.view.CellGrid
import javafx.scene.effect.GaussianBlur

class GameEngineSubscriberBackground(val component: CellGrid) : GameObserver {

    override fun onGridInit(game: GameEngine) {
        val gridWidth = game.getGridWidth()
        val gridHeight = game.getGridHeight()

        component.clearCanvas()

        val cellsMatrix = mutableListOf<Array<Pair<Double, Double>>>()
        for (x in 0 until gridWidth) {
            val newCellsColumn = mutableListOf<Pair<Double, Double>>()
            for (y in 0 until gridHeight) {
                newCellsColumn.add(Pair(
                    x * component.cellWidth,
                    y * component.cellHeight
                ))
            }
            cellsMatrix.add(newCellsColumn.toTypedArray())
        }
        component.cellsMatrix = cellsMatrix.toTypedArray()
        component.effect = GaussianBlur(20.0)
    }

    override fun onGridChanged(grid: Grid) {
        component.clearCanvas()
        component.cellsMatrix.forEach { cellsColumns ->
            cellsColumns.forEach { cell ->
                val cellX = (cell.first / component.cellWidth).toInt()
                val cellY = (cell.second / component.cellHeight).toInt()
                val state = grid.cellAt(cellX, cellY).state

                if (state == CellState.ALIVE) component.drawAliveCell(cell.first, cell.second)
                else component.drawDeadCell(cell.first, cell.second)
            }
        }
    }
}