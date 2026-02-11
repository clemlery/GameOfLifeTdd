package gameoflifetdd

import gameoflifetdd.model.game.Grid


interface GameObserver {
    fun onGridInit(game : GameEngine)
    fun onGridChanged(grid : Grid)
}