package com.example.notescategorieshistory.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.notescategorieshistory.data.entity.Note

@Dao
interface NoteDao {

    @Insert
    fun insertNote(note: Note)

    @Query("SELECT * FROM notes WHERE category_id = :categoryId")
    fun getNotesByCategory(categoryId: Int): List<Note>

    @Query(
        "SELECT * FROM notes " +
                "WHERE note_title LIKE '%' || :text || '%' " +
                "OR note_content LIKE '%' || :text || '%'"
    )
    fun searchNotes(text: String): List<Note>

    @Delete
    fun deleteNote(note: Note)
}
