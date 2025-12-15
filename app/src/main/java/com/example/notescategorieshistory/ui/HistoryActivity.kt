package com.example.notescategorieshistory.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.notescategorieshistory.R
import com.example.notescategorieshistory.data.database.AppDatabase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HistoryActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private lateinit var listView: ListView
    private lateinit var spinner: Spinner
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        // Inicializar base de datos
        database = AppDatabase.getDatabase(this)

        // Referencias UI
        listView = findViewById(R.id.listHistory)
        spinner = findViewById(R.id.spinnerFilter)
        btnBack = findViewById(R.id.btnBack)

        // Botón regresar
        btnBack.setOnClickListener {
            finish()
        }

        setupSpinner()
        loadHistory(null)
    }

    private fun setupSpinner() {
        val options = listOf("All", "insert_note", "delete_note")

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            options
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selected = options[position]
                if (selected == "All") {
                    loadHistory(null)
                } else {
                    loadHistory(selected)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun loadHistory(action: String?) {
        val historyList = if (action == null) {
            database.historyDao().getAllHistory()
        } else {
            database.historyDao().filterByAction(action)
        }

        val formatted = historyList.map {
            val date = SimpleDateFormat(
                "dd/MM/yyyy HH:mm",
                Locale.getDefault()
            ).format(Date(it.createdAt))

            "${it.action} | $date | ${it.details}"
        }

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            formatted
        )

        listView.adapter = adapter
    }
}
