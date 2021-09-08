package com.example.iwallet.features.intro.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding: FragmentRegistrationBinding get() = _binding!!
    private val viewModel: RegistrationViewModel by viewModel()

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
            binding.registrationUsernameLayout.isErrorEnabled = false
            viewModel.onEmailChange(it.toString())
        }

        binding.registrationPassword.addTextChangedListener {
            binding.registrationPasswordLayout.isErrorEnabled = false
            viewModel.onPasswordChange(it.toString())
        }

        binding.registrationConfirmPassword.addTextChangedListener {
            binding.registrationConfirmPasswordLayout.isErrorEnabled = false
            viewModel.onConfirmPasswordChange(it.toString())
        }

        binding.registrationConfirm.setOnClickListener {
            viewModel.onClickRegistrationConfirm()
        }

        viewModel.goToLogin.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        })

        viewModel.showErro.observe(viewLifecycleOwner, {
            AlertDialog.Builder(requireContext()).setTitle(it).setMessage("").show()
        })

        viewModel.showErroEmail.observe(viewLifecycleOwner, {
            binding.registrationUsernameLayout.error = it
        })

        viewModel.showErroPassword.observe(viewLifecycleOwner, {
            binding.registrationPasswordLayout.error = it
        })

        viewModel.showErroPasswordConfirm.observe(viewLifecycleOwner, {
            binding.registrationConfirmPasswordLayout.error = it
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
