package edu.cnm.deepdive.skywatch.javafx.viewmodel;

import edu.cnm.deepdive.skywatch.core.model.SearchResultImage;
import edu.cnm.deepdive.skywatch.core.service.MediaSearchService;
import java.time.OffsetDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import javafx.application.Platform;
import org.jspecify.annotations.Nullable;

public class SearchViewModel {

  private final MediaSearchService service;
  private final List<Consumer<List<SearchResultImage>>> searchResultSubscribers;
  private final List<Consumer<Throwable>> exceptionSubscribers;

  public SearchViewModel(MediaSearchService service) {
    this.service = service;
    searchResultSubscribers = new LinkedList<>();
    exceptionSubscribers = new LinkedList<>();
  }

  public void search(
      @Nullable String searchFilter,
      @Nullable OffsetDateTime startDate,
      @Nullable OffsetDateTime endDate
  ) {
    service
        .search(searchFilter, startDate, endDate)
        .thenAccept((searchResultImages) -> {
          for (var subscriber : searchResultSubscribers) {
            Platform.runLater(() -> subscriber.accept(searchResultImages));
          }
        })
        .exceptionally((throwable) -> {
          for (var subscriber : exceptionSubscribers) {
            Platform.runLater(() -> subscriber.accept(throwable));
          }
          return null;
        });
  }

  public void subscribeToResult(Consumer<List<SearchResultImage>> subscriber) {
    searchResultSubscribers.add(subscriber);
  }

  public void subscribeToException(Consumer<Throwable> subscriber) {
    exceptionSubscribers.add(subscriber);
  }

}
