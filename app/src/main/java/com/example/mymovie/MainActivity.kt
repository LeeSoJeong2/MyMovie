package com.example.mymovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie.databinding.ActivityMainBinding

var items = ArrayList<CommentItem>()

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var likeCount = 0
    var likeState = false
    var unlikeCount = 0
    var unlikeState = false
    var adapter:CommentAdapter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 바인딩 객체 획득
        binding = ActivityMainBinding.inflate(layoutInflater)

        // 액티비티 화면 출력
        setContentView(binding.root)

        // 좋아요 버튼 클릭 이벤트
        binding.likeBtn.setOnClickListener {
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
        likeCount = (binding.likeText.text).toString().toInt()

        // 싫어요 버튼 클릭 이벤트
        binding.unlikeBtn.setOnClickListener {
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
        unlikeCount = (binding.unlikeText.text).toString().toInt()

        // 한줄평 (recycler View)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.commentView.layoutManager = layoutManager

        adapter = CommentAdapter(applicationContext, items)

        adapter!!.addItem(CommentItem("kym71**", "19분전", 5.0f, "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."))
        adapter!!.addItem(CommentItem("angel**", "18분전", 4.6f, "웃긴 내용보다는 좀 더 진지한 영화."))
        adapter!!.addItem(CommentItem("beaut**", "16분전", 4.3f, "연기가 부족한 느낌이 드는 배우도 있다. 그래도 전체적으로는 재밌다."))
        adapter!!.addItem(CommentItem("sales**", "15분전", 5.0f, "마지막이 비극인 영화."))
        adapter!!.addItem(CommentItem("pargo**", "9분전", 5.0f, "스트레스가 해소되는 영화네요."))

        binding.commentView.adapter = adapter

        // 한줄평 작성하기
        binding.writeBtn.setOnClickListener{ showCommentWriteActivity() }

        // 한줄평 모두보기
        binding.showBtn.setOnClickListener{ showCommentListActivity() }

    }
    fun incrLikeCount() {
        likeCount += 1
        binding.likeText.text = likeCount.toString()
        binding.likeBtn.setBackgroundResource(R.drawable.ic_thumb_up_selected)
    }

    fun decrLikeCount() {
        likeCount -= 1
        binding.likeText.text = likeCount.toString()
        binding.likeBtn.setBackgroundResource(R.drawable.ic_thumb_up)
    }

    fun incrUnlikeCount() {
        unlikeCount += 1
        binding.unlikeText.text = unlikeCount.toString()
        binding.unlikeBtn.setBackgroundResource(R.drawable.ic_thumb_down_selected)
    }

    fun decrUnlikeCount() {
        unlikeCount -= 1
        binding.unlikeText.text = unlikeCount.toString()
        binding.unlikeBtn.setBackgroundResource(R.drawable.ic_thumb_down)
    }

    fun showCommentWriteActivity() {
        val intent = Intent(applicationContext, CommentWriteActivity::class.java)

        startActivityForResult(intent, 101)
    }

    fun showCommentListActivity() {
        val intent = Intent(applicationContext, CommentListActivity::class.java)

        startActivityForResult(intent, 102)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == 101) {
            if (intent != null) {
                val contents = intent.getStringExtra("contents")
                val rating = intent.getFloatExtra("rating", 0.0f)
                adapter!!.addItem(CommentItem("kym71**", "0분전", rating, contents))
                adapter?.notifyDataSetChanged()
            }
        }
    }

}