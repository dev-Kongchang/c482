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
import javafx.stage.Stage;
import model.*;
import student.softwarei.main;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyProduct_Controller implements Initializable {

    // This the tableview for the product section
    @FXML
    private TableView<Part> modifyProduct_AllParts_TableView;

    // This is the TableColumn ID for allParts TableView
    @FXML
    private TableColumn<Part, Integer> modifyProduct_AllPartsColumn_ID ;

    // This is the TableColumn Name for allParts TableView
    @FXML
    private TableColumn<Part, String> modifyProduct_AllPartsColumn_Name ;

    // This is the TableColumn Inventory for allParts TableView
    @FXML
    private TableColumn<Part, Integer> modifyProduct_AllPartsColumn_Inventory ;

    // This is the TableColumn for allParts TableView Price
    @FXML
    private TableColumn<Part, Double> modifyProduct_AllPartsColumn_PriceCost ;

    // This is the associatedParts TableView on the Form
    @FXML
    private TableView<Part> modifyProduct_AssociatedParts_TableView;

    // This is the TableColumn for associatedParts TableView
    @FXML
    private TableColumn<Part, Integer> modifyProduct_AssociatedColumn_ID ;

    // This is the TableColumn ID for associatedParts TableView
    @FXML
    private TableColumn<Part, String> modifyProduct_AssociatedColumn_Name ;

    // This is the TableColumn Inventory for associatedParts TableView
    @FXML
    private TableColumn<Part, Integer> modifyProduct_AssociatedColumn_Inventory ;

    // This is the TableColumn Price for associatedParts TableView
    @FXML
    private TableColumn<Part, Double> modifyProduct_AssociatedColumn_PriceCost ;

    // This is the ID TextField on the form
    @FXML
    private TextField modifyProduct_ID_TextField;

    // This is the Name TextField on the form
    @FXML
    private  TextField modifyProduct_Name_TextField;

    // This is the Inventory TextField on the form
    @FXML
    private  TextField modifyProduct_Inv_TextField;

    // This is the Price TextField on the form
    @FXML
    private  TextField modifyProduct_Price_TextField;

    // This is the Max TextField on the form
    @FXML
    private  TextField modifyProduct_Max_TextField;

    // This is the Min TextField on the form
    @FXML
    private  TextField modifyProduct_Min_TextField;

    // This is the Observable List that will hold our associated parts
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    @FXML
    private TextField modifyProduct_Search_TextField;

    // This is the constructed selectedProduct from the Main form
    private Product selectedProduct;


    /**
     *  Let's initialize all the available parts into the parts section of the form.
     *  We can do this by grabbing all the available parts in the inventory
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize_tables();
    }

    /**
     *  This is the setter from the selected product from the main
     *  form. It will update the textfield with the selected product
     */
    public void setSelectedProduct(Product product){
        selectedProduct = product;

        modifyProduct_ID_TextField.setText(String.valueOf(selectedProduct.getId()));
        modifyProduct_ID_TextField.setDisable(true);
        modifyProduct_Name_TextField.setText(selectedProduct.getName());
        modifyProduct_Inv_TextField.setText(String.valueOf(selectedProduct.getStock()));
        modifyProduct_Price_TextField.setText(Double.toString(selectedProduct.getPrice()));
        modifyProduct_Min_TextField.setText(String.valueOf(selectedProduct.getMin()));
        modifyProduct_Max_TextField.setText(String.valueOf(selectedProduct.getMax()));

        associatedParts = selectedProduct.getAllAssociatedParts();
        modifyProduct_AssociatedParts_TableView.setItems(associatedParts);
        modifyProduct_AssociatedColumn_ID.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        modifyProduct_AssociatedColumn_Name.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        modifyProduct_AssociatedColumn_Inventory.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        modifyProduct_AssociatedColumn_PriceCost.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

    }

    /**
     * This is what loads the tableview and the asssociated parts tableview when the form starts
     */
    public void initialize_tables(){
        System.out.println("Initializing All Parts");
        modifyProduct_AllParts_TableView.setItems(Inventory.getAllParts());
        modifyProduct_AllPartsColumn_ID.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        modifyProduct_AllPartsColumn_Name.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        modifyProduct_AllPartsColumn_Inventory.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        modifyProduct_AllPartsColumn_PriceCost.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

    }

    /**
     * Checks if the user selected a Part from the allParts table,
     * if so, adds the part into the assoicatedParts
     */
    public void modifyProduct_AddPart_Button_Clicked(ActionEvent actionEvent) {
        Part selectedPart = modifyProduct_AllParts_TableView.getSelectionModel().getSelectedItem();
        if(selectedPart == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("You did select a Part to add from the top.\n Please select a Part from the top TableView");
            alert.show();
            return;
        }
        associatedParts.add(selectedPart);
    }


    /**
     * Checks if the user selected a Part from the AssociatedParts Table,
     * if so, Confirms with the User and deletes the part from the associatedParts table
     */
    public void modifyProduct_RemoveAssociatedPart_Button_Clicked(ActionEvent actionEvent) {
        Part selectedPart = modifyProduct_AssociatedParts_TableView.getSelectionModel().getSelectedItem();

        if(selectedPart == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("You did select a Associated Part from the Associated Parts Table.\n Please select a Part from the bottom TableView");
            alert.show();
            return;
        }
        String name = selectedPart.getName();
        int id = selectedPart.getId();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Associated Part Removal?");
        alert.setContentText("Do you really want to delete the following part: \n" + "Product Name: " + name + "\nID: " + String.valueOf(id) );
        Optional<ButtonType> results = alert.showAndWait();
        if(results.get() == ButtonType.OK){
            associatedParts.remove(selectedPart);
        }
    }


    /**
     *  This module here is the save button functionality.
     *  We'll first lay out the module in the following steps.
     *  1.) Use the try/except handle exceptions
     *      This method will allow catching issues, in this case
     *      keeping the text field free from bad inputs
     *  2.) We'll check the text from each textfield the user inputted
     *  3.)  Update the Object in the Inventory
     *  Logical Runtime: The index of the product was off by 1 since the id is
     *                   representative of the actual index.
     *
     *  Fix: used a variable placeholder to minus 1 index so that the correct index can be
     *  used when updating the object in the Inventory
     */
    public void modifyProduct_Save_Button_Clicked(ActionEvent actionEvent) {
        modifyProduct_ID_TextField.setDisable(true);
        try{
            if(check_for_errors() == true){return;}

            String Name = modifyProduct_Name_TextField.getText();
            double price = checkPrice(modifyProduct_Price_TextField.getText());
            int inv = checkInv(modifyProduct_Inv_TextField.getText());
            int min = checkMin(modifyProduct_Min_TextField.getText());
            int max = checkMax(modifyProduct_Max_TextField.getText());
            int id = Integer.parseInt(modifyProduct_ID_TextField.getText());

            Product newProduct = new Product(id, Name, price, inv, min, max);

            for (Part part : associatedParts){
                newProduct.addAssociatedPart(part);
            }

            int place = id - 1;

            Inventory.updateProduct(place, newProduct);

            System.out.println("modifyProducts Save Button Clicked!");

            Parent root = FXMLLoader.load(main.class.getResource("Main_Form.fxml"));
            Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root, 1011, 419);

            stage.setScene(scene);
            stage.show();

        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Make Sure there is a valid value for each field.");
            alert.show();
        }
    }

    /**
     *  takes us back to the main form.
     */
    public void modifyProduct_Cancel_Button_Clicked(ActionEvent actionEvent) throws IOException {
        System.out.println("modifyProducts Cancel Button Clicked!");

        Parent root = FXMLLoader.load(main.class.getResource("Main_Form.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 1011, 419);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Checks for error within logical requirements of each textfield,
     * if any, generates a combined message to the user about the error
     *   Logical RunTime: Was error.concat(message).
     *          The error message string was empty every time.
     *          fix: decided to use variable addition to add on the
     *          string. (error = error + errorMessage)
     */
    public boolean check_for_errors(){

        String error = "";
        String name = modifyProduct_Name_TextField.getText();
        int inv = Integer.parseInt(modifyProduct_Inv_TextField.getText());
        double price = Double.parseDouble(modifyProduct_Price_TextField.getText());
        int min = Integer.parseInt(modifyProduct_Min_TextField.getText());
        int max = Integer.parseInt(modifyProduct_Max_TextField.getText());

        if (inv > max){error = error + " The Inventory is more than the max capacity!\n";}
        if (inv < min){error = error + " The Inventory is less than the min capacity!\n";}
        if (inv <= 0 ){error = error + " The Inventory cannot be below 0! \n";}
        if (min > max){error = error + " The Min must be lower than the Max\n";}
        if (max < min){error = error + " The Max must be more than the Min\n";}
        if (name.isBlank()) {error = error + " The Name cannot be empty!!! \n";}

        if(!error.isBlank()) {
            System.out.println("Modify Parts: User inputted Mistake on the text field");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(error);
            alert.show();
            return true;
        }
        return false;

    }

    /**
     *  Simply checking if Max is in number format, if so return the value back
     *  or throw an error
     */
    public int checkMax(String what){

        try {
            return Integer.parseInt(what);
        } catch (NumberFormatException n){
            throw new NumberFormatException("The Max field must be Integer and greater than Min");
        }
    }

    /**
     *  Simply checking if Min is in number format, if so return the value back
     *  or throw an error
     */
    public int checkMin(String what){
        try {
            return Integer.parseInt(what);
        } catch (NumberFormatException n){
            throw new NumberFormatException("The Min field must be Integer and Less than Max");
        }
    }

    /**
     *  Simply checking if Price/Cost is in number format, if so return the value back
     *  or throw an error
     */
    public double checkPrice(String what){
        try {
            return Double.parseDouble(what);
        } catch (NumberFormatException n){
            throw new NumberFormatException("Price has to be a Integer that is greater than or equal to 0!");
        }
    }

    /**
     *  Simply checking if Inv is in number format, if so return the value back
     *  or throw an error
     */
    public int checkInv(String what){
        try {
            return Integer.parseInt(what);
        } catch (NumberFormatException n){
            throw new NumberFormatException("Inventory has to be a Integer in between Min and Max!");
        }
    }

    /**
     * This checks for user input in the search field and populates the table accordingly
     * if users inputs integers when we try one, if string, we try the other way
     * also runs for loop to check each product if they contain the given character or string
     * Future Enhancement: If this module can be broken down to one function, it would allow for more flexibility
     */
    public void modifyProduct_Search_TextField_onAction(ActionEvent actionEvent) {
        String userSearch = modifyProduct_Search_TextField.getText();
        if (!userSearch.isBlank()){
            try {
                Part givenpart = Inventory.lookupPart(Integer.parseInt(userSearch));
                if (givenpart != null) {
                    modifyProduct_AllParts_TableView.getSelectionModel().select(givenpart);
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
                        modifyProduct_AllParts_TableView.setItems(newlist);
                    }
                }
            }
        } else{
            modifyProduct_AllParts_TableView.setItems(Inventory.getAllParts());
        }
    }
}
