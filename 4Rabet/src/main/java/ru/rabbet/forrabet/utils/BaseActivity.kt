package ru.rabbet.forrabet.utils

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import ru.rabbet.forrabet.application.FourRabetApp
import android.content.pm.PackageManager



open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
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