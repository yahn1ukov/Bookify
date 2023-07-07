package ua.yahniukov.bookify.presentation.auth.register.viewmodels

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
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private var _registerState = MutableLiveData<Resource<FirebaseUser>?>(null)
    val registerState: LiveData<Resource<FirebaseUser>?> = _registerState

    fun register(firstName: String, lastName: String, email: String, password: String) =
        viewModelScope.launch {
            _registerState.value = Resource.Loading
            _registerState.value = authRepository.register(firstName, lastName, email, password)
        }
}