package com.liao.propertymanagement.helper

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.liao.propertymanagement.R
import kotlinx.android.synthetic.main.app_bar.*


fun AppCompatActivity.toolbar(title: String){
    var toolbar = this.toolbar
    toolbar.title = title
    this.setSupportActionBar(toolbar)
    this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
}


@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String?){
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.wait)
        .into(this)
}

