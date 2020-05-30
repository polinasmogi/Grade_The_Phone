package com.polinasmogi.gradethephone.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phones")
data class PhoneEntity (

    @PrimaryKey
    @ColumnInfo(name = "_id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "grade")
    val grade: Int?,

    @ColumnInfo(name = "photo1")
    val photo1: String?,

    @ColumnInfo(name = "photo2")
    val photo2: String?,

    @ColumnInfo(name = "photo3")
    val photo3: String?

)