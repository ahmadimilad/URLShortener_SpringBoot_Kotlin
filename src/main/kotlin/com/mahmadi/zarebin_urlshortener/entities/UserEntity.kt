package com.mahmadi.zarebin_urlshortener.entities

import javax.persistence.*

/**
 * @project Zarebin_URLShortener_SpringBoot
 * @author Milad Ahmadi on 9/6/2021 and 2:51 PM.
 */

@Table (name = "users")
@Entity
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    @Column(nullable = false, unique = true, length = 45)
    var email: String,

    @Column(nullable = false, length = 64)
    var password: String,

    @Column(name = "first_name", nullable = false, length = 20)
    var firstName: String,

    @Column(name = "last_name", nullable = false, length = 20)
    var lastName: String
){
    constructor() : this(1, "", "","", "")
}
