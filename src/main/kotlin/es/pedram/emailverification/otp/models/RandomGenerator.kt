package es.pedram.emailverification.otp.models

import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class RandomGenerator {
    fun nextString() = Random.nextInt(1000, 9999).toString()
}
