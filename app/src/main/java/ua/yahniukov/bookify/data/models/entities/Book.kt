package ua.yahniukov.bookify.data.models.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val imageUrl: String? = "",
    val name: String? = "",
    val author: String? = "",
    val description: String? = "",
    val ownerUID: String? = ""
) : Parcelable