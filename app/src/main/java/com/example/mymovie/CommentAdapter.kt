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

class CommentAdapter(var context: Context?) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    private val items = ArrayList<CommentItem>()

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
        items.add(item)
    }

    fun getItem(position: Int): CommentItem {
        return items[position]
    }

    class ViewHolder(binding: CommentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val commentText: TextView = itemView.findViewById(R.id.commentText)
        private val userId: TextView = itemView.findViewById(R.id.userId)
        private val commentTime: TextView = itemView.findViewById(R.id.commentTime)
        private val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)
        private val recommendCount: TextView = itemView.findViewById(R.id.recommendCount)

        fun setItem(item: CommentItem) {
            commentText.text = item.comment
            userId.text = item.id
            commentTime.text = item.time
            ratingBar.rating = item.rating
            recommendCount.text = item.recommend.toString()
        }
    }
}