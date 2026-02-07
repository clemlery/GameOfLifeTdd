package gameoflifetdd.controler

import gameoflifetdd.GameEngine
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.event.ActionEvent
import javafx.event.EventHandler

class ControlerNbCellsSlider(val game: GameEngine) : ChangeListener<Number> {

    override fun changed(observable: ObservableValue<out Number?>?, oldValue: Number?, newValue: Number?) {
        newValue?.let { game.setNbCellsInit(it.toInt()) }
    }

}