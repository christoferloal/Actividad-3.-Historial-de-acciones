package com.example.notescategorieshistory.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.notescategorieshistory.R
import com.example.notescategorieshistory.data.database.AppDatabase
import com.example.notescategorieshistory.data.entity.History
import com.example.notescategorieshistory.data.entity.Note

class AddNoteActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private var categoryId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        database = AppDatabase.getDatabase(this)
        categoryId = intent.getIntExtra("category_id", -1)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etContent = findViewById<EditText>(R.id.etContent)
        val btnSave = findViewById<Button>(R.id.btnSave)

        val btnBack = findViewById<Button>(R.id.btnBack)

        btnBack.setOnClickListener {
            finish()
        }

        btnSave.setOnClickListener {
            val title = etTitle.text.toString()
            val content = etContent.text.toString()

            if (title.isNotBlank() && content.isNotBlank()) {

                // Insertar nota
                val note = Note(
                    noteTitle = title,
                    noteContent = content,
                    createdAt = System.currentTimeMillis(),
                    categoryId = categoryId
                )
                database.noteDao().insertNote(note)

                val history = History(
                    action = "insert_note",
                    createdAt = System.currentTimeMillis(),
                    details = title
                )
                database.historyDao().insertHistory(history)

                finish()
            }
        }
    }
}
