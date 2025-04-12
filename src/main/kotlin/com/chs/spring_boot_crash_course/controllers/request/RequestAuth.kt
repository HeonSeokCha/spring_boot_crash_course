package com.chs.spring_boot_crash_course.controllers.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern

data class RequestAuth(
    @field:Email(message = "Invalid email format.")
    val email: String,
    @field:Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{9,}\$",
        message = "Password mut be at least 9 characters long and contains at least one digit, uppercase and lowercase character."
    )
    val password: String
)
