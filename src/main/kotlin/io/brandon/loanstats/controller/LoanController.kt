package io.brandon.loanstats.controller

import io.brandon.loanstats.model.FinData
import io.brandon.loanstats.model.State
import io.brandon.loanstats.service.LoanService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/loanStats")
class LoanController(private val loanService: LoanService) {

    @PostMapping("/load")
    fun loadData(@RequestParam("file") f: MultipartFile): String =
        loanService.loadData(f).fold(
            ifLeft = { throw Exception(it.message) },
            ifRight = { it }
        )

    @QueryMapping
    fun finDataById(@Argument id: Int): FinData? = loanService.finDataById(id)
    @QueryMapping
    fun finDataByState(@Argument state: State) = loanService.finDataByState(state)
    @QueryMapping
    fun finDataByGrade(@Argument grade: String) = loanService.finDataByGrade(grade)
    @QueryMapping
    fun finDataByFicoRange(@Argument min: Int, @Argument max: Int) = loanService.finDataByFicoRange(min, max)
    @QueryMapping
    fun finDataGroupedByState() = loanService.finDataGroupedByState()
    @QueryMapping
    fun finDataGroupedByGrade() = loanService.finDataGroupedByGrade()

}