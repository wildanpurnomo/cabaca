package com.wildanpurnomo.cabaca.ui.genre

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class GenreFragment : Fragment(), BookListRVAdapter.OnItemClickCallback {
    private val mGenreViewModel: GenreViewModel by activityViewModels()

    private val mBookViewModel: BookViewModel by activityViewModels()

    private lateinit var mBookListAdapter: BookListRVAdapter

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

        fragGenreRV.adapter = mBookListAdapter
        fragGenreRV.layoutManager = LinearLayoutManager(requireContext())

        mGenreViewModel.getGenreNameList().observe(viewLifecycleOwner, Observer {
            val adapter = ArrayAdapter(
                requireContext(), android.R.layout.simple_spinner_item, it
            )
            fragGenreSpinner.setAdapter(adapter)
            fragGenreSpinner.setOnItemSelectedListener { _, position, _, _ ->
                if (position != 0) {
                    val genreId = mGenreViewModel.getGenreId(position)
                    mBookViewModel.setBookByGenre(genreId ?: -1)
                    fragGenreProgressBar.visibility = View.VISIBLE
                }
            }
        })

        mBookViewModel.getBookByGenre().observe(viewLifecycleOwner, Observer {
            mBookListAdapter.updateDataset(it)
            fragGenreRV.visibility = View.VISIBLE
            fragGenreProgressBar.visibility = View.GONE
        })
    }

    override fun onItemClick(data: BookModel) {
        val action = MainFragmentDirections.actionMainPageToBookDetailPage(data.id ?: -1)
        findNavController().navigate(action)
    }
}