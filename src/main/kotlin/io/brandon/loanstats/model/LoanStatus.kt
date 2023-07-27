package io.brandon.loanstats.model

enum class LoanStatus {
    FULLY_PAID,
    CHARGED_OFF,
    CURRENT,
    UNKNOWN;

    companion object {
        fun fromString(s: String): LoanStatus = when (s.lowercase()) {
            "fully paid" -> FULLY_PAID
            "charged off" -> CHARGED_OFF
            "current" -> CURRENT
            else -> UNKNOWN
        }
    }
}