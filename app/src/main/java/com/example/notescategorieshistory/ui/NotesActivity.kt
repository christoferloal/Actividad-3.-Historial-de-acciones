package com.example.notescategorieshistory.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.notescategorieshistory.R
import com.example.notescategorieshistory.data.database.AppDatabase
import com.example.notescategorieshistory.data.entity.History
import com.example.notescategorieshistory.data.entity.Note

class NotesActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private lateinit var listView: ListView
    private lateinit var txtCategoryTitle: TextView
    private lateinit var btnAddNote: Button
    private lateinit var btnBack: Button

    private var categoryId: Int = -1
    private var categoryName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        // Base de datos
        database = AppDatabase.getDatabase(this)

        // UI
        listView = findViewById(R.id.listNotes)
        txtCategoryTitle = findViewById(R.id.txtCategoryTitle)
        btnAddNote = findViewById(R.id.btnAddNote)
        btnBack = findViewById(R.id.btnBack)

        // Datos desde MainActivity
        categoryId = intent.getIntExtra("category_id", -1)
        categoryName = intent.getStringExtra("category_name")

        txtCategoryTitle.text = categoryName ?: "Notes"

        // Agregar nota
        btnAddNote.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            intent.putExtra("category_id", categoryId)
            startActivity(intent)
        }

        // Regresar a categorías
        btnBack.setOnClickListener {
            finish()
        }

        // Cargar notas
        loadNotes(categoryId)
    }

    override fun onResume() {
        super.onResume()
        loadNotes(categoryId)
    }

    private fun loadNotes(categoryId: Int) {
        val notes: List<Note> = database.noteDao().getNotesByCategory(categoryId)

        val titles = notes.map { it.noteTitle }

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            titles
        )

        listView.adapter = adapter

        // Eliminar nota
        listView.setOnItemLongClickListener { _, _, position, _ ->
            val note = notes[position]

            // Eliminar nota
            database.noteDao().deleteNote(note)

            // Registrar historial DELETE
            database.historyDao().insertHistory(
                History(
                    action = "delete_note",
                    createdAt = System.currentTimeMillis(),
                    details = "Título: ${note.noteTitle} | Contenido: ${note.noteContent}"                )
            )

            loadNotes(categoryId)
            true
        }
    }
}
