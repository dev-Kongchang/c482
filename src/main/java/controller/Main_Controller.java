package controller;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class Main_Controller implements Initializable {

    // This the tableview for parts section
    @FXML
    private TableView<Part> parts_TableView;

    // This is the id tableColumn for the parts section
    @FXML
    private TableColumn<Part, Integer> Parts_TableViewColumn_ID;

    // This is the Part name tableColumn for the parts section
    @FXML
    private TableColumn<Part, String> Parts_TableViewColumn_PartName;

    // This is the Inventory tableColumn for the parts section
    @FXML
    private TableColumn<Part, Integer> Parts_TableViewColumn_Inventory;

    // This is the Price/Cost tableColumn for the parts section
    @FXML
    private TableColumn<Part, Double> Parts_TableViewColumn_PriceCost;

    // This is the Products tableview for the products section
    @FXML
    private TableView<Product> products_TableView;

    // This is the ID TableColumn for the Products section
    @FXML
    private TableColumn<Product, Integer> Products_TableViewColumn_ID;

    // This is the Product Name TableColumn for the Products section
    @FXML
    private TableColumn<Product, String> Products_TableViewColumn_Name;

    // This is the Inventory TableColumn for the Products section
    @FXML
    private TableColumn<Product, Integer> Products_TableViewColumn_Inventory;

    // This is the Price/Cost TableColumn for the Products section
    @FXML
    private TableColumn<Product, Double> Products_TableViewColumn_PriceCost;

    // This is the Search field for the parts section
    @FXML
    private TextField parts_Search_field;

    // This is the Search field for the products section
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

    /**
     * This module allows the tables to load properly,
     * sets the product and parts table accordingly to the Inventory
     */
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


    /**
     *  1.) Before going to the Modify Part Form, we set the current Part within
     *      Modify Form, so that the data can pass over to the form.
     *  2.) Checks if user selected a part from the parts section.
     *  3.) Opens the Modify part form
     */
    public void Parts_Modify_Button_Clicked(ActionEvent actionEvent) throws IOException {
        System.out.println("Parts Modify Button Clicked!");

        try {
            Part selectedPart = parts_TableView.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(main.class.getResource("ModifyPart_Form.fxml"));
            Parent root = loader.load();
            ModifyPart_Controller control = loader.getController();
            control.setSelectedPart(selectedPart);

            Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root, 540, 623);

            stage.setScene(scene);
            stage.show();
        } catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You did not select a part, Please Try again");
            System.out.println("User did not select a part to modify!");
            alert.show();
        }
    }

    /**
     *  1.) Warns User about deletion of the selected Part.
     *  2.) if agreed, deletes the part from inventory
     */
    public void parts_Delete_Button_Clicked(ActionEvent actionEvent) {
        System.out.println("Parts Delete Button Clicked!");
        try {
            Part selectedPart = parts_TableView.getSelectionModel().getSelectedItem();

            String name = selectedPart.getName();
            String id = String.valueOf(selectedPart.getId());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Remove");
            alert.setContentText("Are you sure you want to delete the following:\nProduct: " + name + "\nID: " + id);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.CANCEL){
                System.out.println("User clicked Cancel!");
                return;
            } else if (result.get() == ButtonType.OK){
                Inventory.deletePart(selectedPart);
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You did not select a Part, Please Try again");
            System.out.println("User did not select a Part to Delete!");
            alert.show();

        }
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
     *  When the Product modify button is clicked:
     *  1.) checks if user selected a product from the product table
     *  2.) sets the selected product into the product modify form 
     *  3.) opens the modify product form
     */
    public void products_Modify_Button_Clicked(ActionEvent actionEvent) throws IOException {
        System.out.println("Products Add Button Clicked!");
        try {
            Product selectedProduct = products_TableView.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader(main.class.getResource("ModifyProduct_Form.fxml"));
            Parent root = loader.load();
            ModifyProduct_Controller control = loader.getController();
            control.setSelectedProduct(selectedProduct);

            Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root,  1182, 744);

            stage.setScene(scene);
            stage.show();
        } catch (NullPointerException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You did not select a Product, Please Try again");
            System.out.println("User did not select a Product to modify!");
            alert.show();

        }
    }

    /**
     *  1.) Warns User about deletion of the selected Product.
     *  2.) if agreed, deletes the product from inventory
     */
    public void products_Delete_Button_Clicked(ActionEvent actionEvent) {
        System.out.println("Products Add Button Clicked!");
        Product selectedProduct = products_TableView.getSelectionModel().getSelectedItem();
        if(selectedProduct == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You did not select a Product, Please Try again");
            System.out.println("User did not select a Product to modify!");
            alert.show();
        }

        if(selectedProduct.getAllAssociatedParts().size() == 0){
            String name = selectedProduct.getName();
            String id = String.valueOf(selectedProduct.getId());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Remove");
            alert.setContentText("Are you sure you want to delete the following:/nProduct: " + name + "\nID: " + id);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.CANCEL){
                System.out.println("User clicked Cancel!");
                return;
            } else if (result.get() == ButtonType.OK){
                Inventory.deleteProduct(selectedProduct);
            }

        } else if (selectedProduct.getAllAssociatedParts().size() > 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("This Product has Parts\nIf you want to delete it.\nPlease delete the parts first!");
            System.out.println("User did not parts to the Product!");
            alert.show();
        }

    }

    /**
     * This checks for user input in the search field and populates the table accordingly
     * if users inputs integers when we try one, if string, we try the other way
     * also runs for loop to check each part if they contain the given character or string
     */
    @FXML
    public void parts_Search_Field_onAction(ActionEvent actionEvent) {
        String userSearch = parts_Search_field.getText();
        if (!userSearch.isBlank()){
            try {
                Part givenpart = Inventory.lookupPart(Integer.parseInt(userSearch));
                if (givenpart != null) {
                    parts_TableView.getSelectionModel().select(givenpart);
                } else if (givenpart == null){
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e){
                ObservableList<Part> checklist = Inventory.getAllParts();
                ObservableList<Part> newlist = FXCollections.observableArrayList();
                Part part;
                if(checklist.size() > 0){
                    for (int i = 0; i < checklist.size(); i ++){
                        part = checklist.get(i);
                        if (part.getName().toLowerCase().contains(userSearch.toLowerCase())){
                            newlist.add(part);
                        }
                    }

                    if (newlist.size() == 0){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("NOT FOUND!");
                        alert.setContentText("Item does not exist");
                        alert.show();
                    } else {
                        parts_TableView.setItems(newlist);
                    }
                }
            }
        } else{
            parts_TableView.setItems(Inventory.getAllParts());
        }
    }

    /** FUTURE ENHANCEMENT
     * This checks for user input in the search field and populates the table accordingly
     * if users inputs integers when we try one, if string, we try the other way
     * also runs for loop to check each product if they contain the given character or string
     * Future Enhancement: If this module can be broken down to one function, it would allow for more flexibility
     */
    public void products_Search_Field_onAction(ActionEvent actionEvent) {
        String userSearch = product_Search_field.getText();
        if (!userSearch.isBlank()){
            try {
                Product givenproduct = Inventory.lookupProduct(Integer.parseInt(userSearch));
                if (givenproduct != null) {
                    products_TableView.getSelectionModel().select(givenproduct);
                } else if (givenproduct == null){
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e){
                ObservableList<Product> checklist = Inventory.getAllProduct();
                ObservableList<Product> newlist = FXCollections.observableArrayList();
                Product product;
                if(checklist.size() > 0){
                    for (int i = 0; i < checklist.size(); i ++){
                        product = checklist.get(i);
                        if (product.getName().toLowerCase().contains(userSearch.toLowerCase())){
                            newlist.add(product);
                        }
                    }

                    if (newlist.size() == 0){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("NOT FOUND!");
                        alert.setContentText("Product does not exist");
                        alert.show();
                    }else {
                        products_TableView.setItems(newlist);
                    }
                }
            }
        } else{
            products_TableView.setItems(Inventory.getAllProduct());
        }

    }

    /**
     * exits the program with normal status code
     */
    public void Main_Form_ExitButton_Clicked(ActionEvent actionEvent) {
        System.exit(0);
    }
}
