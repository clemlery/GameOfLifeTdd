package gameoflifetdd

import gameoflifetdd.model.Grid
import gameoflifetdd.model.NextGenerationCalculator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.javafx.JavaFx
import kotlinx.coroutines.launch

class GameEngine {
    private var grid = Grid()
    private val generationCalculator = NextGenerationCalculator()
    private var gameSpeed = 16
    private val gameScope = CoroutineScope(Dispatchers.JavaFx)
    private val observers = mutableListOf<GameObserver>()

    fun addObserver(observer: GameObserver) {
        observers.add(observer)
    }

    private fun notifyObservers() {
        observers.forEach { it.onGridChanged(grid) }
    }

    fun init(gridWidth : Int, gridHeight : Int, numberOfCells : Int) {
        grid = Grid.ofAliveCellsRandom(numberOfCells, gridWidth, gridHeight)
    }

    fun start() {
        gameScope.launch {
            while (isActive) {
                tick()
                delay(gameSpeed.toLong())
            }
        }
    }

    fun stop() {
        gameScope
    }

    private fun tick() {
        grid = generationCalculator.next(grid)
        notifyObservers()
    }

    fun setSpeed(newSpeed : Int) {
        gameSpeed = newSpeed
    }
}