package ua.yahniukov.bookify.utils

import android.content.Context
import android.net.Uri
import android.util.Patterns
import android.widget.Toast
import javax.inject.Singleton

@Singleton
class ValidateHelper(private val context: Context) {
    fun validateFirstName(firstName: String): Boolean {
        return if (firstName.isEmpty()) {
            Toast.makeText(context, "Please enter first name", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    fun validateLastName(lastName: String): Boolean {
        return if (lastName.isEmpty()) {
            Toast.makeText(context, "Please enter last name", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    fun validateEmail(email: String): Boolean {
        return if (email.isEmpty()) {
            Toast.makeText(context, "Please enter email address", Toast.LENGTH_SHORT).show()
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(context, "Invalid email address", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    fun validatePassword(password: String): Boolean {
        return if (password.isEmpty()) {
            Toast.makeText(context, "Please enter password", Toast.LENGTH_SHORT).show()
            false
        } else if (password.length < 8) {
            Toast.makeText(context, "Password too short", Toast.LENGTH_SHORT).show()
            false
        } else if (password.length > 20) {
            Toast.makeText(context, "Password too long", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    fun validateConfirmPassword(password: String, confirmPassword: String): Boolean {
        return if (confirmPassword.isEmpty()) {
            Toast.makeText(context, "Please enter confirm password", Toast.LENGTH_SHORT).show()
            false
        } else if (password != confirmPassword) {
            Toast.makeText(context, "Passwords don't match", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    fun validateBookImage(image: Uri?): Boolean {
        return if (image == null) {
            Toast.makeText(context, "Please select image of a book", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    fun validateBookName(bookName: String): Boolean {
        return if (bookName.isEmpty()) {
            Toast.makeText(context, "Please enter book name", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    fun validateBookAuthor(author: String): Boolean {
        return if (author.isEmpty()) {
            Toast.makeText(context, "Please enter author", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }
}