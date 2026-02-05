package gameoflifetdd

import gameoflifetdd.model.Grid


interface GameObserver {
    fun onGridChanged(grid : Grid)
}