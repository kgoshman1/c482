package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;

public class Product extends Parent {

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

    public Product() {

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

    public static String isPartValid(String partName, int min, int max, int productStock, double partPrice, String errorMessage) {
        if (partName == null) {
            errorMessage = errorMessage + "The name field is required";
        } else if (productStock < 1) {
            errorMessage = errorMessage + "The price must be greater than 50";
        } else if (partPrice <= 0) {
            errorMessage = errorMessage + "The price must be greater than 0";
        } else if (max < min) {
            errorMessage = errorMessage + "Max must be greater than min";
        } else if (productStock < 1 || productStock > max) {
            errorMessage = errorMessage + "The inventory must be between min and max values";
        }
        return errorMessage;
    }
}
