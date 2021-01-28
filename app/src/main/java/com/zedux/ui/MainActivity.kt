package com.zedux.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zedux.R
import com.zedux.ui.fragments.*
import com.zedux.ui.viewModels.MainViewModel
import com.zedux.utils.SharedPref

class MainActivity : AppCompatActivity() {


    private lateinit var sharedPref: SharedPref

    private val homeFragment = HomeFragment.newInstance()
    private val communityFragment = CommunityFragment.newInstance()
    private val inboxFragment = InboxFragment.newInstance()
    private val profileFragment = ProfileFragment.newInstance()

    private lateinit var viewModel: MainViewModel

    companion object {
         lateinit var bottomNav: BottomNavigationView

        fun hideBottomNavBar() {
            bottomNav.visibility = View.GONE
        }

        fun showBottomNavBar() {
            bottomNav.visibility = View.VISIBLE
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val currentUser by lazy {
            viewModel.user
        }

        Log.d("Okay", "setting up...")
        if (savedInstanceState == null) {
            Log.d("Okay", "bottom bar setting up...")
            setupBottomNavigationBar()
        }

        sharedPref = SharedPref.getInstance(this)


        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container)


        if (isFirstLauch()) {
            if (currentFragment == null) {
                val onBoardFragment = OnboardingFragment.newInstance()
                addFragment(onBoardFragment)

            }
            sharedPref.setIsFirstLaunchToFalse()
        } else {
            if(currentUser != null) {
                addFragment(homeFragment)
            } else {
                val loginFragment = LoginFragment.newInstance()
                addFragment(loginFragment)
            }


        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {

        bottomNav = findViewById(R.id.bottom_nav)
        bottomNav.visibility = View.GONE

        bottomNav.setOnNavigationItemSelectedListener { menutItem ->
            when (menutItem.itemId) {
                R.id.nav_home -> {
                    Log.i("nav", "home")
                    addFragment(homeFragment)
                    true
                }
                R.id.nav_community -> {
                    Log.i("nav", "community")
                    addFragment(communityFragment)
                    true
                }
                R.id.nav_inbox -> {
                    Log.i("nav", "inbox")
                    addFragment(inboxFragment)
                    true
                }
                R.id.nav_profile -> {
                    Log.i("nav", "profile")
                    addFragment(profileFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }



    fun isFirstLauch(): Boolean {
        return sharedPref.isFirstLaunch()
    }


}