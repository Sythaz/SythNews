package com.example.sythnews.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.sythnews.note.database.Note

class NoteDiffCallback(private val mOldNoteList: List<Note>, private val mNewNoteList: List<Note>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldNoteList.size
    }

    override fun getNewListSize(): Int {
        return mNewNoteList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldNoteList[oldItemPosition].id == mNewNoteList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldNoteList[oldItemPosition]
        val newEmployee = mNewNoteList[newItemPosition]
        //Membuat data yang baru ditampilkan di adapter dan disimpan di field masing-masing
        return oldEmployee.title == newEmployee.title && oldEmployee.description == newEmployee.description && oldEmployee.link == newEmployee.link
    }
}