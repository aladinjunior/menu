package com.aladin.menu.util

import android.text.Editable
import android.text.TextWatcher

class Watcher(private val getText: (String) -> Unit) : TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        getText.invoke(p0.toString())
    }

    override fun afterTextChanged(p0: Editable?) {
    }
}