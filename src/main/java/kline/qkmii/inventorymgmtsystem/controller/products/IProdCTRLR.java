package kline.qkmii.inventorymgmtsystem.controller.products;

import javafx.event.ActionEvent;

import java.io.IOException;

public interface IProdCTRLR {

  void handleAddPartBtnEvent(ActionEvent event);

  void handleCancelBtnEvent(ActionEvent event) throws IOException;

  void handleRmvPartBtnEvent(ActionEvent event);

  void handleSaveBtnEvent(ActionEvent event) throws Exception;
}
