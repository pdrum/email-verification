package es.pedram.emailverification.otp.controllers

import es.pedram.emailverification.otp.models.OtpSender
import es.pedram.emailverification.otp.models.RandomGenerator
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

data class EmailAddressPayload(var email: String)

@RestController
class OtpController(
    val otpSender: OtpSender,
    val randomGenerator: RandomGenerator,
) {
    @PostMapping("/api/users/otp")
    fun sendOtp(@RequestBody payload: EmailAddressPayload) {
        otpSender.send(payload.email, randomGenerator.nextString())
    }

    @PostMapping("/api/users/otp-verification")
    fun verifyOtp() {
        println(":(")
    }
}
