package ua.yahniukov.bookify.presentation.home.listBook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.yahniukov.bookify.domain.models.Book
import ua.yahniukov.bookify.domain.usecases.book.BookUseCase
import ua.yahniukov.bookify.utils.Result
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val bookUseCase: BookUseCase
) : ViewModel() {
    private var _uiState = MutableLiveData<Result<List<Book>>>(Result.Idle)
    val uiState: LiveData<Result<List<Book>>> = _uiState

    init {
        getAll()
    }

    fun getAll() {
        viewModelScope.launch {
            _uiState.value = Result.Loading
            _uiState.value = bookUseCase.getAllBooksUseCase()
        }
    }
}