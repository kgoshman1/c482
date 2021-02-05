package view_controller;


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
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class MainScreenController implements Initializable {


    @FXML
    private Label partsSearchLabel;
    @FXML
    private Label productsSearchLabel;
    @FXML
    private TextField partsSearchBox;
    @FXML
    private TextField productsSearchBox;
    @FXML
    private TableView<Part> partsTableview;
    @FXML
    private TableView<Product> productsTableview;
    @FXML
    private TableColumn<Part, Integer> partsIDColumn;
    @FXML
    private TableColumn<Part, String> partsNameColumn;
    @FXML
    private TableColumn<Part, Integer> partsInventoryColumn;
    @FXML
    private TableColumn<Part, Double> partsPriceColumn;
    @FXML
    private TableColumn<Part, Integer> productsIDColumn;
    @FXML
    private TableColumn<Part, String> productsNameColumn;
    @FXML
    private TableColumn<Part, Integer> productsInventoryColumn;
    @FXML
    private TableColumn<Part, Double> productsPriceColumn;
    @FXML
    private Button partsAddButton;
    @FXML
    private Button partsDeleteButton;
    @FXML
    private Button partsModifyButton;
    @FXML
    private Button productsAddButton;
    @FXML
    private Button productsDeleteButton;
    @FXML
    private Button productsModifyButton;
    @FXML
    public static int partsToModify;
    @FXML
    public static int productsToModify;
    @FXML
    public static int modifyPartIndex;
    @FXML
    private static ObservableList<Product> singleProduct;
    @FXML
    private Button exitButton;
    private static int modifyProductIndex;

    public static int productToModifyIndex() {
        return modifyProductIndex;
    }

    public static int partsToModifyIndex() {
        return modifyPartIndex;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        partsIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partsNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        partsInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        partsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

        productsIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productsInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        partsTableview.setItems(Inventory.getAllParts());
        productsTableview.setItems(Inventory.getAllProducts());

        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Product> allProducts = Inventory.getAllProducts();

//PARTS SEARCH
        FilteredList<Part> filteredData = new FilteredList<>(allParts, p -> true);
        partsSearchBox.textProperty().addListener((observable, oldValue, newValue) -> {
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
        sortedData.comparatorProperty().bind(partsTableview.comparatorProperty());
        partsTableview.setItems(sortedData);

//
////PRODUCTS SEARCH
//        FilteredList<Product>filteredData2 = new FilteredList<>(allProducts,p->true);
//        productsSearchBox.textProperty().addListener((observable,oldValue,newValue) -> {
//            filteredData2.setPredicate(product -> {
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//
//                String lowerCaseFilter = newValue.toLowerCase();
//
//                if (product.getName().toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                } else return String.valueOf(product.getProductID()).toLowerCase().contains(lowerCaseFilter);
//            });
//        });
//
//        SortedList<Product> sortedData2 = new SortedList<>(filteredData2);
//        sortedData2.comparatorProperty().bind(productsTableview.comparatorProperty());
//        productsTableview.setItems(sortedData2);
//

    }


    @FXML
    void partsAddButton(ActionEvent event) throws IOException { //java.lang.reflect.InvocationTargetException
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AddPart.fxml"));  //Unreported Exception IO exception must be caught or declared to be thrown
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }


    @FXML
    void partsDeleteButton(ActionEvent event) {    //NEED TO DOUBLE CHECK THESE FOR ACCURACY***

//        ObservableList<Part> allProduct,singleProduct;
//        singleProduct = partsTableview.getItems();
//        allProduct = partsTableview.getSelectionModel().getSelectedItems();
//        for (Part part: singleProduct){
//            allProduct.remove(part);
//        }


        ObservableList<Part> allProduct, singleProduct;
        allProduct = partsTableview.getItems();
        singleProduct = partsTableview.getSelectionModel().getSelectedItems();
        singleProduct.forEach(allProduct::remove);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this item? " +
                "Select OK to proceed or CANCEL to return");
        alert.setTitle("Deleted");
        Optional<ButtonType> delete = alert.showAndWait();
        if (delete.isPresent() && delete.get() == ButtonType.OK) {
            ObservableList<Part> allProducts, aProduct;

            allProducts = partsTableview.getItems();
            aProduct = partsTableview.getSelectionModel().getSelectedItems();
            aProduct.forEach((allProducts::remove));
        }
    }

    @FXML
    void partsModifyButton(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        partsToModify = Inventory.getAllParts().indexOf(partsTableview.getSelectionModel().getSelectedItem());

        Parent tableViewParents = FXMLLoader.load(getClass().getResource("ModifyPart.fxml"));  //Unreported Exception IO exception must be caught or declared to be thrown
        Scene tableViewScene = new Scene(tableViewParents);
        // Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    void productsAddButton(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));  //Unreported Exception IO exception must be caught or declared to be thrown
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    void ProductsDeleteButton(ActionEvent event) {             //NEED TO DOUBLE CHECK FOR ACCURACY ***
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this item? " +
//                "Select OK to proceed or CANCEL to return");
//        alert.setTitle("Deleted");
//        Optional<ButtonType> delete = alert.showAndWait();
//        if (delete.isPresent() && delete.get() == ButtonType.OK) {
        ObservableList<Product> allProducts, singlePart;
        allProducts = productsTableview.getItems();
        singlePart = productsTableview.getSelectionModel().getSelectedItems();
        singlePart.forEach((allProducts::remove));


    }

    //}
    @FXML
    void ProductsModifyButton(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("ModifyProduct.fxml"));
//        Parent tableViewParent = loader.load();
//        Scene tableViewScene = new Scene (tableViewParent);
//
//        ModifyProductController controller = loader.getController();
//        controller.initData((Product) productsTableview.getSelectionModel().getSelectedItems());
//
//        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//
//        window.setScene(tableViewScene);
//        window.show();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        productsToModify = Inventory.getAllProducts().indexOf(productsTableview.getSelectionModel().getSelectedItem());

        Parent tableViewParents = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));  //Unreported Exception IO exception must be caught or declared to be thrown
        Scene tableViewScene = new Scene(tableViewParents);
        // Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();


//        singleProduct = productsTableview.getSelectionModel().getSelectedItems();
//        productsToModify = Inventory.getAllProducts().indexOf(singleProduct);
//        Parent tableViewParents = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));  //Unreported Exception IO exception must be caught or declared to be thrown
//        Scene tableViewScene = new Scene(tableViewParents);
//        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//        window.setScene(tableViewScene);
//        window.show();
//Caused by: java.lang.IllegalArgumentException: Can not set int field view_controller.ModifyProductController.modifyProductIDTextField to javafx.scene.control.TextField

    }

    @FXML
    void exitButton(ActionEvent event) {
        System.exit(0);
    }


    public void updatePartTableView() {
        partsTableview.setItems(Inventory.getAllParts());
    }

    public void updateProductTableView() {
        productsTableview.setItems(Inventory.getAllProducts());


    }
}