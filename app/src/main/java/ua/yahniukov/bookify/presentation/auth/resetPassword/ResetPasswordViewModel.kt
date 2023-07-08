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
    private var _resetPasswordState = MutableLiveData<Result<Nothing>>()
    val resetPasswordState: LiveData<Result<Nothing>>
        get() = _resetPasswordState

    fun resetPassword(email: String) {
        viewModelScope.launch {
            _resetPasswordState.value = Result.Loading
            _resetPasswordState.value = authRepository.resetPassword(email)
        }
    }
}