package com.example.mymovie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovie.databinding.FragmentCommentListBinding


class CommentListFragment : Fragment() {
    private lateinit var binding: FragmentCommentListBinding
    private lateinit var adapter:CommentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommentListBinding.inflate(inflater, container, false)

        adapter = CommentAdapter(context) // 어댑터 생성
        binding.commentView.adapter = adapter // 리사이클러뷰에 어댑터 연결

        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.commentView.layoutManager = layoutManager

        // 한줄평 작성하기
        binding.writeBtn.setOnClickListener{ showCommentWrite() }

        // TODO: 한줄평 추가했을 때 정보 받아와서 리사이클러뷰에 추가하기

       return binding.root
    }

    private fun showCommentWrite() {
//        var parentFragment:Fragment? = parentFragmentManager.findFragmentByTag("MovieDetail")
//        if (parentFragment == null){
//            Log.d("test", "test")
//            parentFragment = parentFragmentManager.findFragmentByTag("FullCommentList")
//        }
//        if (parentFragment == null){
//            Log.d("test", "test")
//            parentFragment = parentFragmentManager.findFragmentByTag("MoviePager")
//        }
//        val fragmentManager: FragmentManager? = MovieDetailFragment().parentFragmentManager
//        val transaction: FragmentTransaction? = fragmentManager?.beginTransaction()
//        val fragment = CommentWriteFragment()
//
//        transaction?.add(R.id.fragment_content, fragment)
//        transaction?.commit()
        val intent = Intent(context, CommentWriteActivity::class.java)
        startActivity(intent)
    }

}