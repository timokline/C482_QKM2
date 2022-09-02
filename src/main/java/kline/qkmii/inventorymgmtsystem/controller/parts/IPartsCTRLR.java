package kline.qkmii.inventorymgmtsystem.controller.parts;

import javafx.event.ActionEvent;

import java.io.IOException;

//Interface class for handle-event methods
public interface IPartsCTRLR {
    void handleSaveBtnEvent(ActionEvent event) throws Exception;

    void handleCancelBtnEvent(ActionEvent event) throws IOException;

    void handleInSrcBtnEvent(ActionEvent actionEvent);

    void handleOutSrcBtnEvent(ActionEvent actionEvent);
}
