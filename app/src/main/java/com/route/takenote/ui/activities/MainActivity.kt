package com.route.takenote.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.route.takenote.R
import com.route.takenote.databinding.ActivityMainBinding
import com.route.takenote.ui.fragments.ArchivedFragment
import com.route.takenote.ui.fragments.NotesFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    val notesFragment = NotesFragment()
    val arshivedFragment = ArchivedFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addFragment(notesFragment)
        binding.bottomNavView.setOnItemSelectedListener {
            if(it.itemId == R.id.notes_menu_item){
                replaceFragment(notesFragment)
            }
            if(it.itemId == R.id.archived_notes_menu_item){
                replaceFragment(arshivedFragment)
            }
            return@setOnItemSelectedListener true //must determine the scope of return
        }
    }

    private fun addFragment(fragment: Fragment){
        val trans = supportFragmentManager.beginTransaction()
        trans.add(R.id.fragmentContainer,fragment)
        trans.commit()
    }
    private fun replaceFragment(fragment : Fragment){
        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.fragmentContainer,fragment)
        trans.commit()
    }
}