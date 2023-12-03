package com.swoh.perfectwedding.persistence.node

import org.springframework.data.neo4j.repository.Neo4jRepository
import java.util.UUID

interface PlanRepository: Neo4jRepository<Plan, UUID>