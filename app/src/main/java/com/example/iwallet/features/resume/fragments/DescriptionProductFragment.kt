package com.example.iwallet.features.resume.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.iwallet.R
import com.example.iwallet.databinding.FragmentDescriptionProductBinding
import com.example.iwallet.features.resume.viewmodel.DescriptionProductViewModel
import com.example.iwallet.utils.helperfunctions.HelperFunctions.formatDate
import com.example.iwallet.utils.mask.MoneyTextMask
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.*

class DescriptionProductFragment : Fragment() {

    private var _binding: FragmentDescriptionProductBinding? = null
    private val binding: FragmentDescriptionProductBinding get() = _binding!!
    private val arguments: DescriptionProductFragmentArgs by navArgs()
    private val viewModel: DescriptionProductViewModel by viewModel {
        parametersOf(arguments)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDescriptionProductBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        paintButtonOnAndOff(binding.addApplication, binding.addRescue)

        binding.registrationPrice.addTextChangedListener(MoneyTextMask(binding.registrationPrice))

        binding.toolbarDescriptionProduct.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.addApplication.setOnClickListener {
            paintButtonOnAndOff(binding.addApplication, binding.addRescue)
            viewModel.buttonPressedApplication()
        }

        binding.addRescue.setOnClickListener {
            paintButtonOnAndOff(binding.addRescue, binding.addApplication)
            viewModel.buttonPressedRescue()
        }

        binding.toolbarDescriptionProduct.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.removeTransaction -> {
                    confirmRemoveItem()
                    true
                }
                else -> false
            }
        }

        binding.nameBrokerDescription.text = "Instituição financeira: ${arguments.nameBroker}"

        binding.nameProductDescription.text = "Produto: ${arguments.nameProduct}"

        binding.colorProduct.setCardBackgroundColor(arguments.color.toInt())

        binding.registrationPrice.addTextChangedListener {
            binding.registrationPriceLayout.isErrorEnabled = false
            viewModel.changePriceProduct(it.toString())
        }

        binding.quantityProduct.addTextChangedListener {
            viewModel.changeQuantityProduct(it.toString())
        }

        binding.dateCard.setOnClickListener {
            MaterialDatePicker.Builder.datePicker()
                .setTitleText(getString(R.string.title_date_picker))
                .build().apply {
                    addOnPositiveButtonClickListener {
                        val calendar = Calendar.getInstance()
                        calendar.time = Date(it)
                        val dateFormat = formatDate(calendar)
                        binding.dateProduct.text = dateFormat
                        viewModel.changeDateProduct(dateFormat)
                    }
                }.show(childFragmentManager, null)
        }

        binding.colorProduct.setOnClickListener {
            ColorPickerDialogBuilder
                .with(context)
                .setTitle(getString(R.string.title_color_picker_edit))
                .initialColor(arguments.color.toInt())
                .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
                .density(12)
                .setPositiveButton(
                    getString(R.string.text_ok)
                ) { dialog, selectedColor, allColors ->
                    binding.colorProduct.setCardBackgroundColor(selectedColor)
                    viewModel.changeColorProduct(selectedColor)
                }
                .setNegativeButton(
                    getString(R.string.text_cancel)
                ) { dialog, which -> }
                .build()
                .show()
        }

        binding.registrationApplyConfirm.setOnClickListener {
            viewModel.applyRegisterUpdateProduct()
        }

        viewModel.showErroPrice.observe(viewLifecycleOwner, {
            binding.registrationPriceLayout.error = it
        })

        viewModel.showErroDate.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

        viewModel.confirmRegistration.observe(viewLifecycleOwner, {
            findNavController().navigateUp()
        })

    }

    private fun confirmRemoveItem() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.title_remove_product))
            .setMessage("")
            .setNegativeButton(getString(R.string.text_negative)) { _, _ -> }
            .setPositiveButton(getString(R.string.text_positive)) { _, _ ->
                viewModel.deleteProduct(arguments.nameProduct)
                findNavController().navigateUp()
            }
            .create()
            .show()
    }

    private fun paintButtonOnAndOff(
        buttonOn: ExtendedFloatingActionButton,
        buttonOff: ExtendedFloatingActionButton
    ) {
        with(buttonOn) {
            setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            setTextColor(ContextCompat.getColor(requireContext(), R.color.main_theme))
            setStrokeColorResource(R.color.main_theme)
        }
        with(buttonOff) {
            setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.main_theme))
            setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            setStrokeColorResource(R.color.white)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val APPLICATION = "application"
        const val RESCUE = "rescue"
    }
}