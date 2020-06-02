package com.bombadu.spinedit

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.util.*
import kotlin.collections.HashSet

class MainActivity : AppCompatActivity() {

    private var categoryList = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadList()

        var adapter = ArrayAdapter(this, android.R.layout.select_dialog_singlechoice, categoryList)
        val aCView = findViewById<AutoCompleteTextView>(R.id.languageACView)
        aCView.threshold = 1
        aCView.setAdapter(adapter)

        saveButton.setOnClickListener {
            val category = aCView.text.toString()
            categoryList.add(category)

            aCView.text = null
            saveList()
            loadList()
            adapter = ArrayAdapter(this, android.R.layout.select_dialog_singlechoice, categoryList)
            val aCView = findViewById<AutoCompleteTextView>(R.id.languageACView)
            aCView.threshold = 1
            aCView.setAdapter(adapter)



        }


    }

    private fun saveList() {
        val set: HashSet<String> = HashSet(categoryList)

        val prefs = getSharedPreferences("key", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putStringSet("key", set)
        editor.apply()

    }

    private fun loadList() {
        val set: HashSet<String>
        try {
            val prefs: SharedPreferences = getSharedPreferences("key", Context.MODE_PRIVATE)
            set = prefs.getStringSet("key", null) as HashSet<String>
            categoryList = set.toMutableList()

        } catch (e: Exception) {
            e.printStackTrace()
        }


    }
}