package com.swoh.perfectwedding.persistence.node

import org.springframework.data.neo4j.repository.Neo4jRepository
import java.util.UUID

interface GroomBrideRepository: Neo4jRepository<GroomBride, UUID> {
    fun findByUid(uid: String): GroomBride?
}