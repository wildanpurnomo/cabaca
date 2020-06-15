package com.wildanpurnomo.cabaca.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.wildanpurnomo.cabaca.R
import com.wildanpurnomo.cabaca.data.book.BookViewModel
import com.wildanpurnomo.cabaca.data.genre.GenreViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mBookViewModel: BookViewModel

    private lateinit var mGenreVIewModel: GenreViewModel

    private lateinit var mAppBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(mainToolbar)

        mBookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        mGenreVIewModel = ViewModelProvider(this).get(GenreViewModel::class.java)

        val navController = findNavController(R.id.nav_host_fragment)
        mAppBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, mAppBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(mAppBarConfiguration) || super.onSupportNavigateUp()
    }
}