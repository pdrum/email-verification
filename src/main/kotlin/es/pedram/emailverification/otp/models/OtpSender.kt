package es.pedram.emailverification.otp.models

import org.springframework.stereotype.Component

@Component
class OtpSender {
    fun send(emailAddress: String, otp: String) {
        println("Sending $otp to $emailAddress")
    }
}