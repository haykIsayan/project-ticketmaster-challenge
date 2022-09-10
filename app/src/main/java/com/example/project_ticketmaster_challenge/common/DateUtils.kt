package com.example.project_ticketmaster_challenge.common

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private const val DATE_FORMAT_TICKET_MASTER = "yyyy-mm-dd"
    private const val DATE_FORMAT_DISPLAY = "MMM dd, yyyy"

    fun dateFormat(dateString: String): String {
        val date = SimpleDateFormat(
            DATE_FORMAT_TICKET_MASTER,
            Locale.US
        ).parse(dateString) ?: dateString
        return SimpleDateFormat(
            DATE_FORMAT_DISPLAY,
            Locale.US
        ).format(date)
    }
}