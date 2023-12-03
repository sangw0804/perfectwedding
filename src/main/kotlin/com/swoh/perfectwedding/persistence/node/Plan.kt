package com.swoh.perfectwedding.persistence.node

import org.springframework.data.neo4j.core.schema.Node

@Node
class Plan(): BaseNode() {
    companion object {
        fun create(): Plan {
            return Plan()
        }
    }
}