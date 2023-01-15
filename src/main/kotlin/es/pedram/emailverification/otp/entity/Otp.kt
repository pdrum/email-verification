package es.pedram.emailverification.otp.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Otp(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var code: String? = null,
    @Column(unique = true)
    var email: String? = null,
    var used: Boolean? = null,
)
