package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;
import student.softwarei.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Main_Controller implements Initializable {

    private TableView<Part> partTableView;

    private TableView<Product> productTableView;

    private

    /**
     *  This function is very important, as everytime the Main Form is called, whether when
     *  we first start the program or when it called again from other forms, all the information
     *  will initialize first, therefore always keeping all the information up to date.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Started Main Form");
        System.out.println("Populating TableView with Test Data...");



    }




    /**
     *  This is what opens up the add parts form
     */
    public void Parts_Add_Button_Clicked(ActionEvent actionEvent) throws IOException {
        System.out.println("Parts Add Button Clicked!");

        Parent root = FXMLLoader.load(main.class.getResource("AddPart_Form.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 540, 623);

        stage.setScene(scene);
        stage.show();
    }


    private static Part selected_Part = null;


    public static Part get_Parts_SelectedPart(){
        return selected_Part;
    }

    /**
     *
     */
    public void Parts_Modify_Button_Clicked(ActionEvent actionEvent) throws IOException {
        System.out.println("Parts Modify Button Clicked!");



        Parent root = FXMLLoader.load(main.class.getResource("ModifyPart_Form.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 540, 623);

        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     */
    public void parts_Delete_Button_Clicked(ActionEvent actionEvent) {
        System.out.println("Parts Delete Button Clicked!");

    }

    /**
     *
     */
    public void products_Add_Button_Clicked(ActionEvent actionEvent) throws IOException {
        System.out.println("Products Add Button Clicked!");

        Parent root = FXMLLoader.load(main.class.getResource("AddProduct_Form.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 1182, 744);

        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     */
    public void products_Modify_Button_Clicked(ActionEvent actionEvent) throws IOException {
        System.out.println("Products Add Button Clicked!");

        Parent root = FXMLLoader.load(main.class.getResource("ModifyPart_Form.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root,  1182, 744);

        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     */
    public void products_Delete_Button_Clicked(ActionEvent actionEvent) {
        System.out.println("Products Add Button Clicked!");

    }
}
