package gameoflifetdd.model.strategy

import gameoflifetdd.model.dataccess.Pattern
import gameoflifetdd.model.game.Cell
import gameoflifetdd.model.game.CellState

interface Strategy {
    fun load(pattern: Pattern): MutableList<MutableList<CellState>>
}