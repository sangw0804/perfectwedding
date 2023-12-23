package com.swoh.perfectwedding.domain.error

fun throwIf(shouldThrow: Boolean, errorCode: ErrorCode) {
    if (shouldThrow) {
        throw DomainException(errorCode)
    }
}

class DomainException(
    val code: ErrorCode,
): RuntimeException(code.name)

enum class ErrorCode {
    GROOMBRIDE_TYPE_NOT_NULL
}