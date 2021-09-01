package com.example.iwallet.features.intro.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.iwallet.R
import com.example.iwallet.databinding.FragmentSplashBinding
import com.example.iwallet.features.intro.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashFragment: Fragment() {

    private val SPLASH_DELAY: Long = 3000
    private var _binding: FragmentSplashBinding? = null
    private val binding: FragmentSplashBinding get() = _binding!!
    private val viewModel: SplashViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.logoSplash.animation = AnimationUtils
            .loadAnimation(requireContext(), R.anim.top_center_animation)

        binding.logoImageSplash.animation = AnimationUtils
            .loadAnimation(requireContext(), R.anim.top_center_animation)

        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.initSplash()
        }, SPLASH_DELAY)

        viewModel.splashToOnBoarding.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.action_splashFragment_to_onbordingFragment)
        })

        viewModel.splashToLogin.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}