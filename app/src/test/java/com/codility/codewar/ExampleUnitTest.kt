package com.codility.codewar

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ValidateRecurrentTest {
	@Test
	fun addition_isCorrect() {
		assertEquals(false, validateRecurrent(controlID = 1, oneTimeID = 1))
		assertEquals(true, validateRecurrent(controlID = 2, oneTimeID = 1))
		assertEquals(true, validateRecurrent(controlID = 3, oneTimeID = 1))
		assertEquals(true, validateRecurrent(controlID = 4, oneTimeID = 1))
	}
}

class RegexURLTest {
	@Test
	fun addition_isCorrect() {
		assertEquals(
			"sync_service_data",
			regexURL(dirtyUrl = "http://acdivoca.poola.org/api/sync_service_data", newInstance = "")
		)
		assertEquals(
			"sync_service_data",
			regexURL(dirtyUrl = "http://acdivoca-test.poola.org/api/sync_service_data", newInstance = "")
		)
	}
}

class WhiteSpaceTest {
	@Test
	fun addition_isCorrect() {
		assertEquals("Airtel Escrow", whiteSpaceTestSolution(S = "Airtel  Escrow"))
		assertEquals("Airtel Escrow", whiteSpaceTestSolution(S = "Airtel   Escrow"))
		assertEquals("Airtel Escrow", whiteSpaceTestSolution(S = "Airtel    Escrow"))
		assertEquals("Airtel Escrow", whiteSpaceTestSolution(S = "Airtel    Escrow "))
		assertEquals("Airtel Escrow", whiteSpaceTestSolution(S = " Airtel    Escrow"))
	}
}

class MaskingTest {
	@Test
	fun addition_isCorrect() {
		assertEquals("01xxxxxxx03", maskAccountNumber(mask = "01080530003"))
		assertEquals("10xxxxxx08", maskAccountNumber(mask = "1097580008"))
		assertEquals("10xxxxxxxxx00", maskAccountNumber(mask = "1030002341100"))
	}
}

class NumberUnitTest {
	@Test
	fun addition_isCorrect() {
		assertEquals(4, solution(A = intArrayOf(1, 2, 3)))
		assertEquals(1, solution(A = intArrayOf(-1, -3)))
		assertEquals(5, solution(A = intArrayOf(1, 3, 6, 4, 1, 2)))
	}
}

class TransactionUnitTest {
	@Test
	fun addition_isCorrect() {
		assertEquals(230, transactionSolution(A = intArrayOf(100, 100, 100, -10), D = arrayOf("2020-12-22", "2020-12-22", "2020-12-03", "2020-12-29")))
		assertEquals(25, transactionSolution(A = intArrayOf(180, -50, -25, -25), D = arrayOf("2020-01-01", "2020-01-01", "2020-01-01", "2020-01-31")))
	}
}

class StringUnitTest {
	@Test
	fun addition_isCorrect() {
		assertEquals(4, stringSolution(S = "We test coders. Give us a try?"))
		assertEquals(2, stringSolution(S = "Forget  CVs..Save time . x x"))
	}
}

//

class ExampleUnitTest {
	@Test
	fun addition_isCorrect() {
		assertEquals(4, 2 + 2)
	}
}

class ProdFibTest {
	@Test
	fun test1() {
		val r = longArrayOf(55, 89, 1)
		assertArrayEquals(r, productFib(4895))
	}
	@Test
	fun test2() {
		val r = longArrayOf(89, 144, 0)
		assertArrayEquals(r, productFib(5895))
	}

}

class  BuyCarTest {

	private fun testing(startPriceOld: Int, startPriceNew: Int, savingperMonth: Int, percentLossByMonth: Double, expect: Pair<Int, Int>) {
		val actual = Backup.BuyCar.nbMonths(startPriceOld, startPriceNew, savingperMonth, percentLossByMonth)
		assertEquals(expect, actual)
	}
	@Test
	fun fixedTests() {
		testing(2000, 8000, 1000, 1.5, Pair(6, 766))
		testing(12000, 8000, 1000, 1.5 , Pair(0, 4000))
		testing(8000, 12000, 500, 1.0, Pair(8, 597))
		testing(18000, 32000, 1500, 1.25, Pair(8, 332))
		testing(7500, 32000, 300, 1.55, Pair(25, 122))

	}

}

class  StockListTest {
	private fun testing(lstOfArt: Array<String>, lstOfCat: Array<String>, expect: String) {
		val actual: String = Backup.StockList.stockSummary(lstOfArt, lstOfCat)
		assertEquals(expect, actual)
	}

	@Test
	fun basicTests() {
		var b = arrayOf("BBAR 150", "CDXE 515", "BKWR 250", "BTSQ 890", "DRTY 600")
		var c = arrayOf("A", "B", "C", "D")
		var res = "(A : 0) - (B : 1290) - (C : 515) - (D : 600)"
		testing(b, c, res)

		b = arrayOf("ABAR 200", "CDXE 500", "BKWR 250", "BTSQ 890", "DRTY 600")
		c = arrayOf("A", "B")
		res = "(A : 200) - (B : 1140)"
		testing(b, c, res)
	}
}

class TestExample {
	@Test
	fun testFixed() {
		val a2 = arrayOf("lively", "alive", "harp", "sharp", "armstrong")
		val a3 = arrayOf("12951295", "ode", "46", "10281066", "par")
		assertArrayEquals(arrayOf("live", "strong"), Backup().inArray(arrayOf("xyz", "live", "strong"), a2))
		assertArrayEquals(arrayOf("arp", "live", "strong"), Backup().inArray(arrayOf("live", "strong", "arp"), a2))
		assertArrayEquals(arrayOf<String>(), Backup().inArray(arrayOf("tarp", "mice", "bull"), a2))
		assertArrayEquals(arrayOf("1028", "1295", "ar"), Backup().inArray(arrayOf("1295", "code", "1346", "1028", "ar"), a3))
	}
}