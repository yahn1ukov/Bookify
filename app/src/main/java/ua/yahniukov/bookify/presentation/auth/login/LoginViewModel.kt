package ua.yahniukov.bookify.presentation.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.yahniukov.bookify.data.repositories.AuthRepository
import ua.yahniukov.bookify.data.repositories.UserRepository
import ua.yahniukov.bookify.dto.auth.LoginRequest
import ua.yahniukov.bookify.utils.Result
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    userRepository: UserRepository
) : ViewModel() {
    private var _uiState = MutableLiveData<Result<Nothing>>()
    val uiState: LiveData<Result<Nothing>> = _uiState

    init {
        if (userRepository.currentUser != null) {
            _uiState.value = Result.Success()
        }
    }

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            _uiState.value = Result.Loading
            _uiState.value = authRepository.login(loginRequest)
        }
    }
}