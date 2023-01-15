package es.pedram.emailverification

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EmailVerificationApplication

fun main(args: Array<String>) {
	runApplication<EmailVerificationApplication>(*args)
}
