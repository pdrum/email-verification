package es.pedram.emailverification.otp.controllers

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OtpController {
    @PostMapping("/api/users/otp")
    fun sendOtp() {

    }

    @PostMapping("/api/users/otp-verification")
    fun verifyOtp() {

    }
}
