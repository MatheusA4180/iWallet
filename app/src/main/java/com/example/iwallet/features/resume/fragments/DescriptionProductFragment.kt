package com.example.iwallet.features.resume.fragments

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.iwallet.R
import com.example.iwallet.databinding.FragmentDescriptionProductBinding
import com.example.iwallet.features.resume.viewmodel.DescriptionProductViewModel
import com.example.iwallet.utils.helperfunctions.HelperFunctions.formatDate
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class DescriptionProductFragment : Fragment() {

    private var _binding: FragmentDescriptionProductBinding? = null
    private val binding: FragmentDescriptionProductBinding get() = _binding!!
    private val viewModel: DescriptionProductViewModel by viewModel()
    private val arguments: DescriptionProductFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDescriptionProductBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        paintButtonOn(binding.addApplication)

        viewModel.changeNameProduct(arguments.nameProduct)
        viewModel.changeBrokerProduct(arguments.nameBroker)
        viewModel.changeCategotyProduct(arguments.category)
        viewModel.changeColorProduct(arguments.color.toInt())

        binding.toolbarDescriptionProduct.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.addApplication.setOnClickListener {
            paintButtonOn(binding.addApplication)
            paintButtonOff(binding.addRescue)
            viewModel.buttonPressedApplication()
        }

        binding.addRescue.setOnClickListener {
            paintButtonOn(binding.addRescue)
            paintButtonOff(binding.addApplication)
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

        binding.nameBrokerDescription.text = "Instituição financeira: " + arguments.nameBroker

        binding.nameProductDescription.text = "Produto: " + arguments.nameProduct

        binding.colorProduct.setCardBackgroundColor(arguments.color.toInt())

        binding.registrationPrice.addTextChangedListener {
            viewModel.changePriceProduct(it.toString())
        }

        binding.quantityProduct.addTextChangedListener {
            viewModel.changeQuantityProduct(it.toString())
        }

        binding.dateCard.setOnClickListener {
            MaterialDatePicker.Builder.datePicker().setTitleText(getString(R.string.title_date_picker))
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
                .setTitle("Escolha a nova cor do produto")
                .initialColor(arguments.color.toInt())
                .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
                .density(12)
                .setPositiveButton(
                    "Ok"
                ) { dialog, selectedColor, allColors ->
                    binding.colorProduct.setCardBackgroundColor(selectedColor)
                    viewModel.changeColorProduct(selectedColor)
                }
                .setNegativeButton(
                    "Cancelar"
                ) { dialog, which -> }
                .build()
                .show()
        }

        binding.registrationApplyConfirm.setOnClickListener {
            viewModel.applyRegisterUpdateProduct()
            findNavController().navigateUp()
        }

    }

    private fun confirmRemoveItem() {
        AlertDialog.Builder(requireContext())
            .setTitle("Deseja excluir esse produto?")
            .setMessage("")
            .setNegativeButton("Não") { _, _ -> }
            .setPositiveButton("Sim") { _, _ ->
                viewModel.deleteProduct(arguments.nameProduct)
                findNavController().navigateUp() }
            .create()
            .show()
    }

    private fun paintButtonOn(button: ExtendedFloatingActionButton) {
        with(button) {
            setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            setTextColor(ContextCompat.getColor(requireContext(), R.color.main_theme))
            setStrokeColorResource(R.color.main_theme)
        }
    }

    private fun paintButtonOff(button: ExtendedFloatingActionButton) {
        with(button) {
            setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.main_theme))
            setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            setStrokeColorResource(R.color.white)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val APPLICATION = "application"
        const val RESCUE = "rescue"
    }
}