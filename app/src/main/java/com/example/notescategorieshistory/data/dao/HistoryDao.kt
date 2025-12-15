package com.example.notescategorieshistory.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.notescategorieshistory.data.entity.History

@Dao
interface HistoryDao {

    @Insert
    fun insertHistory(history: History)

    @Query("SELECT * FROM history ORDER BY created_at DESC")
    fun getAllHistory(): List<History>

    @Query("SELECT * FROM history WHERE `action` = :actionType")
    fun filterByAction(actionType: String): List<History>

    @Query("SELECT * FROM history WHERE created_at BETWEEN :startDate AND :endDate")
    fun filterByDate(startDate: Long, endDate: Long): List<History>
}
