package ru.rabbet.forrabet.utils

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import ru.rabbet.forrabet.application.FourRabetApp
import android.content.pm.PackageManager
import ru.rabbet.forrabet.network.ApiService


open class BaseActivity: AppCompatActivity() {

    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        apiService = (application as FourRabetApp).apiService
        super.onCreate(savedInstanceState)
    }

    fun getRemoteConfig(): FirebaseRemoteConfig {
        return (application as FourRabetApp).remoteConfig
    }

    fun isAppAvailable(appName: String): Boolean {
        val pm = packageManager
        try {
            pm.getPackageInfo(appName, PackageManager.GET_ACTIVITIES)
            return true
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }
    }
}