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

    public void switchScenes(ActionEvent event, String location) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InvMgmtSysMain.class.getResource(location));
        scene = new Scene(fxmlLoader.load()).getRoot();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow(); //Might be a bug w/ type-casting
        stage.setTitle("YAY!");
        stage.setScene(scene.getScene());
        stage.show();
    }

    public void returnToMenu(ActionEvent event) throws IOException {
        switchScenes(event, FilePath.MAIN_MENU);
    }
}
