package com.andromesh.movieslistassignment.binding

import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.andromesh.movieslistassignment.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton


@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("isGone")
fun bindIsGone(view: FloatingActionButton, isGone: Boolean?) {
    if (isGone == null || isGone) {
        view.hide()
    } else {
        view.show()
    }
}

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {

        val requestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)

        Glide.with(view.context)
            .load(imageUrl)
            .apply(requestOptions)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)

    }
}

@BindingAdapter("renderHtml")
fun bindRenderHtml(view: TextView, description: String?) {
    if (description != null) {
        view.text = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
        view.movementMethod = LinkMovementMethod.getInstance()
    } else {
        view.text = ""
    }
}

@BindingAdapter("onError")
fun bindOnError(editText: EditText, strOrResId: Any?) {
    if (strOrResId is Int) {
        editText.error = editText.context
            .getString((strOrResId as Int?)!!)
    } else {
        editText.error = strOrResId as String?
    }
}

@BindingAdapter("setFavoriteIcon")
fun bindonsetFavoriteIcon(imageButton: ImageButton, isFavorite: Boolean) {
    if (isFavorite) {
        imageButton.setImageResource(R.drawable.ic_favorite_filled)
    } else {
        imageButton.setImageResource(R.drawable.ic_favorite_hollow)

    }
}



