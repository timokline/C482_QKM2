package kline.qkmii.inventorymgmtsystem.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kline.qkmii.inventorymgmtsystem.InvMgmtSysMain;

import java.io.IOException;

public abstract class SceneManager {
    Stage stage; //private
    Parent scene; //private
    FXMLLoader loader; //private

    public void switchScene(ActionEvent event, String location) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow(); //Might be a bug w/ type-casting
        loader = new FXMLLoader(InvMgmtSysMain.class.getResource(location));
        scene = new Scene(loader.load()).getRoot();
        stage.setScene(scene.getScene());
        stage.show();
    }

    public void switchScene(ActionEvent event, FXMLLoader fxmlLoader){
        stage = (Stage)((Node)event.getSource()).getScene().getWindow(); //Might be a bug w/ type-casting
        scene = fxmlLoader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public FXMLLoader createLoader(Object controller, String location) {
        var fxmlLoader = new FXMLLoader(InvMgmtSysMain.class.getResource(location));
        fxmlLoader.setController(controller);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fxmlLoader;
    }

    public FXMLLoader loadScene(String location) throws IOException {
        loader = new FXMLLoader(InvMgmtSysMain.class.getResource(location));
        loader.load();
        return loader;
    }

    public void returnToMenu(ActionEvent event) throws IOException {
        switchScene(event, FilePath.MAIN_MENU);
    }
}
