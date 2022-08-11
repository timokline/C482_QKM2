package kline.qkmii.inventorymgmtsystem.model;

import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts;
    private static ObservableList<Product> allProducts;

    public static void addPart(Part newPart) {
        //TODO: Write addPart() method
    }

    public static void addProduct(Product newProduct) {
        //TODO: Write addProduct() method.
    }

    //FOR PART G:
    //IMPROVEMENT: CREATE A GENERIC METHOD FOR lookUpPart and lookUpProduct
    //              HELPER FUNCTION TO GENERALIZE THE ALGORITHM/LOGIC?
    public static Part lookupPart(int partId) {
        //TODO: Write lookupPart() method
        return new InHouse(0,"",0.0,0,0,0,0);
    }

    public static Product lookupProduct(int productId) {
        //TODO: Write lookupProduct() method
        return new Product();
    }

    public static ObservableList<Part> lookupPart(String partName) {
        //TODO: Write lookupPart() method
        return allParts;
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        //TODO: Write lookupProduct() method
        return allProducts;
    }

    public static void updatePart(int index, Part selectedPart) {
        //TODO: Write updatePart() method
    }

    public static void updateProduct(int index, Product newProduct) {
        //TODO: Write updateProduct() method
    }

    public static boolean deletePart(Part selectedPart) {
        //TODO: Write deletePart() method
        return false;
    }

    public static boolean deleteProduct(Product selectedProduct) {
        //TODO: Write deleteProduct() method
        return false;
    }

    public static ObservableList<Part> getAllParts() {
        //TODO: Write getAllParts() abstract method
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        //TODO: Write getAllProducts() abstract method
        return allProducts;
    }
}