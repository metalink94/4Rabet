package ru.rabbet.forrabet.application

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

class FourRabetApp : Application() {

    lateinit var remoteConfig: FirebaseRemoteConfig

    override fun onCreate() {
        FirebaseApp.initializeApp(this)
        remoteConfig = FirebaseRemoteConfig.getInstance()
        remoteConfig.setDefaults(getDefaults())
        remoteConfig.activateFetched()
        remoteConfig.fetch(60)
        super.onCreate()
    }

    private fun getDefaults(): Map<String, Any> {
        return mapOf(
                "url" to "http://4rabet.com"
        )
    }
}
