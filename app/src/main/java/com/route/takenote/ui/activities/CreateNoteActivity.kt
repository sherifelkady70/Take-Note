package com.route.takenote.ui.activities

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.route.takenote.databinding.CreateNoteActivityBinding
import com.route.takenote.model.DataItems
import com.route.takenote.model.allNotesItems
import com.route.takenote.ui.fragments.NotesFragment
import java.util.Calendar

class CreateNoteActivity : AppCompatActivity() {
    lateinit var binding : CreateNoteActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CreateNoteActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNewNoteClick()
    }

    private fun createNewNoteClick(){
        binding.btnCreateNote.setOnClickListener {
            val title = binding.edtNoteTitle.text.toString()
            val details = binding.edtNoteDetails.text.toString()
            val isArchived = intent.getBooleanExtra("isArchived",false)
            val date = getCurrentDate()
            if(validateTextFileds(title,details)){
                allNotesItems.add(DataItems(title,details,date,isArchived))
//                val intent = Intent(this@CreateNoteActivity,MainActivity::class.java)
//                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"Some Thing Wrong ",Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun validateTextFileds(title:String,details:String): Boolean {
        return title.isNotEmpty() || details.isNotEmpty()
    }

    private fun getCurrentDate(): String {
        val date = Calendar.getInstance().time
        return DateFormat.format("dd MMMM, yyyy",date).toString()
    }
}