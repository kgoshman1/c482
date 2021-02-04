package model;

import javafx.scene.control.TextField;

public abstract class Part {

    protected int partID;
    protected String partName;
    protected double partPrice = 0.0;
    protected int partInStock;
    protected int min;
    protected int max;

    public Part(int partID, String partName, double partPrice, int partInStock, int min, int max) {
        this.partID = partID;
        this.partName = partName;
        this.partPrice = partPrice;
        this.partInStock = partInStock;
        this.min = min;
        this.max = max;
    }

    public Part() {

    }

    public int getPartID() {
        return partID;
    }

    public void setPartID(int partID) {
        this.partID = partID;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public double getPartPrice() {
        return partPrice;
    }

    public void setPartPrice(double partPrice) {
        this.partPrice = partPrice;
    }

    public int getPartInStock() {
        return partInStock;
    }

    public void setPartInStock(int partInStock) {
        this.partInStock = partInStock;
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

    public static String isPartValid(String partName, int min, int max, int partInStock, double partPrice, String errorMessage) {
        if (partName == null) {
            errorMessage = errorMessage + "The name field is required";
        } else if (partInStock < 1) {
            errorMessage = errorMessage + "The price must be greater than 50";
        } else if (partPrice <= 0) {
            errorMessage = errorMessage + "The price must be greater than 0";
        } else if (max < min) {
            errorMessage = errorMessage + "Max must be greater than min";
        } else if (partInStock < 1 || partInStock > max) {
            errorMessage = errorMessage + "The inventory must be between min and max values";
        }
        return errorMessage;
    }
}



