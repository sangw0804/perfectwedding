package com.swoh.perfectwedding.security

import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig(
    private val oAuthService: OAuthService,
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .oauth2Login {
                it
                    .successHandler { _, response, _ ->
                        response.sendRedirect("/groomBrides/edit") // TODO: 로그인 요청전 페이지로 가기
                    }
                    .userInfoEndpoint {
                        it
                            .userService(oAuthService)
                    }
            }
            .exceptionHandling {
                it.accessDeniedHandler { _, response, _ ->
                    if (SecurityContextHolder.getContext().authentication.authorities.contains(CustomAuthority.INFO_SUBMITTED.asAuthority())) {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied")
                    } else {
                        response.sendRedirect("/groomBrides/edit")
                    }
                }
            }
            .build()
    }
}