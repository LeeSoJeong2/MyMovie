package com.example.mymovie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovie.databinding.FragmentCommentListBinding


class CommentListFragment : Fragment() {
    private lateinit var binding: FragmentCommentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommentListBinding.inflate(inflater, container, false)

        binding.commentView.adapter = MainActivity.adapter // 리사이클러뷰에 어댑터 연결

        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.commentView.layoutManager = layoutManager

        // 한줄평 작성하기
        binding.writeBtn.setOnClickListener{ showCommentWrite() }
        MainActivity.adapter.notifyDataSetChanged()

       return binding.root
    }


    private fun showCommentWrite() {
        val intent = Intent(context, CommentWriteActivity::class.java)
        startActivity(intent)
    }

}