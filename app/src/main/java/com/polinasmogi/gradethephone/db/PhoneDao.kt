package com.polinasmogi.gradethephone.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update

@Dao
abstract class PhoneDao {

    @Query("SELECT * FROM phones")
    abstract fun getPhones(): List<PhoneEntity>

    @Query("SELECT * FROM phones where _id = :id")
    abstract fun getPhoneById(id: Int): PhoneEntity

    @Query("UPDATE phones SET grade = :grade WHERE _id = :id ")
    abstract fun updatePhone(id: Int, grade: Int)


}