package kline.qkmii.inventorymgmtsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kline.qkmii.inventorymgmtsystem.util.FilePath;

import java.io.IOException;

public class InvMgmtSysMain extends Application {
    
    @Override
    public void init() {
        System.out.println ("Starting application....");
    }

    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("FXMLLoader will load: " + FilePath.MAIN_MENU);
        FXMLLoader fxmlLoader = new FXMLLoader(InvMgmtSysMain.class.getResource(FilePath.MAIN_MENU));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
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
    public static void main(String[] args) { launch(args); }
}