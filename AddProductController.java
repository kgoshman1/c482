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
import java.util.Optional;
import java.util.ResourceBundle;


public class AddProductController implements  Initializable {

    @FXML private TextField productNameTextfield;
    @FXML private TextField productIDTextfield;
    @FXML private TextField productInventoryTextField;
    @FXML private TextField priceTextField;
    @FXML private TextField minTextField;
    @FXML private TextField maxTextField;
    @FXML private TextField searchTextField;
    @FXML private TableView<Part> addPartsTableView;
    @FXML private TableColumn<Part, Integer> addPartsIDTableColumn;
    @FXML private TableColumn<Part, String> addPartsNameTableColumn;
    @FXML private TableColumn<Part, Integer> addPartsInventoryTableColumn;
    @FXML private TableColumn<Part, Double> addPartsCostTableColumn;
    @FXML private TableView<Part> asscPartsTableView;
    @FXML private TableColumn<Part, Integer> asscPartsIDTableColumn;
    @FXML private TableColumn<Part, String> asscPartsNameTableColumn;
    @FXML private TableColumn<Part, Integer> asscPartsInventoryTableColumn;
    @FXML private TableColumn<Part, Double> asscPartsCostTableColumn;
    @FXML protected ObservableList<Part>associatedParts = FXCollections.observableArrayList();
    private final int productIndex = MainScreenController.productsToModify;
    private int productID;
    private boolean outsourced;
    String Error = "";
    int keepTrack = 0;
    Product newestProduct = new Product(0,"",0.0,0,0,0);


    /** Initializes controller after root element has completely processed.
     * FUTURE ENHCANCEMENT - Add a picture view which includes the new product, as well as being able to interact with
     * it in a 3d view.  Also would include a text area for additional comments and container for links.
     * RUNTIME ERROR - Unreported Exception IO exception must be caught or declared to be thrown
     * Happened during multiple methods involving screen changes
     * @param url Fetches the data
     * @param resourceBundle Contains locale-specific data
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // updatePartTable();

        addPartsIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        addPartsNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        addPartsInventoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        addPartsCostTableColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

        asscPartsIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        asscPartsNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        asscPartsInventoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        asscPartsCostTableColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

        ObservableList<Part> allParts = Inventory.getAllParts();

        productID = Inventory.getProductID();
        productIDTextfield.setText(Integer.toString(productID));
        productIDTextfield.setEditable(false);

        asscPartsTableView.setItems(associatedParts);

        //PARTS SEARCH
        addPartsTableView.setPlaceholder(new Label("CUSTOM ALERT! NO PARTS FOUND!"));
        FilteredList<Part> filteredData = new FilteredList<>(allParts, p -> true);
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
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
        sortedData.comparatorProperty().bind(addPartsTableView.comparatorProperty());
        addPartsTableView.setItems(sortedData);

    }

    /** When save button is clicked, new product is created and saved.
     *
     * @param event Defines Event
     * @throws IOException Throws exception upon error
     */
    @FXML
    void tableViewSaveButton(ActionEvent event) throws IOException, NumberFormatException {

        String pName = productNameTextfield.getText();
        String pInventory = productInventoryTextField.getText();
        String pPrice = priceTextField.getText();
        String pMin = minTextField.getText();
        String pMax = maxTextField.getText();

try {
        if (!outsourced) {
            Product product = new Product();
            product.setProductID(productID);
            product.setName(pName);
            product.setStock(Integer.parseInt(pInventory));
            product.setPrice(Double.parseDouble(pPrice));
            product.setMin(Integer.parseInt(pMin));
            product.setMax(Integer.parseInt(pMax));
            for (int i = 0; i < associatedParts.size(); i++) {
                product.addAssociatedPart(associatedParts.get(i));
            }

            if (keepTrack == 0) {
                Inventory.addProduct(product);
            } else {

            }
            saveProduct();
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }
    } catch (NumberFormatException e) {
    Alert alert = new Alert(Alert.AlertType.ERROR, "ALL fields must have valid input to save, please try again");
    alert.showAndWait();
} catch (IOException e) {
    e.printStackTrace();
}

    }

