package com.example.sythnews.helper

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    //Fungsi untuk mendapatkan data tanggal/date
    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }
}