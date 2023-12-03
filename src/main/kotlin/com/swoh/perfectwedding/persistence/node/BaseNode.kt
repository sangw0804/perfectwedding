package com.swoh.perfectwedding.persistence.node

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id
import java.time.LocalDateTime
import java.util.UUID

open class BaseNode {
    @GeneratedValue @Id
    lateinit var id: UUID
    @CreatedDate
    lateinit var createdAt: LocalDateTime
    @LastModifiedDate
    lateinit var updatedAt: LocalDateTime
}