package com.pawanmaniyar.android.rocketinfo.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pawanmaniyar.android.rocketinfo.R
import com.pawanmaniyar.android.rocketinfo.ui.rockets.RocketsFragment

private const val RESTORE_CURRENT_TAB = "current tab"

class MainActivity : AppCompatActivity() {
    private var currentFragment: androidx.fragment.app.Fragment? = null
    lateinit var bottomNav: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNav = findViewById(R.id.bottomNavigationView) as BottomNavigationView
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0F
        supportActionBar?.title = getString(R.string.app_name)
        savedInstanceState?.let {
            for (fragment in supportFragmentManager.fragments) {
                supportFragmentManager.beginTransaction().remove(fragment).commit()
            }
        }

        initBottomNavigationMenu()
    }

    private fun initBottomNavigationMenu() {
        val rocketsFragment = RocketsFragment()

        addAndHideFragment(rocketsFragment)

        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_rockets -> showFragment(rocketsFragment)
            }
            true
        }

        currentFragment = rocketsFragment
        bottomNav.selectedItemId = R.id.rocketFragment
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putInt(RESTORE_CURRENT_TAB, bottomNav.selectedItemId)
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        bottomNav.selectedItemId = savedInstanceState.getInt(RESTORE_CURRENT_TAB)
    }

    private fun addAndHideFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager
                .beginTransaction()
                .add(R.id.container, fragment)
                .hide(fragment)
                .commit()
    }

    private fun showFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction()
                .hide(currentFragment!!)
                .show(fragment)
                .commit()
        currentFragment = fragment
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}
