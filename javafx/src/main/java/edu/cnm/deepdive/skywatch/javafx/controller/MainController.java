package edu.cnm.deepdive.skywatch.javafx.controller;

import edu.cnm.deepdive.skywatch.core.model.SearchResultImage;
import edu.cnm.deepdive.skywatch.javafx.adapter.SearchResultImageAdapter;
import edu.cnm.deepdive.skywatch.javafx.viewmodel.SearchViewModel;
import jakarta.inject.Inject;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class MainController {

  private final SearchViewModel viewModel;

  @FXML
  private BorderPane rootPane;
  @FXML
  private TextField searchFilter;
  @FXML
  private Button search;
  @FXML
  private ListView<SearchResultImage> searchResults;

  @Inject
  public MainController(SearchViewModel viewModel) {
    this.viewModel = viewModel;
  }

  @FXML
  private void initialize() {
    searchResults.setCellFactory(new SearchResultImageAdapter());
    viewModel.subscribeToResult((results) -> {
      var items = searchResults.getItems();
      items.clear();
      items.addAll(results);
    });
  }

  @FXML
  private void search(ActionEvent actionEvent) {
    viewModel.search(searchFilter.getText(), null, null);
  }

}
