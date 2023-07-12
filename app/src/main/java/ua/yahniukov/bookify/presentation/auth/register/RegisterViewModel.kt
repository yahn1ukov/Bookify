package ua.yahniukov.bookify.presentation.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.yahniukov.bookify.data.repositories.AuthRepository
import ua.yahniukov.bookify.dto.auth.RegisterRequest
import ua.yahniukov.bookify.utils.Result
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private var _uiState = MutableLiveData<Result<Nothing>>()
    val uiState: LiveData<Result<Nothing>> = _uiState

    fun register(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            _uiState.value = Result.Loading
            _uiState.value = authRepository.register(registerRequest)
        }
    }
}