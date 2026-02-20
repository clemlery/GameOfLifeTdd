package gameoflifetdd.controler.game

import gameoflifetdd.GameEngine
import gameoflifetdd.config.NodeConfig
import gameoflifetdd.model.data.CsvDAO
import gameoflifetdd.view.ViewGame
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button

class ControlerFeatureGameButton(val view : ViewGame, val game : GameEngine, val dao: CsvDAO) : EventHandler<ActionEvent> {

    var toggleContinue = false
    var toggleBookmark = false

    override fun handle(event: ActionEvent?) {
        val buttonId = (event!!.source as Button).id
        when (buttonId) {
            NodeConfig.BUTTON_CONTINUE_ID -> {
                if (toggleContinue) game.stop()
                else game.start()
                toggleContinue = !toggleContinue
                view.toggleIconById(toggleContinue, NodeConfig.BUTTON_CONTINUE_ID)
            }
            NodeConfig.BUTTON_BOOKMARK_ID -> {
                val patternName = view.getPatternName() ?: return
                val pattern = dao.getPattern(patternName) ?: return
                if (toggleBookmark) dao.deleteBookmark(pattern)
                else dao.addBookmark(pattern)
                toggleBookmark = !toggleBookmark
                view.toggleIconById(toggleBookmark, NodeConfig.BUTTON_BOOKMARK_ID)
            }
            NodeConfig.BUTTON_CLEAR_ID -> game.clear()
            NodeConfig.BUTTON_REGEN_ID -> game.regenerate()
            NodeConfig.BUTTON_EXPORT_ID -> game.export()
        }
    }
}