package com.chs.spring_boot_crash_course.controllers

import com.chs.spring_boot_crash_course.controllers.response.ResponseNote
import com.chs.spring_boot_crash_course.database.model.Note

fun Note.toResponse(): ResponseNote {
    return ResponseNote(
        id = this.id.toHexString(),
        title = this.title,
        content = this.content,
        color = this.color,
        createTime = this.createTime
    )
}