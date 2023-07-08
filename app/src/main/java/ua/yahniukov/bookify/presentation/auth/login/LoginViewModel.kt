package ua.yahniukov.bookify.presentation.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.yahniukov.bookify.data.repositories.AuthRepository
import ua.yahniukov.bookify.data.repositories.UserRepository
import ua.yahniukov.bookify.utils.Result
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    userRepository: UserRepository
) : ViewModel() {
    private var _loginState = MutableLiveData<Result<FirebaseUser>?>(null)
    val loginState: LiveData<Result<FirebaseUser>?>
        get() = _loginState

    init {
        if (userRepository.currentUser != null) {
            _loginState.value = Result.Success(userRepository.currentUser!!)
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = Result.Loading
            _loginState.value = authRepository.login(email, password)
        }
    }
}