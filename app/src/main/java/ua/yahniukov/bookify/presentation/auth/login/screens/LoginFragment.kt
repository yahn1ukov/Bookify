package ua.yahniukov.bookify.presentation.auth.login.screens

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint
import ua.yahniukov.bookify.R
import ua.yahniukov.bookify.data.Resource
import ua.yahniukov.bookify.databinding.FragmentLoginBinding
import ua.yahniukov.bookify.presentation.auth.login.viewmodels.LoginViewModel
import ua.yahniukov.bookify.presentation.home.HomeActivity
import ua.yahniukov.bookify.utils.ValidateHelper

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel.loginState.observe(viewLifecycleOwner) {
            handleUI(it)
        }
        binding.buttonLogIn.setOnClickListener { login() }
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

    private fun showLoading() {
        binding.textLogo.visibility = View.INVISIBLE
        binding.progressBarLogin.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.textLogo.visibility = View.VISIBLE
        binding.progressBarLogin.visibility = View.INVISIBLE
    }

    private fun handleUI(state: Resource<FirebaseUser>?) {
        when (state) {
            is Resource.Success -> {
                hideLoading()
                val intent = Intent(requireActivity(), HomeActivity::class.java)
                requireActivity().startActivity(intent)
            }

            is Resource.Failure -> {
                hideLoading()
                Toast.makeText(
                    requireContext(),
                    state.exception.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            Resource.Loading -> {
                showLoading()
            }

            else -> {}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}