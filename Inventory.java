package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**Inventory Class */
public class Inventory {

    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();

    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();


    //Defines the starting  values for "auto-generated" ID values
    public static int partID = 20;
    public static int productID = 50;

    /**  Adds a part to "allParts" Observable List.
     *
     * @param part The selected part which is added to the list.
     */
    public static void addPart(Part part) {
        allParts.add(part);
    }

    /** Adds a product to ""allProducts" Observable List.
     *
     * @param product The selected product which is added to the list.
     */
    public static void addProduct(Product product) { allProducts.add(product); }

    /** Updates part tableview with new data.
     *
     * @param index location
     * @param selectedPart The new part
     */
    public static void updatePart(int index, Part selectedPart){
             allParts.set(index, selectedPart);
    }


    /** Updates product tableview with new data.
     *
     * @param index location
     * @param newProduct The new product
     */
    public static void updateProduct(int index, Product newProduct) {
            allProducts.set(index, newProduct);
    }

    /** Used in delete method to remove a selected product.
     *
     * @param product The selected products which is to be removed.
     */
    public static void removeProduct(Product product){
        allProducts.remove(product);
    }

    /** Used in the delete method to remove a selected part.
     *
     * @param part The selected part which is to be removed.
     */
    public static void removePart(Part part){
        allParts.remove(part);
    }

    /** Gets new Part ID value.
     *
     * @return Returns a new Part ID value incremented by 1.
     */
    public static int getPartID(){
        partID = partID + 1;
        return partID;
    }

    /** Gets new Prdouct ID value.
     *
     * @return Returns a new Product ID value incremented by 1.
     */
    public static int getProductID(){
        productID = productID + 1;
        return productID;
    }

    /** Gets all part observable list values.
     *
     * @return Returns "AllPart" values.
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /** Gets all product observable list values.
     *
     * @return Returns "AllProduct values.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
