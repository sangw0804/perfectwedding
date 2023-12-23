package com.swoh.perfectwedding.presentation

import com.swoh.perfectwedding.domain.error.DomainException
import com.swoh.perfectwedding.persistence.node.GroomBride
import com.swoh.perfectwedding.persistence.node.GroomBrideRepository
import com.swoh.perfectwedding.presentation.dto.EditGroomBrideReq
import com.swoh.perfectwedding.security.CustomOAuth2User
import com.swoh.perfectwedding.usecase.EditGroomBrideUsecase
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping("/groomBrides")
class GroomBrideController(
    private val groomBrideRepository: GroomBrideRepository,
    private val editGroomBrideUsecase: EditGroomBrideUsecase,
) {
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/edit")
    fun getEdit(
        @AuthenticationPrincipal user: CustomOAuth2User,
        model: Model,
    ): String {
        val groomBride = checkNotNull(groomBrideRepository.findByUid(user.name))

        if (!model.containsAttribute("editGroomBrideReq")) {
            model.addAttribute("editGroomBrideReq", EditGroomBrideReq(
                type = groomBride.groomBrideType,
            ))
        }

        return "groomBrides/edit"
    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping
    fun edit(
        @AuthenticationPrincipal user: CustomOAuth2User,
        @ModelAttribute form: EditGroomBrideReq,
        attr: RedirectAttributes,
    ): String {
        val groomBride = checkNotNull(groomBrideRepository.findByUid(user.name))

        try {
            editGroomBrideUsecase.edit(
                id = groomBride.id,
                type = form.type,
            )
        } catch (e: DomainException) {
            attr.addFlashAttribute("editGroomBrideReq", form)
            attr.addFlashAttribute("globalErrorCode", e.code)
        }

        return "redirect:/groomBrides/edit"
    }
}