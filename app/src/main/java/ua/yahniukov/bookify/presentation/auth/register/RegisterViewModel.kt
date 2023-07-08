package ua.yahniukov.bookify.presentation.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.yahniukov.bookify.data.repositories.AuthRepository
import ua.yahniukov.bookify.utils.Result
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private var _registerState = MutableLiveData<Result<FirebaseUser>>()
    val registerState: LiveData<Result<FirebaseUser>>
        get() = _registerState

    fun register(firstName: String, lastName: String, email: String, password: String) {
        viewModelScope.launch {
            _registerState.value = Result.Loading
            _registerState.value = authRepository.register(firstName, lastName, email, password)
        }
    }
}