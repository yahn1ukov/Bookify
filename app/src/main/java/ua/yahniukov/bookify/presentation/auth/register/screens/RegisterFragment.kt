package ua.yahniukov.bookify.presentation.auth.register.screens

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint
import ua.yahniukov.bookify.data.Resource
import ua.yahniukov.bookify.databinding.FragmentRegisterBinding
import ua.yahniukov.bookify.presentation.auth.register.viewmodels.RegisterViewModel
import ua.yahniukov.bookify.presentation.home.HomeActivity
import ua.yahniukov.bookify.utils.ValidateHelper

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val registerViewModel: RegisterViewModel by viewModels()
    private val validateHelper: ValidateHelper by lazy { ValidateHelper(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerViewModel.registerState.observe(viewLifecycleOwner) {
            handleUI(it)
        }
        binding.buttonCreateAccount.setOnClickListener { register() }
    }

    private fun register() {
        val firstName = binding.editTextRegisterFirstName.text.toString()
        val lastName = binding.editTextRegisterLastName.text.toString()
        val email = binding.editTextRegisterEmail.text.toString()
        val password = binding.editTextRegisterPassword.text.toString()
        val confirmPassword = binding.editTextRegisterConfirmPassword.text.toString()

        if (
            validateHelper.validateFirstName(firstName) &&
            validateHelper.validateLastName(lastName) &&
            validateHelper.validateEmail(email) &&
            validateHelper.validatePassword(password) &&
            validateHelper.validateConfirmPassword(password, confirmPassword)
        ) {
            registerViewModel.register(firstName, lastName, email, password)
        }
    }

    private fun showLoading() {
        binding.toolbarRegister.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.toolbarRegister.visibility = View.INVISIBLE
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