package view_controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;


import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyProductController implements Initializable {


    @FXML private TextField modifySearchTextField;
    @FXML private Button modifySearchTextButton;
    @FXML private TableView<Part> modifyPartsTableView ;
    @FXML private TableColumn<Part, Integer> modifyPartsIDTableColumn;
    @FXML private TableColumn<Part, String> modifyPartsNameTableColumn;
    @FXML private TableColumn<Part, Integer> modifyPartsInventoryTableColumn;
    @FXML private TableColumn<Part, Double> modifyPartsCostTableColumn;
    @FXML private TableView<Part> modifyAsscPartsTableView;
    @FXML private TableColumn<Part, Integer> modifyAsscPartsIDTableColumn;
    @FXML private TableColumn<Part, String> modifyAsscPartsNameTableColumn;
    @FXML private TableColumn<Part, Integer> modifyAsscPartsInventoryTableColumn;
    @FXML private TableColumn<Part, Double> modifyAsscPartsCostTableColumn;
    @FXML private Button modifyTableViewCancelButton;
    @FXML private Button modifyTableViewDeleteButton;
    @FXML private Button modifyTableViewSaveButton;
    @FXML private Button tableViewAddButton;
    @FXML void modifyTableViewDeleteButton(ActionEvent event) {}
    @FXML void modifyTableViewSaveButton(ActionEvent event) {}
    @FXML
    protected ObservableList<Part> partsToAdd = FXCollections.observableArrayList();


    @FXML void ModifyTableViewDeleteButton(ActionEvent event){
            ObservableList<Part>partsToAdd,singleProduct;
            partsToAdd = modifyPartsTableView.getItems();
            singleProduct = modifyPartsTableView.getSelectionModel().getSelectedItems();
            singleProduct.forEach(partsToAdd::remove);


//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this item? " +
//                    "Select OK to proceed or CANCEL to return");
//            alert.setTitle("Deleted");
//            Optional<ButtonType> delete = alert.showAndWait();
//            if(delete.isPresent() && delete.get() == ButtonType.OK){
//                ObservableList<Part>allProducts, aProduct;
//                allProducts = modifyPartsTableView.getItems();
//                aProduct = modifyPartsTableView.getSelectionModel().getSelectedItems();
//                aProduct.forEach((allProducts::remove));
//            }
    }

                                              @FXML void addButtonMethod(ActionEvent event){
        Part part = modifyAsscPartsTableView.getSelectionModel().getSelectedItem();
        partsToAdd.add(part);
        updatePartTable2();
    }

    public void updatePartTable2() {
        modifyPartsTableView.setItems(partsToAdd);
    }


    @FXML void modifyTableViewCancelButton(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));  //Unreported Exception IO exception must be caught or declared to be thrown
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();}

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        updatePartTable();

        modifyPartsIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        modifyPartsNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        modifyPartsInventoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        modifyPartsCostTableColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

        modifyAsscPartsIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        modifyAsscPartsNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        modifyAsscPartsInventoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        modifyAsscPartsCostTableColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

        modifyAsscPartsTableView.setItems(Inventory.getAllParts());

        ObservableList<Part> allParts = Inventory.getAllParts();

//PARTS SEARCH
            FilteredList<Part> filteredData = new FilteredList<>(allParts, p->true);
            modifySearchTextField.textProperty().addListener((observable,oldValue,newValue) -> {
                filteredData.setPredicate(part -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (part.getPartName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else return String.valueOf(part.getPartID()).toLowerCase().contains(lowerCaseFilter);
                });
            });

            SortedList<Part> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(modifyAsscPartsTableView.comparatorProperty());
            modifyAsscPartsTableView.setItems(sortedData);

}

    private void updatePartTable() {
        modifyAsscPartsTableView.setItems(Inventory.getAllParts());
    }
    }




