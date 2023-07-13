package ua.yahniukov.bookify.presentation.home.profile

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import ua.yahniukov.bookify.data.repositories.UserRepository
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    userRepository: UserRepository
) : ViewModel() {
    val currentUser: FirebaseUser = userRepository.currentUser!!
}