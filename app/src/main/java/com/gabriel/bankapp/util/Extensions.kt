package com.gabriel.bankapp.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gabriel.bankapp.R

fun Fragment.initToolbar(toolbar: androidx.appcompat.widget.Toolbar, homeAsUpEnabled: Boolean = true){
    (activity as AppCompatActivity).setSupportActionBar(toolbar)
    (activity as AppCompatActivity).title = ""
    (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(homeAsUpEnabled)
    (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
    toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
}