    /** Method called by save button to verify data.*/
    public void saveProduct() throws NumberFormatException {
        try {
            //Creates new product, and gets values from textfields
            Product product = new Product(Integer.parseInt(productIDTextfield.getText()), productNameTextfield.getText(),
                    Double.parseDouble(priceTextField.getText()), Integer.parseInt(productInventoryTextField.getText()),
                    Integer.parseInt(minTextField.getText()), Integer.parseInt(maxTextField.getText()));

            String name = productNameTextfield.getText();
            String price = priceTextField.getText();
            String inv = productInventoryTextField.getText();
            String max = maxTextField.getText();
            String min = minTextField.getText();

            //Text field data in non string form for data verification
            double price2 = Double.parseDouble(priceTextField.getText());
            int inv2 = Integer.parseInt(productInventoryTextField.getText());
            int max2 = Integer.parseInt(maxTextField.getText());
            int min2 = Integer.parseInt(minTextField.getText());


            //Verification of data
            if (name == null || name.trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error. Must include a valid name, please try again");
                keepTrack++;
                alert.showAndWait();
            } else if (inv.equals("") || inv.trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must include valid Inventory, please try again");
                alert.showAndWait();
                keepTrack++;
            } else if (inv2 < 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must include valid inventory, please try again");
                alert.showAndWait();
                keepTrack++;
            } else if (price.equals("") || price.trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must include valid Price, please try again");
                alert.showAndWait();
                keepTrack++;
            } else if (price2 < 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must include valid Price, please try again");
                alert.showAndWait();
                keepTrack++;
            } else if (min.equals("") || min.trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must include valid Min, please try again");
                alert.showAndWait();
                keepTrack++;
            } else if (min2 < 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Min must be greater than 0");
                alert.showAndWait();
                keepTrack++;
            } else if (min2 > max2) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Min must be less than Max");
                alert.showAndWait();
                keepTrack++;
            } else if (max.equals("") || max.trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must include valid Max, please try again");
                alert.showAndWait();
                keepTrack++;
            } else if (max2 < 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Max must be greater than 0");
                alert.showAndWait();
                keepTrack++;
            } else if (inv2 < min2 || inv2 > max2) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Min must be greater than inventory, and" +
                        "Max must be less than inventory");
                alert.showAndWait();
                keepTrack++;
            } else if (associatedParts.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must add associated parts before saving");
                alert.showAndWait();
            }

            if (keepTrack == 0) {

                //If verification passes updates product
                Inventory.updateProduct(productIndex, product);
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ALL fields must be populated with correct data in" +
                    " order to be saved");
            keepTrack++;
            alert.showAndWait();
        }
    }





    /** Adds part to associated parts list.
     *
     * @param event Defines event
     */
        @FXML
    void setTableViewAddButton(ActionEvent event) {
        Part part = addPartsTableView.getSelectionModel().getSelectedItem();
        newestProduct.addAssociatedPart(part);

        if (part == null){
        } else {
            int id = part.getPartID();
            for (Part associatedPart : associatedParts) {
                if (associatedPart.getPartID() == id) {
                }
            }
            associatedParts.add(part);
            asscPartsTableView.setItems(associatedParts);
        }
    }

    /** Takes user back to MainMenu if cancel button is clicked.
     *
     * @param event Defines Event
     * @throws IOException Throws exception if Error
     */
    @FXML
    void tableViewCancelButton(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /** Deletes associated part from associated parts tableview.
     *
     * @param event Defines event
     */
    @FXML
    void tableViewDeleteButton(ActionEvent event) {

        ObservableList<Part> allParts, partToRemove;
        allParts = asscPartsTableView.getItems();
        partToRemove = asscPartsTableView.getSelectionModel().getSelectedItems();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this Associated Part? " +
                "Select OK to proceed or CANCEL to return");
        Optional<ButtonType> delete = alert.showAndWait();
        if (delete.isPresent() && delete.get() == ButtonType.OK) {
            partToRemove.forEach(allParts::remove);
        }
    }
}