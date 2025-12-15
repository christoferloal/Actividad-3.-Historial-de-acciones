package com.example.notescategorieshistory.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.notescategorieshistory.data.entity.Category

@Dao
interface CategoryDao {

    @Insert
    fun insertCategory(category: Category)

    @Query("SELECT * FROM categories")
    fun getAllCategories(): List<Category>
}
