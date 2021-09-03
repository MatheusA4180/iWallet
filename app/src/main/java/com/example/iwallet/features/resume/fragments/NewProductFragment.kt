package com.example.iwallet.features.resume.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.iwallet.R
import com.example.iwallet.databinding.FragmentNewProductBinding
import com.example.iwallet.features.resume.adapter.ListCategoriesAdapter
import com.example.iwallet.features.resume.adapter.ListProductsAdapter

class NewProductFragment : Fragment(), ListCategoriesAdapter.ClickedCategoryListener {

    private var _binding: FragmentNewProductBinding? = null
    private val binding: FragmentNewProductBinding get() = _binding!!
    //private val viewModel: WalletViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewProductBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.toolbarNewProduct.setNavigationOnClickListener {
            requireActivity().finish()
        }

        binding.listCategories.adapter = ListCategoriesAdapter(
            listOf(
                "Ações/Stocks, BDR, ADR, FII e Reits",
                "Criptomoedas",
                "Debêntures",
                "Fundo",
                "Moeda",
                "Poupança",
                "Previdência",
                "Renda fixa Pós-fixada",
                "Renda fixa Prefixada",
                "Tesouro direto"
            ),this@NewProductFragment
        )

    }

    override fun clickCategoryListener(positionRecyclerView: Int,category:String) {
        findNavController()
            .navigate(NewProductFragmentDirections
                .actionNewProductFragmentToDescriptionNewProductFragment(category))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}