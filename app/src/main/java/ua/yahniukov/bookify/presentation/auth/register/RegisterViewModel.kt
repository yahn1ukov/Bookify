package ua.yahniukov.bookify.presentation.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.yahniukov.bookify.domain.usecases.auth.AuthUseCase
import ua.yahniukov.bookify.utils.Result
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    private var _uiState = MutableLiveData<Result<Nothing>>(Result.Idle)
    val uiState: LiveData<Result<Nothing>> = _uiState

    fun register(firstName: String, lastName: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = Result.Loading
            _uiState.value = authUseCase.registerUseCase(firstName, lastName, email, password)
        }
    }
}