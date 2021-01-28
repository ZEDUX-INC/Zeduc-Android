package com.zedux.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.zedux.R
import com.zedux.adapters.OnboardingItemAdapter
import com.zedux.data.OnboardItem
import com.zedux.data.interfaces.AuthListener
import com.zedux.databinding.FragmentLoginBinding
import com.zedux.ui.viewModels.AuthViewModel
import kotlinx.android.synthetic.main.fragment_onboarding.*

class LoginFragment : Fragment(), AuthListener {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: AuthViewModel


    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
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
            val fragment = SignUpFragment.newInstance()
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