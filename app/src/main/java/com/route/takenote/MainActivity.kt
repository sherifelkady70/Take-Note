package com.route.takenote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.transition.FragmentTransitionSupport
import com.route.takenote.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    val notesFragment = NotesFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        notes_Fragnment()
        binding.bottomNavView.setOnItemSelectedListener {
            if(it.itemId == R.id.notes_menu_item){
                notes_Fragnment()
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun notes_Fragnment(){
        addFragment(notesFragment)
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