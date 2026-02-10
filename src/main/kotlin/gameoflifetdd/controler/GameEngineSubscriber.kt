package gameoflifetdd.controler

import gameoflifetdd.GameEngine
import gameoflifetdd.GameObserver
import gameoflifetdd.model.CellState
import gameoflifetdd.model.Grid
import gameoflifetdd.view.ViewGame
import javafx.application.Platform

class GameEngineSubscriber(val view: ViewGame) : GameObserver{

    override fun onGridInit(game : GameEngine) {
        val gridWidth = game.getGridWidth()
        val gridHeight = game.getGridHeight()

        view.clearGrid()

        val cellsMatrix = mutableListOf<Array<Pair<Double, Double>>>()
        for (x in 0 until gridWidth) {
            val newCellsColumn = mutableListOf<Pair<Double, Double>>()
            for (y in 0 until gridHeight) {
                newCellsColumn.add(Pair(
                    x * view.cellGrid.cellWidth,
                    y * view.cellGrid.cellHeight
                ))
            }
            cellsMatrix.add(newCellsColumn.toTypedArray())
        }
        view.cellGrid.cellsMatrix = cellsMatrix.toTypedArray()
        Platform.runLater {
            view.updateCellsShape(view.height, gridWidth)
        }
        view.setSliderNbCellsMax((gridWidth * gridHeight) * 0.75)
    }

    override fun onGridChanged(grid: Grid) {
        view.cellGrid.clearCanvas()
        view.cellGrid.cellsMatrix.forEach { cellsColumns ->
            cellsColumns.forEach { cell ->
                val cellX = (cell.first / view.cellGrid.cellWidth).toInt()
                val cellY = (cell.second / view.cellGrid.cellHeight).toInt()
                val state = grid.cellAt(cellX, cellY).state

                if (state == CellState.ALIVE) view.cellGrid.drawAliveCell(cell.first, cell.second)
                else view.cellGrid.drawDeadCell(cell.first, cell.second)
            }
        }
    }
}