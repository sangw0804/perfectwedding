package com.swoh.perfectwedding.presentation

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController {
    @PreAuthorize("hasAuthority(T(com.swoh.perfectwedding.security.CustomAuthority).INFO_SUBMITTED.code)")
    @GetMapping("/main")
    fun getMain(): String {
        return "main"
    }

    @GetMapping("/")
    fun getRoot(): String {
        return "redirect:/main"
    }
}