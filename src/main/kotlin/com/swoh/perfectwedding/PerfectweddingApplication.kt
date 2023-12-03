package com.swoh.perfectwedding

import com.swoh.perfectwedding.domain.GroomBrideType
import com.swoh.perfectwedding.usecase.JoinUsecase
import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.neo4j.config.EnableNeo4jAuditing
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories

@EnableNeo4jRepositories
@EnableNeo4jAuditing
@SpringBootApplication
class PerfectweddingApplication(
    private val joinUsecase: JoinUsecase,
) {
    @PostConstruct
    fun test() {
        joinUsecase.join(uid = "test1", type = GroomBrideType.GROOM)
    }
}

fun main(args: Array<String>) {
    runApplication<PerfectweddingApplication>(*args)
}
