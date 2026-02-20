package gameoflifetdd.controler.modal

import gameoflifetdd.model.data.CsvDao
import gameoflifetdd.view.ViewMain
import javafx.event.ActionEvent
import javafx.event.EventHandler

class ControlerSearchTextField(val view: ViewMain, val dao: CsvDao) : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent?) {
        val patternSearched = view.currentModal.getTextFromSearch() ?: return
        val patterns = dao.searchPatterns(
            search = patternSearched,
            updateLastSearch = true
        )
        view.currentModal.loadLabels(patterns.map { it.toString() })
        view.currentModal.loadPagination(dao.getCurrentPage())
    }

}