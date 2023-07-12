package ua.yahniukov.bookify.utils

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import ua.yahniukov.bookify.R

object BookifyAdapter {
    @BindingAdapter("android:loadProfileImage")
    @JvmStatic
    fun loadProfileImage(imageView: ImageView, image: Uri?) {
        if (image != null) {
            imageView.load(image) {
                error(R.drawable.ic_person)
            }
        }
    }
}