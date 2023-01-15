package es.pedram.emailverification

import es.pedram.emailverification.otp.repositories.OtpRepository
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BaseComponentTest {
    @Autowired
    protected lateinit var restTemplate: TestRestTemplate

    @Autowired
    private lateinit var otpRepository: OtpRepository

    @BeforeEach
    fun beforeEach() {
        otpRepository.deleteAll()
    }
}
