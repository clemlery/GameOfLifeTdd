package gameoflifetdd.controler.modal

import gameoflifetdd.GameEngine
import gameoflifetdd.model.data.CsvDao
import gameoflifetdd.view.ViewMain
import javafx.event.ActionEvent
import javafx.event.EventHandler

class ControlerImportButton(
    val view: ViewMain,
    val dao : CsvDao,
    val game : GameEngine,
    val modalId: String
) : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent?) {
        println("current modal id : $modalId")

        val patterns = dao.getCurrentPatterns()
        println("patterns : $patterns")
        view.showImportModal(modalId)
        view.currentModal.loadLabels(patterns.map { it.toString() })
        view.currentModal.loadPagination(dao.getCurrentPage())

    }
}