package com.zedux.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.zedux.R
import com.zedux.adapters.OnboardingItemAdapter
import com.zedux.data.OnboardItem
import kotlinx.android.synthetic.main.fragment_continue.*
import kotlinx.android.synthetic.main.fragment_onboarding.*

class ContinueFragment : Fragment() {


    companion object {
        fun newInstance(): ContinueFragment {
            return ContinueFragment()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_continue, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_tutor.setOnClickListener {
            val signUpFragment = SignUpFragment.newInstance()
            addFragment(signUpFragment)
        }

        btn_student.setOnClickListener {
            val signUpFragment = SignUpFragment.newInstance()
            addFragment(signUpFragment)
        }

    }

    private fun addFragment(fragment: Fragment) {
        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragment_container, fragment)
            ?.commit()
    }

}