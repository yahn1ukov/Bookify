package ua.yahniukov.bookify.presentation.home.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ua.yahniukov.bookify.R
import ua.yahniukov.bookify.databinding.FragmentBookBinding
import ua.yahniukov.bookify.utils.Result
import ua.yahniukov.bookify.utils.showDialog
import ua.yahniukov.bookify.utils.showToast

@AndroidEntryPoint
class BookFragment : Fragment() {
    private var _binding: FragmentBookBinding? = null
    private val binding get() = _binding!!

    private val args: BookFragmentArgs by navArgs()
    private val bookViewModel: BookViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbarMenu()
        binding.book = args.book
        bookViewModel.uiState.observe(viewLifecycleOwner) { state ->
            handleUIState(state)
        }
    }

    private fun setupToolbarMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu_book, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.remove) {
                    showRemoveDialog()
                } else if (menuItem.itemId == android.R.id.home) {
                    findNavController().navigate(R.id.action_bookFragment_to_bookListFragment)
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun showRemoveDialog() {
        showDialog(
            "Remove?",
            "Are you sure that you want to remove?",
            R.drawable.ic_bin,
            { _, _ -> remove() },
            { dialog, _ -> dialog.cancel() }
        )
    }

    private fun remove() {
        bookViewModel.delete(args.book.uid)
    }

    private fun handleUIState(state: Result<Nothing>) {
        when (state) {
            is Result.Success -> {
                findNavController().navigate(R.id.action_bookFragment_to_bookListFragment)
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