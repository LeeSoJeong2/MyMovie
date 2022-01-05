package com.example.mymovie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.mymovie.databinding.FragmentMovie1Binding

class MovieFragment1 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMovie1Binding.inflate(inflater, container, false)

        binding.detailBtn.setOnClickListener{ showMainActivity() }

        return binding.root
    }

    fun showMainActivity() {
        val intent = Intent(super.getContext(), MainActivity::class.java)

        startActivity(intent)
    }

}