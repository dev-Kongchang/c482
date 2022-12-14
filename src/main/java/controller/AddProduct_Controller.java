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
import model.Inventory;
import model.Part;
import model.Product;
import student.softwarei.main;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddProduct_Controller implements Initializable {

    // This the tableview for the product section
    @FXML
    private TableView<Part> addProduct_AllParts_TableView;

    // This is the TableColumn ID for allParts TableView
    @FXML
    private TableColumn<Part, Integer> addProduct_AllPartsColumn_ID ;

    // This is the TableColumn Name for allParts TableView
    @FXML
    private TableColumn<Part, String> addProduct_AllPartsColumn_Name ;

    // This is the TableColumn Inventory for allParts TableView
    @FXML
    private TableColumn<Part, Integer> addProduct_AllPartsColumn_Inventory ;

    // This is the TableColumn for allParts TableView Price
    @FXML
    private TableColumn<Part, Double> addProduct_AllPartsColumn_PriceCost ;

    // This is the associatedParts TableView on the Form
    @FXML
    private TableView<Part> addProduct_AssociatedParts_TableView;

    // This is the TableColumn for associatedParts TableView
    @FXML
    private TableColumn<Part, Integer> addProduct_AssociatedColumn_ID ;

    // This is the TableColumn ID for associatedParts TableView
    @FXML
    private TableColumn<Part, String> addProduct_AssociatedColumn_Name ;

    // This is the TableColumn Inventory for associatedParts TableView
    @FXML
    private TableColumn<Part, Integer> addProduct_AssociatedColumn_Inventory ;

    // This is the TableColumn Price for associatedParts TableView
    @FXML
    private TableColumn<Part, Double> addProduct_AssociatedColumn_PriceCost ;

    // This is the ID TextField on the form
    @FXML
    private TextField addProduct_ID_TextField;

    // This is the Name TextField on the form
    @FXML
    private  TextField addProduct_Name_TextField;

    // This is the Inventory TextField on the form
    @FXML
    private  TextField addProduct_Inv_TextField;

    // This is the Price TextField on the form
    @FXML
    private  TextField addProduct_Price_TextField;

    // This is the Max TextField on the form
    @FXML
    private  TextField addProduct_Max_TextField;

    // This is the Min TextField on the form
    @FXML
    private  TextField addProduct_Min_TextField;

    @FXML
    private TextField addProduct_Search_TextField;

    // This is the Observable List that will hold our associated parts
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();


    /**
     *  Let's initialize all the available parts into the parts section of the form.
     *  We can do this by grabbing all the available parts in the inventory and
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Product> newlist = Inventory.getAllProduct();
        addProduct_ID_TextField.setDisable(true);
        int newid = newlist.size() + 1;
        addProduct_ID_TextField.setText(String.valueOf(newid));

        initialize_tables();
    }

    /**
     * This is what loads the tableview and the asssociated parts tableview when the form starts
     */
    public void initialize_tables(){
        System.out.println("Initializing All Parts");
        addProduct_AllParts_TableView.setItems(Inventory.getAllParts());
        addProduct_AllPartsColumn_ID.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        addProduct_AllPartsColumn_Name.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        addProduct_AllPartsColumn_Inventory.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        addProduct_AllPartsColumn_PriceCost.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

        addProduct_AssociatedParts_TableView.setItems(associatedParts);
        addProduct_AssociatedColumn_ID.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        addProduct_AssociatedColumn_Name.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        addProduct_AssociatedColumn_Inventory.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        addProduct_AssociatedColumn_PriceCost.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
    }

    /**
     * Checks if the user selected a Part from the allParts table,
     * if so, adds the part into the assoicatedParts
     */
    public void addProduct_AddPart_Button_Clicked(ActionEvent actionEvent) {
        Part selectedPart = addProduct_AllParts_TableView.getSelectionModel().getSelectedItem();
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
    public void addProduct_RemoveAssociatedPart_Button_Clicked(ActionEvent actionEvent) {
        Part selectedPart = addProduct_AssociatedParts_TableView.getSelectionModel().getSelectedItem();

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

    public void addProduct_SaveButton_Clicked(ActionEvent actionEvent) {
        addProduct_ID_TextField.setDisable(true);
        try{
            if(check_for_errors() == true){return;}

            String Name = addProduct_Name_TextField.getText();
            double price = checkPrice(addProduct_Price_TextField.getText());
            int inv = checkInv(addProduct_Inv_TextField.getText());
            int min = checkMin(addProduct_Min_TextField.getText());
            int max = checkMax(addProduct_Max_TextField.getText());

            Product newProduct = new Product(get_next_id(), Name, price, inv, min, max);

            for (Part part : associatedParts){
                newProduct.addAssociatedPart(part);
            }

            Inventory.addProduct(newProduct);

            System.out.println("AddProducts Save Button Clicked!");

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

    public void addProduct_CancelButton_Clicked(ActionEvent actionEvent) throws IOException {
        System.out.println("AddProducts Cancel Button Clicked!");

        Parent root = FXMLLoader.load(main.class.getResource("Main_Form.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 1011, 419);

        stage.setScene(scene);
        stage.show();
    }

    /** RUNTIME ERROR
     * Checks for error within logical requirements of each textfield,
     * if any, generates a combined message to the user about the error
     * RUNTIME ERROR Was error.concat(message).
     *          The error message string was empty every time.
     *          fix: decided to use variable addition to add on the
     *          string. (error = error + errorMessage)
     */
    public boolean check_for_errors(){

        String error = "";
        String name = addProduct_Name_TextField.getText();
        int inv = Integer.parseInt(addProduct_Inv_TextField.getText());
        double price = Double.parseDouble(addProduct_Price_TextField.getText());
        int min = Integer.parseInt(addProduct_Min_TextField.getText());
        int max = Integer.parseInt(addProduct_Max_TextField.getText());

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
     *  We can simply check the size of the total products within the Inventory
     *  Return the id when finished. if none, id will default to 1.
     */
    public int get_next_id(){
        int id = 1;
        for(int i = 0; i < Inventory.getAllProduct().size(); i++){
            id ++;
        }
        return id;
    }

    /** FUTURE ENHANCEMENT
     * This checks for user input in the search field and populates the table accordingly
     * if users inputs integers when we try one, if string, we try the other way
     * also runs for loop to check each product if they contain the given character or string
     * Future Enhancement: If this module can be broken down to one function, it would allow for more flexibility
     */
    public void addProduct_Search_TextField_onAction(ActionEvent actionEvent) {
        String userSearch = addProduct_Search_TextField.getText();
        if (!userSearch.isBlank()){
            try {
                Part givenpart = Inventory.lookupPart(Integer.parseInt(userSearch));
                if (givenpart != null) {
                    addProduct_AllParts_TableView.getSelectionModel().select(givenpart);
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
                        addProduct_AllParts_TableView.setItems(newlist);
                    }
                }
            }
        } else{
            addProduct_AllParts_TableView.setItems(Inventory.getAllParts());
        }
    }
}
