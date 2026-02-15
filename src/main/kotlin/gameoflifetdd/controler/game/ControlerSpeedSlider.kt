package gameoflifetdd.controler.game

import gameoflifetdd.GameEngine
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue

class ControlerSpeedSlider(val game: GameEngine) : ChangeListener<Number> {
    override fun changed(observable: ObservableValue<out Number>?, oldValue: Number?, newValue: Number?) {
        newValue?.let { game.setSpeed(it.toInt()) }
    }
}
