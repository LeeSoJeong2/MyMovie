package com.example.mymovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.mymovie.databinding.FragmentFullCommentListBinding

class FullCommentListFragment : Fragment() {
    private lateinit var binding: FragmentFullCommentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFullCommentListBinding.inflate(inflater, container, false)
        binding.backBtn.setOnClickListener{
            changeToMovieDetailFragment()
        }

        return binding.root
    }

    private fun changeToMovieDetailFragment(){
        val fragmentManager: FragmentManager = parentFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = MovieDetailFragment()
        transaction.add(R.id.fragment_content, fragment,"MovieDetail")
        transaction.commit()
    }
}