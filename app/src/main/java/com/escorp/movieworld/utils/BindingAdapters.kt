package com.escorp.movieworld.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
fun setImageUrl(view: ImageView, url: String?, placeholderDrawable: Drawable) {
    if (url != null) {
        Picasso.get().load(url).placeholder(placeholderDrawable).into(view)
    } else {
        view.setImageDrawable(placeholderDrawable)
    }
}