// LottoController.kt
package com.example.kotlinlotto6.lotto.controllers

import com.example.kotlinlotto6.lotto.services.LottoService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class LottoController(private val lottoService: LottoService) {

    @PostMapping("/lotto")
    fun playLotto(
        @RequestParam purchaseAmount: Int,
        @RequestParam winNumbers: String,
        @RequestParam bonusNumber: Int
    ): String {
        val result = lottoService.play(purchaseAmount, winNumbers, bonusNumber)
        println("구입금액을 입력해 주세요.")
        println(purchaseAmount)
        return result
    }
}