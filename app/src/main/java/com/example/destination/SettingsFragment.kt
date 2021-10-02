package com.example.destination

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferenceFragment: Preference? = findPreference("author")

        preferenceFragment?.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            view?.findNavController()?.navigate(R.id.aboutAuthorFragment)
            true
        }

    }

}