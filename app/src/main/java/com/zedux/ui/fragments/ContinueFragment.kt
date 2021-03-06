package com.zedux.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zedux.R
import com.zedux.other.FragmentCallback
import com.zedux.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_continue.*

class ContinueFragment : Fragment() {

    private var callBack: FragmentCallback? = null

    companion object {
        fun newInstance(): ContinueFragment {
            return ContinueFragment()
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
            callBack?.navigateContinueToSignUp()
        }

        btn_student.setOnClickListener {
            callBack?.navigateContinueToSignUp()
        }

    }
}