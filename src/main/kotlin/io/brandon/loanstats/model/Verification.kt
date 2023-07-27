package io.brandon.loanstats.model

enum class Verification {
    NOT_VERIFIED,
    VERIFIED,
    SOURCE_VERIFIED,
    UNKNOWN;

    companion object {
        fun fromString(s: String?): Verification = when (s?.lowercase()) {
            "not verified" -> NOT_VERIFIED
            "verified" -> VERIFIED
            "source verified" -> SOURCE_VERIFIED
            else -> UNKNOWN
        }
    }
}