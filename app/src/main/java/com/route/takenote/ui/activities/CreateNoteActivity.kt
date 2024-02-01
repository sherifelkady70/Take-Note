package com.route.takenote.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.route.takenote.databinding.CreateNoteActivityBinding
import com.route.takenote.ui.fragments.NotesFragment

class CreateNoteActivity : AppCompatActivity() {
    lateinit var binding : CreateNoteActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CreateNoteActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = binding.edtNoteTitle.editableText.toString()
        val details = binding.edtNoteDetails.editableText.toString()

        binding.btnCreateNote.setOnClickListener {
            val intent = Intent(this@CreateNoteActivity,MainActivity::class.java)
            intent.putExtra("tit",title)
            intent.putExtra("deta",details)
            startActivity(intent)
        }




    }
}