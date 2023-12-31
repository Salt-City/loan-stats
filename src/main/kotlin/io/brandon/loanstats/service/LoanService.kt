package io.brandon.loanstats.service

import arrow.core.Either
import com.univocity.parsers.csv.CsvParser
import com.univocity.parsers.csv.CsvParserSettings
import io.brandon.loanstats.error.LoanStatsError
import io.brandon.loanstats.model.FinData
import io.brandon.loanstats.model.GradeToData
import io.brandon.loanstats.model.State
import io.brandon.loanstats.model.StateToData
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.concurrent.ConcurrentHashMap

private val logger = KotlinLogging.logger {}
@Service
class LoanService {
    val csvParser = CsvParser(CsvParserSettings().apply {
        this.format.setLineSeparator("\n")
        this.ignoreLeadingWhitespaces = true
        this.nullValue = ""
    })
    companion object {
        /**
         *  Why go with an in-mem (mutable) hashmap? My vision for this application is one that
         * would service one person/company and that it would be eventually tooled to allow for
         * more dynamic GraphQL queries (ie: I can upload any type of denormalized and inconsistent csv data into
         * this and then make custom GraphQL queries to extract data however I please).
         */

        /**
         * For this exercise I envision this following an Order of Operations such as :
         * User has some sort of process where they find themselves with a large amount of csv data generated from some sort of
         * batching or other legacy setup on their side (accountants with mega-excel projects), and they want a small service that
         * just allows them to bulk dump the data somewhere and extract some specific queries and aggregations for some sort of reports
         * they want to run. This isn't some crazy setup they need and ideally the solution is something an engineer could come up with and deliver in say, a few hours?
         * 1. Load whatever csv data you want (up to 1GB)
         * 2. make however many queries you want to the data
         * 3. load some different data and rinse and repeat as often as needed
         */
        val dataMap: ConcurrentHashMap<Long, FinData> = ConcurrentHashMap()
    }

    fun loadData(f: MultipartFile): Either<LoanStatsError, String> =
        Either.catch {
            dataMap.clear()
            f.inputStream.bufferedReader().use {
                csvParser.parseAll(it).forEach { s ->
                    FinData.fromLine(s).fold(
                        ifLeft = { e -> logger.warn { "won't add line $s because of error: ${e.message}" } },
                        ifRight = { fin -> dataMap[fin.id!!] = fin }
                    )
                }
            }
        }.fold(
            ifLeft = { t -> Either.Left(LoanStatsError.CsvParseError(t.message ?: t::class.java.name)) },
            ifRight = { Either.Right("Ok") }
        )

    fun finDataById(id: Int): FinData? = dataMap[id.toLong()]
    fun finDataByState(state: State): List<FinData> = basicFilters { it.addressState == state }
    fun finDataByGrade(grade: String): List<FinData> = basicFilters { it.grade?.lowercase() == grade.lowercase() }
    fun finDataByFicoRange(min: Int, max: Int): List<FinData> = basicFilters { it.ficoRangeLow != null && it.ficoRangeLow >= min && it.ficoRangeHigh != null && it.ficoRangeHigh <= max }
    fun finDataGroupedByState(): List<StateToData> = basicGrouping { it.addressState ?: State.UNKNOWN }.map { StateToData(it.key, it.value) }
    fun finDataGroupedByGrade(): List<GradeToData> = basicGrouping { it.grade ?: "Unknown" }.map { GradeToData(it.key, it.value) }
    private fun basicFilters(f: (FinData) -> Boolean): List<FinData> = dataMap.values.filter(f)
    private fun<A> basicGrouping(f: (FinData) -> A): Map<A, List<FinData>> = dataMap.values.groupBy(f)
}