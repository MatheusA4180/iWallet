package com.example.iwallet.features.wallet.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.iwallet.R
import com.example.iwallet.databinding.FragmentWalletBinding
import com.example.iwallet.features.wallet.adapter.ViewPagerWalletAdapter
import com.google.android.material.tabs.TabLayoutMediator

class WalletFragment : Fragment() {

    private var _binding: FragmentWalletBinding? = null
    private val binding: FragmentWalletBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWalletBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewPagerAndTabLayoutConfig()

    }

    private fun viewPagerAndTabLayoutConfig() {
        binding.pagerWallet.adapter = ViewPagerWalletAdapter(this)
        tabLayoutConfig()
    }

    private fun tabLayoutConfig() {
        TabLayoutMediator(
            binding.tabLayoutWallet,
            binding.pagerWallet
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.title_tab_layout_wallet_1)
                }
                1 -> {
                    tab.text = getString(R.string.title_tab_layout_wallet_2)
                }
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
