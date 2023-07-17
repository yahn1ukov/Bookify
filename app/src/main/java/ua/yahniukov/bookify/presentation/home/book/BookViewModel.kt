package ua.yahniukov.bookify.presentation.home.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.yahniukov.bookify.domain.usecases.book.BookUseCase
import ua.yahniukov.bookify.utils.Result
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val bookUseCase: BookUseCase
) : ViewModel() {
    private var _uiState = MutableLiveData<Result<Nothing>>(Result.Idle)
    val uiState: LiveData<Result<Nothing>> = _uiState

    fun delete(bookUID: String) {
        viewModelScope.launch {
            _uiState.value = Result.Loading
            _uiState.value = bookUseCase.deleteBookUseCase(bookUID)
        }
    }
}