package ua.yahniukov.bookify.presentation.home.profile

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.yahniukov.bookify.data.repositories.UserRepository
import ua.yahniukov.bookify.utils.Result
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private var _uiState = MutableLiveData<Result<Nothing>>()
    val uiState: LiveData<Result<Nothing>> = _uiState

    val currentUser: FirebaseUser = userRepository.currentUser!!

    fun updateProfilePhoto(image: Uri) {
        viewModelScope.launch {
            _uiState.value = Result.Loading
            _uiState.value = userRepository.updateProfilePhoto(image)
        }
    }
}