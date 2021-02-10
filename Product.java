package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;

    /** Product Class */
public class Product extends Parent {

    public ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    protected int productID;
    protected String productName;
    protected double productPrice;
    protected int productStock;
    protected int min;
    protected int max;

        /** Product Constructor.
         * Defines a Product based on the below parameters
         * @param productID ID of the new product
         * @param productName Name of the new product
         * @param productPrice Price of the new product
         * @param productInStock Inventory of the new product
         * @param min Minumum amount of the new product
         * @param max Maximum amount of the new product
         */
    public Product (int productID, String productName, double productPrice, int productInStock, int min, int max) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productInStock;
        this.min = min;
        this.max = max;
    }

    public Product() {

    }

        /** Gets associated parts Observable List.
         *
         * @return Returns Associated parts list
         */
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

        /** Sets associated parts.
         *
         * @param part Part which is added to the Associated Parts list
         */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);



    }

        /** Gets Auto Generated Product ID.
         *
         * @return Returns Auto Generated Product ID
         */
    public int getProductID() {
        return productID;
    }

        /** Sets Product ID.
         *
         * @param productID Auto Generated Product ID
         */
    public void setProductID(int productID) {
        this.productID = productID;
    }

        /** Gets Product Name.
         *
         * @return Returns the product name
         */
    public String getName() {
        return productName;
    }

        /** Sets name of the product.
         *
         * @param name Name of the product
         */
    public void setName(String name) {
        this.productName = name;
    }

        /** Gets price of the product.
         *
         * @return Returns product price
         */
    public double getPrice() {
        return productPrice;
    }

        /** Sets price of the product.
         *
         * @param price Sets price of the product
         */
    public void setPrice(double price) {
        this.productPrice = price;
    }

        /** Gets product inventory.
         *
         * @return Returns product inventory
         */
    public int getStock() {
        return productStock;
    }

        /** Sets product inventory.
         *
         * @param stock Sets inventory of the product
         */
    public void setStock(int stock) {
        this.productStock = stock;
    }

        /** Get minimum allowable product.
         *
         * @return Returns minimum allowable product
         */
    public int getMin() {
        return min;
    }

        /** Sets minumum allowable Product.
         *
         * @param min Sets minimum allowable product
         */
    public void setMin(int min) {
        this.min = min;
    }

        /** Gets maximum allowable product.
         *
         * @return Returns maximum allowable product
         */
    public int getMax() {
        return max;
    }

        /** Sets maximum product.
         *
         * @param max Sets maximum allowable product
         */
    public void setMax(int max) {
        this.max = max;
    }

    public void setProductAssParts(ObservableList<Part>relatedParts){
        this.associatedParts = relatedParts;
    }

}
