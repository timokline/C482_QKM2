/*
 * FNAM: module-info.java
 * DESC: Collection of handle methods for validating input
 * AUTH: Timothy Albert Kline
 * STRT: 15 Sep 2022
 * UPDT: 22 Sep 2022
 * VERS: 1.0
 * COPR: 2022 Timothy Albert Kline <timothyal.kline@gmail.com>
 */
/**
 * This module provides all the packages for an inventory management system
 * with JavaFX. Packages are structured with the MVC framework.
 * <p>
 *   The {@link kline.qkmii.inventorymgmtsystem} contains the main application
 *   and JavaFX FXML loading and dialog creation.
 * </p>
 * <p>
 *   The {@link kline.qkmii.inventorymgmtsystem.controller} contains controller
 *   classes for the views and access to the models.
 * </p>
 * <p>
 *   The {@link kline.qkmii.inventorymgmtsystem.controller.parts} contains the
 *   controllers for the parts form view.
 * </p>
 * <p>
 *   The {@link kline.qkmii.inventorymgmtsystem.controller.products} contains
 *   the controllers for the products form view.
 * </p>
 * <p>
 *  The {@link kline.qkmii.inventorymgmtsystem.model} contains the model classes
 *  that handle logic and the database.
 * </p>
 * <p>
 *   The {@link kline.qkmii.inventorymgmtsystem.util} contains extra tools that
 *   are used throughout the application.
 * </p>
 * @author Timothy Albert Kline
 */
module kline.qkmii.inventorymgmtsystem {
  requires javafx.controls;
  requires javafx.fxml;

  exports kline.qkmii.inventorymgmtsystem;
  opens kline.qkmii.inventorymgmtsystem to javafx.fxml;
  exports kline.qkmii.inventorymgmtsystem.controller;
  opens kline.qkmii.inventorymgmtsystem.controller to javafx.fxml;
  exports kline.qkmii.inventorymgmtsystem.model;
  opens kline.qkmii.inventorymgmtsystem.model to javafx.fxml;
  exports kline.qkmii.inventorymgmtsystem.util;
  opens kline.qkmii.inventorymgmtsystem.util to javafx.fxml;
  exports kline.qkmii.inventorymgmtsystem.controller.parts;
  opens kline.qkmii.inventorymgmtsystem.controller.parts to javafx.fxml;
  exports kline.qkmii.inventorymgmtsystem.controller.products;
  opens kline.qkmii.inventorymgmtsystem.controller.products to javafx.fxml;
}