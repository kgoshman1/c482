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
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AddProductController implements  Initializable {

    Product newProduct;
    private String errorMessage = "";
    @FXML
    private Label addProductLabel;
    @FXML
    private Label min_maxToLabel;

    @FXML
    private TextField productNameTextfield;
    @FXML
    private TextField productIDTextfield;
    @FXML
    private TextField productInventoryTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField minTextField;
    @FXML
    private TextField maxTextField;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchTextButton;
    @FXML
    private TableView<Part> addPartsTableView;
    @FXML
    private TableColumn<Part, Integer> addPartsIDTableColumn;
    @FXML
    private TableColumn<Part, String> addPartsNameTableColumn;
    @FXML
    private TableColumn<Part, Integer> addPartsInventoryTableColumn;
    @FXML
    private TableColumn<Part, Double> addPartsCostTableColumn;
    @FXML
    private Button tableViewAddButton;
    @FXML
    private TableView<Part> asscPartsTableView;



    @FXML
    private TableColumn<Part, Integer> asscPartsIDTableColumn;
    @FXML
    private TableColumn<Part, String> asscPartsNameTableColumn;
    @FXML
    private TableColumn<Part, Integer> asscPartsInventoryTableColumn;
    @FXML
    private TableColumn<Part, Double> asscPartsCostTableColumn;
    @FXML
    private Button tableViewCancelButton;
    @FXML
    private Button tableViewDeleteButton;
    @FXML
    private Button tableViewSaveButton;
    @FXML
    protected ObservableList<Part> partsToAdd = FXCollections.observableArrayList();
    @FXML protected ObservableList<Part>associatedParts = FXCollections.observableArrayList();
    private final int productIndex = MainScreenController.productsToModify;


    @FXML protected ObservableList<Part> associatedPartsList = FXCollections.observableArrayList();






    private int prodID;

    private boolean isOutsourced;

    Product newestProduct = new Product(0,"",0.0,0,0,0);

    @FXML
    void tableViewSaveButton(ActionEvent event) throws IOException {
        String pName = productNameTextfield.getText();
        String pInventory = productInventoryTextField.getText();
        String pPrice = priceTextField.getText();
        String pMin = minTextField.getText();
        String pMax = maxTextField.getText();


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
                if (!isOutsourced) {
                    Product product = new Product();
                    product.setProductID(prodID);
                    product.setName(pName);
                    product.setStock(Integer.parseInt(pInventory));
                    product.setPrice(Double.parseDouble(pPrice));
                    product.setMin(Integer.parseInt(pMin));
                    product.setMax(Integer.parseInt(pMax));
                    for (int i = 0; i < associatedParts.size(); i++) {
                        product.addAssProduct(associatedParts.get(i));

                    }
                    Inventory.addProduct(product);
//                    Inventory.updateProduct(product);
                    saveProduct();
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

    public void saveProduct(){
        Product product = new Product(Integer.parseInt(productIDTextfield.getText()),productNameTextfield.getText(),
                Double.parseDouble(priceTextField.getText()),Integer.parseInt(productInventoryTextField.getText()),
                Integer.parseInt(minTextField.getText()),Integer.parseInt(maxTextField.getText()));
        for (Part associatedPart : associatedParts) {
            product.addAssProduct(associatedPart);
        }
        Inventory.updateProduct(productIndex,product);
    }


    @FXML
    void setTableViewAddButton(ActionEvent event) {
        Part selectedparts = addPartsTableView.getSelectionModel().getSelectedItem();
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

            asscPartsTableView.setItems(associatedParts);

        }
//        Part selectedparts = addPartsTableView.getSelectionModel().getSelectedItem();
//        newestProduct.addAssProduct(selectedparts);
//        asscPartsTableView.setItems(newestProduct.getAssociatedParts());

        //updatePartTable2();
    }

    //public void updatePartTable2() {
     //   asscPartsTableView.setItems(tempList);
    //}


    @FXML
    void tableViewCancelButton(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));  //Unreported Exception IO exception must be caught or declared to be thrown
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    void tableViewDeleteButton(ActionEvent event) {
        ObservableList<Part>partsToAdd,singleProduct;
        partsToAdd = asscPartsTableView.getItems();
        singleProduct = asscPartsTableView.getSelectionModel().getSelectedItems();
        singleProduct.forEach(partsToAdd::remove);

//
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this item? " +
//                "Select OK to proceed or CANCEL to return");
//        alert.setTitle("Deleted");
//        Optional<ButtonType> delete = alert.showAndWait();
//        if(delete.isPresent() && delete.get() == ButtonType.OK){
//            ObservableList<Part>allProducts, aProduct;
//            allProducts = asscPartsTableView.getItems();
//            aProduct = asscPartsTableView.getSelectionModel().getSelectedItems();
//              aProduct.forEach((allProducts::remove));
       }
   // }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //updatePartTable();

        addPartsIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        addPartsNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        addPartsInventoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        addPartsCostTableColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

        asscPartsIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        asscPartsNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        asscPartsInventoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        asscPartsCostTableColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

        ObservableList<Part> allParts = Inventory.getAllParts();

        prodID = Inventory.getProductID();
        productIDTextfield.setText(Integer.toString(prodID));
        productIDTextfield.setEditable(false);

        asscPartsTableView.setItems(associatedParts);



//PARTS SEARCH
        FilteredList<Part> filteredData = new FilteredList<>(allParts, p -> true);
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(part -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (part.getPartName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(part.getPartID()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Part> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(addPartsTableView.comparatorProperty());
        addPartsTableView.setItems(sortedData);


    }


    //public void updatePartTable() {
      ///  addPartsTableView.setItems(Inventory.getAllParts());
    //}





}