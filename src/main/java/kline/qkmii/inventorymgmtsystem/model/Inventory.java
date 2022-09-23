/*
 * FNAM: Inventory.java
 * DESC: Static model class of stored Part and Product objects
 * AUTH: Timothy Albert Kline
 * STRT: 11 Aug 2022
 * UPDT: 21 Sep 2022
 * VERS: 1.0
 * COPR: 2022 Timothy Albert Kline <timothyal.kline@gmail.com>
 */
package kline.qkmii.inventorymgmtsystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/** 
 * Static class for storing list of <code>Part</code>s and <code>Product</code>s.
 * @author Timothy Albert Kline
 * @version 1.0
 * @see Part
 * @see Product
 */
public class Inventory {
  private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
  private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

  /** Adds a given <code>Part</code> object to the current list of parts.
   *
   * @param newPart part to be added.
   * @throws NullPointerException if newPart is null.
   * @see List#add(Object)
   */
  public static void addPart(Part newPart) {
    allParts.add(newPart);
  }

  /** Adds a given <code>Part</code> object to the current list of parts.
   *
   * @param newProduct product to be added.
   * @throws NullPointerException if newProduct is null.
   * @see List#add(Object)
   */
  public static void addProduct(Product newProduct) {
    allProducts.add(newProduct);
  }

  /**
   * @param partId the query for the part
   * @return the part if found; null if ID does not exist.
   */
  public static Part lookupPart(int partId) {
    for (var part : allParts) {
      if (part.getId() == partId) {
        return part;
      }
    }

    return null;
  }

  /**
   * @param productId the query for the part
   * @return the product if found; null if ID does not exist.
   */
  public static Product lookupProduct(int productId) {
    for (var product : allProducts) {
      if (product.getId() == productId) {
        return product;
      }
    }

    return null;
  }

  /** 
   * Given a string, searches for partial or exact matches of a part's name.
   * 
   * @param partName the query for the part
   * @return the list of matches;
   *         or, the current part inventory if no matches found.
   */
  public static ObservableList<Part> lookupPart(String partName) {
    List<Part> filteredParts = new ArrayList<>();

    //Search for matching substring
    for (var part : allParts) {
      if (part.getName().contains(partName)) {
        filteredParts.add(part);
      }
    }

    return filteredParts.isEmpty() ? allParts : FXCollections.observableArrayList(filteredParts);
  }

  /** 
   * Given a string, searches for partial or exact matches of a product's name.
   * 
   * @param productName the query for the part
   * @return the list of matches;
   *         or, the current product inventory if no matches found.
   */
  public static ObservableList<Product> lookupProduct(String productName) {
    List<Product> filteredProducts = new ArrayList<>();

    //Search for matching substring
    for (var product : allProducts) {
      if (product.getName().contains(productName)) {
        filteredProducts.add(product);
      }
    }

    return filteredProducts.isEmpty() ? allProducts : FXCollections.observableArrayList(filteredProducts);
  }

  /**
   * @param index the index of the part to be modified
   * @param selectedPart the part to replace modified part
   * @throws NullPointerException if selectedPart is null.
   * @throws IndexOutOfBoundsException if index is out of bounds.
   * @see List#set(int, Object)
   */
  public static void updatePart(int index, Part selectedPart) {
    allParts.set(index, selectedPart);
  }

  /**
   * @param index the index of the product to be modified.
   * @param newProduct the part to replace modified product.
   * @throws NullPointerException if newProduct is null.
   * @throws IndexOutOfBoundsException if index is out of bounds.
   * @see List#set(int, Object)
   */
  public static void updateProduct(int index, Product newProduct) {
    allProducts.set(index, newProduct);
  }

  /** 
   * Given a <code>Part</code>, attempts to remove if part is found in part list.
   * 
   * @param selectedPart the part to be removed.
   * @return true if part is removed; false, otherwise.
   * @throws NullPointerException if selectedPart is null.
   * @see List#remove(Object)
   */
  public static boolean deletePart(Part selectedPart) {
    for (var currentPart : allParts) {
      if (currentPart == selectedPart) {
        return allParts.remove(currentPart);
      }
    }
    return false;
  }

  /** 
   * Given a <code>Product</code>, attempts to remove if product is found in product list.
   * Product cannot be removed if it has any associated parts.
   * 
   * @param selectedProduct the product to be removed.
   * @return true, if product is removed;
   * false, otherwise or selectedProduct has associated parts.
   * @see List#remove(Object)
   */
  public static boolean deleteProduct(Product selectedProduct) {
    if(selectedProduct.getAllAssociatedParts().isEmpty()) {
      for (var currentProduct : allProducts) {
        if (currentProduct == selectedProduct) {
          return allProducts.remove(currentProduct);
        }
      }
    }

    return false;
  }

  /**
   * @return the list of all currently stored parts
   */
  public static ObservableList<Part> getAllParts() {
    return allParts;
  }

  /**
   * @return the list of all currently stored products
   */
  public static ObservableList<Product> getAllProducts() {
    return allProducts;
  }
}