package com.example.iwallet.features.wallet.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.iwallet.databinding.FragmentExtractBinding
import com.example.iwallet.features.wallet.adapter.ListExtractAdapter
import com.example.iwallet.features.wallet.viewmodel.ExtractViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExtractFragment : Fragment() {

    private var _binding: FragmentExtractBinding? = null
    private val binding: FragmentExtractBinding get() = _binding!!
    private val viewModel: ExtractViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExtractBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.requestListExtracts()

        viewModel.listExtracts.observe(viewLifecycleOwner, {
            binding.listExtract.adapter = ListExtractAdapter(it, requireContext())
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}