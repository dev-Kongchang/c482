package controller;

import model.InHouse;
import model.Inventory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Outsourced;
import model.Part;
import student.softwarei.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPart_Controller implements Initializable {

    /**
     *  To make Life easier, instead of reusing Stage again, we'll initialize it here
     */
    Stage stage;

    /**
     *  To make Life easier, instead of reusing Scene again, we'll initialize it here
     */
    Scene scene;

    // Initializing Alert
    Alert alert;

    // Initializing button selection'
    private boolean is_InHouse_Selected;

    // This is the InHouse Radio button on the form
    @FXML
    private RadioButton addPart_InHouse_Radio;

    // This is the Outsourced Radio button on the form
    @FXML
    private RadioButton addPart_Outsourced_Radio;

    // This is the ID textfield on the form
    @FXML
    private TextField addPart_ID_TextField ;

    // This is the Name textfield on the form
    @FXML
    private TextField addPart_Name_TextField ;

    // This is the Inv textfield on the form
    @FXML
    private TextField addPart_Inv_TextField ;

    // This is the Price/Cost textfield on the form
    @FXML
    private TextField addPart_PriceCost_TextField ;

    // This is the Max textfield on the form
    @FXML
    private TextField addPart_Max_TextField ;

    // This is the Machine ID Label on the form

    @FXML
    private Label addPart_MachineID_Label ;

    // This is the Machine ID TextField on the form
    @FXML
    private TextField addPart_MachineID_TextField;

    // This is the Min textfield on the form
    @FXML
    private TextField addPart_Min_TextField ;
    /**
     *  We'll set the textfield to be Disabled
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addPart_ID_TextField.setDisable(true);
    }

    /**
     *  This module here is the save button functionality.
     *  We'll first lay out the module in the following steps.
     *  1.) Use the try/except handle exceptions
     *      This method will allow catching issues, in this case
     *      keeping the text field free from bad inputs
     *  2.) We'll check the text from each textfield the user inputted
     *  3.) We must know which radio button was selected, so we can
     *      add the update the corresponding objects
     *  4.) Add the object into the Inventory
     */
    public void addPart_Save_Button_Clicked(ActionEvent actionEvent) throws IOException {

        addPart_ID_TextField.setDisable(true);
        try{
            if(check_for_error() == true){return;}


            String Name = addPart_Name_TextField.getText();
            double price = checkPrice(addPart_PriceCost_TextField.getText());
            int inv = checkInv(addPart_Inv_TextField.getText());
            int min = checkMin(addPart_Min_TextField.getText());
            int max = checkMax(addPart_Max_TextField.getText());


            if(addPart_InHouse_Radio.isSelected()){
                int newNum = get_next_id();
                InHouse inHouse = new InHouse(newNum, Name, price, inv, min, max, newNum);
                Inventory.addPart(inHouse);
            }else {
                String company = checkCompanyName(addPart_MachineID_Label.getText());
                int newNum = get_next_id();
                Outsourced outsourced = new Outsourced(newNum, Name, price, inv, min, max, company);
                Inventory.addPart(outsourced);
            }

            System.out.println("AddParts Save Button Clicked!");

            Parent root = FXMLLoader.load(main.class.getResource("Main_Form.fxml"));
            Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root, 1011, 419);

            stage.setScene(scene);
            stage.show();


        } catch (Exception e){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Make Sure there is a valid value for each field.");
            alert.show();
        }

    }

    /**
     *  When the cancel button is clicked, we will return to the main form,
     *  with the main form also initializing
     */
    public void addPart_Cancel_Button_Clicked(ActionEvent actionEvent) throws IOException {
        System.out.println("AddParts Cancel Button Clicked!");

        Parent root = FXMLLoader.load(main.class.getResource("Main_Form.fxml"));
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();

        scene = new Scene(root, 1011, 419);

        stage.setScene(scene);
        stage.show();

    }

    /**
     * Checks for error within logical requirements of each textfield,
     * if any, generates a combined message to the user about the error
     *RUNTIME ERROR : Was error.concat(message).
     *          The error message string was empty every time.
     *          fix: decided to use variable addition to add on the
     *          string. (error = error + errorMessage)
     */
    public boolean check_for_error(){
        String error = "";
        String name = addPart_Name_TextField.getText();
        int max = Integer.parseInt(addPart_Max_TextField.getText());
        int min = Integer.parseInt(addPart_Min_TextField.getText());
        double price = Double.parseDouble(addPart_PriceCost_TextField.getText());
        int inv = Integer.parseInt(addPart_Inv_TextField.getText());

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
     *  To check if the MachineID is in format, we'll have a function that we call
     *  to check on the format of the MachineID, we will check by using try/catch
     *  if it passes number formatting, we'll return a int. If not, we'll throw error
     */
    public int checkMachineID(String what){
        try{
            return Integer.parseInt(what);
        } catch (NumberFormatException n){
            throw new NumberFormatException("The Machine ID much be a integer value. Please try Again!");
        }
    }

    /**
     *  We'll check the company name by simply if the field is empty or not,
     *  Company Names may include numbers, this is why we do not need to use format checking
     */
    public String checkCompanyName(String what){
        if(what.isBlank()){
            throw new NumberFormatException("Company Name Field is Empty!");
        }
        return what;
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
     *  When the radio button is selected:
     *  boolean variable is updated
     *  sets true/false for both radiobuttons
     */
    public void Outsource_RadioButton_Clicked(ActionEvent actionEvent) {
        is_InHouse_Selected = false;
        addPart_InHouse_Radio.setSelected(false);
        addPart_Outsourced_Radio.setSelected(true);
        addPart_MachineID_Label.setText("Company Name");
    }

    /**
     *  When the radio button is selected:
     *  boolean variable is updated
     *  sets true/false for both radiobuttons
     */
    public void InHouse_RadioButton_Clicked(ActionEvent actionEvent) {
        is_InHouse_Selected = true;
        addPart_InHouse_Radio.setSelected(true);
        addPart_Outsourced_Radio.setSelected(false);
        addPart_MachineID_Label.setText("Machine ID");
    }

    /**
     *  We can simply check the size of the total parts within the Inventory
     *  Return the id when finished. if none, id will default to 1.
     */
    public int get_next_id(){
        int id = 1;
        for(int i = 0; i < Inventory.getAllParts().size(); i++){
            id ++;
        }
        return id;
    }
}
