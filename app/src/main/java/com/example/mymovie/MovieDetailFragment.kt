package com.example.mymovie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.mymovie.databinding.FragmentCommentListBinding
import com.example.mymovie.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailBinding

    private var likeCount = 0
    private var likeState = false
    private var unlikeCount = 0
    private var unlikeState = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)


        // 다른 액티비티에서 결과 받기
//        getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//            if (it.resultCode == RESULT_OK) {
//                if (intent != null) {
//                    val contents = intent.getStringExtra("contents")
//                    val rating = intent.getFloatExtra("rating", 0.0f)
//                    adapter.addItem(CommentItem("kym71**", "0분전", rating, contents))
//                    adapter.notifyDataSetChanged() // TODO: 데이터변경되었을 때 사용하는 함수 구체적으로 변경
//                }
//            }
//        }

        // 좋아요 버튼 클릭 이벤트
        binding.likeBtn.setOnClickListener {
            if (likeState) {
                decreaseLikeCount()
            } else {
                increaseLikeCount()
            }
            likeState = !likeState

            if (unlikeState) {
                decreaseUnlikeCount()
                unlikeState = !unlikeState
            }
        }
        likeCount = (binding.likeText.text).toString().toInt()

        // 싫어요 버튼 클릭 이벤트
        binding.unlikeBtn.setOnClickListener {
            if (unlikeState) {
                decreaseUnlikeCount()
            } else {
                increaseUnlikeCount()
            }
            unlikeState = !unlikeState

            if (likeState) {
                decreaseLikeCount()
                likeState = !likeState
            }
        }
        unlikeCount = (binding.unlikeText.text).toString().toInt()


        // 한줄평 모두보기
        binding.showBtn.setOnClickListener{ showFullComment() }

        return binding.root
    }

    private fun increaseLikeCount() {
        likeCount += 1
        binding.likeText.text = likeCount.toString()
        binding.likeBtn.setBackgroundResource(R.drawable.ic_thumb_up_selected)
    }

    private fun decreaseLikeCount() {
        likeCount -= 1
        binding.likeText.text = likeCount.toString()
        binding.likeBtn.setBackgroundResource(R.drawable.ic_thumb_up)
    }

    private fun increaseUnlikeCount() {
        unlikeCount += 1
        binding.unlikeText.text = unlikeCount.toString()
        binding.unlikeBtn.setBackgroundResource(R.drawable.ic_thumb_down_selected)
    }

    private fun decreaseUnlikeCount() {
        unlikeCount -= 1
        binding.unlikeText.text = unlikeCount.toString()
        binding.unlikeBtn.setBackgroundResource(R.drawable.ic_thumb_down)
    }


    private fun showFullComment() {
        val fragmentManager: FragmentManager = parentFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = FullCommentListFragment()
        transaction.add(R.id.fragment_content, fragment,"FullCommentList")
        transaction.commit()
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
//        super.onActivityResult(requestCode, resultCode, intent)
//        if (requestCode == 101) {
//            if (intent != null) {
//                val contents = intent.getStringExtra("contents")
//                val rating = intent.getFloatExtra("rating", 0.0f)
//                adapter!!.addItem(CommentItem("kym71**", "0분전", rating, contents))
//                adapter?.notifyDataSetChanged()
//            }
//        }
//    }
}