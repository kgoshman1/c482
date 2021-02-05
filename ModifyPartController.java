package view_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.InHousePart;
import model.Inventory;
import model.OutsourcedPart;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartController implements Initializable {

    public TextField addPartMinTextField;
    public TextField addPartPriceTextField;
    @FXML
    Label modifyPartLabel;

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
    TextField modifyPartNameTextField;
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

    @FXML
    Button modifyPartMainMenuButton;
    private String errorMessage = "";
    private boolean isOutsourced;
    private boolean isInhouse;
    protected int autoID = MainScreenController.partsToModify;

    @FXML
    void modifyinHouseRadioButton(ActionEvent event) {
        if (modifyinHouseRadioButton.isSelected()) {
            MachineIDLabel.setText("Machine ID");
        }
    }

    @FXML
    void modifyOutsourcedRadioButton(ActionEvent event) {
        if (modifyOutsourcedRadioButton.isSelected()) {
            MachineIDLabel.setText("Company Name");
        }
    }

    @FXML
    void modifyPartNameField(ActionEvent event) {
    }

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

    @FXML
    void modifyPartCancelButton(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));  //Unreported Exception IO exception must be caught or declared to be thrown
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    void modifyPartSaveButton(ActionEvent event) throws IOException {

        String pName = modifyPartCompanyNameTextField.getText();
        String pInventory = modifyPartInventoryTextField.getText();
        String pPrice = modifyPartPriceTextField.getText();
        String pMin = modifyPartMinTextField.getText();
        String pMax = modifyPartMaxTextField.getText();
        String pMach = modifyPartMachineIDTextField.getText();




        try {
//            errorMessage = Part.isPartValid(pName, Integer.parseInt(pMin), Integer.parseInt(pMax), Integer.parseInt(pInventory), Double.parseDouble(pPrice), errorMessage);
//            if (errorMessage.length() > 0) {
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Error adding part");
//                alert.setHeaderText("error");
//                alert.setContentText(errorMessage);
//                alert.showingProperty();
//                errorMessage = "";
////            } else {
            if (isInhouse == true) {
                InHousePart inhouse = new InHousePart();
                inhouse.setPartID(inhouse.getPartID());
                inhouse.setPartName(pName);
                inhouse.setPartInStock(Integer.parseInt(pInventory));
                inhouse.setPartPrice(Double.parseDouble(pPrice));
                inhouse.setMin(Integer.parseInt(pMin));
                inhouse.setMax(Integer.parseInt(pMax));
                inhouse.setMachineID(Integer.parseInt(pMach));
                Inventory.updatePart(autoID, inhouse);
            } else {
                OutsourcedPart outsourced = new OutsourcedPart();
                outsourced.setPartID(outsourced.getPartID());
                outsourced.setPartName(pName);
                outsourced.setPartInStock(Integer.parseInt(pInventory));
                outsourced.setPartPrice(Double.parseDouble(pPrice));
                outsourced.setMin(Integer.parseInt(pMin));
                outsourced.setMax(Integer.parseInt(pMax));
                outsourced.setCompanyName(pMach);
                Inventory.updatePart(autoID, outsourced);
            }

            Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));  //Unreported Exception IO exception must be caught or declared to be thrown
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        } finally {


        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Part part = Inventory.getAllParts().get(autoID);
        modifyPartCompanyNameTextField.setText(part.getPartName());
        modifyPartInventoryTextField.setText(Integer.toString(part.getPartInStock()));
        modifyPartPriceTextField.setText(Double.toString(part.getPartPrice()));
        modifyPartMinTextField.setText(Integer.toString(part.getMin()));
        modifyPartMaxTextField.setText(Integer.toString(part.getMin()));
        if (part instanceof InHousePart) {
            modifyinHouseRadioButton.setSelected(true);
            MachineIDLabel.setText("Machine ID");
            modifyPartMachineIDTextField.setText(Integer.toString(((InHousePart) Inventory.getAllParts().get(autoID)).getMachineID()));
        } else {
            modifyOutsourcedRadioButton.setSelected(true);
            MachineIDLabel.setText("Company Name");
            modifyPartMachineIDTextField.setText((((OutsourcedPart) Inventory.getAllParts().get(autoID)).getCompanyName()));

        }
    }
}








