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

    private val _throwable: MutableLiveData<Throwable?> = MutableLiveData()
    val throwable: LiveData<Throwable?>
        get() = _throwable

    fun getFavoritesByDescription(): LiveData<List<Favorite>> =
        service.selectOrderByDescription(null)

    fun getFavoritesByFavorited(): LiveData<List<Favorite>> =
        service.selectOrderByFavorited(null)

    fun insert(favorite: Favorite) {
        service.insert(favorite)
            .thenAccept {  }
            .exceptionally { postThrowable(it) }
    }

    fun delete(favorite: Favorite) {
        service.delete(favorite)
            .exceptionally { postThrowable(it) }
    }

    private fun postThrowable(throwable: Throwable): Void? {
        Log.e(tag, (throwable.message ?: ""), throwable)
        _throwable.postValue(throwable)
        return null
    }

}

private val tag = FavoritesViewModel::class.java.simpleName