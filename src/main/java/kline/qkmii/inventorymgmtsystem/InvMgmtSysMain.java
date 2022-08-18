package kline.qkmii.inventorymgmtsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kline.qkmii.inventorymgmtsystem.model.InHouse;
import kline.qkmii.inventorymgmtsystem.model.Inventory;
import kline.qkmii.inventorymgmtsystem.util.FilePath;

import java.io.IOException;

public class InvMgmtSysMain extends Application {
    private static int partUID = -1;
    private static int productUID = 999;
    public static int getPartUID() {
        return ++partUID;
    }
    public static int getProductUID() {return ++productUID;}
    
    @Override
    public void init() {
        System.out.println ("Starting application....");
    }

    @Override
    public void start(Stage stage) throws IOException {
        //System.out.println("FXMLLoader will load: " + FilePath.MAIN_MENU); //TODO: REMOVE LOG MSG
        FXMLLoader fxmlLoader = new FXMLLoader(InvMgmtSysMain.class.getResource(FilePath.MAIN_MENU));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        System.out.println ("Stopping application....");
    }

    /**
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        InHouse myPart1 = new InHouse(getPartUID(), "handle", 0.80, 50, 1, 500, 1000);
        InHouse myPart2 = new InHouse(getPartUID(), "nail", 0.07, 100, 1, 1000, 10);
        InHouse myPart3 = new InHouse(getPartUID(), "screw", 0.11, 100, 1, 1000, 11);

        Inventory.addPart(myPart1);
        Inventory.addPart(myPart2);
        Inventory.addPart(myPart3);

        launch(args);
    }
}