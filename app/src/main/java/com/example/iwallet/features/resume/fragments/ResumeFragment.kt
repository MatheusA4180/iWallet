package com.example.iwallet.features.resume.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.iwallet.R
import com.example.iwallet.databinding.FragmentResumeBinding
import com.example.iwallet.features.resume.ProfileDialog
import com.example.iwallet.features.resume.viewmodel.ResumeViewModel
import com.example.iwallet.utils.helperfunctions.HelperFunctions.converterToReal
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResumeFragment: Fragment() {

    private var _binding: FragmentResumeBinding? = null
    private val binding: FragmentResumeBinding get() = _binding!!
    private val viewModel: ResumeViewModel by viewModel()
    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.to_bottom_anim) }
    private var clicked = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResumeBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.requestCurrentBalanceAndProfitability()

        binding.addButton.setOnClickListener {
            setVisibility(clicked)
            setAnimation(clicked)
            clicked = !clicked
        }

        binding.toolbarResume.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.profile -> {
                    ProfileDialog().show(childFragmentManager, null)
                    true
                }
                else -> false
            }
        }

        binding.addApplication.setOnClickListener {
            navigateToProductActivity(ADD_OR_SUBTRACT_PRODUCT)
        }

        binding.addRescue.setOnClickListener {
            navigateToProductActivity(ADD_OR_SUBTRACT_PRODUCT)
        }

        binding.addProduct.setOnClickListener {
            navigateToProductActivity(NEW_PRODUCT)
        }

        viewModel.currentBalance.observe(viewLifecycleOwner,{
            binding.currentBalance.text = converterToReal(it)
        })

        viewModel.profitability.observe(viewLifecycleOwner,{
            binding.profitability.text = "${it.toString().replace(".",",")} %"
        })

        viewModel.showLoading.observe(viewLifecycleOwner,{
            binding.progressCircular.isVisible = it
        })

    }

    private fun navigateToProductActivity(argument: String){
        findNavController().navigate(ResumeFragmentDirections
            .actionResumeFragmentToProductsActivity(argument)
        )
    }

    private fun setVisibility(clicked: Boolean){
        if(!clicked){
            binding.addApplication.isVisible = true
            binding.addRescue.isVisible = true
            binding.addProduct.isVisible = true
        }else{
            binding.addApplication.isVisible = false
            binding.addRescue.isVisible = false
            binding.addProduct.isVisible = false
        }
    }

    private fun setAnimation(clicked: Boolean){
        if(!clicked){
            binding.addApplication.startAnimation(fromBottom)
            binding.addRescue.startAnimation(fromBottom)
            binding.addProduct.startAnimation(fromBottom)
            binding.addButton.startAnimation(rotateOpen)
        }else{
            binding.addApplication.startAnimation(toBottom)
            binding.addRescue.startAnimation(toBottom)
            binding.addProduct.startAnimation(toBottom)
            binding.addButton.startAnimation(rotateClose)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val ADD_OR_SUBTRACT_PRODUCT = "AddOrSubtractProduct"
        const val NEW_PRODUCT = "NewProduct"
    }
}