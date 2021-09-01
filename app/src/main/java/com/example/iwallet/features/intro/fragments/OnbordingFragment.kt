package com.example.iwallet.features.intro.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.iwallet.R
import com.example.iwallet.databinding.FragmentOnbordingBinding
import com.example.iwallet.features.intro.repository.OnbordingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnbordingFragment: Fragment() {

    private var _binding: FragmentOnbordingBinding? = null
    private val binding: FragmentOnbordingBinding get() = _binding!!
    private val viewModel: OnbordingViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnbordingBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginStart.setOnClickListener {
            viewModel.onClickedLoginStart()
        }

        binding.registration.setOnClickListener{
            findNavController().navigate(R.id.action_onbordingFragment_to_registrationFragment)
        }

        viewModel.goToLogin.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.action_onbordingFragment_to_loginFragment)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}