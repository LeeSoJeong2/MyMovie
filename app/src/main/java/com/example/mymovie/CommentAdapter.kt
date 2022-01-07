package com.example.mymovie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie.databinding.CommentItemBinding
import java.util.ArrayList

data class CommentItem(var id:String, var time:String, var rating:Float, var comment: String?)

class CommentAdapter(var context: Context?) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    private val items = ArrayList<CommentItem>()

    init {
        items.add(CommentItem("kym71**", "19분전", 5.0f, "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."))
        items.add(CommentItem("angel**", "18분전", 4.6f, "웃긴 내용보다는 좀 더 진지한 영화."))
        items.add(CommentItem("beaut**", "16분전", 4.3f, "연기가 부족한 느낌이 드는 배우도 있다. 그래도 전체적으로는 재밌다."))
        items.add(CommentItem("sales**", "15분전", 5.0f, "마지막이 비극인 영화."))
        items.add(CommentItem("pargo**", "9분전", 5.0f, "스트레스가 해소되는 영화네요."))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        CommentItemBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
    }

    fun addItem(item: CommentItem) {
        items.add(0, item)
    }

    fun getItem(position: Int): CommentItem {
        return items[position]
    }

    class ViewHolder(binding: CommentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private var commentText: TextView
        private var userId: TextView
        private var commentTime: TextView
        private var ratingBar: RatingBar

        fun setItem(item: CommentItem) {
            commentText.text = item.comment
            userId.text = item.id
            commentTime.text = item.time
            ratingBar.rating = item.rating

        }

        init {
            commentText = itemView.findViewById<View>(R.id.commentText) as TextView
            userId = itemView.findViewById<View>(R.id.userId) as TextView
            commentTime = itemView.findViewById<View>(R.id.commentTime) as TextView
            ratingBar = itemView.findViewById<View>(R.id.ratingBar) as RatingBar
        }
    }
}