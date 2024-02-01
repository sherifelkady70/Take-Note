package com.route.takenote.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.route.takenote.R
import com.route.takenote.databinding.ItemNoteBinding
import com.route.takenote.model.DataItems

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    var dataList = mutableListOf<DataItems>()
    fun setMyList(newList : MutableList<DataItems>){
        this.dataList=newList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layuotInflator = ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteViewHolder(layuotInflator)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val data : DataItems = dataList[position]
        holder.bind(data)

        onArshivClick?.let {
            holder.binding.archiveImv.setOnClickListener {
                onArshivClick?.onArshivClickListener(data,position)
            }
        }

        onDeleteClick?.let {
            holder.binding.deleteImv.setOnClickListener {
                onDeleteClick?.onDeleteClickListener(position)
            }
        }
    }



    class NoteViewHolder(val binding : ItemNoteBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(note: DataItems){
            binding.title.text = note.title
            binding.details.text = note.details
            if(note.isArchived){
                binding.archiveImv.setImageResource(R.drawable.baseline_unarchive_24)
            }else{
                binding.archiveImv.setImageResource(R.drawable.baseline_archive_24)
            }
        }
    }
    var onArshivClick : OnArshivClickListenter?=null
    interface OnArshivClickListenter{
        fun onArshivClickListener(note : DataItems ,position: Int)
    }
    var onDeleteClick : OnDeleteClickListener?=null
    interface OnDeleteClickListener{
        fun onDeleteClickListener(position: Int)
    }
}