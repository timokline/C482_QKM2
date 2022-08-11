module kline.qkmii.inventorymgmtsystem {
    requires javafx.controls;
    requires javafx.fxml;

    exports kline.qkmii.inventorymgmtsystem;
    opens kline.qkmii.inventorymgmtsystem to javafx.fxml;
    exports kline.qkmii.inventorymgmtsystem.controller;
    opens kline.qkmii.inventorymgmtsystem.controller to javafx.fxml;
    exports kline.qkmii.inventorymgmtsystem.model;
    opens kline.qkmii.inventorymgmtsystem.model to javafx.fxml;
}