package ua.yahniukov.bookify.presentation.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ua.yahniukov.bookify.R
import ua.yahniukov.bookify.databinding.FragmentLoginBinding
import ua.yahniukov.bookify.presentation.home.HomeActivity
import ua.yahniukov.bookify.utils.Result
import ua.yahniukov.bookify.utils.ValidateHelper
import ua.yahniukov.bookify.utils.hide
import ua.yahniukov.bookify.utils.navigate
import ua.yahniukov.bookify.utils.show
import ua.yahniukov.bookify.utils.showToast

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModels()
    private val validateHelper: ValidateHelper by lazy { ValidateHelper(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel.uiState.observe(viewLifecycleOwner) { state ->
            handleUIState(state)
        }
        binding.buttonLogIn.setOnClickListener { login() }
        binding.textForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment)
        }
        binding.buttonNewAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun login() {
        val email = binding.editTextLoginEmail.text.toString()
        val password = binding.editTextLoginPassword.text.toString()
        if (validateHelper.validateEmail(email) && validateHelper.validatePassword(password)) {
            loginViewModel.login(email, password)
        }
    }

    private fun handleUIState(state: Result<Nothing>) {
        when (state) {
            is Result.Success -> {
                hideLoading()
                navigate(requireActivity(), HomeActivity::class.java)
            }

            is Result.Error -> {
                hideLoading()
                showToast(state.exception.message.toString())
            }

            Result.Loading -> {
                showLoading()
            }
            
            Result.Idle -> {}
        }
    }

    private fun showLoading() {
        binding.textLogo.hide()
        binding.progressBarLogin.show()
    }

    private fun hideLoading() {
        binding.progressBarLogin.hide()
        binding.textLogo.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}