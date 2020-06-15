package com.wildanpurnomo.cabaca.ui.bookList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wildanpurnomo.cabaca.R
import com.wildanpurnomo.cabaca.data.book.BookModel
import com.wildanpurnomo.cabaca.data.book.BookViewModel
import kotlinx.android.synthetic.main.fragment_book_list.*

class BookListFragment : Fragment(), BookListRVAdapter.OnItemClickCallback {
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
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragBookListRefresh.isEnabled = false
        fragBookListRV.adapter = mBookListAdapter
        fragBookListRV.layoutManager = LinearLayoutManager(requireContext())

        mBookViewModel.getLatestBook().observe(viewLifecycleOwner, Observer {
            mBookListAdapter.updateDataset(it)
            fragBookListProgressBar.visibility = View.GONE
            fragBookListRV.visibility = View.VISIBLE

            fragBookListRefresh.isRefreshing = false
            fragBookListRefresh.isEnabled = true
            fragBookListRefresh.setOnRefreshListener {
                mBookViewModel.setLatestBook()
            }
        })

        mBookViewModel.getErrorMessage().observe(viewLifecycleOwner, Observer {
            fragBookListTVError.text = it
        })

        mBookViewModel.getIsError().observe(viewLifecycleOwner, Observer {
            fragBookListTVError.isVisible = it
            fragBookListProgressBar.visibility = View.GONE
            fragBookListRefresh.isRefreshing = false
        })
    }

    override fun onItemClick(data: BookModel) {
    }
}