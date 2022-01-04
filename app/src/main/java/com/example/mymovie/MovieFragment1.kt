package com.example.mymovie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class MovieFragment1 : Fragment() {
    var detailButton: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_movie1, container, false) as ViewGroup

        detailButton = rootView.findViewById<View>(R.id.detailBtn) as Button
        detailButton!!.setOnClickListener{ showMainActivity() }

        return rootView
    }

    fun showMainActivity() {
        val intent = Intent(super.getContext(), MainActivity::class.java)

        startActivityForResult(intent, 102)
    }

}