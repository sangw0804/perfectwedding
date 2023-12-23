package com.swoh.perfectwedding.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

object ChangeAuthorityHelper {
    fun change(newAuthority: CustomAuthority) {
        val auth = SecurityContextHolder.getContext().authentication
        SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(
            auth.principal, auth.credentials, listOf(newAuthority.asAuthority())
        )
    }
}