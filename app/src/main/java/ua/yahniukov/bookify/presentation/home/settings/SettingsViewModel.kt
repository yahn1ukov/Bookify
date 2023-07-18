package ua.yahniukov.bookify.presentation.home.settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ua.yahniukov.bookify.domain.usecases.auth.AuthUseCase
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    fun logout() {
        authUseCase.logOutUseCase()
    }
}