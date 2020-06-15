package com.wildanpurnomo.cabaca.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.wildanpurnomo.cabaca.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private lateinit var mMainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mMainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        fragMainViewPager.adapter = MainViewPagerAdapter(this)
        TabLayoutMediator(fragMainTabLayout, fragMainViewPager) { tab, position ->
            tab.text = mMainViewModel.determineTabText(position)
        }.attach()
    }
}