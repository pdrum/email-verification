package es.pedram.emailverification.otp

import es.pedram.emailverification.BaseComponentTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus


class OtpApiTest: BaseComponentTest() {
    @Test
    fun `test sending and verifying verification-code`() {
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
}
