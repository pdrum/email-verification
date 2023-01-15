package es.pedram.emailverification.otp.controllers

import es.pedram.emailverification.otp.entity.Otp
import es.pedram.emailverification.otp.models.OtpSender
import es.pedram.emailverification.otp.models.RandomGenerator
import es.pedram.emailverification.otp.repositories.OtpRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.transaction.Transactional

data class EmailAddressPayload(val email: String)
data class EmailVerificationPayload(val email: String, val code: String)

@RestController
class OtpController(
    val otpSender: OtpSender,
    val randomGenerator: RandomGenerator,
    val otpRepository: OtpRepository,
) {
    @PostMapping("/api/users/otp")
    @Transactional
    fun sendOtp(@RequestBody payload: EmailAddressPayload) {
        val code = randomGenerator.nextString()
        val otp = otpRepository.findByEmail(payload.email) ?: Otp(
            code = code,
            email = payload.email,
            used = false,
        )
        otpRepository.save(otp)
        otpSender.send(payload.email, code)
    }

    @PostMapping("/api/users/otp-verification")
    @Transactional
    fun verifyOtp(@RequestBody payload: EmailVerificationPayload): ResponseEntity<Any> {
        val forbiddenResponse = ResponseEntity.status(HttpStatus.FORBIDDEN).build<Any>()
        val otp = otpRepository.findByEmail(payload.email) ?: return forbiddenResponse
        if (otp.used == true || otp.code!! != payload.code) return forbiddenResponse
        otp.used = true
        otpRepository.save(otp)
        return ResponseEntity.ok().build()
    }
}
