package com.swoh.perfectwedding.persistence.node

import com.swoh.perfectwedding.domain.GroomBrideType
import com.swoh.perfectwedding.domain.error.ErrorCode
import com.swoh.perfectwedding.domain.error.throwIf
import com.swoh.perfectwedding.persistence.relationship.HasPlan
import com.swoh.perfectwedding.security.CustomAuthority
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship

@Node("GroomBride")
class GroomBride(
    @Relationship(type = "HAS_PLAN", direction = Relationship.Direction.OUTGOING)
    val has: HasPlan,
    var type: String?,
    var uid: String,
): BaseNode() {
    val groomBrideType: GroomBrideType?
        get() = type?.let { GroomBrideType.valueOf(it) }

    val authority: CustomAuthority
        get() = if (type == null) CustomAuthority.JOINED else CustomAuthority.INFO_SUBMITTED

    fun changeType(type: GroomBrideType?) {
        throwIf(type == null, ErrorCode.GROOMBRIDE_TYPE_NOT_NULL)
        this.type = type!!.name
    }

    companion object {
        fun create(hasPlan: HasPlan, uid: String): GroomBride {
            return GroomBride(
                has = hasPlan,
                uid = uid,
                type = null,
            )
        }
    }
}