package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import student.softwarei.main;

import java.io.IOException;

public class ModifyPart_Controller {

    /**
     *  When the cancel button is clicked, we will return to the main form,
     *  with the main form also initializing
     */
    public void modifyPart_Cancel_Button_Clicked(ActionEvent actionEvent) throws IOException {
        System.out.println("ModifyPart Cancel Button Clicked!");

        Parent root = FXMLLoader.load(main.class.getResource("Main_Form.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 1011, 419);

        stage.setScene(scene);
        stage.show();
    }

    public void modifyPart_Save_Button_Clicked(ActionEvent actionEvent) throws IOException {
        System.out.println("ModifyPart Save Button Clicked!");

        Parent root = FXMLLoader.load(main.class.getResource("Main_Form.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 1011, 419);

        stage.setScene(scene);
        stage.show();
    }
}
