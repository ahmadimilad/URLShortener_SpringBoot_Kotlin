package com.mahmadi.zarebin_urlshortener.config

import com.mahmadi.zarebin_urlshortener.entities.UserEntity
import com.mahmadi.zarebin_urlshortener.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * @project Zarebin_URLShortener_SpringBoot
 * @author Milad Ahmadi on 9/6/2021 and 4:43 PM.
 */
@Service
class CustomUserDetailsService : UserDetailsService{
    @Autowired
    private val userRepo: UserRepository? = null

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String?): UserDetails? {
        val user: UserEntity = userRepo?.findByEmail(username) ?: throw UsernameNotFoundException("User not found")
        return CustomUserDetails(user)
    }
}