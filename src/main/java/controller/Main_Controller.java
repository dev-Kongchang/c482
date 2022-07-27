package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import student.softwarei.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Main_Controller implements Initializable {

    // This initialization function is very important as it will allow the main form to show info again
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Started Main Form");

    }

    public void Parts_Add_Button_Clicked(ActionEvent actionEvent) throws IOException {
        System.out.println("Parts Add Button Clicked!");

        Parent root = FXMLLoader.load(main.class.getResource("AddPart_Form.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 540, 623);

        stage.setScene(scene);
        stage.show();
    }

    public void Parts_Modify_Button_Clicked(ActionEvent actionEvent) throws IOException {
        System.out.println("Parts Modify Button Clicked!");

        Parent root = FXMLLoader.load(main.class.getResource("ModifyPart_Form.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 540, 623);

        stage.setScene(scene);
        stage.show();
    }

    public void parts_Delete_Button_Clicked(ActionEvent actionEvent) {
        System.out.println("Parts Delete Button Clicked!");

    }

    public void products_Add_Button_Clicked(ActionEvent actionEvent) throws IOException {
        System.out.println("Products Add Button Clicked!");

        Parent root = FXMLLoader.load(main.class.getResource("AddProduct_Form.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 1182, 744);

        stage.setScene(scene);
        stage.show();
    }

    public void products_Modify_Button_Clicked(ActionEvent actionEvent) throws IOException {
        System.out.println("Products Add Button Clicked!");

        Parent root = FXMLLoader.load(main.class.getResource("ModifyPart_Form.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root,  1182, 744);

        stage.setScene(scene);
        stage.show();
    }

    public void products_Delete_Button_Clicked(ActionEvent actionEvent) {
        System.out.println("Products Add Button Clicked!");

    }
}
