package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;


public class Inventory {

//    public static void updateProduct (int index, Product selectedProduct) {
//        //Product originalProduct = Inventory.lookupProduct(index);
//        //Inventory.deleteProduct(originalProduct);
//        Inventory.addProduct(selectedProduct);
//    }
//
//
//    public static Product lookupProduct(int productID){
//        for (Product product : allProducts) {
//            if (product.getProductID() == productID) {
//                return product;
//            }
//        }
//        return  null;
//    }
//
//    public static void deleteProduct(Product selectedProduct) {
//        allProducts.remove(selectedProduct);
//    }







    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();

    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();



    public static int partID = 20;
    public static int productID = 50;

    public static void addPart(Part part) {
        allParts.add(part);
    }

    public static void addProduct(Product product) { allProducts.add(product); }


    public static void updatePart(int index, Part selectedPart){
             allParts.set(index, selectedPart);                                         //DOUBLE CHECK WHAT BELONGS IN FUNCTION
    }



    public static void updateProduct(int index, Product newProduct) {
            allProducts.set(index, newProduct);
    }


    public static void removeProduct(Product product){
        allProducts.remove(product);
    }

    public static void removePart(Part part){
        allParts.remove(part);
    }


    public static int getPartID(){
        partID = partID + 1;
        return partID;
    }

//    public Product lookupProduct(int productID){
//        for (Product p : allProducts){
//            if (productID == p.getProductID()) {
//                return p;
//            }
//        }
//        return null;
//    }



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
