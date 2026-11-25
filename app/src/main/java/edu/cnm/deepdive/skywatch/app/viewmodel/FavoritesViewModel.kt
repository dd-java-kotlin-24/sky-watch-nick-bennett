package edu.cnm.deepdive.skywatch.app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.cnm.deepdive.skywatch.app.model.entity.Favorite
import edu.cnm.deepdive.skywatch.app.service.FavoritesService
import jakarta.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val service: FavoritesService) : ViewModel() {

    val throwable: LiveData<Throwable?>
        field: MutableLiveData<Throwable?> = MutableLiveData()

    val favoritesByDescription: LiveData<List<Favorite>>
        get() = service.selectOrderByDescription()

    val favoritesByFavorited: LiveData<List<Favorite>>
        get() = service.selectOrderByFavorited()

    fun insert(favorite: Favorite) {
        service.insert(favorite)
            .thenAccept {  }
            .exceptionally(::postThrowable)
    }

    fun delete(favorite: Favorite) {
        service.delete(favorite)
            .exceptionally(::postThrowable)
    }

    private fun postThrowable(throwable: Throwable): Void? {
        Log.e(tag, (throwable.message ?: ""), throwable)
        this.throwable.postValue(throwable)
        return null
    }

}

private val tag = FavoritesViewModel::class.java.simpleName