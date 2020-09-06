package com.example.gestaodacozinha.utils

import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

@BindingAdapter("content")
fun setContent(spinner: Spinner, content: List<*>?) {
    content?.let {
        ArrayAdapter(
            spinner.context,
            android.R.layout.simple_spinner_item,
            it
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }
}

@BindingAdapter("selection")
fun setSelection(spinner: Spinner, newSelection: Any?) {
    if (newSelection != null && spinner.selectedItem != newSelection) {
        spinner.setSelection(getIndexOfItem(spinner, newSelection));
    }
}

private fun getIndexOfItem(spinner: Spinner, value: Any): Int {
    for (i in 0 until spinner.count) {
        if (spinner.getItemAtPosition(i).toString().equals(value.toString(), ignoreCase = true)) {
            return i
        }
    }
    return 0
}

@InverseBindingAdapter(attribute = "selection")
fun getSelection(spinner: Spinner): Any? {
    return spinner.selectedItem
}

@BindingAdapter("selectionAttrChanged")
fun setListeners(
    spinner: Spinner,
    selectionAttrChanged: InverseBindingListener
) {
    spinner.onItemSelectedListener = object : OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            selectionAttrChanged.onChange()
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            selectionAttrChanged.onChange()
        }
    }
}