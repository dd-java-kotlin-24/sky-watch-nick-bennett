package edu.cnm.deepdive.skywatch.app.viewmodel;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import edu.cnm.deepdive.skywatch.core.model.SearchResultImage;
import edu.cnm.deepdive.skywatch.core.service.MediaSearchService;
import jakarta.inject.Inject;
import java.time.OffsetDateTime;
import java.util.List;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

@HiltViewModel
public class SearchViewModel extends ViewModel {

  private static final String TAG = SearchViewModel.class.getSimpleName();

  private final MediaSearchService service;
  private final MutableLiveData<List<SearchResultImage>> searchResults;
  private final MutableLiveData<Throwable> throwable;

  @Inject
  SearchViewModel(@NonNull MediaSearchService service) {
    this.service = service;
    searchResults = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
  }

  public @NonNull LiveData<List<SearchResultImage>> getSearchResults() {
    return searchResults;
  }

  public @NonNull LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public void search(
      @Nullable String searchFilter,
      @Nullable OffsetDateTime startDate,
      @Nullable OffsetDateTime endDate
  ) {
    service
        .search(searchFilter, startDate, endDate)
        .thenAccept(searchResults::postValue)
        .exceptionally(this::postThrowable);
  }

  private Void postThrowable(Throwable throwable) {
    Log.e(TAG, throwable.getMessage(), throwable);
    this.throwable.postValue(throwable);
    return null;
  }

}
