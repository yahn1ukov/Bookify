package ua.yahniukov.bookify.utils

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot

inline fun <reified T> Iterable<DataSnapshot>.toList(): List<T> {
    val list = mutableListOf<T>()
    for (snapshot in this) {
        val value = snapshot.getValue(T::class.java)
        value?.let { list.add(it) }
    }
    return list
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}

fun Fragment.navigate(from: Context, to: Class<*>) {
    val intent = Intent(from, to)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    requireActivity().startActivity(intent)
}