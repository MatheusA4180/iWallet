package com.example.iwallet.features.wallet.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.iwallet.features.resume.fragments.ListProductsFragment
import com.example.iwallet.features.wallet.fragments.ExtractFragment

class ViewPagerWalletAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                ListProductsFragment().also {
                    it.arguments = Bundle().apply {
                        putBoolean(TOOLBAR_ENABLE, false)
                    }
                }
            }
            else -> {
                ExtractFragment()
            }
        }
    }

    companion object {
        const val TOOLBAR_ENABLE = "toolbarEnable"
    }

}
