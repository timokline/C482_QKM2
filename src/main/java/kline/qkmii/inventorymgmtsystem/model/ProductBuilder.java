package kline.qkmii.inventorymgmtsystem.model;

import javafx.collections.ObservableList;

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

  public ProductBuilder() {
    isNew = true;
    this.product = new Product(-1, null, -1, -1, -1, -1);
  }

  public ProductBuilder(Product product) {
    isNew = false;
    id = product.getId();
    this.product = new Product(id,
        product.getName(),
        product.getPrice(),
        product.getStock(),
        product.getMin(),
        product.getMax());
    for (var part : product.getAllAssociatedParts()) {
      this.product.addAssociatedPart(part);
    }
  }

  public static int getProductUID() {
    return ++productUID;
  }

  public ProductBuilder name(String name) {
    this.name = name;

    return this;
  }

  public ProductBuilder price(double price) {
    this.price = price;

    return this;
  }

  public ProductBuilder stock(int stock) {
    this.stock = stock;

    return this;
  }

  public ProductBuilder max(int max) {
    this.max = max;

    return this;
  }

  public ProductBuilder min(int min) {
    this.min = min;

    return this;
  }

  public ProductBuilder add(Part part) {
    product.addAssociatedPart(part);

    return this;
  }

  public boolean delete(Part part) {
    return product.deleteAssociatedPart(part);
  }

  public ObservableList<Part> viewAssocParts() {
    return product.getAllAssociatedParts();
  }

  public Product build() {
    product.setName(name);
    product.setPrice(price);
    product.setStock(stock);
    product.setMin(min);
    product.setMax(max);
    product.setId(isNew ? getProductUID() : id);

    return product;
  }
}
