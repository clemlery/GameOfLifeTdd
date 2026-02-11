package gameoflifetdd.controler

import gameoflifetdd.GameEngine
import gameoflifetdd.GameObserver
import gameoflifetdd.model.CellState
import gameoflifetdd.model.Grid
import gameoflifetdd.view.ViewGame
import javafx.application.Platform
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import kotlin.math.floor

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
        if (view.height > 0) {
            updateCellShape(view.height, game)
        } else {
            Platform.runLater {
                updateCellShape(view.height, game)
            }
        }
        view.setSliderNbCellsMax((gridWidth * gridHeight) * 0.75)
    }

    override fun onGridChanged(grid: Grid) {
        view.cellGrid.clearCanvas()
        view.cellGrid.cellsMatrix.forEach { cellsColumns ->
            cellsColumns.forEach { cell ->
                val cellX : Int = floor(cell.first / view.cellGrid.cellWidth).toInt()
                val cellY : Int = floor(cell.second / view.cellGrid.cellHeight).toInt()
                val state = grid.cellAt(cellX, cellY).state

                if (state == CellState.ALIVE) view.cellGrid.drawAliveCell(cell.first, cell.second)
                else view.cellGrid.drawDeadCell(cell.first, cell.second)
            }
        }
    }

    // Update cell shape
    fun updateCellShape(newHeight: Double, game: GameEngine) {
        val newCellSize = newHeight / game.getGridWidth()
        println("====================== GameEngineSubscriber ======================")
        println("CenterContainer shape: ${view.getCenterContainerShape()}")
        println("View new height : $newHeight")
        println("Grid dimensions: ${game.getGridWidth()} x ${game.getGridHeight()}")
        println("Cell size: $newCellSize")
        println("Canvas size: ${view.cellGrid.width} x ${view.cellGrid.height}")
        view.cellGrid.width = (newCellSize * game.getGridWidth())
        view.cellGrid.height = (newCellSize * game.getGridHeight())
        view.cellGrid.cellWidth = newCellSize
        view.cellGrid.cellHeight = newCellSize
        view.cellGrid.clearCanvas()
        view.cellGrid.cellsMatrix.forEach { cellsColumns ->
            cellsColumns.forEach { cell ->
                val cellX = (cell.first / view.cellGrid.cellWidth).toInt()
                val cellY = (cell.second / view.cellGrid.cellHeight).toInt()
                val state = game.getCellAt(cellX, cellY).state

                if (state == CellState.ALIVE) view.cellGrid.drawAliveCell(cell.first, cell.second)
                else view.cellGrid.drawDeadCell(cell.first, cell.second)
            }
        }
    }
}