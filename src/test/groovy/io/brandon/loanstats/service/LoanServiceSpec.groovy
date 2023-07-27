package io.brandon.loanstats.service

import io.brandon.loanstats.model.ApplicationType
import io.brandon.loanstats.model.LoanStatus
import org.springframework.mock.web.MockMultipartFile
import spock.lang.Specification

/**
 * pretty pathetic test coverage here, but I ran out of time and was having a lot of fun messing with the GraphQL stuff.
 * This test did come in handy making sure that I was parsing things correctly. There are A LOT of tests that would be needed
 * here to handle all the edge cases of such unreliable data input though
 */
class LoanServiceSpec extends Specification {

    def "ValidateLoanServiceLoad"() {
        given:
        LoanService loanService = new LoanService()
        def aLine = "\"119883740\",\"\",\"5200\",\"5200\",\"5200\",\" 36 months\",\" 11.99%\",\"172.69\",\"B\",\"B5\",\"Hair stylist\",\"10+ years\",\"RENT\",\"19772\",\"Not Verified\",\"Oct-2017\",\"Current\",\"n\",\"https://lendingclub.com/browse/loanDetail.action?loan_id=119883740\",\"\",\"credit_card\",\"Credit card refinancing\",\"080xx\",\"NJ\",\"30.12\",\"0\",\"Jul-2001\",\"715\",\"719\",\"0\",\"\",\"\",\"7\",\"0\",\"18509\",\"64.9%\",\"14\",\"w\",\"1479.30\",\"1479.30\",\"4641.86\",\"4641.86\",\"3720.70\",\"921.16\",\"0.0\",\"0.0\",\"0.0\",\"Dec-2019\",\"172.69\",\"Jan-2020\",\"Nov-2019\",\"739\",\"735\",\"0\",\"\",\"1\",\"Individual\",\"\",\"\",\"\",\"0\",\"0\",\"18509\",\"0\",\"0\",\"0\",\"0\",\"76\",\"0\",\"\",\"0\",\"0\",\"8991\",\"65\",\"28500\",\"0\",\"0\",\"0\",\"0\",\"3702\",\"8510\",\"68.4\",\"0\",\"0\",\"172\",\"195\",\"87\",\"76\",\"0\",\"95\",\"\",\"\",\"\",\"0\",\"4\",\"5\",\"4\",\"4\",\"5\",\"7\",\"9\",\"5\",\"7\",\"0\",\"0\",\"0\",\"0\",\"100\",\"50\",\"0\",\"0\",\"28500\",\"18509\",\"26900\",\"0\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"N\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"N\",\"\",\"\",\"\",\"\",\"\",\"\""
        def tempCsv = File.createTempFile("loanstats", ".csv")
        tempCsv.write(aLine)
        def multi = new MockMultipartFile("file", tempCsv.getBytes())

        when: "The loan service parses the lines "
        def res = loanService.loadData(multi)

        then: "The result is a Right and the content matches the original string"
        res.isRight()

        def value = LoanService.@Companion.dataMap[119883740 as Long]
        value != null
        value.loanStatus == LoanStatus.CURRENT
        value.term == " 36 months"
        value.intRate == 11.99 as Float
        value.grade == "B"
        value.applicationType == ApplicationType.INDIVIDUAL
    }
}
