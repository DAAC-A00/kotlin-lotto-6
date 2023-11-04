// LottoService.kt
package com.example.kotlinlotto6.lotto.services

import com.example.kotlinlotto6.lotto.entities.LottoTicket
import com.example.kotlinlotto6.lotto.entities.MatchResult
import org.springframework.stereotype.Service

@Service
class LottoService {

    fun play(purchaseAmount: Int, winningNumbers: String, bonusNumber: Int): String {
        val winNums = setWinNums(winningNumbers)
        val tickets = buyTickets(purchaseAmount)
        val result: MutableList<MatchResult> = mutableListOf()
        if (winNums != null) {
            for (ticket in tickets) {
                result += match(ticket, winNums, bonusNumber)
            }
        }
        // 로또 번호 생성 및 당첨 결과 계산 로직을 작성해주세요.
        // 예외 처리도 잊지 마세요.
        return ""
    }

    private fun setWinNums(winningNumbers: String): List<Int>? {
        val stringList: List<String> = winningNumbers.split(",")
        val numbers = stringList.map { it.toIntOrNull() ?: throw NumberFormatException("$it is not a number.") }
        return if (validate(numbers)) {
            numbers
        } else {
            null
        }
    }

    private fun validate(numbers: List<Int>): Boolean {
        if (numbers.size != 6) {
            throw IllegalArgumentException("")
        } else {
            return true
        }
    }

    private fun buyTickets(purchaseAmount: Int): List<LottoTicket> {
        val ticketCount = purchaseAmount / 1000
        val tickets = mutableListOf<LottoTicket>()

        repeat(ticketCount) {
            tickets.add(LottoTicket(generateRandomNum()))
        }
        return tickets
    }

    private fun generateRandomNum(): List<Int> {
        return (1..45).shuffled().take(6).sorted()
    }

    private fun match(ticket: LottoTicket, winningNumbers: List<Int>, bonusNumber: Int): MatchResult {
        var matchCount = 0
        val isBonusMatch: Boolean
        // 당첨 결과 계산 및 수익률 계산 로직을 작성해주세요.
        // 예외 처리도 잊지 마세요.
        for (num in ticket.numbers) {
            if (winningNumbers.contains(num)) {
                matchCount++
            }
        }
        isBonusMatch = if (winningNumbers.last() == bonusNumber) {
            true
        } else {
            false
        }
        return MatchResult(
            matchCount,
            isBonusMatch
        )
    }

}