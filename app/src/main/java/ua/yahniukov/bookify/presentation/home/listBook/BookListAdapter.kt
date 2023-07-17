package ua.yahniukov.bookify.presentation.home.listBook

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ua.yahniukov.bookify.databinding.BookListItemBinding
import ua.yahniukov.bookify.domain.models.Book
import ua.yahniukov.bookify.utils.ListDiff

class BookListAdapter : RecyclerView.Adapter<BookListAdapter.BookListViewHolder>() {
    private var books = emptyList<Book>()

    class BookListViewHolder(
        private val binding: BookListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): BookListViewHolder {
                return BookListViewHolder(
                    BookListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

        fun bind(book: Book) {
            binding.book = book
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        return BookListViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book)

        holder.itemView.setOnClickListener {
            val action = BookListFragmentDirections.actionBookListFragmentToBookFragment(book)
            it.findNavController().navigate(action)
        }
    }

    fun setData(newBooks: List<Book>) {
        val bookListDiff = ListDiff(newBooks, books)
        val bookDiffResult = DiffUtil.calculateDiff(bookListDiff)
        books = newBooks
        bookDiffResult.dispatchUpdatesTo(this)
    }
}