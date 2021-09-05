package com.example.iwallet.features.intro.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.iwallet.R
import com.example.iwallet.databinding.FragmentRegistrationBinding
import com.example.iwallet.features.intro.viewmodel.RegistrationViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding: FragmentRegistrationBinding get() = _binding!!
    private val viewModel: RegistrationViewModel by viewModel()
    private var auth = Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registrationCancel.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.registrationEmail.addTextChangedListener {
            viewModel.onEmailChange(it.toString())
        }

        binding.registrationPassword.addTextChangedListener {
            viewModel.onPasswordChange(it.toString())
        }

        binding.registrationConfirmPassword.addTextChangedListener {
            viewModel.onConfirmPasswordChange(it.toString())
        }

        binding.registrationConfirm.setOnClickListener {
            viewModel.onClickRegistrationConfirm()
        }

        viewModel.registratioUser.observe(viewLifecycleOwner, {
        })

        viewModel.goToLogin.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        })

        viewModel.showErro.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

        viewModel.showLoading.observe(viewLifecycleOwner, {
            binding.progressCircular.isVisible = it
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}