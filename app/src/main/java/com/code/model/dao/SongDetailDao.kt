package com.code.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.code.model.SongDetail

@Dao
interface SongDetailDao {

  @get:Query("Select * from songdetail")
  val all: List<SongDetail>

  @Insert
  fun insertAll(songDetaiList: ArrayList<SongDetail>)

  @Query("Delete from songdetail")
  fun deleteAll()
}