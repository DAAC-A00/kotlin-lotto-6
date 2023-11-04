// vim DrawLottoRequest.kt

package com.example.kotlinlotto6.lotto.entities

data class DrawLottoRequest(
    val winningNumbers: List<Int>,
    val bonusNumber: Int
)
