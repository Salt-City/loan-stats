package io.brandon.loanstats

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LoanstatsApplication

fun main(args: Array<String>) {
	runApplication<LoanstatsApplication>(*args)
}
