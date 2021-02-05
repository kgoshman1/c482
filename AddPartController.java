package view_controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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


public class AddPartController {
    @FXML
    Label MachineIdLabel;
    @FXML
    Label addPartLabel;
    @FXML
    RadioButton inHouseRadioButton;
    @FXML
    RadioButton OutsourcedRadioButton;
    @FXML
    TextField addPartMachineIDTextField;
    @FXML
    TextField addPartInventoryTextField;
    @FXML
    TextField addPartPriceTextField;
    @FXML
    TextField addPartMinTextField;
    @FXML
    TextField addPartNameTextField;
    @FXML
    TextField addPartIDTextField;
    @FXML
    TextField addPartMaxTextField;
    @FXML
    Button addpartCancelButton;
    @FXML
    Button addpartSaveButton;
    @FXML
    private boolean isInhouse;

    private String errorMessage = "";

    private boolean isOutsourced;
//    //  private String exceptionMessage = new String();
//    public modifyPartController(){

    //   }

    @FXML
    void inHouseRadioButton(ActionEvent event) {
        if (inHouseRadioButton.isSelected()) {
            MachineIdLabel.setText("Machine ID");
        }
    }

    @FXML
    void OutsourcedRadioButton(ActionEvent event) {
        if (OutsourcedRadioButton.isSelected()) {
            MachineIdLabel.setText("Company Name");
        }
    }

    @FXML
    void addpartCancelButton(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));  //Unreported Exception IO exception must be caught or declared to be thrown
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    void addpartSaveButton(ActionEvent event) throws IOException {
        String pName = addPartNameTextField.getText();
        String pInventory = addPartInventoryTextField.getText();
        String pPrice = addPartPriceTextField.getText();
        String pMin = addPartMinTextField.getText();
        String pMach = addPartMachineIDTextField.getText();
        String pMax = addPartMaxTextField.getText();


        try {
            errorMessage = Part.isPartValid(pName, Integer.parseInt(pMin), Integer.parseInt(pMax), Integer.parseInt(pInventory), Double.parseDouble(pPrice), errorMessage);
            if (errorMessage.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error adding part");
                alert.setHeaderText("error");
                alert.setContentText(errorMessage);
                alert.showingProperty();
                errorMessage = "";
            } else {
                if (isInhouse) {
                    InHousePart inhouse = new InHousePart();
                    inhouse.setPartID(inhouse.getPartID());
                    inhouse.setPartName(pName);
                    inhouse.setPartInStock(Integer.parseInt(pInventory));
                    inhouse.setPartPrice(Double.parseDouble(pPrice));
                    inhouse.setMin(Integer.parseInt(pMin));
                    inhouse.setMax(Integer.parseInt(pMax));
                    inhouse.setMachineID(Integer.parseInt(pMach));
                    Inventory.addPart(inhouse);
                } else {
                    OutsourcedPart outsourced = new OutsourcedPart();
                    outsourced.setPartID(outsourced.getPartID());
                    outsourced.setPartName(pName);
                    outsourced.setPartInStock(Integer.parseInt(pInventory));
                    outsourced.setPartPrice(Double.parseDouble(pPrice));
                    outsourced.setMin(Integer.parseInt(pMin));
                    outsourced.setMax(Integer.parseInt(pMax));
                    outsourced.setCompanyName(pMach);
                    Inventory.addPart(outsourced);
                }

            } Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));  //Unreported Exception IO exception must be caught or declared to be thrown
              Scene tableViewScene = new Scene(tableViewParent);
              Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
              window.setScene(tableViewScene);
              window.show();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}


        // Exception in thread "JavaFX Application Thread" java.lang.RuntimeException: java.lang.reflect.InvocationTargetException