package com.example.mymovie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class CommentItem(var id:String, var time:String, var rating:Float, var comment: String?)

class CommentAdapter(var context: Context, var items: ArrayList<CommentItem>) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
//    var items = ArrayList<CommentItem>()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = inflater.inflate(R.layout.comment_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
    }

    fun addItem(item: CommentItem) {
        items.add(0, item)
    }

    fun addItems(items: ArrayList<CommentItem>) {
        this.items = items
    }

    fun getItme(position: Int): CommentItem {
        return items[position]
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var commentText: TextView
        var userId: TextView
        var commentTime: TextView
        var ratingBar: RatingBar

        fun setItem(item: CommentItem) {
            commentText.setText(item.comment)
            userId.setText(item.id)
            commentTime.setText(item.time)
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