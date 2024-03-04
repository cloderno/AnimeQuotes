package com.example.animequotes.presentation

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.example.animequotes.R
import com.example.animequotes.data.AppDatabase
import com.example.animequotes.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        AppDatabase.getInstance(this)

        replaceFragment(HomeFragment())

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.menu_home -> {
                    replaceFragment(HomeFragment(), addToBackStack = false)
                    true
                }
                R.id.menu_favourites -> {
                    replaceFragment(FavouritesFragment())
                    true
                }
                else -> false
            }
        }

        onBackPressedDispatcher?.addCallback(this) {
            if (supportFragmentManager.backStackEntryCount > 1) {
                supportFragmentManager.popBackStack()
            } else {
                finish()
            }
        }
    }

    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .apply {
                if (addToBackStack) {
                    addToBackStack(null)
                }
            }
            .commit()
    }

}