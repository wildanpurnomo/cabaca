package com.wildanpurnomo.cabaca.ui.writerDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.wildanpurnomo.cabaca.R
import com.wildanpurnomo.cabaca.data.writer.WriterViewModel
import kotlinx.android.synthetic.main.fragment_book_detail.*
import kotlinx.android.synthetic.main.fragment_genre.*
import kotlinx.android.synthetic.main.fragment_writer_detail.*

class WriterDetailFragment : Fragment() {
    private val args: WriterDetailFragmentArgs by navArgs()

    private lateinit var mWriterViewModel: WriterViewModel

    private lateinit var mWriterDetailViewModel: WriterDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_writer_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mWriterViewModel = ViewModelProvider(this).get(WriterViewModel::class.java)
        mWriterDetailViewModel = ViewModelProvider(this).get(WriterDetailViewModel::class.java)

        mWriterViewModel.setWriterDetail(args.writerDetailArgs)

        mWriterViewModel.getWriterDetail().observe(viewLifecycleOwner, Observer {
            // Set Photo
            val options: RequestOptions = RequestOptions()
                .fitCenter()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)
            val imageURL = mWriterDetailViewModel.getPhotoUrl(it.photoURL.toString())
            Glide.with(this).load(imageURL).apply(options)
                .into(fragWriterDetailImg)

            // Set name
            fragWriterDetailNameValue.text = it.name.toString()

            // Set email
            fragWriterDetailEmailValue.text = it.email.toString()

            // Set Phone
            fragWriterDetailPhoneValue.text = it.phone ?: "-"

            // Set Desc
            fragWriterDetailDescValue.text = it.desc.toString()

            fragWriterDetailContent.visibility = View.VISIBLE
            fragWriterDetailProgressBar.visibility = View.GONE
        })
    }

}