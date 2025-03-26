package com.example.orgs.extensions

import android.widget.ImageView
import coil.load
import com.example.orgs.R

fun ImageView.tentaCarregarImagem(url: String? = null,
                                  fallback: Int = R.drawable.empty_image) {
    load(url) {
        fallback(fallback)
        error(R.drawable.empty_image)
        placeholder(R.drawable.placeholder_loader)
    }
}