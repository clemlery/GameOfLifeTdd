package gameoflifetdd.config

import javafx.geometry.Insets
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.paint.Color

class NodeConfig {
    companion object {
        // VIEW MAIN
        const val MAIN_VIEW_CSS_CLASS = "main-container"

        // VIEW HOME
        const val BUTTON_START_ID = "start-game-button"
        const val BUTTON_BACK_ID = "back-home-button"

        const val TEXT_FIELD_WIDTH = "text-field-input-width"
        const val TEXT_FIELD_HEIGHT = "text-field-input-height"
        const val TEXT_FIELD_NB_OF_CELLS = "text-field-input-nb-cells"

        const val TEXT_FIELDS_MAX_WIDTH = 250.0
        const val TEXT_FIELDS_MIN_HEIGHT = 40.0
        const val BUTTON_MAX_WIDTH = 250.0

        val TEXT_FIELDS_BACKGROUND_COLOR : Background = Background(
            BackgroundFill(
                Color.WHITE,
                CornerRadii.EMPTY,
                Insets.EMPTY
            )
        )
        const val TEXT_FIELDS_BORDER_RADIUS = 5

        val INPUT_LABELS = arrayOf("Grid Width", "Grid Height", "Number Of Cells")
    }
}
