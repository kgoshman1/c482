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
import main.Main;
import model.Inventory;
import model.Part;
import model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyProductController implements Initializable {

    private final int productIndex = MainScreenController.productsToModify;
    private final String errorMessage = "";

    @FXML
    private TextField modifyProductIDTextField;
    @FXML
    private TextField modifyProductNameTextField;
    @FXML
    private TextField modifyProductInventoryTextField;
    @FXML
    private TextField modifyPriceTextField;
    @FXML
    private TextField modifyMinTextField;
    @FXML
    private TextField modifyMaxTextField;
    @FXML
    public ObservableList<Part> AssociatedParts;

    @FXML
    private Product product;

    @FXML
    private TextField modifySearchTextField;

    @FXML
    private Button modifySearchTextButton;
    @FXML
    private TableView<Part> modifyPartsTableView;
    @FXML
    private TableColumn<Part, Integer> modifyPartsIDTableColumn;
    @FXML
    private TableColumn<Part, String> modifyPartsNameTableColumn;
    @FXML
    private TableColumn<Part, Integer> modifyPartsInventoryTableColumn;
    @FXML
    private TableColumn<Part, Double> modifyPartsCostTableColumn;
    @FXML
    private TableView<Part> modifyAsscPartsTableView;
    @FXML
    private TableColumn<Part, Integer> modifyAsscPartsIDTableColumn;
    @FXML
    private TableColumn<Part, String> modifyAsscPartsNameTableColumn;
    @FXML
    private TableColumn<Part, Integer> modifyAsscPartsInventoryTableColumn;
    @FXML
    private TableColumn<Part, Double> modifyAsscPartsCostTableColumn;
    @FXML
    private final ObservableList<Part> tempList = FXCollections.observableArrayList();

    private Product selectedProduct;
   @FXML private final ObservableList<Part>associatedParts = FXCollections.observableArrayList();
    Product newestProduct = new Product();


    @FXML
    void addButtonMethod(ActionEvent event) {

        Part selectedparts = modifyAsscPartsTableView.getSelectionModel().getSelectedItem();
        newestProduct.addAssProduct(selectedparts);
        boolean repeatedItem = false;

        if (selectedparts == null){
            return;
        } else {
            int id = selectedparts.getPartID();
            for (int i = 0; i < associatedParts.size(); i++) {
                if (associatedParts.get(i).getPartID() == id) {

                }

            }
            if (!repeatedItem) {
                associatedParts.add(selectedparts);

            }

            modifyPartsTableView.setItems(associatedParts);
        }//modifyPartsTableView.setItems(newestProduct.getAssociatedParts());


       // modifyPartsTableView.setItems(newestProduct.getAssociatedParts());

//
//        Part selectedPart = modifyAsscPartsTableView.getSelectionModel().getSelectedItem();
//        if (selectedPart != null) {
//            associatedParts.add(selectedPart);
//            modifyPartsTableView.setItems(associatedParts);

        }
        //}
   // }

    @FXML
    void modifyTableViewSaveButton(ActionEvent event) throws IOException {
//        selectedProduct = product;
//        String pId = modifyProductIDTextField.getText();
//        String pName = modifyProductNameTextField.getText();
//        String pInventory = modifyProductInventoryTextField.getText();
//        String pPrice = modifyPriceTextField.getText();
//        String pMin = modifyMinTextField.getText();
//        String pMax = modifyMaxTextField.getText();
//
////        try {
//        Product product = new Product(0, "", 0, 0, 0, 0);
//        product.setProductID(Integer.parseInt(pId));
//        product.setName(pName);
//        product.setPrice(Double.parseDouble(pPrice));
//        product.setStock(Integer.parseInt(pInventory));
//        product.setMin(Integer.parseInt(pMin));
//        product.setMax(Integer.parseInt(pMax));
////        for (Part part : tempList) {
////            product.addAssProduct(part);
//
//            Inventory.updateProduct(productIndex, product);
//            /// saveProduct();
//            // Inventory.updateProduct(Inventory.getPartID(),product);
////         Inventory.getAllProducts().set(selectedIndex,product);

            saveProduct();

            Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));  //Unreported Exception IO exception must be caught or declared to be thrown
            Scene tableViewScene = new Scene(tableViewParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();

//        } catch (NullPointerException e) {
//
//        }
        }


    public void saveProduct(){
        Product product = new Product(Integer.parseInt(modifyProductIDTextField.getText()),modifyProductNameTextField.getText(),
                Double.parseDouble(modifyPriceTextField.getText()),Integer.parseInt(modifyProductInventoryTextField.getText()),
                Integer.parseInt(modifyMinTextField.getText()),Integer.parseInt(modifyMaxTextField.getText()));
        for (int i =0; i< associatedParts.size(); i++){
            product.addAssProduct(associatedParts.get(i));
        }
                Inventory.updateProduct(productIndex,product);
    }

    @FXML
    void ModifyTableViewDeleteButton(ActionEvent event) {
        ObservableList<Part> partsToAdd, singleProduct;
        partsToAdd = modifyPartsTableView.getItems();
        singleProduct = modifyPartsTableView.getSelectionModel().getSelectedItems();
        singleProduct.forEach(partsToAdd::remove);


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this item? " +
                "Select OK to proceed or CANCEL to return");
        alert.setTitle("Deleted");
        Optional<ButtonType> delete = alert.showAndWait();
        if (delete.isPresent() && delete.get() == ButtonType.OK) {
            ObservableList<Part> allProducts, aProduct;
            allProducts = modifyPartsTableView.getItems();
            aProduct = modifyPartsTableView.getSelectionModel().getSelectedItems();
            aProduct.forEach((allProducts::remove));
        }
    }



    @FXML
    void modifyTableViewCancelButton(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));  //Unreported Exception IO exception must be caught or declared to be thrown
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modifyProductIDTextField.setEditable(false);
        Product product = Inventory.getAllProducts().get(productIndex);
        modifyProductIDTextField.setText(Integer.toString(product.getProductID()));
        modifyProductNameTextField.setText(product.getName());
        modifyProductInventoryTextField.setText(Integer.toString(product.getStock()));
        modifyMinTextField.setText(Integer.toString(product.getMin()));
        modifyMaxTextField.setText(Integer.toString(product.getMax()));
        modifyPriceTextField.setText(Double.toString(product.getPrice()));

        //modifyAsscPartsTableView.setItems(Inventory.getAllParts());


        modifyAsscPartsIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        modifyAsscPartsNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        modifyAsscPartsInventoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        modifyAsscPartsCostTableColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

        //I think this is the problem
        modifyPartsTableView.setItems(Inventory.getAllProducts().get(productIndex).getAssociatedParts());
        //CALLING GET ASSOCIATED PARTS WITH NOTHING IN IT!!!!THIS IS THE PROBLEM

        modifyPartsIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        modifyPartsNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        modifyPartsInventoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        modifyPartsCostTableColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

    //   modifyPartsTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        modifyPartsTableView.setItems(product.getAssociatedParts());

        //modifyPartsTableView.setItems(selectedProduct.getAssociatedParts());


        //updatePartTable2();
        ObservableList<Part> allParts = Inventory.getAllParts();



       // updatePartTable2();

/////////////////////////////////////////////////////////////////////////////////////

//PARTS SEARCH
        FilteredList<Part> filteredData = new FilteredList<>(allParts, p -> true);
        modifySearchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
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

        //SETS MODIFIED PARTS TABLEVIEW
        modifyAsscPartsTableView.setItems(sortedData);


    }

    public void updatePartTable() {
        modifyAsscPartsTableView.setItems(Inventory.getAllParts());
    }



    public void updatePartTable2() {
        modifyPartsTableView.setItems(product.getAssociatedParts()); }
}
