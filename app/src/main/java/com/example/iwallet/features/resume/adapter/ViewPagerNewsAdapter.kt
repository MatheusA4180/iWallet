package com.example.iwallet.features.resume.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.iwallet.features.resume.fragments.NewsFragment

class ViewPagerNewsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 20

    override fun createFragment(position: Int): Fragment {
        return NewsFragment().also {
            it.arguments = Bundle().apply {
                putInt(POSITION_VIEW_PAGER_NEWS, position)
            }
        }
    }

    companion object{
        const val POSITION_VIEW_PAGER_NEWS = "position_view_pager_news"
    }

}
