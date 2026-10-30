package edu.cnm.deepdive.skywatch.javafx.controller;

import edu.cnm.deepdive.skywatch.core.model.SearchResultImage;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class MainController {

  @FXML
  private BorderPane rootPane;
  @FXML
  private ListView<SearchResultImage> searchResults;

}
