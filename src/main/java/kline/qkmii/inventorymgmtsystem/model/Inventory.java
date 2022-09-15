package kline.qkmii.inventorymgmtsystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
  private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
  private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

  public static void addPart(Part newPart) throws NullPointerException {
    allParts.add(newPart);
  }

  public static void addProduct(Product newProduct) throws NullPointerException {
    allProducts.add(newProduct);
  }

  //FOR PART G:
  //IMPROVEMENT: CREATE A GENERIC METHOD FOR lookUpPart and lookUpProduct
  //              HELPER FUNCTION TO GENERALIZE THE ALGORITHM/LOGIC?
  public static Part lookupPart(int partId) throws IndexOutOfBoundsException {
    for (var part : allParts) {
      if (part.getId() == partId) {
        return part;
      }
    }

    return null;
  }

  public static Product lookupProduct(int productId) throws IndexOutOfBoundsException {
    for (var product : allProducts) {
      if (product.getId() == productId) {
        return product;
      }
    }

    return null;
  }

  public static ObservableList<Part> lookupPart(String partName) {
    List<Part> filteredParts = new ArrayList<>();

    for (var part : allParts) {
      if (part.getName().contains(partName)) {
        filteredParts.add(part);
      }
    }

    if (filteredParts.isEmpty()) {
      return allParts;
    } else {
      return FXCollections.observableArrayList(filteredParts);
    }
  }

  //Returns ObservableList of all products that match the string from `allProducts`.
  public static ObservableList<Product> lookupProduct(String productName) {
    List<Product> filteredProducts = new ArrayList<>();

    for (var product : allProducts) {
      if (product.getName().contains(productName)) {
        filteredProducts.add(product);
      }
    }

    if (filteredProducts.isEmpty()) {
      return allProducts;
    } else {
      return FXCollections.observableArrayList(filteredProducts);
    }
  }

  public static void updatePart(int index, Part selectedPart) {
    allParts.set(index, selectedPart);
  }

  public static void updateProduct(int index, Product newProduct) {
    allProducts.set(index, newProduct);
  }

  public static boolean deletePart(Part selectedPart) {
    for (var currentPart : allParts) {
      if (currentPart == selectedPart) {
        return allParts.remove(currentPart);
      }
    }
    return false;
  }

  public static boolean deleteProduct(Product selectedProduct) {
    for (var currentProduct : allProducts) {
      if (currentProduct == selectedProduct) {
        if (selectedProduct.getAllAssociatedParts().isEmpty()) {
          return allProducts.remove(currentProduct);
        }
        break;
      }
    }
    return false;
  }

  public static ObservableList<Part> getAllParts() {
    return allParts;
  }

  public static ObservableList<Product> getAllProducts() {
    return allProducts;
  }
}