package view_controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



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
    TextField addPartCompanyNameTextField;
    @FXML
    TextField addPartMachineIDTextField;
    @FXML
    TextField addPartInventoryTextField;
    @FXML
    TextField addPartPriceTextField;
    @FXML
    TextField addPartMinTextField;
    @FXML
    TextField AddPartMaxTextField;
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

    private String errorMessage = new String();
//    @FXML
//    private final Part ModifyPart;
//    @FXML
//    Button addpartmainMenuButton;
//
//    private boolean isOutsourced;
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
            {
                InHousePart modifiedPart = new InHousePart();
                modifiedPart.setPartName(pName);
                modifiedPart.setPartPrice(Double.parseDouble(pPrice));
                modifiedPart.setPartInStock(Integer.parseInt(pInventory));
                modifiedPart.setMin(Integer.parseInt(pMin));
                modifiedPart.setMax(Integer.parseInt(pMax));
                modifiedPart.setMachineID(Integer.parseInt(pMach));

                try {
                    modifiedPart.setPartID(Inventory.getPartsCount());
                    Inventory.addPart(modifiedPart);

                    Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));  //Unreported Exception IO exception must be caught or declared to be thrown
                    Scene tableViewScene = new Scene(tableViewParent);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(tableViewScene);
                    window.show();


                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        } finally {

        }
    }}



        // Exception in thread "JavaFX Application Thread" java.lang.RuntimeException: java.lang.reflect.InvocationTargetException