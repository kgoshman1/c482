package view_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import javax.swing.*;
import java.io.IOException;

public class ModifyPartController {

    public TextField addPartMinTextField;
    public TextField addPartPriceTextField;
    @FXML Label modifyPartLabel;

    @FXML RadioButton modifyinHouseRadioButton;
    @FXML RadioButton modifyOutsourcedRadioButton;
    @FXML Label MachineIDLabel;
    @FXML TextField modifyPartIDTextField;
    @FXML TextField modifyPartCompanyNameTextField;
    @FXML TextField modifyPartNameTextField;
    @FXML TextField modifyPartMachineIDTextField;
    @FXML TextField modifyPartInventoryTextField;
    @FXML TextField modifyPartPriceTextField;
    @FXML TextField modifyPartMinTextField;
    @FXML TextField modifyPartMaxTextField;

    @FXML Button modifyPartCancelButton;
    @FXML Button modifyPartSaveButton;

    @FXML Button modifyPartMainMenuButton;


    @FXML void modifyinHouseRadioButton(ActionEvent event) {
        if (modifyinHouseRadioButton.isSelected()) {
            MachineIDLabel.setText("Machine ID");
        }
    }
    @FXML void modifyOutsourcedRadioButton(ActionEvent event){
        if (modifyOutsourcedRadioButton.isSelected()) {
            MachineIDLabel.setText("Company Name");
        }
    }
    @FXML void modifyPartNameField(ActionEvent event){}
    @FXML void modifyPartIDTextField(ActionEvent event){}
    @FXML void modifyPartCompanyNameTextField(ActionEvent event){}
    @FXML void modifyPartMachineIDTextField(ActionEvent event){}
    @FXML void modifyPartInventoryTextField(ActionEvent event){}
    @FXML void modifyPartPriceTextField(ActionEvent event){}
    @FXML void modifyPartMinTextField(ActionEvent event){}
    @FXML void modifyPartMaxTextField(ActionEvent event){}
    @FXML void modifyPartCancelButton(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));  //Unreported Exception IO exception must be caught or declared to be thrown
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    @FXML void modifyPartSaveButton(ActionEvent event) throws IOException{

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));  //Unreported Exception IO exception must be caught or declared to be thrown
        Scene tableViewScene = new Scene(tableViewParent);


        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
}
