package com.example.iwallet.features.resume.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iwallet.R
import com.example.iwallet.databinding.FragmentNewProductBinding
import com.example.iwallet.features.resume.adapter.ListCategoriesAdapter
import com.example.iwallet.utils.clicklistener.ClickListeners

class NewProductFragment : Fragment(), ClickListeners.ClickedCategoryListener {

    private var _binding: FragmentNewProductBinding? = null
    private val binding: FragmentNewProductBinding get() = _binding!!
    private val listCategory: List<String> by lazy {
        listOf(
            getString(R.string.item_category_1),
            getString(R.string.item_category_2),
            getString(R.string.item_category_3),
            getString(R.string.item_category_4),
            getString(R.string.item_category_5),
            getString(R.string.item_category_6),
            getString(R.string.item_category_7),
            getString(R.string.item_category_8),
            getString(R.string.item_category_9),
            getString(R.string.item_category_10)
        )
    }

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
            listCategory,this@NewProductFragment
        )

    }

    override fun clickCategoryListener(positionRecyclerView: Int, category: String) {
        findNavController()
            .navigate(
                NewProductFragmentDirections
                    .actionNewProductFragmentToDescriptionNewProductFragment(category)
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
