package com.zedux.ui.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.zedux.R
import com.zedux.adapters.OnboardingItemAdapter
import com.zedux.data.OnboardItem
import com.zedux.databinding.FragmentCommunityBinding
import com.zedux.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_onboarding.*

class CommunityFragment : Fragment() {

    private lateinit var binding: FragmentCommunityBinding


    companion object {
        fun newInstance(): CommunityFragment {
            return CommunityFragment()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommunityBinding.inflate(inflater, container, false)

        MainActivity.showBottomNavBar()

//        val searchView: SearchView = binding.root.findViewById(R.id.search_view)
        val searchView: SearchView = binding.root.findViewById(R.id.search_view)
        val id = searchView.context.resources.getIdentifier("android:id/search_src_text", null,null)

        Log.i("Dapo", "$id")
//        val searchHintText: EditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text)
//        searchHintText.setHintTextColor(ContextCompat.getColor(requireContext(), R.color.color_black))

//        searchView!!.findViewById<EditText>(
//            searchView.context.resources.getIdentifier("android:id/search_src_text", null, null)
//        ).apply {
//            setTextColor(ContextCompat.getColor(requireContext()!!, R.color.color_black))
//            setHintTextColor(ContextCompat.getColor(requireContext()!!, R.color.color_black))
//        }
//
//        09132051140

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun setStatusBarColor(color: Int) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity?.window
            window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window?.statusBarColor = color
        }
    }

}