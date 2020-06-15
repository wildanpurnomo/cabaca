package com.wildanpurnomo.cabaca.ui.genre

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.wildanpurnomo.cabaca.R
import com.wildanpurnomo.cabaca.data.book.BookModel
import com.wildanpurnomo.cabaca.data.book.BookViewModel
import com.wildanpurnomo.cabaca.data.genre.GenreViewModel
import com.wildanpurnomo.cabaca.ui.MainFragmentDirections
import com.wildanpurnomo.cabaca.ui.bookList.BookListRVAdapter
import kotlinx.android.synthetic.main.fragment_genre.*

class GenreFragment : Fragment(), BookListRVAdapter.OnItemClickCallback,
    AdapterView.OnItemSelectedListener {
    private val mGenreViewModel: GenreViewModel by activityViewModels()

    private val mBookViewModel: BookViewModel by activityViewModels()

    private lateinit var mBookListAdapter: BookListRVAdapter

    private lateinit var mLayoutAnimationController: LayoutAnimationController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBookListAdapter = BookListRVAdapter()
        mBookListAdapter.setOnItemClickCallback(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_genre, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mLayoutAnimationController =
            AnimationUtils.loadLayoutAnimation(requireContext(), R.anim.layout_animation_fall_down)

        fragGenreRV.adapter = mBookListAdapter
        fragGenreRV.layoutManager = LinearLayoutManager(requireContext())
        fragGenreRV.layoutAnimation = mLayoutAnimationController

        fragGenreSpinner.setOnItemSelectedListener(this)

        mGenreViewModel.getGenreNameList().observe(viewLifecycleOwner, Observer {
            val adapter = ArrayAdapter(
                requireContext(), android.R.layout.simple_spinner_item, it
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            fragGenreSpinner.setAdapter(adapter)
        })

        mBookViewModel.getBookByGenre().observe(viewLifecycleOwner, Observer {
            mBookListAdapter.updateDataset(it)
            fragGenreRV.visibility = View.VISIBLE
            fragGenreRV.scheduleLayoutAnimation()
            fragGenreProgressBar.visibility = View.GONE
        })
    }

    override fun onItemClick(data: BookModel) {
        val action = MainFragmentDirections.actionMainPageToBookDetailPage(data.id ?: -1)
        findNavController().navigate(action)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if (p2 != 0) {
            val genreId = mGenreViewModel.getGenreId(p2)
            mBookViewModel.setBookByGenre(genreId ?: -1)
            fragGenreProgressBar.visibility = View.VISIBLE
        }
    }
}