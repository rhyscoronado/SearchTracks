package com.code.injection

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.code.details.DetailsViewModel
import com.code.model.database.AppDatabase
import com.code.home.HomeViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
      val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "songdetail").build()
      return HomeViewModel(db.songDetailDao()) as T
    } else {
      return DetailsViewModel() as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
  }

}