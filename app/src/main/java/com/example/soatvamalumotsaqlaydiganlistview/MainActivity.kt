package com.example.soatvamalumotsaqlaydiganlistview

import MySharedPrefManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import com.example.soatvamalumotsaqlaydiganlistview.adapter.NoteAdapter
import com.example.soatvamalumotsaqlaydiganlistview.model.Note
import com.example.soatvamalumotsaqlaydiganlistview.util.Time
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var fab: FloatingActionButton
    private lateinit var noteAdapter: NoteAdapter
    private val sharedPrefManager by lazy { MySharedPrefManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.listView)
        fab = findViewById(R.id.fab)


        supportActionBar?.hide()
        noteAdapter = NoteAdapter(this, sharedPrefManager.getNotes() ?: arrayListOf())
        listView.adapter = noteAdapter
        fab.setOnClickListener {
            showAlertDialog()
        }
    }

    private fun showAlertDialog() {
        val alertDialog = AlertDialog.Builder(this).create()
        val view: View = LayoutInflater.from(this).inflate(R.layout.alert_dialog, null)
        alertDialog.setView(view)
        val editText: AppCompatEditText = view.findViewById(R.id.editText)
        val btnCancel: MaterialButton = view.findViewById(R.id.btnCancel)
        val btnSave: MaterialButton = view.findViewById(R.id.btnSave)

        btnCancel.setOnClickListener {
            alertDialog.dismiss()
        }

        btnSave.setOnClickListener {
            val title = editText.text.toString().trim()
            val time = Time.timeStamp()
            if (title.isNotBlank()) {
                noteAdapter.saveNote(Note(title, time))
                sharedPrefManager.saveNote(noteAdapter.list)
                alertDialog.dismiss()
            }
        }
        alertDialog.show()
    }
}