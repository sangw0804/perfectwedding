package com.swoh.perfectwedding.persistence.relationship

import com.swoh.perfectwedding.persistence.node.Plan
import org.springframework.data.neo4j.core.schema.RelationshipProperties
import org.springframework.data.neo4j.core.schema.TargetNode

@RelationshipProperties
class HasPlan(
    @TargetNode
    val plan: Plan,
): BaseRelationship() {
    companion object {
        fun create(plan: Plan): HasPlan {
            return HasPlan(
                plan = plan,
            )
        }
    }
}