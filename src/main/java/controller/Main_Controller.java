package controller;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;
import student.softwarei.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Main_Controller implements Initializable {



    @FXML
    private TableView<Part> parts_TableView;

    @FXML
    private TableColumn<Part, Integer> Parts_TableViewColumn_ID;

    @FXML
    private TableColumn<Part, String> Parts_TableViewColumn_PartName;

    @FXML
    private TableColumn<Part, Integer> Parts_TableViewColumn_Inventory;

    @FXML
    private TableColumn<Part, Double> Parts_TableViewColumn_PriceCost;


    @FXML
    private TableView<Product> products_TableView;

    @FXML
    private TableColumn<Product, Integer> Products_TableViewColumn_ID;

    @FXML
    private TableColumn<Product, String> Products_TableViewColumn_Name;

    @FXML
    private TableColumn<Product, Integer> Products_TableViewColumn_Inventory;

    @FXML
    private TableColumn<Product, Double> Products_TableViewColumn_PriceCost;

    @FXML
    private TextField parts_Search_field;

    @FXML
    private TextField product_Search_field;



    /**
     *  This function is very important, as everytime the Main Form is called, whether when
     *  we first start the program or when it called again from other forms, all the information
     *  will initialize first, therefore always keeping all the information up to date.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Started Main Form");
        System.out.println("Populating TableView with Test Data...");
        initialize_tables();


    }

    public void initialize_tables(){

        parts_TableView.setItems(Inventory.getAllParts());
        Parts_TableViewColumn_ID.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        Parts_TableViewColumn_PartName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        Parts_TableViewColumn_Inventory.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        Parts_TableViewColumn_PriceCost.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

        products_TableView.setItems(Inventory.getAllProduct());
        Products_TableViewColumn_ID.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        Products_TableViewColumn_Name.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        Products_TableViewColumn_Inventory.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        Products_TableViewColumn_PriceCost.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
    }




    /**
     *  This is what opens up the add parts form
     */
    public void Parts_Add_Button_Clicked(ActionEvent actionEvent) throws IOException {
        System.out.println("Parts Add Button Clicked!");

        FXMLLoader loader = new FXMLLoader(main.class.getResource("AddPart_Form.fxml"));
        Parent root = loader.load();
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
     *  1.) Before going to the Modify Part Form, we set the current Part within
     *      Modify Form, so that the data can pass over to the form.
     *  2.) Checks if user selected a part from the parts section.
     *  3.) Opens the Modify part form
     */
    public void Parts_Modify_Button_Clicked(ActionEvent actionEvent) throws IOException {
        System.out.println("Parts Modify Button Clicked!");
        Part selectedPart = parts_TableView.getSelectionModel().getSelectedItem();
        if(selectedPart == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You did not select a part, Please Try again");
            System.out.println("User did not select a part to modify!");
            alert.show();
        }

        FXMLLoader loader = new FXMLLoader(main.class.getResource("ModifyPart_Form.fxml"));
        Parent root = loader.load();
        ModifyPart_Controller control = loader.getController();
        control.setSelectedPart(selectedPart);

        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 540, 623);

        stage.setScene(scene);
        stage.show();
    }

    /**
     *  1.) Warns User about deletion of the selected Part.
     *  2.) if agreed, deletes the part from inventory
     */
    public void parts_Delete_Button_Clicked(ActionEvent actionEvent) {
        System.out.println("Parts Delete Button Clicked!");

    }

    /**
     *  This section opens up the Add Part Form
     */
    public void products_Add_Button_Clicked(ActionEvent actionEvent) throws IOException {
        System.out.println("Products Add Button Clicked!");

        FXMLLoader loader = new FXMLLoader(main.class.getResource("AddProduct_Form.fxml"));
        Parent root = loader.load();
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

        FXMLLoader loader = new FXMLLoader(main.class.getResource("ModifyProduct_Form.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root,  1182, 744);

        stage.setScene(scene);
        stage.show();
    }

    /**
     *  1.) Warns User about deletion of the selected Product.
     *  2.) if agreed, deletes the product from inventory
     */
    public void products_Delete_Button_Clicked(ActionEvent actionEvent) {
        System.out.println("Products Add Button Clicked!");

    }
}
