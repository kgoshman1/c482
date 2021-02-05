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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProductController implements Initializable {

    private final int productIndex = MainScreenController.productToModifyIndex();
    private final String errorMessage = "";

    @FXML private TextField modifyProductIDTextField;
    @FXML private TextField modifyProductNameTextField;
    @FXML private TextField modifyProductInventoryTextField;
    @FXML private TextField modifyPriceTextField;
    @FXML private TextField modifyMinTextField;
    @FXML private TextField modifyMaxTextField;


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
    protected  int autoID = MainScreenController.productsToModify;

    public void initData(Product product){
        modifyProductNameTextField.setText(product.getName());
        modifyProductInventoryTextField.setText(Integer.toString(product.getStock()));
        modifyPriceTextField.setText(Double.toString(product.getPrice()));
        modifyMaxTextField.setText(Integer.toString(product.getMax()));
        modifyMinTextField.setText(Integer.toString(product.getMin()));

    }

    @FXML void modifyTableViewSaveButton(ActionEvent event) throws IOException{
        String pId = modifyProductIDTextField.getText();
        String pName = modifyProductNameTextField.getText();
        String pInventory = modifyProductInventoryTextField.getText();
        String pPrice = modifyPriceTextField.getText();
        String pMin = modifyMinTextField.getText();
        String pMax = modifyMaxTextField.getText();

//        try {
            Product product = new Product();
            product.setProductID(Integer.parseInt(pId));
            product.setName(pName);
            product.setPrice(Double.parseDouble(pPrice));
            product.setStock(Integer.parseInt(pInventory));
            product.setMin(Integer.parseInt(pMin));
            product.setMax(Integer.parseInt(pMax));
            Inventory.updateProduct(productIndex, product);

            Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));  //Unreported Exception IO exception must be caught or declared to be thrown
            Scene tableViewScene = new Scene(tableViewParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();

//        } catch (NullPointerException e) {
//
//        }
    }
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
        Product product = Inventory.getAllProducts().get(autoID);
        modifyProductNameTextField.setText(product.getName());
        modifyProductInventoryTextField.setText(Integer.toString(product.getStock()));
        modifyMinTextField.setText(Integer.toString(product.getMin()));
        modifyMaxTextField.setText(Integer.toString(product.getMax()));
        modifyPriceTextField.setText(Double.toString(product.getPrice()));





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

    public void updatePartTable() {
        modifyAsscPartsTableView.setItems(Inventory.getAllParts());
    }
    }




