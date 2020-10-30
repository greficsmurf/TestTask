package com.example.goratest.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbUser(
        @PrimaryKey
        val id: Long
)