package com.zedux.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.zedux.R
import com.zedux.adapters.OnboardingItemAdapter
import com.zedux.adapters.RecentAdapter
import com.zedux.data.OnboardItem
import com.zedux.databinding.FragmentHomeBinding
import com.zedux.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_onboarding.*

class HomeFragment : Fragment(){

    private var binding: FragmentHomeBinding? = null


    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        fragmentManager?.popBackStack("", FragmentManager.POP_BACK_STACK_INCLUSIVE)

        MainActivity.showBottomNavBar()
        setStatusBarColor(ContextCompat.getColor(requireContext(), R.color.color_white))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding?.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = ArrayList<String>()
        for (i in 0..5) {
            items.add("test")
        }

        val adapterRecent = RecentAdapter()
        binding?.recyclerRecent?.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayout.HORIZONTAL, false)
            adapter = adapterRecent
        }

        adapterRecent.submitList(items)


    }

    private fun setStatusBarColor(color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity?.window
            window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window?.statusBarColor = color
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}