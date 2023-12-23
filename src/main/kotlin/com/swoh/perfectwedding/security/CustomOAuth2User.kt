package com.swoh.perfectwedding.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User

class CustomOAuth2User(
    private val uid: String,
    private val authorities: List<CustomAuthority>,
): OAuth2User {
    override fun getName(): String {
        return uid
    }

    override fun getAttributes(): MutableMap<String, Any> {
        return mutableMapOf()
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities.map { it.asAuthority() }.toMutableList()
    }
}