package ua.yahniukov.bookify.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import ua.yahniukov.bookify.utils.Constants.DATA_STORE_NAME
import ua.yahniukov.bookify.utils.Constants.PREFERENCE_KEY_THEME_MODE
import ua.yahniukov.bookify.utils.Constants.THEME_MODE_DEFAULT
import java.io.IOException
import javax.inject.Inject

private val Context.datastore by preferencesDataStore(DATA_STORE_NAME)

class PreferenceManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val datastore = context.datastore

    private object PreferenceKeys {
        val themeMode = intPreferencesKey(PREFERENCE_KEY_THEME_MODE)
    }

    suspend fun setThemeMode(themeMode: Int) {
        datastore.edit { preferences ->
            preferences[PreferenceKeys.themeMode] = themeMode
        }
    }

    val getThemeMode: Flow<Int> = datastore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferenceKeys.themeMode] ?: THEME_MODE_DEFAULT
        }
}