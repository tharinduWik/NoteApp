package com.example.note

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.note.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db:NoteDatabseHelper
    private var noteId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabseHelper(this)

        noteId = intent.getIntExtra("note_id",-1)
        // Check note ID is valid
        if(noteId == -1){
            // If note ID is invalid, finish the activity
            finish()
            return
        }

        val note = db.getNoteBuId(noteId)
        binding.updateTitleEditText.setText(note.title)
        binding.UpdateContentEditText.setText((note.content))
        binding.updateDateEditText.setText(note.date)

        binding.updateSaveButton.setOnClickListener(){
            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.UpdateContentEditText.text.toString()
            val newDate = binding.updateDateEditText.text.toString()
            val updateNote = Note(noteId,newTitle,newContent,newDate)
            db.updateNote(updateNote)
            finish()
            Toast.makeText(this,"Changes Saved",Toast.LENGTH_SHORT).show()

        }
    }
}