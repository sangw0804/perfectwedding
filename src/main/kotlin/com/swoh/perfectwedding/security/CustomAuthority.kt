package com.swoh.perfectwedding.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

enum class CustomAuthority(
    val code: String,
) {
    JOINED("ROLE_JOINED"), INFO_SUBMITTED("ROLE_INFO_SUBMITTED");

    fun asAuthority(): GrantedAuthority {
        return SimpleGrantedAuthority(code)
    }
}