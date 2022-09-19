package kline.qkmii.inventorymgmtsystem.model;

import javafx.collections.ObservableList;
import kline.qkmii.inventorymgmtsystem.util.FeedbackMessage;

//ProductBuilder
//Implements a builder pattern based on Joshua Bloch's version
//Stores a temporary Product object to update its list of associated parts
//Creates product from information given
public class ProductBuilder {
  private static int productUID = 999;

  private final boolean isNew;
  private final Product product;
  private int id;
  private String name;
  private double price;
  private int stock;
  private int min;
  private int max;

  //Constructs a product builder with placeholder information for a new product;
  public ProductBuilder() {
    isNew = true;
    name = "";
    price = -1.0;
    stock = -1;
    min = -1;
    max = -1;
    this.product = new Product(-1, name, price, stock, min, max);
  }

  //Constructs a product builder with provided information for a new product
  public ProductBuilder(String name, double price, int stock, int min, int max) {
    isNew = true;
    this.name = name;
    this.price = price;
    this.stock = stock;
    this.min = min;
    this.max = max;
    product = new Product(-1, name, price, stock, min, max);
  }

  //Constructs a product builder by copying information of a provided product. Stores a copy of said product.
  public ProductBuilder(Product product) {
    isNew = false;
    id = product.getId();
    name = product.getName();
    price = product.getPrice();
    stock = product.getStock();
    min = product.getMin();
    max = product.getMax();
    this.product = new Product(id,
        name,
        price,
        stock,
        min,
        max);
    for (var part : product.getAllAssociatedParts()) {
      this.product.addAssociatedPart(part);
    }
  }

  private int getProductUID() {
    return ++productUID;
  }

  //sets the name of the product. returns reference to this ProductBuilder
  public ProductBuilder name(String name) {
    this.name = name;

    return this;
  }

  //sets the price of the product. returns reference to this ProductBuilder
  public ProductBuilder price(double price) {
    this.price = price;

    return this;
  }

  //sets the inventory level of the product. returns reference to this ProductBuilder
  public ProductBuilder stock(int stock) {
    this.stock = stock;

    return this;
  }

  //sets the maximum amount for the product's stock. returns reference to this ProductBuilder
  public ProductBuilder max(int max) {
    this.max = max;

    return this;
  }

  //sets the minimum amount for the product's stock. returns reference to this ProductBuilder
  public ProductBuilder min(int min) {
    this.min = min;

    return this;
  }

  //adds a Part to the running list of associated parts for the product. returns reference to this ProductBuilder
  public ProductBuilder add(Part part) {
    product.addAssociatedPart(part);

    return this;
  }

  //removes a Part from the running list of associated parts for the product. returns boolean if success
  public boolean delete(Part part) {
    return product.deleteAssociatedPart(part);
  }

  //returns ObservableList<Part> the running list of associated parts for the product
  public ObservableList<Part> viewAssocParts() {
    return product.getAllAssociatedParts();
  }

  //Sets the variables of the current product. Throws if any parameters are invalid. Returns the built product.
  public Product build() throws IllegalStateException {
    product.setName(name);
    product.setPrice(price);
    product.setStock(stock);
    product.setMin(min);
    product.setMax(max);
    validate();
    product.setId(isNew ? getProductUID() : id);

    return product;
  }

  private void validate() throws IllegalStateException {
    var feedbackMessages = new FeedbackMessage.Builder();
    if (name == null || name.isEmpty()) {
      feedbackMessages.append("ProductBuilder.name cannot be null or empty.");
    }
    if (price < 0) {
      feedbackMessages.append("ProductBuilder.price cannot be negative.");
    }
    if (stock < 0) {
      feedbackMessages.append("ProductBuilder.stock cannot be negative.");
    }
    if (max < 0) {
      feedbackMessages.append("ProductBuilder.price cannot be negative.");
    }
    if (min < 0) {
      feedbackMessages.append("ProductBuilder.price cannot be negative.");
    }
    if (feedbackMessages.length() > 0) {
      throw new IllegalStateException(feedbackMessages.printLog().toString());
    }
  }
}
