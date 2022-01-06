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

import java.util.ArrayList

data class MoviePosterItem(var title:String, var movieNumber:Int)

class MoviePagerAdapter(var context: Context?, var changeMovieDetailFragment: ChangeMovieDetailFragment) : RecyclerView.Adapter<MoviePagerAdapter.MoviePagerViewHolder>() {
    private var moviePosterItems = ArrayList<MoviePosterItem>()

    init {
        moviePosterItems.add(MoviePosterItem("1. 군 도", 1))
        moviePosterItems.add(MoviePosterItem("2. 공 조", 2))
        moviePosterItems.add(MoviePosterItem("3. 더 킹", 3))
        moviePosterItems.add(MoviePosterItem("4. 레지던트 이블", 4))
        moviePosterItems.add(MoviePosterItem("5. 럭 키", 5))
        moviePosterItems.add(MoviePosterItem("6. 아수라", 6))
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePagerViewHolder =
        MoviePagerViewHolder(ItemMoviePagerBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MoviePagerViewHolder, position: Int) {
        val moviePosterItem = moviePosterItems[position]
        holder.setItem(moviePosterItem)
    }

    override fun getItemCount(): Int {
       return moviePosterItems.size
    }

    inner class MoviePagerViewHolder(binding: ItemMoviePagerBinding): RecyclerView.ViewHolder(binding.root) {
        private var movieTitle: TextView
        private var posterImg: ImageView
        private var detailBtn: Button

        fun setItem(item: MoviePosterItem) {
            movieTitle.text = item.title
            when(item.movieNumber){
                1 -> posterImg.setImageResource(R.drawable.image1)
                2 -> posterImg.setImageResource(R.drawable.image2)
                3 -> posterImg.setImageResource(R.drawable.image3)
                4 -> posterImg.setImageResource(R.drawable.image4)
                5 -> posterImg.setImageResource(R.drawable.image5)
                6 -> posterImg.setImageResource(R.drawable.image6)
            }
            detailBtn.setOnClickListener{
                changeMovieDetailFragment.detailBtnClick()
            }
        }

        init {
            movieTitle = itemView.findViewById<View>(R.id.movieTitle) as TextView
            posterImg = itemView.findViewById<View>(R.id.posterImg) as ImageView
            detailBtn = itemView.findViewById<View>(R.id.detailBtn) as Button
        }
    }
}

