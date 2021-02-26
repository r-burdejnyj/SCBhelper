package ru.telembot.scbhelperkotlin.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "section")
data class Section(

    @PrimaryKey
    val id: Int,
    val title: String,
    val subtitle: String,
    val icon: String
)
