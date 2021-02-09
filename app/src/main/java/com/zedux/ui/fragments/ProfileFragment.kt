package com.zedux.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.zedux.R
import com.zedux.adapters.OnboardingItemAdapter
import com.zedux.data.OnboardItem
import com.zedux.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_onboarding.*

class ProfileFragment : Fragment(R.layout.fragment_profile) {


    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        requireActivity()
//            .onBackPressedDispatcher
//            .addCallback(this, object: OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    MainActivity.bottomNav.selectedItemId = R.id.nav_home
//                }
//            })
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MainActivity.showBottomNavBar()
        setStatusBarColor(ContextCompat.getColor(requireContext(), R.color.color_white))


    }

    private fun setStatusBarColor(color: Int) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity?.window
            window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window?.statusBarColor = color
        }
    }

}