package com.route.takenote.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.route.takenote.adapters.NoteAdapter
import com.route.takenote.databinding.FragmentNotesBinding
import com.route.takenote.model.DataItems
import com.route.takenote.ui.activities.CreateNoteActivity

class NotesFragment : Fragment() {
    lateinit var binding: FragmentNotesBinding
    lateinit var adapter: NoteAdapter
    val dataList = ArrayList<DataItems>()
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



        binding.btnAddNotes.setOnClickListener {
            val intent = Intent(requireContext(), CreateNoteActivity::class.java)
            startActivity(intent)
        }

        prepareRv()
    }

    private fun prepareRv() {
        val title = arguments?.getString("tit")
        val details = arguments?.getString("deta")
        val dataList = ArrayList<DataItems>()
        dataList.add(DataItems(title.toString(),details.toString()))
        adapter = NoteAdapter()
        adapter.setList(dataList)
        binding.rvAllNotes.layoutManager = LinearLayoutManager(requireContext()
            ,LinearLayoutManager.VERTICAL,false)
        binding.rvAllNotes.adapter = adapter
    }
}