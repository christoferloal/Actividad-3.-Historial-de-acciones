package com.example.notescategorieshistory.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.notescategorieshistory.R
import com.example.notescategorieshistory.data.database.AppDatabase
import com.example.notescategorieshistory.data.entity.Category
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private lateinit var listView: ListView
    private lateinit var categories: List<Category>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = AppDatabase.getDatabase(this)
        listView = findViewById(R.id.listCategories)

        // Insertar categoría de prueba si no hay datos
        if (database.categoryDao().getAllCategories().isEmpty()) {
            database.categoryDao().insertCategory(
                Category(categoryName = "General")
            )
        }

        val btnHistory = findViewById<Button>(R.id.btnHistory)

        btnHistory.setOnClickListener {
            startActivity(
                Intent(this, HistoryActivity::class.java)
            )
        }

        loadCategories()

        //  Conectar con NotesActivity
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedCategory = categories[position]

            val intent = Intent(this, NotesActivity::class.java)
            intent.putExtra("category_id", selectedCategory.categoryId)
            intent.putExtra("category_name", selectedCategory.categoryName)
            startActivity(intent)
        }
    }

    private fun loadCategories() {
        categories = database.categoryDao().getAllCategories()

        val categoryNames = categories.map { it.categoryName }

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            categoryNames
        )

        listView.adapter = adapter
    }
}
