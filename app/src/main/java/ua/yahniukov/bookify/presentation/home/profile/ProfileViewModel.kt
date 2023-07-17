package ua.yahniukov.bookify.presentation.home.profile

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import ua.yahniukov.bookify.domain.usecases.user.UserUseCase
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    userUseCase: UserUseCase
) : ViewModel() {
    val currentUser: FirebaseUser = userUseCase.getCurrentUserUseCase()!!
}