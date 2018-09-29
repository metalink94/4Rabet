package ru.rabbet.forrabet.screen.menu

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.menu.*
import ru.rabbet.forrabet.R
import ru.rabbet.forrabet.utils.BaseActivity


class MenuActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)
        results.setOnClickListener { onResultsClick() }
        chat.setOnClickListener { onChatClick() }
        about.setOnClickListener { onAboutClick() }
    }

    private fun onAboutClick() {

    }

    private fun onChatClick() {
        val appName = getString(R.string.telegram)
        if (isAppAvailable(appName)) {
            val myIntent = Intent(Intent.ACTION_SEND)
            myIntent.type = "text/plain"
            myIntent.`package` = appName
            myIntent.putExtra(Intent.EXTRA_TEXT, "")//
            startActivity(Intent.createChooser(myIntent, getString(R.string.open_with)))
        } else {
            Toast.makeText(this, "Telegram not Installed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onResultsClick() {

    }
}