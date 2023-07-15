package ua.yahniukov.bookify.presentation.home.add

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.yahniukov.bookify.data.repositories.BookRepository
import ua.yahniukov.bookify.utils.Result
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {
    private var _uiState = MutableLiveData<Result<Nothing>>(Result.Idle)
    val uiState: LiveData<Result<Nothing>> = _uiState

    fun create(image: Uri, name: String, author: String, description: String) {
        viewModelScope.launch {
            _uiState.value = Result.Loading
            _uiState.value = bookRepository.create(image, name, author, description)
        }
    }

    fun restoreUIState() {
        _uiState.value = Result.Idle
    }
}