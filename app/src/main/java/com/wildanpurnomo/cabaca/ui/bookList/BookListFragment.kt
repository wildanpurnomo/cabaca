package com.wildanpurnomo.cabaca.ui.bookList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.wildanpurnomo.cabaca.R
import com.wildanpurnomo.cabaca.data.book.BookModel
import com.wildanpurnomo.cabaca.data.book.BookViewModel
import com.wildanpurnomo.cabaca.ui.MainFragmentDirections
import kotlinx.android.synthetic.main.fragment_book_list.*

class BookListFragment : Fragment(), BookListRVAdapter.OnItemClickCallback {
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
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mLayoutAnimationController =
            AnimationUtils.loadLayoutAnimation(requireContext(), R.anim.layout_animation_fall_down)

        fragBookListRefresh.isEnabled = false
        fragBookListRV.adapter = mBookListAdapter
        fragBookListRV.layoutManager = LinearLayoutManager(requireContext())
        fragBookListRV.layoutAnimation = mLayoutAnimationController

        mBookViewModel.getLatestBook().observe(viewLifecycleOwner, Observer {
            mBookListAdapter.updateDataset(it)
            fragBookListProgressBar.visibility = View.GONE
            fragBookListRV.visibility = View.VISIBLE
            fragBookListRV.scheduleLayoutAnimation()

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
            fragBookListRefresh.isEnabled = true
        })
    }

    override fun onItemClick(data: BookModel) {
        val action = MainFragmentDirections.actionMainPageToBookDetailPage(data.id ?: -1)
        findNavController().navigate(action)
    }
}