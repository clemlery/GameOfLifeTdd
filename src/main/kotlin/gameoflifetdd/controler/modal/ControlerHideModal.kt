package gameoflifetdd.controler.modal

import gameoflifetdd.view.ViewMain
import javafx.event.EventHandler
import javafx.scene.input.MouseEvent

class ControlerHideModal(val view: ViewMain) : EventHandler<MouseEvent> {

    override fun handle(event: MouseEvent?) {
        view.hideImportModal()
        view.currentModal.resetNodes()
    }

}