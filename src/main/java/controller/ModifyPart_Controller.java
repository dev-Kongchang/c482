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

public class ModifyPart_Controller implements Initializable {


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

    // This is the Machine ID label on the form
    @FXML
    private Label modifyPart_MachineID_Label ;

    // This is the Machine ID TextField on the form
    @FXML
    private TextField modifyPart_MachineID_TextField;

    // This is the Min textfield on the form
    @FXML
    private TextField modifyPart_Min_TextField ;

    // This is the constructed selectedPart Part Object
    public Part selectedPart;

    /**
     *  We'll get the selected part that was in the Main menu, then set the information
     *  within the modify form to that selected part
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modifyPart_ID_TextField.setDisable(true);
    }

    /**
     *  This is the setter from the selected part from the main
     *  form.
     *  1.) We'll first use our variable placeholder to set the incoming Part object
     *  2.) Check if the part is outsourced or inhouse
     *  3.) update form textfields accordingly
     */
    public void setSelectedPart(Part partSelected){
        selectedPart = partSelected;

        if (selectedPart instanceof InHouse){
            modifyPart_MachineID_Label.setText("Machine ID");
            modifyPart_InHouse_Radio.setSelected(true);
            modifyPart_Outsourced_Radio.setSelected(false);
            modifyPart_MachineID_TextField.setText(Integer.toString(((InHouse) selectedPart).getMachineId()));
        }else if (selectedPart instanceof Outsourced){
            modifyPart_MachineID_Label.setText("Company Name");
            modifyPart_Outsourced_Radio.setSelected(true);
            modifyPart_InHouse_Radio.setSelected(false);
            modifyPart_MachineID_TextField.setText(((Outsourced) selectedPart).getCompanyName());
        }

        String id = Integer.toString(selectedPart.getId());
        String name = selectedPart.getName();
        String inv = Integer.toString(selectedPart.getStock());
        String price = Double.toString(selectedPart.getPrice());
        String max = Integer.toString(selectedPart.getMax());
        String min = Integer.toString(selectedPart.getMin());

        modifyPart_ID_TextField.setText(id);
        modifyPart_Name_TextField.setText(name);
        modifyPart_Inv_TextField.setText(inv);
        modifyPart_PriceCost_TextField.setText(price);
        modifyPart_Max_TextField.setText(max);
        modifyPart_Min_TextField.setText(min);
        modifyPart_ID_TextField.setDisable(true);
    }


    /**
     *  This module here is the save button functionality.
     *  We'll first lay out the module in the following steps.
     *  1.) Use the try/except handle exceptions
     *      This method will allow catching issues, in this case
     *      keeping the text field free from bad inputs
     *  2.) We'll check the text from each textfield the user inputted
     *  3.) We must know which radio button was selected, so we can
     *      update with the correct object information
     *  4.) Update the Object in the Inventory
     *  Logical Runtime: Using selectedPart.getId() as the index kept giving me
     *                   the wrong placement, therefore updating the part one index ahead
     *  Fix: used a variable placeholder to minus 1 index so that the correct index can be
     *  used when updating the object in the Inventory
     */
    public void modifyPart_Save_Button_Clicked(ActionEvent actionEvent) throws IOException {

        try{
            if(check_for_error() == true){return;}

            modifyPart_ID_TextField.setDisable(true);
            String Name = modifyPart_Name_TextField.getText();
            double price = checkPrice(modifyPart_PriceCost_TextField.getText());
            int inv = checkInv(modifyPart_Inv_TextField.getText());
            int min = checkMin(modifyPart_Min_TextField.getText());
            int max = checkMax(modifyPart_Max_TextField.getText());


            if(modifyPart_InHouse_Radio.isSelected()){
                int machineID = checkMachineID(modifyPart_MachineID_TextField.getText());
                InHouse updatedPart = new InHouse(selectedPart.getId(), Name, price, inv, min, max, machineID);
                int index = selectedPart.getId() - 1;
                Inventory.updatePart(index, updatedPart);
            }else if (modifyPart_Outsourced_Radio.isSelected()){
                String company = checkCompanyName(modifyPart_MachineID_TextField.getText());
                Outsourced updatedPart = new Outsourced(selectedPart.getId(), Name, price, inv, min, max, company);
                int index = selectedPart.getId() - 1;
                Inventory.updatePart(index, updatedPart);
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

    /** RUNTIME ERROR
     * Checks for error within logical requirements of each textfield,
     * if any, generates a combined message to the user about the error
     *   Logical RunTime: Was error.concat(message).
     *          The error message string was empty every time.
     *          fix: decided to use variable addition to add on the
     *          string. (error = error + errorMessage)
     */

    public boolean check_for_error(){
        String error = "";
        String name = modifyPart_Name_TextField.getText();
        int max = Integer.parseInt(modifyPart_Max_TextField.getText());
        int min = Integer.parseInt(modifyPart_Min_TextField.getText());
        double price = Double.parseDouble(modifyPart_PriceCost_TextField.getText());
        int inv = Integer.parseInt(modifyPart_Inv_TextField.getText());

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
    public Double checkPrice(String what){
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
        modifyPart_InHouse_Radio.setSelected(false);
        modifyPart_Outsourced_Radio.setSelected(true);
        modifyPart_MachineID_Label.setText("Company Name");
    }

    /**
     *  When the radio button is selected:
     *  boolean variable is updated
     *  sets true/false for both radiobuttons
     */
    public void InHouse_RadioButton_Clicked(ActionEvent actionEvent) {
        is_InHouse_Selected = true;
        modifyPart_InHouse_Radio.setSelected(true);
        modifyPart_Outsourced_Radio.setSelected(false);
        modifyPart_MachineID_Label.setText("Machine ID");
    }

}
