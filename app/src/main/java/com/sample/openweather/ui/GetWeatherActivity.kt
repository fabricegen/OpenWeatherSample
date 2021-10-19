package com.sample.openweather.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.sample.openweather.R
import com.sample.openweather.databinding.ActivityGetweatherBinding

class GetWeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetweatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGetweatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null) {
            addGetWeatherFragment()
        }
    }

    private fun addGetWeatherFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragmentContainer, GetWeatherFragment())
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
