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


    /**
     *  Let's initialize all the available parts into the parts section of the form.
     *  We can do this by grabbing all the available parts in the inventory and
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize_tables();

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

        modifyProduct_AssociatedParts_TableView.setItems(associatedParts);
        modifyProduct_AssociatedColumn_ID.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        modifyProduct_AssociatedColumn_Name.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        modifyProduct_AssociatedColumn_Inventory.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        modifyProduct_AssociatedColumn_PriceCost.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
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

    public void modifyProduct_SaveButton_Clicked(ActionEvent actionEvent) {
        modifyProduct_ID_TextField.setDisable(true);
        try{
            if(check_for_errors() == true){return;}

            String Name = modifyProduct_Name_TextField.getText();
            double price = checkPrice(modifyProduct_Price_TextField.getText());
            int inv = checkInv(modifyProduct_Inv_TextField.getText());
            int min = checkMin(modifyProduct_Min_TextField.getText());
            int max = checkMax(modifyProduct_Max_TextField.getText());

            Product newProduct = new Product(get_next_id(), Name, price, inv, min, max);

            for (Part part : associatedParts){
                newProduct.addAssociatedPart(part);
            }

            Inventory.addProduct(newProduct);

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

    public void modifyProduct_CancelButton_Clicked(ActionEvent actionEvent) throws IOException {
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
    public float checkPrice(String what){
        try {
            return Float.parseFloat(what);
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

    public int get_next_id(){
        int id = 1;
        for(int i = 0; i < Inventory.getAllProduct().size(); i++){
            id ++;
        }
        return id;
    }
}
