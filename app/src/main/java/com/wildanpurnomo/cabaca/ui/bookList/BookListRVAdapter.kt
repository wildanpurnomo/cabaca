package com.wildanpurnomo.cabaca.ui.bookList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.wildanpurnomo.cabaca.R
import com.wildanpurnomo.cabaca.data.book.BookModel
import com.wildanpurnomo.cabaca.utils.Constants.API.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.item_row_book.view.*


class BookListRVAdapter :
    RecyclerView.Adapter<BookListRVAdapter.ViewHolder>() {

    private val dataset: ArrayList<BookModel> = ArrayList()

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClick(data: BookModel)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookListRVAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_book, parent, false)
        )
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: BookListRVAdapter.ViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    fun updateDataset(dataset: ArrayList<BookModel>) {
        if (!this.dataset.isNullOrEmpty()) dataset.clear()
        this.dataset.addAll(dataset)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            bookModel: BookModel
        ) {
            with(itemView) {
                item_row_book_title.text = bookModel.title
                item_row_book_author.text = bookModel.writerByWriterId?.userByUserId?.name

                val coverUrl = bookModel.coverUrl?.replace("%2F", "/").toString()
                val imageURL =
                    IMAGE_BASE_URL + "/" + coverUrl + "&api_key=32ded42cfffb77dee86a29f43d36a3641849d4b5904aade9a79e9aa6cd5b5948"

                val options: RequestOptions = RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round)
                Glide
                    .with(itemView)
                    .load(imageURL)
                    .apply(options)
                    .into(item_row_book_image as ImageView)

                itemView.setOnClickListener {
                    onItemClickCallback.onItemClick(bookModel)
                }
            }
        }
    }
}