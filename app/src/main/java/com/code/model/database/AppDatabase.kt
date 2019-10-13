package com.code.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.code.model.SongDetail
import com.code.model.dao.SongDetailDao

@Database(entities = [(SongDetail::class)], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
  abstract fun songDetailDao(): SongDetailDao
}