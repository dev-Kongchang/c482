package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;
import student.softwarei.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProduct_Controller implements Initializable {

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
    private RadioButton modifyPart_InHouse_Radio;

    // This is the Outsourced Radio button on the form
    @FXML
    private RadioButton modifyPart_Outsourced_Radio;

    // This is the ID textfield on the form
    @FXML
    private TextField modifyPart_ID_TextField ;

    // This is the Name textfield on the form
    @FXML
    private TextField modifyPart_Name_TextField ;

    // This is the Inv textfield on the form
    @FXML
    private TextField modifyPart_Inv_TextField ;

    // This is the Price/Cost textfield on the form
    @FXML
    private TextField modifyPart_PriceCost_TextField ;

    // This is the Max textfield on the form
    @FXML
    private TextField modifyPart_Max_TextField ;

    // This is the Machine ID textfield on the form

    @FXML
    private Label modifyPart_MachineID_Label ;

    // This is the Min textfield on the form
    @FXML
    private TextField modifyPart_Min_TextField ;


    /**
     *  We'll get the selected part that was in the Main menu, then set the information
     *  within the modify form to that selected part
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Main_Controller.get_Parts_SelectedPart();

    }


    /**
     *  This module here is the save button functionality.
     *  We'll first lay out the module in the following steps.
     *  1.) Use the try/except handle exceptions
     *      This method will allow catching issues, in this case
     *      keeping the text field free from bad inputs
     *  2.) We'll check the text from each textfield the user inputted from the functions
     *      at the end of the code section.
     *  3.) We'll also check for blanks, if any, will present error messages
     *  4.) We must know which radio button was selected, so we can
     *      update the textfield information.
     */
    public void modifyPart_Save_Button_Clicked(ActionEvent actionEvent) throws IOException {

        try{

            String Name = modifyPart_Name_TextField.getText();
            double price = checkPrice(modifyPart_PriceCost_TextField.getText());
            int inv = checkInv(modifyPart_Inv_TextField.getText());
            int min = checkMin(modifyPart_Min_TextField.getText());
            int max = checkMax(modifyPart_Max_TextField.getText());

            Inventory inventory = new Inventory();
            if(modifyPart_InHouse_Radio.isSelected()){
                int id = checkMachineID(modifyPart_MachineID_Label.getText());
                int newNum = inventory.getNextPartID();
                InHouse inHouse = new InHouse(newNum, Name, price, inv, min, max, id);
                inventory.updatePart(inHouse);
            }else {
                String company = checkCompanyName(modifyPart_MachineID_Label.getText());
                int newNum = inventory.getNextPartID();
                Outsourced outsourced = new Outsourced(newNum, Name, price, inv, min, max, company);
                inventory.updatePart(outsourced);
            }

            System.out.println("modifyParts Save Button Clicked!");

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
    public void modifyPart_Cancel_Button_Clicked(ActionEvent actionEvent) throws IOException {
        System.out.println("modifyParts Cancel Button Clicked!");

        Parent root = FXMLLoader.load(main.class.getResource("Main_Form.fxml"));
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();

        scene = new Scene(root, 1011, 419);

        stage.setScene(scene);
        stage.show();

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



    public void Outsource_RadioButton_Clicked(ActionEvent actionEvent) {
        is_InHouse_Selected = false;
        modifyPart_InHouse_Radio.setSelected(false);
        modifyPart_Outsourced_Radio.setSelected(true);
        modifyPart_MachineID_Label.setText("Company Name");
    }

    public void InHouse_RadioButton_Clicked(ActionEvent actionEvent) {
        is_InHouse_Selected = true;
        modifyPart_InHouse_Radio.setSelected(true);
        modifyPart_Outsourced_Radio.setSelected(false);
        modifyPart_MachineID_Label.setText("Machine ID");
    }
    
    
    
}
