package gameoflifetdd

import gameoflifetdd.model.Cell
import gameoflifetdd.model.Grid
import gameoflifetdd.model.NextGenerationCalculator
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

    fun changeCellAt(x : Int, y : Int, newCell : Cell) {
        grid.cells[x][y] = newCell
        notifyObservers()
    }

    fun getCellAt(x: Int, y : Int) = grid.cells[x][y]

    fun getGridWidth() = grid.width

    fun getGridHeight() = grid.height

    fun import() {
        TODO()
    }

    fun export() {
        TODO()
    }
}