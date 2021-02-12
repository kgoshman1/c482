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
import model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/** Modify Part Controller Class.
 * FUTURE ENHANCEMENTS - Create a way to interact with the product and view the additional parts.  Also would be
 * able to view individual pricing information.
 */
public class ModifyPartController implements Initializable {

    @FXML
    private int pId;
    @FXML
    RadioButton modifyinHouseRadioButton;
    @FXML
    RadioButton modifyOutsourcedRadioButton;
    @FXML
    Label MachineIDLabel;
    @FXML
    TextField modifyPartIDTextField;
    @FXML
    TextField modifyPartCompanyNameTextField;
    @FXML
    TextField modifyPartMachineIDTextField;
    @FXML
    TextField modifyPartInventoryTextField;
    @FXML
    TextField modifyPartPriceTextField;
    @FXML
    TextField modifyPartMinTextField;
    @FXML
    TextField modifyPartMaxTextField;
    @FXML
    Button modifyPartCancelButton;
    @FXML
    Button modifyPartSaveButton;
    private boolean outsourced;
    private boolean inhouse;
    int keepTrack = 0;
    private final int partLocation = MainScreenController.partsToModify;

    @FXML
    void modifyPartIDTextField(ActionEvent event) {
    }

    @FXML
    void modifyPartCompanyNameTextField(ActionEvent event) {
    }

    @FXML
    void modifyPartMachineIDTextField(ActionEvent event) {
    }

    @FXML
    void modifyPartInventoryTextField(ActionEvent event) {
    }

    @FXML
    void modifyPartPriceTextField(ActionEvent event) {
    }

    @FXML
    void modifyPartMinTextField(ActionEvent event) {
    }

    @FXML
    void modifyPartMaxTextField(ActionEvent event) {
    }

