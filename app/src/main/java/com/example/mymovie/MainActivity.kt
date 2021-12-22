package com.example.mymovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var likeButton: Button? = null
    var likeCountView: TextView? = null
    var likeCount = 0
    var likeState = false
    var unlikeButton: Button? = null
    var unlikeCountView: TextView? = null
    var unlikeCount = 0
    var unlikeState = false
    var commentView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 좋아요 버튼 클릭 이벤트
        likeButton = findViewById<View>(R.id.likeBtn) as Button
        likeButton!!.setOnClickListener {
            if (likeState) {
                decrLikeCount()
            } else {
                incrLikeCount()
            }
            likeState = !likeState

            if (unlikeState) {
                decrUnlikeCount()
                unlikeState = !unlikeState
            }
        }

        likeCountView = findViewById<View>(R.id.likeText) as TextView
        likeCount = (likeCountView!!.text).toString().toInt()

        // 싫어요 버튼 클릭 이벤트
        unlikeButton = findViewById<View>(R.id.unlikeBtn) as Button
        unlikeButton!!.setOnClickListener {
            if (unlikeState) {
                decrUnlikeCount()
            } else {
                incrUnlikeCount()
            }
            unlikeState = !unlikeState

            if (likeState) {
                decrLikeCount()
                likeState = !likeState
            }
        }

        unlikeCountView = findViewById<View>(R.id.unlikeText) as TextView
        unlikeCount = (unlikeCountView!!.text).toString().toInt()

        // 한줄평 (recycler View)
        commentView = findViewById<View>(R.id.commentView) as RecyclerView

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        commentView!!.layoutManager = layoutManager

        val adapter = CommentAdapter(applicationContext)

        adapter.addItem(CommentItem("kym71**", "10분전", 4.0f, "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."))
        adapter.addItem(CommentItem("kym71**", "10분전", 4.0f, "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."))
        adapter.addItem(CommentItem("kym71**", "10분전", 4.0f, "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."))
        adapter.addItem(CommentItem("kym71**", "10분전", 4.0f, "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."))
        adapter.addItem(CommentItem("kym71**", "10분전", 4.0f, "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."))

        commentView!!.adapter = adapter

    }

    fun incrLikeCount() {
        likeCount += 1
        likeCountView!!.text = likeCount.toString()
        likeButton!!.setBackgroundResource(R.drawable.ic_thumb_up_selected)
    }

    fun decrLikeCount() {
        likeCount -= 1
        likeCountView!!.text = likeCount.toString()
        likeButton!!.setBackgroundResource(R.drawable.ic_thumb_up)
    }

    fun incrUnlikeCount() {
        unlikeCount += 1
        unlikeCountView!!.text = unlikeCount.toString()
        unlikeButton!!.setBackgroundResource(R.drawable.ic_thumb_down_selected)
    }

    fun decrUnlikeCount() {
        unlikeCount -= 1
        unlikeCountView!!.text = unlikeCount.toString()
        unlikeButton!!.setBackgroundResource(R.drawable.ic_thumb_down)
    }
}