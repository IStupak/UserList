package com.example.userlist.utils

import android.widget.ImageView
import com.example.userlist.R
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

fun Picasso.loadWithCache(imageUrl: String?, view: ImageView) {
    this.load(imageUrl).networkPolicy(NetworkPolicy.OFFLINE)
        .placeholder(R.drawable.ic_baseline_image_24)
        .into(view, object : Callback {
            override fun onSuccess() {}

            override fun onError(e: Exception?) {
                this@loadWithCache.load(imageUrl).placeholder(R.drawable.ic_baseline_image_24)
                    .into(view)
            }
        })
}