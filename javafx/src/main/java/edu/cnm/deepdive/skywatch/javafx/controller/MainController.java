package edu.cnm.deepdive.skywatch.javafx.controller;

import edu.cnm.deepdive.skywatch.core.model.SearchResultImage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class MainController {

  @FXML
  private BorderPane rootPane;
  @FXML
  private TextField searchFilter;
  @FXML
  private Button search;
  @FXML
  private ListView<SearchResultImage> searchResults;

  @FXML
  private void search(ActionEvent actionEvent) {
    // TODO: 2026-11-02 Invoke search method on viewmodel.
  }

}
