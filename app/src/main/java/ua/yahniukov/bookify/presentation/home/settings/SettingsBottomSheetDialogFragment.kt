package ua.yahniukov.bookify.presentation.home.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import ua.yahniukov.bookify.R
import ua.yahniukov.bookify.databinding.FragmentSettingsBottomSheetDialogBinding
import ua.yahniukov.bookify.presentation.auth.AuthActivity
import ua.yahniukov.bookify.utils.Result
import ua.yahniukov.bookify.utils.navigate
import ua.yahniukov.bookify.utils.showDialog
import ua.yahniukov.bookify.utils.showToast

@AndroidEntryPoint
class SettingsBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentSettingsBottomSheetDialogBinding? = null
    private val binding get() = _binding!!

    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBottomSheetDialogBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settingsViewModel.uiState.observe(viewLifecycleOwner) { state ->
            handleUIState(state)
        }
        binding.buttonDeleteAllBooks.setOnClickListener { showRemoveAllDialog() }
        binding.buttonLogOut.setOnClickListener { logOut() }
    }

    private fun showRemoveAllDialog() {
        showDialog(
            "Remove All?",
            "Are you sure that you want to remove all?",
            R.drawable.ic_bin,
            { _, _ -> removeAll() },
            { dialog, _ -> dialog.cancel() }
        )
    }

    private fun removeAll() {
        settingsViewModel.deleteAll()
    }

    private fun logOut() {
        settingsViewModel.logout()
        navigate(requireActivity(), AuthActivity::class.java)
    }

    private fun handleUIState(state: Result<Nothing>) {
        when (state) {
            is Result.Success -> {
                findNavController().navigate(R.id.action_bottomSheetDialogSettingsFragment_to_profileFragment)
            }

            is Result.Error -> {
                showToast(state.exception.message.toString())
            }

            Result.Loading -> {}

            Result.Idle -> {}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}