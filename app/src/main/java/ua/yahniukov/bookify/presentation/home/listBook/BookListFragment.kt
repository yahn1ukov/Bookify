package ua.yahniukov.bookify.presentation.home.listBook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ua.yahniukov.bookify.databinding.FragmentBookListBinding
import ua.yahniukov.bookify.domain.models.Book
import ua.yahniukov.bookify.utils.Result
import ua.yahniukov.bookify.utils.showToast

@AndroidEntryPoint
class BookListFragment : Fragment() {
    private var _binding: FragmentBookListBinding? = null
    private val binding get() = _binding!!

    private val bookListViewModel: BookListViewModel by viewModels()
    private val bookListAdapter: BookListAdapter by lazy { BookListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        bookListViewModel.uiState.observe(viewLifecycleOwner) { state ->
            handleUIState(state)
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            bookListViewModel.getAll()
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewPostsList.adapter = bookListAdapter
        binding.recyclerViewPostsList.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewPostsList.itemAnimator = DefaultItemAnimator()
    }

    private fun handleUIState(state: Result<List<Book>>) {
        when (state) {
            is Result.Success -> {
                hideLoading()
                bookListAdapter.setData(state.data!!)
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
        binding.swipeRefreshLayout.isRefreshing = true
    }

    private fun hideLoading() {
        binding.swipeRefreshLayout.isRefreshing = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}