/*
 * FNAM: Product.java
 * DESC: Model class for products
 * AUTH: Timothy Albert Kline
 *
 * UPDT: 21 Sept 2022
 * VERS: 1.0
 * COPR: N/A
 */
package kline.qkmii.inventorymgmtsystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Class for a product with associated parts.
 * Inherits from the base class <code>Part</code>.
 * @author Timothy Albert Kline
 * @version 1.0
 * @see Part
 */
public class Product {
  private final ObservableList<Part> associatedParts = FXCollections.observableArrayList();
  private int id;
  private String name;
  private double price;
  private int stock;
  private int min;
  private int max;

  /**
   * Model constructor specifying product information.
   *
   * @param id the product ID
   * @param name the product name
   * @param price the product's price
   * @param stock the current product's inv. level
   * @param min the minimum inventory level
   * @param max the maximum inventory level
   */
  public Product(int id, String name, double price, int stock, int min, int max) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.stock = stock;
    this.min = min;
    this.max = max;
  }

  /**
   * @return the id
   */
  public int getId() {
    return this.id;
  }

  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return the product name
   */
  public String getName() {
    return this.name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the product price
   */
  public double getPrice() {
    return this.price;
  }

  /**
   * @param price the price to set
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * @return the product stock
   */
  public int getStock() {
    return this.stock;
  }

  /**
   * @param stock the stock to set
   */
  public void setStock(int stock) {
    this.stock = stock;
  }

  /**
   * @return the minimum stock
   */
  public int getMin() {
    return this.min;
  }

  /**
   * @param min the minimum to set
   */
  public void setMin(int min) {
    this.min = min;
  }

  /**
   * @return the maximum stock
   */
  public int getMax() {
    return this.max;
  }

  /**
   * @param max the maximum to set
   */
  public void setMax(int max) {
    this.max = max;
  }

  /**
   * @param part the part to associate
   * @see java.util.List#add(Object)
   */
  public void addAssociatedPart(Part part) {
    associatedParts.add(part);
  }

  /**
   * @param selectedAssociatedPart the associate part to remove
   * @return true, if successful; false, otherwise
   * @see java.util.List#remove(Object)
   */
  public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
    return associatedParts.remove(selectedAssociatedPart);
  }

  /**
   * @return the list of associated parts
   */
  public ObservableList<Part> getAllAssociatedParts() {
    return associatedParts;
  }
}
