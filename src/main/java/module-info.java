module earth.groundctrl.javafxtreeviewselectionbug {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens earth.groundctrl.javafxtreeviewselectionbug to javafx.fxml;
    exports earth.groundctrl.javafxtreeviewselectionbug;
}
