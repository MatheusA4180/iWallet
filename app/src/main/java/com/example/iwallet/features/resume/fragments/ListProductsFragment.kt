package com.example.iwallet.features.resume.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.iwallet.databinding.FragmentListProductsBinding
import com.example.iwallet.features.resume.adapter.ListProductsAdapter
import com.example.iwallet.features.resume.viewmodel.ListProductsViewModel
import com.example.iwallet.features.wallet.adapter.ViewPagerWalletAdapter.Companion.TOOLBAR_ENABLE
import com.example.iwallet.utils.clicklistener.ClickListeners
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListProductsFragment : Fragment(), ClickListeners.ClickedProductListener {

    private var _binding: FragmentListProductsBinding? = null
    private val binding: FragmentListProductsBinding get() = _binding!!
    private val viewModel: ListProductsViewModel by viewModel()
    private var toobarEnable: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListProductsBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.requestListProducts()

        try {
            binding.toolbarListProducts.isVisible = requireArguments().getBoolean(TOOLBAR_ENABLE)
            binding.titleToolbarListProducts.isVisible =
                requireArguments().getBoolean(TOOLBAR_ENABLE)
            toobarEnable = requireArguments().getBoolean(TOOLBAR_ENABLE)
        } catch (e: Exception) { }

        binding.toolbarListProducts.setNavigationOnClickListener {
            requireActivity().finish()
        }

        //bug
        binding.searchView.setOnClickListener {
            viewModel.searchProduct(binding.searchView.query.toString())
        }

        viewModel.listProducts.observe(viewLifecycleOwner, {
            binding.listProducts.adapter = ListProductsAdapter(
                it,
                this@ListProductsFragment,
                toobarEnable
            )
        })

    }

    override fun clickProductListener(
        nameBroker: String,
        nameProduct: String,
        category: String,
        color: String
    ) {
        findNavController()
            .navigate(
                ListProductsFragmentDirections
                    .actionAddOrSubtractProductFragmentToDescriptionProductFragment(
                        nameBroker,
                        nameProduct,
                        category,
                        color
                    )
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
