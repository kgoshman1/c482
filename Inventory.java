package model;

import com.sun.scenario.effect.impl.prism.PrDrawable;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.util.ArrayList;


public class Inventory {

    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();

    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static int partID = 0;
    public static int productID = 0;

    public static void addPart(Part part) {
        allParts.add(part);
    }

    public static void addProduct(Product product) { allProducts.add(product); }


    public static Part lookupPart(int partID) {
        return null;                                //DOUBLE CHECK THIS WHY RETURN NULL
    }

    public static Product lookupProduct(int productID) {
        return null;                                //DOUBLE CHECK THIS WHY RETURN NULL
    }

    public static ObservableList<Part> lookupPart(String partName) {
        return null;                                //DOUBLE CHECK THIS WHY RETURN NULL
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        return null;                                 //DOUBLE CHECK THIS WHY RETURN NULL
    }

    public static void updatePart(int index, Part selectedPart){
             allParts.set(index, selectedPart);                                         //DOUBLE CHECK WHAT BELONGS IN FUNCTION
    }

    public static void updateProduct(int index, Product newProduct) {
            allProducts.set(index, newProduct);
    }

    public static boolean deletePart(Part selectedPart) {
        return false;                                   //WHY RETURN FALSE HERE
    }

    public static boolean deleteProduct(Product selectedProduct) {
        return true;                                   //WHY RETURN TRUE/FALSE HERE
    }

    public static int getPartsCount(){ //yK ;)
        return allParts.size();
    }

    public static int getPartID(){
        partID = partID + 1;
        return partID;
    }

    public static int getProductID(){
        productID = productID + 1;
        return productID;
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
