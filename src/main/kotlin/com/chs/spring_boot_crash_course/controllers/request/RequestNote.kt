package com.chs.spring_boot_crash_course.controllers.request

import jakarta.validation.constraints.NotBlank

data class RequestNote(
    val id: String?,
    @NotBlank(message = "Title can't be blank.")
    val title: String,
    val content: String,
    val color: Long
)
