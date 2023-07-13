package ua.yahniukov.bookify.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import ua.yahniukov.bookify.R

object DataBindingAdapter {
    @BindingAdapter("android:loadImage")
    @JvmStatic
    fun loadImage(imageView: ImageView, imageUrl: String?) {
        if (imageUrl != null) {
            imageView.load(imageUrl) {
                error(R.drawable.ic_image)
            }
        }
    }
}