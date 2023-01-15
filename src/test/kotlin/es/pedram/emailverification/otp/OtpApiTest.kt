package es.pedram.emailverification.otp

import com.ninjasquad.springmockk.MockkBean
import es.pedram.emailverification.BaseComponentTest
import es.pedram.emailverification.otp.models.OtpSender
import es.pedram.emailverification.otp.models.RandomGenerator
import io.mockk.every
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus


class OtpApiTest : BaseComponentTest() {
    @MockkBean
    private lateinit var otpSender: OtpSender

    @MockkBean
    private lateinit var randomGenerator: RandomGenerator

    @Test
    fun `test sending and verifying verification-code`() {
        every { randomGenerator.nextString() } returns "1234"
        every { otpSender.send("foo@bar.com", "1234") } answers {}
        val response1 = restTemplate.postForEntity(
            "/api/users/otp",
            mapOf("email" to "foo@bar.com"),
            Any::class.java
        )
        Assertions.assertThat(response1.statusCode).isEqualTo(HttpStatus.OK)

        val response2 = restTemplate.postForEntity(
            "/api/users/otp-verification",
            mapOf(
                "email" to "foo@bar.com",
                "code" to "1234",
            ),
            Any::class.java
        )
        Assertions.assertThat(response2.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    fun `test verifying invalid verification-code`() {
        every { randomGenerator.nextString() } returns "1234"
        every { otpSender.send("foo@bar.com", "1234") } answers {}
        val response1 = restTemplate.postForEntity(
            "/api/users/otp",
            mapOf("email" to "foo@bar.com"),
            Any::class.java
        )
        Assertions.assertThat(response1.statusCode).isEqualTo(HttpStatus.OK)

        val response2 = restTemplate.postForEntity(
            "/api/users/otp-verification",
            mapOf(
                "email" to "foo@bar.com",
                "code" to "wrong",
            ),
            Any::class.java
        )
        Assertions.assertThat(response2.statusCode).isEqualTo(HttpStatus.FORBIDDEN)
    }
}
