package com.chs.spring_boot_crash_course.controllers.response

import java.time.Instant

data class ResponseNote(
    val id: String,
    val title: String,
    val content: String,
    val color: Long,
    val createTime: Instant
)
