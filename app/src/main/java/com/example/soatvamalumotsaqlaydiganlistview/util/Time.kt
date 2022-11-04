package com.example.soatvamalumotsaqlaydiganlistview.util

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object Time {
    fun timeStamp(): String {
        val timeStamp = Timestamp(System.currentTimeMillis())
        val msh = SimpleDateFormat("EEE, d MMM yyy HH:mm a", Locale.getDefault())
        val time = msh.format(Date(timeStamp.time))
        return time.toString()
    }
}