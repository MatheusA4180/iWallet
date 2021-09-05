package com.example.iwallet.features.resume.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.iwallet.databinding.FragmentAddOrSubtractProductBinding
import com.example.iwallet.features.resume.adapter.ListProductsAdapter
import com.example.iwallet.features.resume.viewmodel.AddOrSubtractProductViewModel
import com.example.iwallet.features.wallet.adapter.ViewPagerWalletAdapter.Companion.TOOLBAR_ENABLE
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListProductsFragment : Fragment(), ListProductsAdapter.ClickedProductListener {

    private var _binding: FragmentAddOrSubtractProductBinding? = null
    private val binding: FragmentAddOrSubtractProductBinding get() = _binding!!
    private val viewModel: AddOrSubtractProductViewModel by viewModel()
    private var toobarEnable: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddOrSubtractProductBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.requestListProducts()

        try {
            binding.toolbarAddOrSubtractProduct.isVisible = requireArguments().getBoolean(TOOLBAR_ENABLE)
            binding.titleToolbarAddOrSubtractProduct.isVisible = requireArguments().getBoolean(TOOLBAR_ENABLE)
            toobarEnable = requireArguments().getBoolean(TOOLBAR_ENABLE)
        }catch (e:Exception){}

        binding.toolbarAddOrSubtractProduct.setNavigationOnClickListener {
            requireActivity().finish()
        }

        //Toast.makeText(requireContext(), binding.searchView.query.toString() , Toast.LENGTH_LONG).show()

        viewModel.listProducts.observe(viewLifecycleOwner,{
            binding.listProducts.adapter = ListProductsAdapter(
                it,
                this@ListProductsFragment,
                toobarEnable
            )
        })

    }

    override fun clickProductListener(nameBroker: String,nameProduct: String,category: String,color: String) {
        findNavController()
            .navigate(ListProductsFragmentDirections
                .actionAddOrSubtractProductFragmentToDescriptionProductFragment(nameBroker,nameProduct,category,color))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}