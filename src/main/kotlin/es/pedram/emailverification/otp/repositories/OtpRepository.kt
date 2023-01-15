package es.pedram.emailverification.otp.repositories

import es.pedram.emailverification.otp.entity.Otp
import org.springframework.data.jpa.repository.JpaRepository

interface OtpRepository: JpaRepository<Otp, Long> {
    fun findByEmail(email: String): Otp?
}
