package com.example.orgs.extensions

import android.widget.ImageView
import coil.load
import com.example.orgs.R

fun ImageView.tentaCarregarImagem(url: String? = null) {
    load(url) {
        fallback(R.drawable.empty_image)
        error(R.drawable.empty_image)
        placeholder(R.drawable.placeholder_loader)
    }
}