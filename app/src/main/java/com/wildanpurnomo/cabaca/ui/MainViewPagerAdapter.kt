package com.wildanpurnomo.cabaca.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                BookListFragment()
            }
            2 -> {
                GenreFragment()
            }
            else -> {
                FavoriteFragment()
            }
        }
    }
}