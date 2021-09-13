package com.example.iwallet.features.intro.fragments

import android.app.AlertDialog
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
import com.example.iwallet.databinding.FragmentLoginBinding
import com.example.iwallet.features.intro.viewmodel.LoginViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment: Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginEmail.addTextChangedListener {
            binding.loginUsernameLayout.isErrorEnabled = false
            viewModel.onEmailChange(it.toString())
        }

        binding.loginPassword.addTextChangedListener {
            binding.loginPasswordLayout.isErrorEnabled = false
            viewModel.onPasswordChange(it.toString())
        }

        binding.loginCreate.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        viewModel.initLogin()

        viewModel.emailSave.observe(viewLifecycleOwner,{
            binding.loginEmail.setText(it)
        })

        viewModel.passwordSave.observe(viewLifecycleOwner,{
            binding.loginPassword.setText(it)
        })

        viewModel.rememberUserToogle.observe(viewLifecycleOwner, {
            binding.remeberLogin.toggle()
        })

        viewModel.emailErro.observe(viewLifecycleOwner, {
            binding.loginUsernameLayout.error = it
        })

        viewModel.passwordErro.observe(viewLifecycleOwner, {
            binding.loginPasswordLayout.error = it
        })

        viewModel.goToHome.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.action_loginFragment_to_homeActivity)
            requireActivity().finish()
        })

        viewModel.showErro.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

        binding.remeberLogin.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onRememberChecked(isChecked)
        }

        binding.loginEnter.setOnClickListener {
            viewModel.onLoginClicked()
        }

        viewModel.showLoading.observe(viewLifecycleOwner,{
            binding.progressCircular.isVisible = it
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
