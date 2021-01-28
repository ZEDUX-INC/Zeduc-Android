package com.zedux.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.zedux.R
import kotlinx.android.synthetic.main.fragment_select_courses.*

class SelectCoursesFragment : Fragment() {

    companion object {

        fun newInstance(): SelectCoursesFragment {
            return SelectCoursesFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_select_courses, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addChips()
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