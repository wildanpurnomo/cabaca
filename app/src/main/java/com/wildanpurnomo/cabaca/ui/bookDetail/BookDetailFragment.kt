package com.wildanpurnomo.cabaca.ui.bookDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.wildanpurnomo.cabaca.R
import com.wildanpurnomo.cabaca.data.book.BookViewModel
import kotlinx.android.synthetic.main.fragment_book_detail.*


class BookDetailFragment : Fragment() {

    private val args: BookDetailFragmentArgs by navArgs()

    private lateinit var mBookViewModel: BookViewModel

    private lateinit var mBookDetailViewModel: BookDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        mBookDetailViewModel = ViewModelProvider(this).get(BookDetailViewModel::class.java)
        mBookViewModel.setBookDetail(args.bookDetailArgs)

        mBookViewModel.getBookDetail().observe(viewLifecycleOwner, Observer {
            // Set Image
            val options: RequestOptions = RequestOptions()
                .fitCenter()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)
            val imageURL = mBookDetailViewModel.getImageUrl(it.coverUrl.toString())
            Glide.with(this).load(imageURL).apply(options)
                .into(fragBookDetailImg)

            // Set Title
            fragBookDetailTitleValue.text = it.title.toString()

            // Set Genre
            val genreString = mBookDetailViewModel.getGenreString(it.genre)
            fragBookDetailGenreValue.text = genreString

            // Set Author
            fragBookDetailAuthorValue.text = it.writerByWriterId?.userByUserId?.name.toString()

            // Set Desc
            fragBookDetailDescValue.text = String.format(it.synopsis.toString())

            fragBookDetailContent.visibility = View.VISIBLE
            fragBookDetailProgressBar.visibility = View.GONE
        })
    }
}