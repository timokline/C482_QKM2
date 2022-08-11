package kline.qkmii.inventorymgmtsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InvMgmtSysMain extends Application {

    @Override
    public void init() {
        System.out.println ("Starting application....");
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InvMgmtSysMain.class.getResource("view/main-form.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
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