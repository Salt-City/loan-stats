package io.brandon.loanstats.error

interface LoanStatsError {
    val message: String
    data class CsvParseError(override val message: String): LoanStatsError
    data class LineParseError(override val message: String): LoanStatsError
}