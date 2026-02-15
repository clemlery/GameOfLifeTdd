package gameoflifetdd.model.strategy

import gameoflifetdd.model.data.Pattern
import gameoflifetdd.model.game.CellState

interface Strategy {
    fun load(pattern: Pattern): MutableList<MutableList<CellState>>
}