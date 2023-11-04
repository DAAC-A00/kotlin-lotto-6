// LottoControllerTest.kt

package com.example.kotlinlotto6

import com.example.kotlinlotto6.lotto.services.LottoService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class LottoControllerTest(@Autowired val mockMvc: MockMvc) {

	@MockBean
	private lateinit var lottoService: LottoService

	@Test
	fun `test play lotto`() {
		val purchaseAmount = 1000
		val winNumbers = "1,2,3,4,5,6"
		val bonusNumber = 7
		val expected = "test result"

		`when`(lottoService.play(purchaseAmount, winNumbers, bonusNumber)).thenReturn(expected)

		mockMvc.perform(post("/api/v1/lotto")
			.param("purchaseAmount", purchaseAmount.toString())
			.param("winNumbers", winNumbers)
			.param("bonusNumber", bonusNumber.toString())
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk)

		verify(lottoService, times(1)).play(purchaseAmount, winNumbers, bonusNumber)
	}
}
