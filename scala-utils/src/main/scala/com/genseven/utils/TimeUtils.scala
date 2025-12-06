package com.genseven.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.sql.Date

object TimeUtils{
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    def now(): String = LocalDateTime.now().format(formatter)
}
