package ua.yahniukov.bookify.presentation.auth.resetPassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ua.yahniukov.bookify.R
import ua.yahniukov.bookify.databinding.FragmentResetPasswordBinding
import ua.yahniukov.bookify.utils.Result
import ua.yahniukov.bookify.utils.ValidateHelper
import ua.yahniukov.bookify.utils.hide
import ua.yahniukov.bookify.utils.show
import ua.yahniukov.bookify.utils.showToast

@AndroidEntryPoint
class ResetPasswordFragment : Fragment() {
    private var _binding: FragmentResetPasswordBinding? = null
    private val binding get() = _binding!!

    private val resetPasswordViewModel: ResetPasswordViewModel by viewModels()
    private val validateHelper: ValidateHelper by lazy { ValidateHelper(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resetPasswordViewModel.uiState.observe(viewLifecycleOwner) { state ->
            handleUIState(state)
        }
        binding.buttonResetPassword.setOnClickListener { resetPassword() }
    }

    private fun handleUIState(state: Result<Nothing>) {
        when (state) {
            is Result.Success -> {
                hideLoading()
                findNavController().navigate(R.id.action_resetPasswordFragment_to_loginFragment)
            }

            is Result.Error -> {
                hideLoading()
                showToast(state.exception.message.toString())
            }

            Result.Loading -> {
                showLoading()
            }
        }
    }

    private fun showLoading() {
        binding.textForgotPassword.hide()
        binding.progressBarResetPassword.show()
    }

    private fun hideLoading() {
        binding.progressBarResetPassword.hide()
        binding.textForgotPassword.show()
    }

    private fun resetPassword() {
        val email = binding.editTextResetPasswordEmail.text.toString()
        if (validateHelper.validateEmail(email)) {
            resetPasswordViewModel.resetPassword(email)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}