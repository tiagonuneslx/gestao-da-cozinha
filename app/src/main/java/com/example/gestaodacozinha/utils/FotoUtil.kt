package com.example.gestaodacozinha.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import timber.log.Timber
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

const val REQUEST_IMAGE_CAPTURE = 1

fun createImageFile(context: Context): File {
    val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
    val pasta = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
    return File.createTempFile(
        "JPEG_${timestamp}_",
        ".jpg",
        pasta
    )
}

fun apagarFotoAntigaSeExistir(context: Context, fotoUri: Uri?) {
    fotoUri?.let {
        val deleteResultCode = context.contentResolver.delete(it, null, null)
        Timber.i("deleteResultCode: $deleteResultCode, URI: $it")
    }
}

fun tirarFoto(context: Context, fragment: Fragment): Uri? {
    var fotoUri: Uri? = null
    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
        intent.resolveActivity(context.packageManager)?.also {
            fotoUri = FileProvider.getUriForFile(
                context,
                "com.example.gestaodacozinha.fileprovider",
                createImageFile(context)
            )
            Timber.i("Ficheiro para guardar imagem criado, URI: $fotoUri")
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri)
            fragment.startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        }
    }
    return fotoUri
}