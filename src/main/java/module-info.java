module student.softwarei {
    requires javafx.controls;
    requires javafx.fxml;


    opens student.softwarei to javafx.fxml;
    opens model to javafx.fxml;
    exports model;

    exports student.softwarei;

    opens controller to javafx.fxml;
    exports controller;

}