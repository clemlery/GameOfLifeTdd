package gameoflifetdd.model.strategy

import gameoflifetdd.model.dataccess.Pattern
import gameoflifetdd.model.game.Cell

interface Strategy {
    fun load(pattern: Pattern): MutableList<MutableList<Cell>>
}