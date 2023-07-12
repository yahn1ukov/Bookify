package ua.yahniukov.bookify.presentation.home.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.yahniukov.bookify.data.repositories.BookRepository
import ua.yahniukov.bookify.dto.home.BookRequest
import ua.yahniukov.bookify.utils.Result
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {
    private var _uiState = MutableLiveData<Result<Nothing>>()
    val uiState: LiveData<Result<Nothing>> = _uiState

    fun create(bookRequest: BookRequest) {
        viewModelScope.launch {
            _uiState.value = Result.Loading
            _uiState.value = bookRepository.create(bookRequest)
        }
    }
}