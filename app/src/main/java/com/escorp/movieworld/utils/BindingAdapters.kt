package com.escorp.movieworld.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
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

@BindingAdapter("textWithCheck")
fun setTextWithCheck(view: TextView, text: String?) {
    if (text != null) {
        view.visibility = View.VISIBLE
        view.text = text
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter("visibilityByContent")
fun setVisibilityByContent(view: TextView, text: String?) {
    if (text != null) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}