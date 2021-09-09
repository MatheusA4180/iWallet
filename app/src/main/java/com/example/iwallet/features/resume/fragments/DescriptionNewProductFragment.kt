package com.example.iwallet.features.resume.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.iwallet.R
import com.example.iwallet.databinding.FragmentDescriptionNewProductBinding
import com.example.iwallet.features.resume.viewmodel.DescriptionNewProductViewModel
import com.example.iwallet.utils.helperfunctions.HelperFunctions.formatDate
import com.example.iwallet.utils.mask.MoneyTextMask
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.*


class DescriptionNewProductFragment: Fragment() {

    private var _binding: FragmentDescriptionNewProductBinding? = null
    private val binding: FragmentDescriptionNewProductBinding get() = _binding!!
    private val arguments: DescriptionNewProductFragmentArgs by navArgs()
    private val viewModel: DescriptionNewProductViewModel by viewModel{
        parametersOf(arguments)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDescriptionNewProductBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.toolbarDescriptionNewProduct.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.newPrice.addTextChangedListener(MoneyTextMask(binding.newPrice))

        binding.newBroker.addTextChangedListener{
            binding.newBrokerLayout.isErrorEnabled = false
            viewModel.changeNameBroker(it.toString())
        }

        binding.newProduct.addTextChangedListener{
            binding.newProductLayout.isErrorEnabled = false
            viewModel.changeNameProduct(it.toString())
        }

        binding.newPrice.addTextChangedListener{
            binding.newPriceLayout.isErrorEnabled = false
            viewModel.changePriceProduct(it.toString())
        }

        binding.newQuantityProduct.addTextChangedListener{
            binding.newQuantityLayout.isErrorEnabled = false
            viewModel.changeQuantityProduct(it.toString())
        }

        binding.newDateCard.setOnClickListener {
            MaterialDatePicker.Builder.datePicker().setTitleText(getString(R.string.title_date_picker))
                .build().apply {
                    addOnPositiveButtonClickListener {
                        val calendar = Calendar.getInstance()
                        calendar.time = Date(it)
                        val dateFormat = formatDate(calendar)
                        binding.newDateProduct.text = dateFormat
                        viewModel.changeDateProduct(dateFormat)
                    }
                }.show(childFragmentManager, null)
        }

        binding.newColor.setOnClickListener{
            ColorPickerDialogBuilder
                .with(context)
                .setTitle(getString(R.string.title_color_picker))
                .initialColor(Color.RED)
                .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
                .density(12)
                .setPositiveButton(
                    getString(R.string.text_ok)
                ) { dialog, selectedColor, allColors ->
                    binding.newColor.setCardBackgroundColor(selectedColor)
                    viewModel.changeColorProduct(selectedColor)
                }
                .setNegativeButton(
                    getString(R.string.text_cancel)
                ) { dialog, which -> }
                .build()
                .show()
        }

        viewModel.showErroNameProduct.observe(viewLifecycleOwner,{
            binding.newProductLayout.error = it
        })

        viewModel.showErroBroker.observe(viewLifecycleOwner,{
            binding.newBrokerLayout.error = it
        })

        viewModel.showErroPrice.observe(viewLifecycleOwner,{
            binding.newPriceLayout.error = it
        })

        viewModel.showErroColorOrDate.observe(viewLifecycleOwner,{
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

        binding.registrationNewApplyConfirm.setOnClickListener {
            viewModel.applyRegisterProduct()
        }

        viewModel.confirmRegistration.observe(viewLifecycleOwner,{
            requireActivity().finish()
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}