package com.devoli.devoliroutethispoc

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.devoli.devoliroutethispoc.databinding.ActivityMainBinding
import com.routethis.assist.RTAssist

class MainActivity : AppCompatActivity(), RTAssist.AssistHandler {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val assist: RTAssist by lazy {
        RTAssist.getInstance(
            this,
            "cea2a221-fb4e-4809-a7f1-09f3386cfd08"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            assist.startAssist()
        }

        assist.setAssistHandler(this)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onAssistReady() {
        Toast.makeText(this, "Assist Ready", Toast.LENGTH_LONG).show()
    }

    override fun onAssistAlreadyRunning() {
        Toast.makeText(this, "Assist Already Running", Toast.LENGTH_LONG).show()
    }

    override fun onSendPhotoClicked() {
        Toast.makeText(this, "Send Photo Clicked", Toast.LENGTH_LONG).show()
    }

    override fun onLiveVideoClicked() {
        Toast.makeText(this, "Live Video Clicked", Toast.LENGTH_LONG).show()
    }

    override fun onAssistFailed(p0: RTAssist.ASSIST_ERROR_CODE?) {
        Toast.makeText(this, "Assist Failed: $p0", Toast.LENGTH_LONG).show()
    }

    override fun onAssistDisconnected() {
        Toast.makeText(this, "Assist Disconnected", Toast.LENGTH_LONG).show()
    }

    override fun isAppMinimized(): Boolean {
        Toast.makeText(this, "App Minimized", Toast.LENGTH_LONG).show()
        return false
    }


}