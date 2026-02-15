package gameoflifetdd.controler.modal

import gameoflifetdd.config.NodeConfig
import gameoflifetdd.model.data.CsvDAO
import gameoflifetdd.view.ViewMain
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button

class ControlerModalsButton(val view: ViewMain,val dao: CsvDAO ) : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent?) {
        val buttonId = (event!!.source as Button).id
        when {
            (buttonId == NodeConfig.BUTTON_NEXT_ID) -> {
                val patterns = dao.next() ?: return
                view.modal.loadLabels(patterns.map { it.toString() })
                view.modal.loadPagination(dao.getCurrentPage())
            }
            (buttonId == NodeConfig.BUTTON_PREVIOUS_ID) -> {
                val patterns = dao.previous() ?: return
                view.modal.loadLabels(patterns.map { it.toString() })
                view.modal.loadPagination(dao.getCurrentPage())
            }
        }
    }
}