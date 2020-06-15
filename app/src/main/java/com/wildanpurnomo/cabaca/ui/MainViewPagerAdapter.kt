package com.wildanpurnomo.cabaca.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wildanpurnomo.cabaca.ui.bookList.BookListFragment
import com.wildanpurnomo.cabaca.ui.genre.GenreFragment

class MainViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                BookListFragment()
            }
            else -> {
                GenreFragment()
            }
        }
    }
}