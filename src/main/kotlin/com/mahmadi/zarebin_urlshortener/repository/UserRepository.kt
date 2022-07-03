package com.mahmadi.zarebin_urlshortener.repository

import com.mahmadi.zarebin_urlshortener.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

/**
 * @project Zarebin_URLShortener_SpringBoot
 * @author Milad Ahmadi on 9/6/2021 and 3:41 PM.
 */
@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
    fun findByEmail(email: String?): UserEntity?
}