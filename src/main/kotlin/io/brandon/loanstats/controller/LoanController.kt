package io.brandon.loanstats.controller

import io.brandon.loanstats.service.LoanService
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

}