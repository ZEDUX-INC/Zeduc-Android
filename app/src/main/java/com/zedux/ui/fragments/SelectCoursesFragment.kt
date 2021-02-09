package com.zedux.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.zedux.R
import com.zedux.other.FragmentCallback
import com.zedux.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_select_courses.*

class SelectCoursesFragment : Fragment(R.layout.fragment_select_courses) {

    private var callBack: FragmentCallback? = null

    companion object {

        fun newInstance(): SelectCoursesFragment {
            return SelectCoursesFragment()
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
        addChips()

        tv_skip.setOnClickListener {
            callBack?.navigateCoursesSelectedToHome()

        }

        btn_continue.setOnClickListener{
            callBack?.navigateSelectCoursesToCoursesSelected()
        }
    }

    private fun addChips() {
        val chipText = arrayOf(
            "English", "Maths", "Civic", "Biology",
            "Chemistry", "Physics", "Government", "Economics", "C.R.K",
            "Further Maths", "Accounting", "Agricultural Science", "Literature I.E",
            "History", "Computer"
        )
        val chipGroup = chip_group


        for (i in chipText) {
            var chip = layoutInflater.inflate(R.layout.single_chip_item, chipGroup, false) as Chip
            chip.text = i
            chipGroup.addView(chip)

        }

    }
}