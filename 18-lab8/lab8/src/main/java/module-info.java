module lab8 {
    requires transitive javafx.graphics;
    requires javafx.controls;

    opens lab8 to javafx.fxml;
    exports lab8;
}
