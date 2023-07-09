package ua.yahniukov.bookify

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.color.DynamicColors
import dagger.hilt.android.HiltAndroidApp
import ua.yahniukov.bookify.utils.Constants.THEME_MODE_DEFAULT

@HiltAndroidApp
class Bookify : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
        AppCompatDelegate.setDefaultNightMode(THEME_MODE_DEFAULT)
    }
}