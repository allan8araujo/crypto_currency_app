package com.example.cryptocurrencyapp

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import com.example.apilibrary.repository.const.Constants
import com.example.cryptocurrencyapp.ui.main.Main
import com.example.cryptocurrencyapp.utils.customColor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSecretKey()

        setContent() {
            MaterialTheme(customColor) {
                Main()
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
