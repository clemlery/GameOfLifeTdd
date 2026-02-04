package gameoflifetdd

import gameoflifetdd.model.Grid

class GameEngine {
    private val grid = Grid()
    private val observers = mutableListOf<GameObserver>()

    fun addObserver(observer: GameObserver) {
        observers.add(observer)
    }

    private fun notifyObservers() {
        observers.forEach { it.onGridChanged(grid) }
    }

    fun start() {
        TODO()
    }

    fun stop() {
        TODO()
    }

    fun tick() {
        TODO()
    }

    fun setSpeed() {
        TODO()
    }
}