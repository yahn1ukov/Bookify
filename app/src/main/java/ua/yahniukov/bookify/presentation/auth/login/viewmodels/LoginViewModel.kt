package ua.yahniukov.bookify.presentation.auth.login.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.yahniukov.bookify.data.Resource
import ua.yahniukov.bookify.data.repositories.AuthRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private var _loginState = MutableLiveData<Resource<FirebaseUser>?>(null)
    val loginState: LiveData<Resource<FirebaseUser>?> = _loginState

    init {
        if (authRepository.currentUser != null) {
            _loginState.value = Resource.Success(authRepository.currentUser!!)
        }
    }

    fun login(email: String, password: String) = viewModelScope.launch {
        _loginState.value = Resource.Loading
        _loginState.value = authRepository.login(email, password)
    }
}