package ua.yahniukov.bookify.presentation.home.add

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import dagger.hilt.android.AndroidEntryPoint
import ua.yahniukov.bookify.R
import ua.yahniukov.bookify.databinding.FragmentAddBinding
import ua.yahniukov.bookify.utils.Result
import ua.yahniukov.bookify.utils.ValidateHelper
import ua.yahniukov.bookify.utils.hide
import ua.yahniukov.bookify.utils.show
import ua.yahniukov.bookify.utils.showToast

@AndroidEntryPoint
class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private val addViewModel: AddViewModel by viewModels()
    private val validateHelper: ValidateHelper by lazy { ValidateHelper(requireContext()) }

    private lateinit var imagePickerLauncher: ActivityResultLauncher<String>
    private var tempImage: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbarMenu()
        setupImagePickerLauncher()
        addViewModel.uiState.observe(viewLifecycleOwner) { state ->
            handleUIState(state)
        }
        binding.imageAddUpload.setOnClickListener { uploadImage() }
    }

    private fun setupToolbarMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu_add, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.add) {
                    create()
                }
                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setupImagePickerLauncher() {
        imagePickerLauncher =
            registerForActivityResult(ActivityResultContracts.GetContent()) { image ->
                if (image != null) {
                    binding.imageAddUpload.setImageURI(image)
                    tempImage = image
                }
            }
    }

    private fun uploadImage() {
        imagePickerLauncher.launch("image/**")
    }

    private fun create() {
        val name = binding.editTextAddName.text.toString()
        val author = binding.editTextAddAuthor.text.toString()
        val description = binding.editTextAddDescription.text.toString()

        if (
            validateHelper.validateBookImage(tempImage) &&
            validateHelper.validateBookName(name) &&
            validateHelper.validateBookAuthor(author)
        ) {
            addViewModel.create(tempImage!!, name, author, description)
        }
    }

    private fun showLoading() {
        binding.progressBarAdd.show()
    }

    private fun hideLoading() {
        binding.progressBarAdd.hide()
    }

    private fun resetForm() {
        binding.imageAddUpload.setImageResource(R.drawable.ic_image)
        binding.editTextAddName.setText("")
        binding.editTextAddAuthor.setText("")
        binding.editTextAddDescription.setText("")
    }

    private fun handleUIState(state: Result<Nothing>) {
        when (state) {
            is Result.Success -> {
                hideLoading()
                resetForm()
                showToast("Post has been successfully created")
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}