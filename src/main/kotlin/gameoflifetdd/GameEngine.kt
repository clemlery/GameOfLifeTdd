package gameoflifetdd

import gameoflifetdd.model.Grid
import gameoflifetdd.model.NextGenerationCalculator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class GameEngine {
    private var grid = Grid()
    private var gameSpeed = 16
    private val gameScope = CoroutineScope(Dispatchers.Default)
    private val observers = mutableListOf<GameObserver>()

    fun addObserver(observer: GameObserver) {
        observers.add(observer)
    }

    private fun notifyObservers() {
        observers.forEach { it.onGridChanged(grid) }
    }

    fun start(gridWidth : Int, gridHeight : Int, numberOfCells : Int) {
        grid = Grid.ofAliveCellsRandom(numberOfCells, gridWidth, gridHeight)
    }

    fun stop() {
        gameScope.cancel()
    }

    fun tick() {
        val generationCalculator = NextGenerationCalculator()
        gameScope.launch {
            while (isActive) {
                grid = generationCalculator.next(grid)
                notifyObservers()
                delay(gameSpeed.toLong())
            }
        }
    }

    fun setSpeed(newSpeed : Int) {
        gameSpeed = newSpeed
    }
}