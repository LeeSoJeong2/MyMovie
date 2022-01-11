package com.example.mymovie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie.databinding.ItemMoviePagerBinding
import org.w3c.dom.Text

import java.util.ArrayList

class MoviePagerAdapter(var context: Context?, var changeMovieDetailFragment: ChangeMovieDetailFragment) : RecyclerView.Adapter<MoviePagerAdapter.MoviePagerViewHolder>() {
    private var moviePosterItems = ArrayList<MoviePosterItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePagerViewHolder =
        MoviePagerViewHolder(ItemMoviePagerBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MoviePagerViewHolder, position: Int) {
        val moviePosterItem = moviePosterItems[position]
        holder.setItem(moviePosterItem)
    }

    override fun getItemCount(): Int {
       return moviePosterItems.size
    }

    fun addItem(item: MoviePosterItem) {
        moviePosterItems.add(item)
    }

    inner class MoviePagerViewHolder(binding: ItemMoviePagerBinding): RecyclerView.ViewHolder(binding.root) {
        private val movieTitle: TextView = itemView.findViewById(R.id.movieTitle)
        private val posterImg: ImageView = itemView.findViewById(R.id.posterImg)
        private val movieInfo: TextView = itemView.findViewById(R.id.movieInfo)
        private val detailBtn: Button = itemView.findViewById(R.id.detailBtn)

        fun setItem(item: MoviePosterItem) {
            movieTitle.text = item.title
            val task = ImageLoadTask(item.movieImage, posterImg)
            task.execute()
            movieInfo.text = item.movieInfo
            detailBtn.setOnClickListener{
                changeMovieDetailFragment.detailBtnClick()
            }
        }
    }
}

