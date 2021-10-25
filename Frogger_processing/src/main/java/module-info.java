module com.example.froggerv2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires core;
    //requires sound;

    opens com.example.froggerv2 to javafx.fxml;
    exports com.example.froggerv2;
    exports GameCommons;
    exports GraphicalElements;
    exports MovingElements;


}