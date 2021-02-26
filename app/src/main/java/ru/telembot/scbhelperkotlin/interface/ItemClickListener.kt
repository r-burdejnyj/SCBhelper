package ru.telembot.scbhelperkotlin.`interface`

import android.view.View

interface ItemClickListener<T> {

    fun onItemClick(view: View, position: Int)
}