package com.example.notescategorieshistory.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notescategorieshistory.data.dao.CategoryDao
import com.example.notescategorieshistory.data.dao.NoteDao
import com.example.notescategorieshistory.data.dao.HistoryDao
import com.example.notescategorieshistory.data.entity.Category
import com.example.notescategorieshistory.data.entity.Note
import com.example.notescategorieshistory.data.entity.History

@Database(
    entities = [Category::class, Note::class, History::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun noteDao(): NoteDao
    abstract fun historyDao(): HistoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "notes_database"
                )
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}
