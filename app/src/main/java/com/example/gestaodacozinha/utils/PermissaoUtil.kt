package com.example.gestaodacozinha.utils

import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import timber.log.Timber

@RequiresApi(23)
private fun permissoesNecessarias23(
    activity: AppCompatActivity,
    permissoes: List<String>,
    onComplete: () -> Unit,
) {
    val permissoesPorConceber = mutableListOf<String>()
    val requestPermissionLauncher =
        activity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { map ->
            for (entry in map) {
                if (entry.value) {
                    Timber.d("Permissão concebida: ${entry.key}")
                    permissoesPorConceber.remove(entry.key)
                }
            }
            if (permissoesPorConceber.isEmpty()) {
                onComplete()
            } else {
                Timber.w("São necessárias as seguintes permissões: $permissoesPorConceber")
            }
        }
    for (permissao in permissoes) {
        if (ContextCompat.checkSelfPermission(
                activity,
                permissao
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Timber.d("Permissão já concebida: $permissao")
        } else {
            permissoesPorConceber.add(permissao)
        }
    }
    if (permissoesPorConceber.isEmpty()) {
        onComplete()
    } else {
        Timber.d("Pedir permissões: $permissoesPorConceber")
        requestPermissionLauncher.launch(permissoesPorConceber.toTypedArray())
    }
}

fun permissoesNecessarias(
    activity: AppCompatActivity,
    permissoes: List<String>,
    onComplete: () -> Unit,
) {
    if (android.os.Build.VERSION.SDK_INT >= 23) {
        Timber.d("Android SDK >= 23")
        permissoesNecessarias23(activity, permissoes, onComplete)
    } else {
        Timber.d("Android SDK < 23")
        onComplete()
    }
}