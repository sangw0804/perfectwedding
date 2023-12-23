package com.swoh.perfectwedding

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.neo4j.config.EnableNeo4jAuditing
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories

@EnableNeo4jRepositories
@EnableNeo4jAuditing
@SpringBootApplication
class PerfectweddingApplication

fun main(args: Array<String>) {
    runApplication<PerfectweddingApplication>(*args)
}
