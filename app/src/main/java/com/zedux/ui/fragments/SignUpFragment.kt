package com.zedux.ui.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zedux.R
import com.zedux.data.interfaces.AuthListener
import com.zedux.databinding.FragmentSignupBinding
import com.zedux.ui.MainActivity
import com.zedux.ui.viewModels.AuthViewModel


class SignUpFragment : Fragment(), AuthListener{

    private lateinit var binding: FragmentSignupBinding
    private lateinit var viewModel: AuthViewModel


    companion object {
        fun newInstance(): SignUpFragment {
            return SignUpFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentSignupBinding.inflate(inflater, container, false)



        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.viewModel = viewModel

        Log.d("Sign", "$viewModel.hashCode()")

        viewModel.authListener = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvLogin.setOnClickListener {
            val fragment = LoginFragment.newInstance()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, fragment)
                ?.commit()
        }


    }

    override fun onStarted() {
        Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess() {
        Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
        val profileFragment = HomeFragment.newInstance()
        fragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, profileFragment)
            ?.commit()
    }

    override fun onFailure(message: String) {
        Toast.makeText(requireContext(), "$message", Toast.LENGTH_SHORT).show()
    }

}