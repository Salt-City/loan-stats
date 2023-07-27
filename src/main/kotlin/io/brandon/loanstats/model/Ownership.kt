package io.brandon.loanstats.model

enum class Ownership {
    MORTGAGE,
    RENT,
    OWN,
    UNKNOWN;

    companion object {
        fun fromString(s: String): Ownership = when (s.lowercase()) {
            "mortgage" -> MORTGAGE
            "rent" -> RENT
            "own" -> OWN
            else -> UNKNOWN
        }
    }
}