package com.example.iwallet.features.manager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.iwallet.databinding.FragmentMoneyManagerBinding

class MoneyManagerFragment: Fragment() {

    private var _binding: FragmentMoneyManagerBinding? = null
    private val binding: FragmentMoneyManagerBinding get() = _binding!!
    //private val viewModel: MoneyManagerViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoneyManagerBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}