package com.route.takenote.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.route.takenote.R
import com.route.takenote.adapters.NoteAdapter
import com.route.takenote.databinding.DialogDeleteBinding
import com.route.takenote.databinding.FragmentNotesBinding
import com.route.takenote.model.DataItems
import com.route.takenote.model.allNotesItems
import com.route.takenote.ui.activities.CreateNoteActivity

class NotesFragment : Fragment() {
    lateinit var binding: FragmentNotesBinding
    val adapter = NoteAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        intentForCreateNote()
        prepareRv()
    }


    private fun prepareRv() {
        binding.rvAllNotes.adapter = adapter
        adapter.setMyList(allNotesItems.filter { allNotesItems -> !allNotesItems.isArchived }.toMutableList())
        binding.rvAllNotes.layoutManager = LinearLayoutManager(requireContext()
            ,LinearLayoutManager.VERTICAL,false)

        onArchivedClick()
        onDeleteClick()
    }

    private fun onDeleteClick(){
        adapter.onDeleteClick = object : NoteAdapter.OnDeleteClickListener {
            override fun onDeleteClickListener(position: Int) {
                displayBottomSheetDialog(position)
            }

        }
    }

    private fun displayBottomSheetDialog(position: Int) {
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
        val binding = DialogDeleteBinding.inflate(requireActivity().layoutInflater)
        bottomSheetDialog.setContentView(binding.root)
        bottomSheetDialog.show()
        binding.noBtn.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        binding.yesBtn.setOnClickListener {
            allNotesItems.removeAt(position)
            adapter.removeNoteFromList(position)
            bottomSheetDialog.dismiss()
        }
    }

    fun onArchivedClick(){
        adapter.onArshivClick = object : NoteAdapter.OnArshivClickListenter{
            override fun onArshivClickListener(note: DataItems, position: Int) {
                note.isArchived=true
                adapter.setListItem(note,position)
                adapter.setMyList(allNotesItems.filter { it -> !it.isArchived }.toMutableList())
            }

        }
    }
    override fun onResume() {
        super.onResume()
        adapter.setMyList(allNotesItems.filter { !it.isArchived }.toMutableList())
    }

    private fun intentForCreateNote(){
        binding.btnAddNotes.setOnClickListener {
            val intent = Intent(requireContext(), CreateNoteActivity::class.java)
            intent.putExtra("isArchived",false)
            startActivity(intent)
        }
    }
}