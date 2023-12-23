package com.swoh.perfectwedding.usecase

import com.swoh.perfectwedding.persistence.node.GroomBride
import com.swoh.perfectwedding.persistence.node.GroomBrideRepository
import com.swoh.perfectwedding.persistence.node.Plan
import com.swoh.perfectwedding.persistence.node.PlanRepository
import com.swoh.perfectwedding.persistence.relationship.HasPlan
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class JoinUsecase(
    private val planRepository: PlanRepository,
    private val groomBrideRepository: GroomBrideRepository,
) {
    @Transactional
    fun join(
        uid: String,
    ) {
        val plan = Plan.create()
        val hasPlan = HasPlan.create(plan)
        val groomBride = GroomBride.create(
            hasPlan = hasPlan, uid = uid
        )
        planRepository.save(plan)
        groomBrideRepository.save(groomBride)
    }
}