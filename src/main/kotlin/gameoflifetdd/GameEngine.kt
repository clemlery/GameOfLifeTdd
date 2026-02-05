package gameoflifetdd

import gameoflifetdd.model.Grid
import gameoflifetdd.model.NextGenerationCalculator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.javafx.JavaFx
import kotlinx.coroutines.launch

class GameEngine {
    private var nbCellsInit = 0
    private var grid = Grid()
    private val generationCalculator = NextGenerationCalculator()
    private var gameSpeed = 16
    private lateinit var gameJob : Job
    private val gameScope = CoroutineScope(Dispatchers.JavaFx)
    private val observers = mutableListOf<GameObserver>()

    fun addObserver(observer: GameObserver) {
        observers.add(observer)
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
        notifyObservers()
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
    }

    fun import() {
        TODO()
    }

    fun export() {
        TODO()
    }
}