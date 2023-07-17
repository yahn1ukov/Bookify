package ua.yahniukov.bookify.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot

fun DataSnapshot.removeAll() {
    for (snapshot in this.children) {
        snapshot.ref.removeValue()
    }
}

inline fun <reified T> DataSnapshot.toList(): List<T> {
    val list = mutableListOf<T>()
    for (snapshot in this.children) {
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

fun Fragment.showDialog(
    title: String,
    message: String,
    icon: Int,
    onPositiveClick: DialogInterface.OnClickListener,
    onNegativeClick: DialogInterface.OnClickListener
) {
    val dialogBuilder = AlertDialog.Builder(requireContext())
    dialogBuilder
        .setTitle(title)
        .setMessage(message)
        .setIcon(icon)
        .setPositiveButton("Yes", onPositiveClick)
        .setNegativeButton("No", onNegativeClick)
    val dialog = dialogBuilder.create()
    dialog.show()
}

fun Fragment.navigate(from: Context, to: Class<*>) {
    val intent = Intent(from, to)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    requireActivity().startActivity(intent)
}