package br.edu.infnet.pokedex

import android.widget.EditText

const val DEFAULT_LIMIT = 20

fun EditText.offset() =
     text.toString().let {
        if (it.isNotBlank()){
            it.toInt()
        } else {
            0
        }
    }


fun EditText.limit() = this.offset() + DEFAULT_LIMIT