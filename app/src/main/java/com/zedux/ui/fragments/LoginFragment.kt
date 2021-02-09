package com.zedux.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.zedux.data.interfaces.AuthListener
import com.zedux.databinding.FragmentLoginBinding
import com.zedux.other.FragmentCallback
import com.zedux.ui.MainActivity
import com.zedux.ui.viewModels.AuthViewModel

class LoginFragment : Fragment(), AuthListener {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: AuthViewModel

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    private var callBack: FragmentCallback? = null

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
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
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        viewModel.authListener = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvRegister.setOnClickListener {
           callBack?.navigateLoginToSignUp()
        }

    }

    override fun onStarted() {
        Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess() {
        Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
        callBack?.navigateToHome()
    }

    override fun onFailure(message: String) {
        Toast.makeText(requireContext(), "$message", Toast.LENGTH_SHORT).show()
    }
}