    /**
     * Initializes controller after root element has completely processed.
     *
     * @param url            Fetches the data
     * @param resourceBundle Contains locale-specific data
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modifyPartIDTextField.setEditable(false);
        Part part = Inventory.getAllParts().get(partLocation);
        modifyPartIDTextField.setText(Integer.toString(part.getPartID()));
        modifyPartCompanyNameTextField.setText(part.getPartName());
        modifyPartInventoryTextField.setText(Integer.toString(part.getPartInStock()));
        modifyPartPriceTextField.setText(Double.toString(part.getPartPrice()));
        modifyPartMinTextField.setText(Integer.toString(part.getMin()));
        modifyPartMaxTextField.setText(Integer.toString(part.getMax()));

        if (part instanceof InHousePart) {
            modifyinHouseRadioButton.setSelected(true);
            MachineIDLabel.setText("Machine ID");
            modifyPartMachineIDTextField.setText(Integer.toString(((InHousePart) Inventory.getAllParts().get(partLocation)).getMachineID()));
        } else {
            modifyOutsourcedRadioButton.setSelected(true);
            MachineIDLabel.setText("Company Name");
            modifyPartMachineIDTextField.setText((((OutsourcedPart) Inventory.getAllParts().get(partLocation)).getCompanyName()));

        }

        pId = part.getPartID();

    }

    /**
     * Saves the modified data and updates values.
     * RUNTIME ERROR - Unreported Exception IO exception must be caught or declared to be thrown
     *
     * @param event Defines Event
     * @throws IOException Throws exception if error occurs
     */
    @FXML
    void modifyPartSaveButton(ActionEvent event) throws IOException, NumberFormatException {
        try{
        String pName = modifyPartCompanyNameTextField.getText();
        String pInventory = modifyPartInventoryTextField.getText();
        String pPrice = modifyPartPriceTextField.getText();
        String pMin = modifyPartMinTextField.getText();
        String pMax = modifyPartMaxTextField.getText();
        String pMach = modifyPartMachineIDTextField.getText();

        //Redefines in number forms for verification purposes
        double price2 = Double.parseDouble(modifyPartPriceTextField.getText());
        int inv2 = Integer.parseInt(modifyPartInventoryTextField.getText());
        int max2 = Integer.parseInt(modifyPartMaxTextField.getText());
        int min2 = Integer.parseInt(modifyPartMinTextField.getText());



        //Verification
        if (pName == null || pName.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error. Must include a valid name, please try again");
            keepTrack++;
            alert.showAndWait();
        } else if (pInventory.equals("") || pName.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must include valid Inventory, please try again");
            alert.showAndWait();
            keepTrack++;
        } else if (inv2 < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must include valid Inventory, please try again");
            alert.showAndWait();
            keepTrack++;
        } else if (pPrice.equals("") || pName.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must include valid Price, please try again");
            alert.showAndWait();
            keepTrack++;
        } else if (price2 < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must include valid Inventory, please try again");
            alert.showAndWait();
            keepTrack++;
        } else if (pMin.equals("") || pName.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must include valid Min, please try again");
            alert.showAndWait();
            keepTrack++;
        } else if (min2 < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must include valid Inventory, please try again");
            alert.showAndWait();
            keepTrack++;
        } else if (pMach.equals("") || pName.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must include valid Company Name or Machine ID, please try again");
            alert.showAndWait();
            keepTrack++;
        } else if (pMax.equals("") || pName.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must include valid Max, please try again");
            alert.showAndWait();
            keepTrack++;
        } else if (max2 < min2) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must include valid Inventory, please try again");
            alert.showAndWait();
            keepTrack++;
        }
        //Keeps track of error count
        if (keepTrack == 0) {


            if (inhouse) {
                InHousePart inhouse = new InHousePart();
                inhouse.setPartID(pId);
                inhouse.setPartName(pName);
                inhouse.setPartInStock(Integer.parseInt(pInventory));
                inhouse.setPartPrice(Double.parseDouble(pPrice));
                inhouse.setMin(Integer.parseInt(pMin));
                inhouse.setMax(Integer.parseInt(pMax));
                inhouse.setMachineID(Integer.parseInt(pMach));
                Inventory.updatePart(partLocation, inhouse);
            } else {
                OutsourcedPart outsourced = new OutsourcedPart();
                outsourced.setPartID(pId);
                outsourced.setPartName(pName);
                outsourced.setPartInStock(Integer.parseInt(pInventory));
                outsourced.setPartPrice(Double.parseDouble(pPrice));
                outsourced.setMin(Integer.parseInt(pMin));
                outsourced.setMax(Integer.parseInt(pMax));
                outsourced.setCompanyName(pMach);
                Inventory.updatePart(partLocation, outsourced);

                Parent parent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));  //Unreported Exception IO exception must be caught or declared to be thrown
                Scene scene = new Scene(parent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            }
        } else if (keepTrack > 0) {

            Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));  //Unreported Exception IO exception must be caught or declared to be thrown
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();

        }
        } catch (NumberFormatException e){

                Alert alert = new Alert(Alert.AlertType.ERROR, "Error. Must include a valid name, please try again");
                keepTrack++;
                alert.showAndWait();
            }
        }


    /** When the user clicks the cancel button, they are are taken back to the main menu.
     * RUNTIME ERROR - Unreported Exception IO exception must be caught or declared to be thrown
     * @param event Defines event
     * @throws IOException Throws exception if error occurs
     */
    @FXML
    void modifyPartCancelButton(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    /** When the Inhouse Radio Button is selected, the last label is set to "Machine ID".
     *
     * @param event Defines Event
     */
    @FXML
    void modifyinHouseRadioButton(ActionEvent event) {
        if (modifyinHouseRadioButton.isSelected()) {
            MachineIDLabel.setText("Machine ID");
        }
    }

    /** When the Outsourced Radio Button is selected, the last label is set to "Company Name".
     *
     * @param event Defines event
     */
    @FXML
    void modifyOutsourcedRadioButton(ActionEvent event) {
        if (modifyOutsourcedRadioButton.isSelected()) {
            MachineIDLabel.setText("Company Name");
        }
    }

}










