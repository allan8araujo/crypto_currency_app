package com.example.cryptocurrencyapp.presentation

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import com.example.apilibrary.repository.const.Constants
import com.example.cryptocurrencyapp.commons.utils.customColor
import com.example.cryptocurrencyapp.presentation.ui.main.RootScaffold

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSecretKey()

        setContent {
            MaterialTheme(customColor) {
                RootScaffold()
            }
        }
    }

    private fun getSecretKey() {
        val applicationInfo: ApplicationInfo = applicationContext.packageManager
            .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)
        val value = applicationInfo.metaData["apiKey"]
        Constants.API_KEY = value.toString()
    }
}
