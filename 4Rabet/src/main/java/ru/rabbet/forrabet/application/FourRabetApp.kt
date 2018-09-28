package ru.rabbet.forrabet.application

import android.app.Application
import android.content.Context
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import java.io.File

class FourRabetApp : Application() {

    lateinit var remoteConfig: FirebaseRemoteConfig

    override fun onCreate() {
        FirebaseApp.initializeApp(this)
        remoteConfig = FirebaseRemoteConfig.getInstance()
        remoteConfig.setDefaults(getDefaults())
        remoteConfig.activateFetched()
        fetch()
        super.onCreate()
    }

    private fun fetch() {
        remoteConfig.fetch(60)
                .addOnSuccessListener {
                    Log.d("FourRabetApp App", "onSuccessLoaded RemoteConfig")
                    Log.d("FourRabetApp App", remoteConfig.getString("url"))
                }
                .addOnCompleteListener {
                    Log.d("FourRabetApp App", "onCompleteLoaded RemoteConfig")
                }
                .addOnFailureListener {
                    Log.e("FourRabetApp App", "onOnFailure RemoteConfig", it)
                }
    }

    private fun getDefaults(): Map<String, Any> {
        return mapOf(
                "url" to "http://4rabet.com"
        )
    }
}
