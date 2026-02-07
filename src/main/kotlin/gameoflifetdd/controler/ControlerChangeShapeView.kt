package gameoflifetdd.controler

import gameoflifetdd.GameEngine
import gameoflifetdd.view.ViewGame
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.event.ActionEvent
import javafx.event.EventHandler

class ControlerChangeShapeView(val view : ViewGame, val game : GameEngine) : ChangeListener<Number> {

    override fun changed(p0: ObservableValue<out Number?>?, p1: Number?, newWidth: Number?) {
        view.calculCellPerfectShape(game.getGridWidth(), game.getGridHeight(), 0.0)
    }
}

