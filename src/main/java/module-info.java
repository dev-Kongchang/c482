module student.softwarei {
    requires javafx.controls;
    requires javafx.fxml;


    opens student.softwarei to javafx.fxml;

    exports student.softwarei;

    opens controller to javafx.fxml;



}