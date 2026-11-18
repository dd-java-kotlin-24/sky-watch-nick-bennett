package edu.cnm.deepdive.skywatch.app.controller

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import edu.cnm.deepdive.skywatch.app.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

}