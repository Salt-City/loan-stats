package io.brandon.loanstats.model

enum class ApplicationType {
    INDIVIDUAL,
    JOINT_APP,
    UNKNOWN;

    companion object {
        fun fromString(s: String): ApplicationType = when (s.lowercase()) {
            "individual" -> INDIVIDUAL
            "joint app" -> JOINT_APP
            else -> UNKNOWN
        }
    }
}