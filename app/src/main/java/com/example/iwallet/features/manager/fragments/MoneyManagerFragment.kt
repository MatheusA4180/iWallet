package com.example.iwallet.features.manager.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.iwallet.R
import com.example.iwallet.databinding.FragmentMoneyManagerBinding
import com.example.iwallet.features.resume.ProfileDialog
import com.example.iwallet.features.resume.ThemeNewsActivity
import com.example.iwallet.features.resume.fragments.ResumeFragment
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class MoneyManagerFragment : Fragment() {

    private var _binding: FragmentMoneyManagerBinding? = null
    private val binding: FragmentMoneyManagerBinding get() = _binding!!
    private val rotateOpen: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.rotate_open_anim
        )
    }
    private val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.rotate_close_anim
        )
    }
    private val fromBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.from_bottom_anim
        )
    }
    private val toBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.to_bottom_anim
        )
    }
    private var clicked = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoneyManagerBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        paintButtonOnAndOff(binding.income, binding.expense)

        binding.toolbarManager.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.extract_manager -> {
                    //findNavController().navigate()
                    true
                }
                R.id.options_manager -> {
                    //findNavController().navigate()
                    true
                }
                else -> false
            }
        }

        binding.addMoneyManager.setOnClickListener {
            setVisibility(clicked)
            setAnimation(clicked)
            clicked = !clicked
        }

        binding.income.setOnClickListener {
            paintButtonOnAndOff(binding.income, binding.expense)
            //viewModel.buttonPressedApplication()
        }

        binding.expense.setOnClickListener {
            paintButtonOnAndOff(binding.expense, binding.income)
            //viewModel.buttonPressedRescue()
        }

    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked) {
            binding.addIncome.isVisible = true
            binding.addExpense.isVisible = true
        } else {
            binding.addIncome.isVisible = false
            binding.addExpense.isVisible = false
        }
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked) {
            binding.addIncome.startAnimation(fromBottom)
            binding.addExpense.startAnimation(fromBottom)
            binding.addMoneyManager.startAnimation(rotateOpen)
        } else {
            binding.addIncome.startAnimation(toBottom)
            binding.addExpense.startAnimation(toBottom)
            binding.addMoneyManager.startAnimation(rotateClose)
        }
    }

    private fun paintButtonOnAndOff(
        buttonOn: ExtendedFloatingActionButton,
        buttonOff: ExtendedFloatingActionButton
    ) {
        with(buttonOn) {
            setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.main_theme))
            setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            setStrokeColorResource(R.color.white)
        }
        with(buttonOff) {
            setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            setTextColor(ContextCompat.getColor(requireContext(), R.color.main_theme))
            setStrokeColorResource(R.color.main_theme)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}