package ua.yahniukov.bookify.presentation.auth.resetPassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.yahniukov.bookify.data.repositories.AuthRepository
import ua.yahniukov.bookify.utils.Result
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private var _uiState = MutableLiveData<Result<Nothing>>()
    val uiState: LiveData<Result<Nothing>> = _uiState

    fun resetPassword(email: String) {
        viewModelScope.launch {
            _uiState.value = Result.Loading
            _uiState.value = authRepository.resetPassword(email)
        }
    }
}