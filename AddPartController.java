package view_controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHousePart;
import model.Inventory;
import model.OutsourcedPart;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AddPartController implements Initializable {
    @FXML Label MachineIdLabel;
    @FXML Label addPartLabel;
    @FXML RadioButton inHouseRadioButton;
    @FXML RadioButton OutsourcedRadioButton;
    @FXML TextField addPartMachineIDTextField;
    @FXML TextField addPartInventoryTextField;
    @FXML TextField addPartPriceTextField;
    @FXML TextField addPartMinTextField;
    @FXML TextField addPartNameTextField;
    @FXML TextField idTextField;
    @FXML TextField addPartMaxTextField;
    @FXML Button addpartCancelButton;
    @FXML Button addpartSaveButton;
    @FXML private int partID;
    @FXML private boolean inhouse;
    @FXML private boolean outsourced;
    @FXML int keepTrack = 0;

    /** Initializes controller after root element has completely processed.
     *  RUNTIME ERROR - Unreported Exception IO exception must be caught or declared to be thrown
     *  Happened in methods involving screen changes.
     *  FUTURE ENCHANCEMENT - Include a box for a description of the new box as well as be able to link
     *  information and company/Inhouse details about the part.
     * @param url Fetches the data
     * @param resourceBundle Contains locale-specific data
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partID = Inventory.getPartID();
        idTextField.setText(Integer.toString(partID));
        idTextField.setEditable(false);
    }

    /** Saves added part to mainscreen part tableview after passing validation.
     *
     * @param event Defines event
     * @throws IOException Throws exception upon error
     */
    @FXML
    void addpartSaveButton(ActionEvent event) throws IOException {

        //Gets values from textfields
        String name = addPartNameTextField.getText();
        String inventory = addPartInventoryTextField.getText();
        String price = addPartPriceTextField.getText();
        String pMin = addPartMinTextField.getText();
        String machID = addPartMachineIDTextField.getText();
        String max = addPartMaxTextField.getText();

        //Converts values to non string values to be used in validation process
        double price2 = Double.parseDouble(addPartPriceTextField.getText());
        int inv2 = Integer.parseInt(addPartInventoryTextField.getText());
        int max2 = Integer.parseInt(addPartMaxTextField.getText());
        int min2 = Integer.parseInt(addPartMinTextField.getText());

        //Verification of values entered in Textfields
        if (name  == null || name.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error. Must include a valid name, please try again");
            alert.showAndWait();
            keepTrack++;
        }

        else if (inventory.equals("") || name.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must include valid Inventory, please try again");
            alert.showAndWait();
            keepTrack++;
        }

        else if (inv2 < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must include valid Inventory, please try again");
            alert.showAndWait();
            keepTrack++;
        }

        else if (price.equals("") || name.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must include valid Price, please try again");
            alert.showAndWait();
            keepTrack++;
        }

        else if (price2 < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must include valid price, please try again");
            alert.showAndWait();
            keepTrack++;
        }

        else if (pMin.equals("") || name.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must include valid Min, please try again");
            alert.showAndWait();
            keepTrack++;
        }

        else if (min2 < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must include valid min, please try again");
            alert.showAndWait();
            keepTrack++;
        }

        else if (machID.equals("") || name.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must include valid Company Name or Machine ID, please try again");
            alert.showAndWait();
            keepTrack++;
        }

        else if (max.equals("") || name.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must include valid Max, please try again");
            alert.showAndWait();
            keepTrack++;
        }

        else if (max2 < min2) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must include valid max, please try again");
            alert.showAndWait();
            keepTrack++;
        }

        if (keepTrack == 0) {

        //Set Textfield values to match either inhouse or outsourced constructor values
            if (inhouse) {
                InHousePart inhouse = new InHousePart();
                inhouse.setPartID(partID);
                inhouse.setPartName(name);
                inhouse.setPartInStock(Integer.parseInt(inventory));
                inhouse.setPartPrice(Double.parseDouble(price));
                inhouse.setMin(Integer.parseInt(pMin));
                inhouse.setMax(Integer.parseInt(max));
                inhouse.setMachineID(Integer.parseInt(machID));
                Inventory.addPart(inhouse);
            } else {
                OutsourcedPart outsourced = new OutsourcedPart();
                outsourced.setPartID(partID);
                outsourced.setPartName(name);
                outsourced.setPartInStock(Integer.parseInt(inventory));
                outsourced.setPartPrice(Double.parseDouble(price));
                outsourced.setMin(Integer.parseInt(pMin));
                outsourced.setMax(Integer.parseInt(max));
                outsourced.setCompanyName(machID);
                Inventory.addPart(outsourced);

                Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                Scene tableViewScene = new Scene(tableViewParent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(tableViewScene);
                window.show();

            }
            //If any verfification step fails, +1 is added to keep track and takes user back to homepage to try again
        } else if (keepTrack > 0){
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }
    }

    /** When Cancel button is pushed takes user back to Mainscreen.
     *
     * @param event Defines an action
     * @throws IOException Throws an exception if incorrect
     */
    @FXML
    void addpartCancelButton(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));  //Unreported Exception IO exception must be caught or declared to be thrown
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }


    /** When the Inhouse Radio Button is selected, the last label is set to "Machine ID".
     *
     * @param event Responds to an event
     */
    @FXML
    void inHouseRadioButton(ActionEvent event) {
        if (inHouseRadioButton.isSelected()) {
            MachineIdLabel.setText("Machine ID");
        }
    }

    /** When the Outsourced Radio Button is selected, the last label is set to "Company Name".
     *
     * @param event Responds to an event
     */
    @FXML
    void OutsourcedRadioButton(ActionEvent event) {
        if (OutsourcedRadioButton.isSelected()) {
            MachineIdLabel.setText("Company Name");
        }
    }
}


        // Exception in thread "JavaFX Application Thread" java.lang.RuntimeException: java.lang.reflect.InvocationTargetException