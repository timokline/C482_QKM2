package kline.qkmii.inventorymgmtsystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart) throws NullPointerException {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) throws NullPointerException{
        allProducts.add(newProduct);
    }

    //FOR PART G:
    //IMPROVEMENT: CREATE A GENERIC METHOD FOR lookUpPart and lookUpProduct
    //              HELPER FUNCTION TO GENERALIZE THE ALGORITHM/LOGIC?
    public static Part lookupPart(int partId) throws IndexOutOfBoundsException {
        return allParts.get(partId);
    }

    public static Product lookupProduct(int productId) throws IndexOutOfBoundsException {
        return allProducts.get(productId);
    }

    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> filteredParts = FXCollections.observableArrayList();

        for(var part : allParts) {
            if(part.getName().contains(partName)){
                filteredParts.add(part);
            }
        }

        if(filteredParts.isEmpty()) {
            return allParts;
        } else {
            return filteredParts;
        }
    }

    //Returns ObservableList of all products that match the string from `allProducts`.
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> filteredProducts = FXCollections.observableArrayList();

        for(var product : allProducts) {
            if(product.getName().contains(productName)) {
                filteredProducts.add(product);
            }
        }

        if(filteredProducts.isEmpty()) {
            return allProducts;
        } else {
            return filteredProducts;
        }
    }

    public static void updatePart(int index, Part selectedPart) throws Exception {
        try {
            allParts.set(index, selectedPart);
        }
        catch (NullPointerException | IndexOutOfBoundsException e){
            throw new Exception(e);
        }
    }

    public static void updateProduct(int index, Product newProduct) throws Exception {
        try {
            allProducts.set(index, newProduct);
        }
        catch (NullPointerException | IndexOutOfBoundsException e){
            throw new Exception(e);
        }
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
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}