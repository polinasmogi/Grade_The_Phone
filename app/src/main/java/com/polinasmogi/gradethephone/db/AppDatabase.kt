package com.polinasmogi.gradethephone.db

import android.content.Context
import androidx.room.*
import androidx.room.Room


@Database(
    entities = [(PhoneEntity::class)],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun phoneDao(): PhoneDao

    companion object {

        private const val DATABASE_NAME = "phones"
        private const val DATABASE_DIR = "databases/nsysgroup.db"

        fun getInstance(context: Context): AppDatabase {
            return Room
                .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .createFromAsset(DATABASE_DIR)
                .build()
        }

    }


}