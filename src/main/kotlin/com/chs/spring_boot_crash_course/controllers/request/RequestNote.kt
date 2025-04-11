package com.chs.spring_boot_crash_course.controllers.request

data class RequestNote(
    val id: String?,
    val title: String,
    val content: String,
    val color: Long
)
