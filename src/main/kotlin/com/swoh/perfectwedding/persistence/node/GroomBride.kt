package com.swoh.perfectwedding.persistence.node

import com.swoh.perfectwedding.domain.GroomBrideType
import com.swoh.perfectwedding.persistence.relationship.HasPlan
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship

@Node("GroomBride")
class GroomBride(
    @Relationship(type = "HAS_PLAN", direction = Relationship.Direction.OUTGOING)
    val has: HasPlan,
    var type: String,
    var uid: String,
): BaseNode() {
    companion object {
        fun create(hasPlan: HasPlan, type: GroomBrideType, uid: String): GroomBride {
            return GroomBride(
                has = hasPlan,
                type = type.name,
                uid = uid,
            )
        }
    }
}