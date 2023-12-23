package com.swoh.perfectwedding.usecase

import com.swoh.perfectwedding.domain.GroomBrideType
import com.swoh.perfectwedding.persistence.node.GroomBrideRepository
import com.swoh.perfectwedding.security.ChangeAuthorityHelper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class EditGroomBrideUsecase(
    private val groomBrideRepository: GroomBrideRepository,
) {
    @Transactional
    fun edit(
        id: UUID,
        type: GroomBrideType?,
    ) {
        val groomBride = checkNotNull(groomBrideRepository.findByIdOrNull(id))

        groomBride.changeType(type)

        groomBrideRepository.save(groomBride)

        ChangeAuthorityHelper.change(groomBride.authority)
    }
}