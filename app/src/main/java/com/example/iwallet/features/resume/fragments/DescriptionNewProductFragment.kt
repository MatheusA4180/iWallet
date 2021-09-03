package com.example.iwallet.features.resume.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.iwallet.R
import com.example.iwallet.databinding.FragmentDescriptionNewProductBinding
import com.example.iwallet.features.resume.viewmodel.DescriptionNewProductViewModel
import com.example.iwallet.utils.helperfunctions.HelperFunctions.formatDate
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class DescriptionNewProductFragment: Fragment() {

    private var _binding: FragmentDescriptionNewProductBinding? = null
    private val binding: FragmentDescriptionNewProductBinding get() = _binding!!
    private val viewModel: DescriptionNewProductViewModel by viewModel()
    private val arguments: DescriptionNewProductFragmentArgs by navArgs()

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

        viewModel.changeCategory(arguments.category)

        binding.newBroker.addTextChangedListener{
            viewModel.changeNameBroker(it.toString())
        }

        binding.newProduct.addTextChangedListener{
            viewModel.changeNameProduct(it.toString())
        }

        binding.newPrice.addTextChangedListener{
            viewModel.changePriceProduct(it.toString())
        }

        binding.newQuantityProduct.addTextChangedListener{
            viewModel.changeQuantityProduct(it.toString())
        }

        binding.newRate.addTextChangedListener {
            viewModel.changeRateProduct(it.toString())
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

        binding.registrationNewApplyConfirm.setOnClickListener {
            viewModel.applyRegisterProduct()
            requireActivity().finish()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}