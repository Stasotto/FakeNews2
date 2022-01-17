package com.example.data.storage.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "subtitle")
    val subtitle: String,

    @ColumnInfo(name = "isSaved")
    val isSaved: Boolean = false,

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String


) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}
