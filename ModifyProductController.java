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
    private TextField modifySearchTextField;
    @FXML
    private TableView<Part> associatedPartsTableView;
    @FXML
    private TableColumn<Part, Integer> associatedPartsIDTableColumn;
    @FXML
    private TableColumn<Part, String> associatedPartsNameTableColumn;
    @FXML
    private TableColumn<Part, Integer> associatedPartsInventoryTableColumn;
    @FXML
    private TableColumn<Part, Double> associatedPartsCostTableColumn;
    @FXML
    private TableView<Part> availablePartsTableView;
    @FXML
    private TableColumn<Part, Integer> availablePartsIDColumn;
    @FXML
    private TableColumn<Part, String> availablePartsNameColumn;
    @FXML
    private TableColumn<Part, Integer> availablePartsInventoryColumn;
    @FXML
    private TableColumn<Part, Double> availablePartsCostColumn;
    @FXML
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    @FXML
    Product newestProduct = new Product();
    @FXML
    private Product product;
    String Error = "";

    int keepTrack = 0;

    /** Initializes controller after root element has completely processed.
     * RUNTIME ERROR - Unreported Exception IO exception must be caught or declared to be thrown
     * RUNTIME ERROR - Happened multiple times primarily with the save button mechanisms.
     * @param url Fetches the data
     * @param resourceBundle Contains locale-specific data
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //SETS ID TO UNEDITABLE
        modifyProductIDTextField.setEditable(false);

        Product product = Inventory.getAllProducts().get(productIndex);

        //Populate Textfield data
        modifyProductIDTextField.setText(Integer.toString(product.getProductID()));
        modifyProductNameTextField.setText(product.getName());
        modifyProductInventoryTextField.setText(Integer.toString(product.getStock()));
        modifyMinTextField.setText(Integer.toString(product.getMin()));
        modifyMaxTextField.setText(Integer.toString(product.getMax()));
        modifyPriceTextField.setText(Double.toString(product.getPrice()));

        //TOP TABLEVIEW
        availablePartsIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        availablePartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        availablePartsInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        availablePartsCostColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

        //BOTTOM TABLEVIEW
        associatedPartsIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        associatedPartsNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        associatedPartsInventoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        associatedPartsCostTableColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

        associatedParts = product.getAssociatedParts();
        associatedPartsTableView.setItems(associatedParts);

        ObservableList<Part> allParts = Inventory.getAllParts();

        //Parts Search
        availablePartsTableView.setPlaceholder(new Label("CUSTOM ALERT! NO PARTS FOUND!"));
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
        sortedData.comparatorProperty().bind(availablePartsTableView.comparatorProperty());

        //SETS MODIFIED PARTS TABLEVIEW
        availablePartsTableView.setItems(sortedData);
    }

    /** When save button is clicked verifies data and returns user to main menu.
     *
     * @param event Defines event
     * @throws IOException Throws exception if error occurs
     */
    @FXML
    void modifyTableViewSaveButton(ActionEvent event) throws IOException {

        saveProduct();
        Parent parent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /** Method called by save button click to create new product and verify data.*/
    public void saveProduct() throws NumberFormatException {
        try {
            Product product = new Product(Integer.parseInt(modifyProductIDTextField.getText()), modifyProductNameTextField.getText(),
                    Double.parseDouble(modifyPriceTextField.getText()), Integer.parseInt(modifyProductInventoryTextField.getText()),
                    Integer.parseInt(modifyMinTextField.getText()), Integer.parseInt(modifyMaxTextField.getText()));

            String name = modifyProductNameTextField.getText();
            String price = modifyPriceTextField.getText();
            String inv = modifyProductInventoryTextField.getText();
            String max = modifyMaxTextField.getText();
            String min = modifyMinTextField.getText();

            double price2 = Double.parseDouble(modifyPriceTextField.getText());
            int inv2 = Integer.parseInt(modifyProductInventoryTextField.getText());
            int max2 = Integer.parseInt(modifyMaxTextField.getText());
            int min2 = Integer.parseInt(modifyMinTextField.getText());

//
//        for (int i = 0; i < associatedParts.size(); i++) {
//            product.addAssociatedPart(associatedParts.get(i));
            product.setProductID(product.getProductID());
            product.setPrice(product.getPrice());
            product.setStock(product.getStock());
            product.setMax(product.getMax());
            product.setMin(product.getMin());
            product.setProductAssParts(associatedParts);


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
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must include valid Price, please try again");
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
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Min must be less than inventory, and" +
                        "Max must be greater than inventory");
                alert.showAndWait();
                keepTrack++;
            } else if (associatedParts.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error.  Must add associated parts before saving");
                alert.showAndWait();
            }

            if (keepTrack == 0) {

                Inventory.updateProduct(productIndex, product);
            }
        } catch(NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error. ALL fields must be populated with valid input, please try again");
                keepTrack++;
                alert.showAndWait();
            }
         }


    /** Adds part to associated parts list.
     *
     * @param event Defines Event
     */
    @FXML
    void addButtonMethod(ActionEvent event) {

        Part selectedparts = availablePartsTableView.getSelectionModel().getSelectedItem();
        //newestProduct.addAssociatedPart(selectedparts);
        if (selectedparts == null) {
            return;
        } else {
            //associatedParts.add (selectedparts);
            associatedParts.add(selectedparts);
        }
            associatedPartsTableView.setItems(associatedParts);
    }


    /** Deletes associated parts from associated parts tableview.
     *
     * @param event Defines an event
     */
    @FXML
    void ModifyTableViewDeleteButton (ActionEvent event) {

        ObservableList<Part> allParts, partToDelete;
        allParts = associatedPartsTableView.getItems();
        partToDelete = associatedPartsTableView.getSelectionModel().getSelectedItems();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this Associated Part? " +
                "Select OK to proceed or CANCEL to return");
        Optional<ButtonType> delete = alert.showAndWait();
        if (delete.isPresent() && delete.get() == ButtonType.OK) {
            partToDelete.forEach(allParts::remove);
        }
    }

    /** When the cancel button is pressed, user is taken back to the main screen.
     *
     * @param event Defines Event
     * @throws IOException If error throws exception
     */
    @FXML
    void modifyTableViewCancelButton(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }


}