package com.zedux.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.zedux.R
import com.zedux.other.FragmentCallback
import com.zedux.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_select_courses.*

class CoursesSelectedFragment : Fragment(R.layout.fragment_courses_selected) {

    private var callBack: FragmentCallback? = null

    companion object {

        fun newInstance(): CoursesSelectedFragment {
            return CoursesSelectedFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBack = context as MainActivity
    }

    override fun onDetach() {
        super.onDetach()
        callBack = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_continue.setOnClickListener{
            callBack?.navigateCoursesSelectedToHome()
        }

    }
}