package gameoflifetdd

import gameoflifetdd.model.Grid


interface GameObserver {
    fun onGridInit(game : GameEngine)
    fun onGridChanged(grid : Grid)
}