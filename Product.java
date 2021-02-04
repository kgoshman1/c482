package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {

    protected ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    protected int productID;
    protected String productName;
    protected double productPrice;
    protected int productStock;
    protected int min;
    protected int max;

    public Product (int productID, String productName, double productPrice, int productInStock, int min, int max) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productInStock;
        this.min = min;
        this.max = max;
    }


    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

    public void setAssociatedParts(Part part) {
        associatedParts.add(part);
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return productName;
    }

    public void setName(String name) {
        this.productName = name;
    }

    public double getPrice() {
        return productPrice;
    }

    public void setPrice(double price) {
        this.productPrice = price;
    }

    public int getStock() {
        return productStock;
    }

    public void setStock(int stock) {
        this.productStock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
