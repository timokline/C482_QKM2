/*
 * FNAM: ProductBuilder.java
 * DESC: Builder design pattern for creating products
 * AUTH: Timothy Albert Kline
 * STRT: 15 Sep 2022
 * UPDT: 21 Sep 2022
 * VERS: 1.0
 * COPR: 2022 Timothy Albert Kline <timothyal.kline@gmail.com>
 * SRCS: https://blogs.oracle.com/javamagazine/post/exploring-joshua-blochs-builder-design-pattern-in-java
 */
package kline.qkmii.inventorymgmtsystem.model;

import javafx.collections.ObservableList;
import kline.qkmii.inventorymgmtsystem.util.FeedbackMessage;

/**
 * A Builder pattern class that creates a product.
 * Implements a builder pattern based on Joshua Bloch's version.
 * Stores a temporary <code>Product</code> object to specify information
 * about it and update its list of associated parts.
 * Code written follows this blog post as a guide:
 * <a href=https://blogs.oracle.com/javamagazine/post/exploring-joshua-blochs-builder-design-pattern-in-java>blogs.oracle.com</a>
 * @author Timothy Albert Kline
 * @version 1.0
 */
public class ProductBuilder {
  private static int productUID = 999;

  private final Product product;
  private boolean isNew;
  private int id;
  private String name;
  private double price;
  private int stock;
  private int min;
  private int max;

  /**
   * Default constructor specifying placeholder information for a new product.
   * Dummy values do not meet the requirements upon <code>build</code> call.
   */
  public ProductBuilder() {
    isNew = true;
    name = "";
    price = -1.0;
    stock = -1;
    min = -1;
    max = -1;
    this.product = new Product(-1, name, price, stock, min, max);
  }

  /**
   * Param constructor specifying provided information for a new product.
   * Used exclusively for product creation during main application lifecycle.
   */
  public ProductBuilder(String name, double price, int stock, int min, int max) {
    isNew = true;
    this.name = name;
    this.price = price;
    this.stock = stock;
    this.min = min;
    this.max = max;
    product = new Product(-1, name, price, stock, min, max);
  }

  /**
   * Constructor specifying to copy information of a provided product.
   * Assumes given product passed is going to be "rebuilt".
   * Stores a copy of said product and its associated parts.
   */
  public ProductBuilder(Product product) {
    isNew = false;
    id = product.getId();
    name = product.getName();
    price = product.getPrice();
    stock = product.getStock();
    min = product.getMin();
    max = product.getMax();
    this.product = new Product(
        id,
        name,
        price,
        stock,
        min,
        max);
    for (var part : product.getAllAssociatedParts()) {
      this.product.addAssociatedPart(part);
    }
  }

  /**
   * @param name the name to set
   * @return the reference to this ProductBuilder
   */
  public ProductBuilder name(String name) {
    this.name = name;

    return this;
  }

  /**
   * @param price the price to set
   * @return the reference to this ProductBuilder
   */
  public ProductBuilder price(double price) {
    this.price = price;

    return this;
  }

  /**
   * @param stock the stock level to set
   * @return the reference to this ProductBuilder
   */
  public ProductBuilder stock(int stock) {
    this.stock = stock;

    return this;
  }

  /**
   * @param max the maximum stock level to set
   * @return the reference to this ProductBuilder
   */
  public ProductBuilder max(int max) {
    this.max = max;

    return this;
  }

  /**
   * @param min the maximum stock level to set
   * @return the reference to this ProductBuilder
   */
  public ProductBuilder min(int min) {
    this.min = min;

    return this;
  }

  /**
   * Adds a <code>Part</code> to the running list of associated parts for the product.
   *
   * @param part the part to add
   * @return the reference to this ProductBuilder
   * @see Product#addAssociatedPart(Part) 
   */
  public ProductBuilder add(Part part) {
    product.addAssociatedPart(part);

    return this;
  }

  /**
   * Removes a <code>Part</code> from the running list of associated parts for the product.
   *
   * @param part the part to delete
   * @return the reference to this ProductBuilder
   * @see Product#deleteAssociatedPart(Part) 
   */
  public ProductBuilder delete(Part part) {
    product.deleteAssociatedPart(part);

    return this;
  }

  /**
   * @return the running list of associated parts for the product
   * @see Product#addAssociatedPart(Part)
   */
  public ObservableList<Part> viewAssocParts() {
    return product.getAllAssociatedParts();
  }

  /**
   * @return the next available UID
   */
  private int getProductUID() {
    return ++productUID;
  }

  /**
   * Specifies information of the product being assembled and creates it.
   *
   * @return the built product
   * @throws IllegalStateException if validate() throws
   * @see #validate()
   */
  public Product build() throws IllegalStateException {
    validate();
    product.setName(name);
    product.setPrice(price);
    product.setStock(stock);
    product.setMin(min);
    product.setMax(max);
    product.setId(isNew ? getProductUID() : id);
    if(isNew) {
      id = product.getId();
      isNew = false;
    }

    return product;
  }

  /**
   * Checks if all pieces of information about the product meet the requirements.
   * Appends feedback for every illegal input to a <code>FeedbackMessage.Builder()</code>
   * Attaches the logs to an <code>IllegalStateException</code>
   *
   * @throws IllegalStateException if error messages were logged
   * @see FeedbackMessage.Builder#Builder()
   */
  private void validate() throws IllegalStateException {
    var feedbackMessages = new FeedbackMessage.Builder();
    if (name == null || name.isEmpty()) {
      feedbackMessages.append("ProductBuilder.name cannot be null or empty.");
    }
    if (price < 0) {
      feedbackMessages.append("ProductBuilder.price: " + FeedbackMessage.NEGATIVE_NUM_MSG);
    }
    if (stock < 0) {
      feedbackMessages.append("ProductBuilder.stock: " + FeedbackMessage.NEGATIVE_NUM_MSG);
    }
    if (max < 0) {
      feedbackMessages.append("ProductBuilder.price: " + FeedbackMessage.NEGATIVE_NUM_MSG);
    }
    if (min < 0) {
      feedbackMessages.append("ProductBuilder.price: " + FeedbackMessage.NEGATIVE_NUM_MSG);
    }

    if (feedbackMessages.length() > 0) {
      throw new IllegalStateException(feedbackMessages.printLog().toString());
    }
  }
}
