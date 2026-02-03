module gameoflifetdd {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    exports gameoflifetdd;
    exports gameoflifetdd.model;
    exports gameoflifetdd.view;
    exports gameoflifetdd.config;
    exports gameoflifetdd.controler;

    opens gameoflifetdd to javafx.fxml;
    opens gameoflifetdd.view to javafx.fxml;
    opens gameoflifetdd.controler to javafx.fxml;
}
