package com.example.testappforbestapps.data.db.Entityes

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.sql.Blob

@Entity(tableName = "newsItem")
@Parcelize
class NewsItem (
    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "descriptin")
    var descriptin: String?,

    @ColumnInfo(name = "publishedAt")
    var publishedAt: String?,

    @ColumnInfo(name = "content")
    var content: String?,

    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
    var image: ByteArray?,

    @PrimaryKey(autoGenerate = true)
var id: Long? = null
) : Parcelable