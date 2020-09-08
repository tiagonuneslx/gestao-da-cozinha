package com.example.gestaodacozinha.utils

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.gestaodacozinha.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

const val REQUEST_IMAGE_CAPTURE = 1

fun obterUriParaFoto(context: Context): File {
    val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
    val pasta = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
    return File.createTempFile(
        "JPEG_${timestamp}_",
        ".jpg",
        pasta
    )
}

@BindingAdapter("imageUri")
fun bindImageFromUri(imgView: ImageView, imageUri: Uri?) {
    if (imageUri != null) {
        Glide.with(imgView.context)
            .load(imageUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    } else {
        Glide.with(imgView.context)
            .load("")
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}