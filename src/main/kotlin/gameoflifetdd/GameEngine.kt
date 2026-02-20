package gameoflifetdd

import gameoflifetdd.model.data.Pattern
import gameoflifetdd.model.game.Cell
import gameoflifetdd.model.game.Grid
import gameoflifetdd.model.game.NextGenerationCalculator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.javafx.JavaFx
import kotlinx.coroutines.launch

class GameEngine {
    private var nbCellsInit = 0
    private val observers = mutableListOf<GameObserver>()
    private var grid = Grid()
    private val generationCalculator = NextGenerationCalculator()
    private var gameSpeed = 250
    private lateinit var gameJob : Job
    private val gameScope = CoroutineScope(Dispatchers.JavaFx)

    fun addObserver(observer: GameObserver) {
        observers.add(observer)
    }

    fun removeObserver() {
        observers.removeFirst()
    }

    private fun notifyInitObservers() {
        observers.forEach { it.onGridInit(this) }
    }

    private fun notifyObservers() {
        observers.forEach { it.onGridChanged(grid) }
    }

    fun regenerate() {
        grid = Grid.ofAliveCellsRandom(nbCellsInit, grid.width, grid.height)
        notifyObservers()
    }

    fun init(gridWidth : Int, gridHeight : Int, numberOfCells : Int) {
        grid = Grid.ofAliveCellsRandom(numberOfCells, gridWidth, gridHeight)
        nbCellsInit = numberOfCells
        gameSpeed = 250
        notifyInitObservers()
    }

    fun start() {
        gameJob = gameScope.launch {
            while (isActive) {
                tick()
                delay(gameSpeed.toLong())
            }
        }
    }

    fun stop() {
        gameJob.cancel()
    }

    fun clear() {
        grid = Grid.empty(grid.width, grid.height)
        notifyObservers()
    }

    private fun tick() {
        grid = generationCalculator.next(grid)
        notifyObservers()
    }

    fun setSpeed(newSpeed : Int) {
        gameSpeed = newSpeed
        if (::gameJob.isInitialized && gameJob.isActive) {
            stop()
            start()
        }
    }

    fun setNbCellsInit(newNbCells : Int) {
        nbCellsInit = newNbCells
    }

    fun changeCellAt(x : Int, y : Int, newCell : Cell) {
        grid.cells[x][y] = newCell
        notifyObservers()
    }

    fun getCellAt(x: Int, y : Int) = grid.cells[x][y]

    fun getGridWidth() = grid.width

    fun getGridHeight() = grid.height

    fun getGrid() = grid

    fun import(pattern : Pattern) {
        grid.loadPatternInGrid(pattern)
        notifyInitObservers()
    }
}