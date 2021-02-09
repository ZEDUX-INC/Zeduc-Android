package com.zedux.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zedux.R
import com.zedux.other.FragmentCallback
import com.zedux.ui.fragments.*
import com.zedux.ui.viewModels.MainViewModel
import com.zedux.utils.SharedPref

class MainActivity : AppCompatActivity(), FragmentCallback {

    private lateinit var sharedPref: SharedPref

    private lateinit var viewModel: MainViewModel

    private var navController: NavController? = null

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

        enableStrictMode()

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val currentUser by lazy {
            viewModel.user
        }

        sharedPref = SharedPref.getInstance(this)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        setupBottomNavigationBar()


        if (isFirstLauch()) {
            sharedPref.setIsFirstLaunchToFalse()
        } else {
            if (currentUser != null) {
                navController?.navigate(R.id.action_global_homeFragment)
            } else {
                navController?.navigate(R.id.action_global_loginFragment)
            }


        }
    }

    private fun setupBottomNavigationBar() {

        bottomNav = findViewById(R.id.bottom_nav)
        bottomNav.visibility = View.GONE

        NavigationUI.setupWithNavController(bottomNav, navController!!)

    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }


    fun isFirstLauch(): Boolean {
        return sharedPref.isFirstLaunch()
    }

    private fun enableStrictMode() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )
    }

    override fun navigateToHome() {
        val actions = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        navController?.navigate(actions)
    }

    override fun navigateLoginToSignUp() {
        val actions = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
        navController?.navigate(actions)
    }

    override fun navigateSignUpToLogin() {
        val actions = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
        navController?.navigate(actions)
    }

    override fun navigateOnboardToContinue() {
        val actions = OnboardingFragmentDirections.actionOnboardingFragmentToContinueFragment()
        navController?.navigate(actions)
    }

    override fun navigateContinueToSignUp() {
        val actions = ContinueFragmentDirections.actionContinueFragmentToSignUpFragment()
        navController?.navigate(actions)
    }

    override fun navigateSignUpToSelectCourses() {
        val actions = SignUpFragmentDirections.actionSignUpFragmentToSelectCoursesFragment2()
        navController?.navigate(actions)
    }

    override fun navigateSelectCoursesToCoursesSelected() {
        val actions = SelectCoursesFragmentDirections.actionSelectCoursesFragmentToCoursesSelectedFragment()
        navController?.navigate(actions)
    }

    override fun navigateCoursesSelectedToHome() {
        val actions = CoursesSelectedFragmentDirections.actionCoursesSelectedFragmentToHomeFragment()
        navController?.navigate(actions)
    }

    override fun navigateSelectCoursesToHome() {
        val actions = SelectCoursesFragmentDirections.actionSelectCoursesFragmentToHomeFragment()
        navController?.navigate(actions)
    }


}