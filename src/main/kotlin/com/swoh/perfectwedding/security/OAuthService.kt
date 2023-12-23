package com.swoh.perfectwedding.security

import com.swoh.perfectwedding.persistence.node.GroomBrideRepository
import com.swoh.perfectwedding.usecase.JoinUsecase
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class OAuthService(
    private val groomBrideRepository: GroomBrideRepository,
    private val joinUsecase: JoinUsecase,
): DefaultOAuth2UserService() {
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val user = super.loadUser(userRequest)
        val userNameAttributeName = userRequest
            .clientRegistration
            .providerDetails
            .userInfoEndpoint
            .userNameAttributeName
        val uid = (user.attributes[userNameAttributeName] as Long).toString()

        var found = groomBrideRepository.findByUid(uid = uid)
        if (found == null) {
            joinUsecase.join(uid = uid)
            found = groomBrideRepository.findByUid(uid = uid)!!
        }

        return CustomOAuth2User(
            uid = uid,
            authorities = listOf(found.authority),
        )
    }
}