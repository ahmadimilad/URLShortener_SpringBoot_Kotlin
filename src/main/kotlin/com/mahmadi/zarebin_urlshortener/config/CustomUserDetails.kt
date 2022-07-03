package com.mahmadi.zarebin_urlshortener.config

import com.mahmadi.zarebin_urlshortener.entities.UserEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * @project Zarebin_URLShortener_SpringBoot
 * @author Milad Ahmadi on 9/6/2021 and 4:41 PM.
 */
class CustomUserDetails(user : UserEntity) : UserDetails {
    private var user: UserEntity? = user

    fun CustomUserDetails(user: UserEntity?) {
        this.user = user
    }

    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return null
    }

    override fun getPassword(): String? {
        return user?.password
    }

    override fun getUsername(): String? {
        return user?.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    fun getFullName(): String? {
        return user?.firstName.toString() + " " + user?.lastName
    }
}