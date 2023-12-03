package com.swoh.perfectwedding.persistence.relationship

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id
import java.time.LocalDateTime

open class BaseRelationship {
    @GeneratedValue @Id
    var id: Long? = null
    @CreatedDate
    lateinit var createdAt: LocalDateTime
    @LastModifiedDate
    lateinit var updatedAt: LocalDateTime
}