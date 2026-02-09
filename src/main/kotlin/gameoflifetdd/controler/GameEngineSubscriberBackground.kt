package gameoflifetdd.controler

import gameoflifetdd.GameEngine
import gameoflifetdd.GameObserver
import gameoflifetdd.config.NodeConfig
import gameoflifetdd.model.CellState
import gameoflifetdd.model.Grid
import gameoflifetdd.view.BackgroundGameOfLife
import gameoflifetdd.view.CellUI
import javafx.application.Platform
import javafx.scene.effect.GaussianBlur

class GameEngineSubscriberBackground(val view: BackgroundGameOfLife) : GameObserver {

    override fun onGridInit(game: GameEngine) {
        val gridWidth = game.getGridWidth()
        val gridHeight = game.getGridHeight()

        view.clearCanvas()

        val cellsMatrix = mutableListOf<Array<Pair<Double, Double>>>()
        for (x in 0 until gridWidth) {
            val newCellsColumn = mutableListOf<Pair<Double, Double>>()
            for (y in 0 until gridHeight) {
                newCellsColumn.add(Pair(
                    x * view.cellWidth,
                    y * view.cellHeight
                ))
            }
            cellsMatrix.add(newCellsColumn.toTypedArray())
        }
        view.cellsMatrix = cellsMatrix.toTypedArray()
        view.effect = GaussianBlur(20.0)
    }

    override fun onGridChanged(grid: Grid) {
        view.clearCanvas()
        view.cellsMatrix.forEach { cellsColumns ->
            cellsColumns.forEach { cell ->
                val state = grid.cellAt(
                    (cell.first / view.cellWidth).toInt(),
                    (cell.second / view.cellHeight).toInt()
                ).state
                if (state == CellState.ALIVE) view.drawAlivedCell(cell.first, cell.second)
                else view.drawDeadCell(cell.first, cell.second)
            }
        }
    }

}