package com.chs.spring_boot_crash_course.controllers

import com.chs.spring_boot_crash_course.controllers.request.RequestAuth
import com.chs.spring_boot_crash_course.controllers.request.RequestRefresh
import com.chs.spring_boot_crash_course.security.AuthService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/register")
    fun register(@Valid @RequestBody body: RequestAuth) {
        authService.register(body.email, body.password)
    }

    @PostMapping("/login")
    fun login(@RequestBody body: RequestAuth): AuthService.TokenPair {
        return authService.login(body.email, body.password)
    }

    @PostMapping("/refresh")
    fun refresh(@RequestBody body: RequestRefresh): AuthService.TokenPair {
        return authService.refresh(body.refreshToken)
    }
}