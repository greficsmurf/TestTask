package com.example.goratest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.goratest.db.models.DbUser

@Database(
        entities = [DbUser::class],
        version = 1
)
abstract class AppDatabase : RoomDatabase(){

}