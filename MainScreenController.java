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

       /** Main Screen Controller Class */
public class MainScreenController implements Initializable {


    @FXML private TextField partsSearchBox;
    @FXML private TextField productsSearchBox;
    @FXML private TableView<Part> partsTableview;
    @FXML private TableView<Product> productsTableview;
    @FXML private TableColumn<Part, Integer> partsIDColumn;
    @FXML private TableColumn<Part, String> partsNameColumn;
    @FXML private TableColumn<Part, Integer> partsInventoryColumn;
    @FXML private TableColumn<Part, Double> partsPriceColumn;
    @FXML private TableColumn<Part, Integer> productsIDColumn;
    @FXML private TableColumn<Part, String> productsNameColumn;
    @FXML private TableColumn<Part, Integer> productsInventoryColumn;
    @FXML private TableColumn<Part, Double> productsPriceColumn;
    @FXML public static int partsToModify;
    @FXML public static int productsToModify;

    ObservableList<Part> allParts = Inventory.getAllParts();
    ObservableList<Product> allProducts = Inventory.getAllProducts();

           /** Initializes controller after root element has completely processed.
            * RUNTIME ERROR - NULL POINTER - x40
            * Caused to due not initializing list or variable
            * FUTURE ENHANCEMENT - The UI is stale and quite small.  Also when resizing it does so in an
            * akward fashion.  I would like to fix these UI issues primarily.
            * @param url Fetches the data
            * @param resourceBundle Contains locale-specific data
            */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Sets up parts tableview
        partsIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partsNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        partsInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        partsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

        //Sets up products tableview
        productsIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productsInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Loads data into tableview
        partsTableview.setItems(Inventory.getAllParts());
        productsTableview.setItems(Inventory.getAllProducts());

        updatePartTableView();
        updateProductTableView();

      //PARTS SEARCH
        partsTableview.setPlaceholder(new Label("CUSTOM ALERT! NO PARTS FOUND!"));
        FilteredList<Part> filteredData = new FilteredList<>(allParts, p -> true);
        partsSearchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(part -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (part.getPartName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return String.valueOf(part.getPartID()).toLowerCase().contains(lowerCaseFilter);

            });
        });

        SortedList<Part> sortedData = new SortedList<>(filteredData);
        if (sortedData.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "error");
            alert.showAndWait();
        }
        sortedData.comparatorProperty().bind(partsTableview.comparatorProperty());
        partsTableview.setItems(sortedData);


        //PRODUCTS SEARCH
        productsTableview.setPlaceholder(new Label("CUSTOM ALERT! NO PRODUCTS FOUND!"));
        FilteredList<Product> filteredData2 = new FilteredList<>(allProducts, p -> true);
        productsSearchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData2.setPredicate(product -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (product.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else return String.valueOf(product.getProductID()).toLowerCase().contains(lowerCaseFilter);
            });
        });
        SortedList<Product> sortedData2 = new SortedList<>(filteredData2);
        sortedData2.comparatorProperty().bind(productsTableview.comparatorProperty());
        productsTableview.setItems(sortedData2);
    }

           /** Takes user to add part menu when add button is clicked.
            * RUNTIME ERROR - Unreported Exception IO exception must be caught or declared to be thrown
            * Caused by: java.lang.IllegalArgumentException: Can not set int field
            * view_controller.ModifyProductController.modifyProductIDTextField to javafx.scene.control.TextField
            * @param event Defines event
            * @throws IOException Throws exception if error exists
            */
    @FXML
    void partsAddButton(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

           /** Takes user to add product menu when add button is clicked.
            *
            * @param event Defines Event
            * @throws IOException Throws exception if error exists
            */
    @FXML
    void productsAddButton(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

           /** When user clicks delete button, removes part from the part tableview.
            *
            * @param event Defines event
            */
    @FXML
    void partsDeleteButton(ActionEvent event) {
        Part part = partsTableview.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this part \n" +
                "Select OK to proceed, or CANCEL to return.");
        Optional<ButtonType> delete = alert.showAndWait();
        if (delete.isPresent() && delete.get() == ButtonType.OK)
            Inventory.removePart(part);
        else if (part == null) {
            Alert alertNull = new Alert(Alert.AlertType.ERROR, "You have not selected an item to delete.\n" +
                    "Please select an item to delete first.");
            alertNull.show();
        }
    }

           /** When user clicks delete button, removes product from the product tableview.
            * RUNTIME ERROR - java.lang.reflect.InvocationTargetException
            * Checked exception thrown by a method / Constructor
            * @param event Defines event
            */
    @FXML
    void ProductsDeleteButton(ActionEvent event) {
        Product product = productsTableview.getSelectionModel().getSelectedItem();
        if (product.associatedParts.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this product? \n" +
                    "Select OK to proceed, or CANCEL to return.");

            Optional<ButtonType> delete = alert.showAndWait();
            if (delete.isPresent() && delete.get() == ButtonType.OK)
                Inventory.removeProduct(product);
        }
        else if (product == null) {
            Alert alertNull = new Alert(Alert.AlertType.ERROR, "You have not selected an item to delete.\n" +
                    "Please select an item to delete first.");
            alertNull.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "This product has a part associated with it");
            alert.showAndWait();
            }
        }



           /** Takes user to modify part menu.
            *
            * @param event Defines event
            * @throws IOException Throws exception in the event of an error
            */
    @FXML
    void partsModifyButton(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        partsToModify = Inventory.getAllParts().indexOf(partsTableview.getSelectionModel().getSelectedItem());
        Parent parent = FXMLLoader.load(getClass().getResource("ModifyPart.fxml"));
        Scene scene = new Scene(parent);
        window.setScene(scene);
        window.show();
    }

           /** Takes user to the modify products menu.
            * RUNTIME ERROR - view_controller.ModifyProductController.modifyProductIDTextField to javafx.scene.control.TextField
            * @param event Defines event
            * @throws IOException Throws exception in the event of an error
            */
    @FXML
    void ProductsModifyButton(ActionEvent event) throws IOException {

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        productsToModify = Inventory.getAllProducts().indexOf(productsTableview.getSelectionModel().getSelectedItem());
        Parent parent = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));
        Scene scene = new Scene(parent);
        window.setScene(scene);
        window.show();
    }



           /** Updates part tableview/sets updated parts */
    public void updatePartTableView() {
        partsTableview.setItems(Inventory.getAllParts());
    }
        /** Updates product tableview/sets updated parts */
    public void updateProductTableView() {
        productsTableview.setItems(Inventory.getAllProducts());
    }

           /** When user clicks exit, closes the application.
            *
            * @param event Defines event
            */
    @FXML
    void exitButton(ActionEvent event) {
        System.exit(0);
    }
}