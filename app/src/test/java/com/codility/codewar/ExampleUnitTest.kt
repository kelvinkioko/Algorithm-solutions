package com.codility.codewar

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ValidateJoinToStringTest {
	private fun testing(actual: String, expected: String) {
		assertEquals(expected, actual)
	}
	val dr = ("/+1-541-754-3010 156 Alphand_St. <J Steeve>\n 133, Green, Rd. <E Kustur> NY-56423 ;+1-541-914-3010\n"
			+ "<Anastasia> +48-421-674-8974 Via Quirinal Roma\n <P Salinger> Main Street, +1-098-512-2222, Denver\n"
			+ "<Q Salinge> Main Street, +1-098-512-2222, Denve\n" + "<R Salinge> Main Street, +1-098-512-2222, Denve\n"
			+ "<C Powel> *+19-421-674-8974 Chateau des Fosses Strasbourg F-68000\n <Bernard Deltheil> +1-498-512-2222; Mount Av.  Eldorado\n")

	val dr2 = ("+1-541-754-3010 156 Alphand_St. <J Steeve>\n" +
			" 133, Green, Rd. <E Kustur> NY-56423 ;+1-541-914-3010\n" +
			"+1-541-984-3012 <P Reed> /PO Box 530; Pollocksville, NC-28573\n" +
			" :+1-321-512-2222 <Paul Dive> Sequoia Alley PQ-67209\n" +
			"+1-741-984-3090 <Peter Reedgrave> _Chicago\n" +
			" :+1-921-333-2222 <Anna Stevens> Haramburu_Street AA-67209\n" +
			"+1-111-544-8973 <Peter Pan> LA\n" +
			" +1-921-512-2222 <Wilfrid Stevens> Wild Street AA-67209\n" +
			"<Peter Gone> LA ?+1-121-544-8974 \n" +
			" <R Steell> Quora Street AB-47209 +1-481-512-2222\n" +
			"<Arthur Clarke> San Antonio \$+1-121-504-8974 TT-45120\n" +
			" <Ray Chandler> Teliman Pk. !+1-681-512-2222! AB-47209,\n" +
			"<Sophia Loren> +1-421-674-8974 Bern TP-46017\n" +
			" <Peter O'Brien> High Street +1-908-512-2222; CC-47209\n" +
			"<Anastasia> +48-421-674-8974 Via Quirinal Roma\n" +
			" <P Salinger> Main Street, +1-098-512-2222, Denver\n" +
			"<C Powel> *+19-421-674-8974 Chateau des Fosses Strasbourg F-68000\n" +
			" <Bernard Deltheil> +1-498-512-2222; Mount Av.  Eldorado\n" +
			"+1-099-500-8000 <Peter Crush> Labrador Bd.\n" +
			" +1-931-512-4855 <William Saurin> Bison Street CQ-23071\n" +
			"<P Salinge> Main Street, +1-098-512-2222, Denve\n" +
			"<P Salinge> Main Street, +1-098-512-2222, Denve\n" +
			"/+5-541-754-3010 156 Alphandria_Street. <Jr Part>\n" +
			" 1333, Green, Road <F Fulgur> NW-46423 ;+6-541-914-3010!\n" +
			"+5-541-984-3012 <Peter Reeves> /PO Box 5300; Albertville, SC-28573\n" +
			" :+5-321-512-2222 <Paulo Divino> Boulder Alley ZQ-87209\n" +
			"+3-741-984-3090 <F Flanaghan> _Chicago Av.\n" +
			" :+3-921-333-2222 <Roland Scorsini> Bellevue_Street DA-67209\n" +
			"+8-111-544-8973 <Laurence Pantow> SA\n" +
			" +8-921-512-2222 <Raymond Stevenson> Joly Street EE-67209\n" +
			"<John Freeland> Mantow ?+2-121-544-8974 \n" +
			" <Robert Mitch> Eleonore Street QB-87209 +2-481-512-2222?\n" +
			"<Arthur Paternos> San Antonio \$+7-121-504-8974 TT-45121\n" +
			" <Ray Charles> Stevenson Pk. !+7-681-512-2222! CB-47209,\n" +
			"<JP Gorce> +9-421-674-8974 New-Bern TP-16017\n" +
			" <P McDon> Revolution Street +2-908-512-2222; PP-47209\n" +
			"<Elizabeth Corber> +8-421-674-8974 Via Papa Roma\n" +
			" <C Saborn> Main Street, +15-098-512-2222, Boulder\n" +
			"<Colin Marshall> *+9-421-674-8974 Edinburgh UK\n" +
			" <Bernard Povit> +3-498-512-2222; Hill Av.  Cameron\n" +
			"+12-099-500-8000 <Pete Highman> Ontario Bd.\n" +
			" +8-931-512-4855 <W Mount> Oxford Street CQ-23071\n" +
			"<Donald Drinkaw> Moon Street, +3-098-512-2222, Peterville")
	@Test
	fun test1() {
		testing(
			phone(dr, "48-421-674-8974"),
			"Phone => 48-421-674-8974, Name => Anastasia, Address => Via Quirinal Roma"
		)
		testing(
			phone(dr, "19-421-674-8974"),
			"Phone => 19-421-674-8974, Name => C Powel, Address => Chateau des Fosses Strasbourg F-68000"
		)
		testing(phone(dr, "1-098-512-2222"), "Error => Too many people: 1-098-512-2222")
		testing(phone(dr, "5-555-555-5555"), "Error => Not found: 5-555-555-5555")

	}

	@Test
	fun test2() {
		testing(
			phone(dr2, "8-421-674-8974"),
			"Phone => 8-421-674-8974, Name => Elizabeth Corber, Address => Via Papa Roma"
		)
		testing(
			phone(dr2, "1-541-754-3010"),
			"Phone => 1-541-754-3010, Name => J Steeve, Address => 156 Alphand_St."
		)
		testing(
			phone(dr2, "1-741-984-3090"),
			"Phone => 1-741-984-3090, Name => Peter Reedgrave, Address => Chicago"
		)
	}

	@Test
	fun testItemCount() {
		val helper = PaginationHelper<Int>(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24), 10)
		assertEquals(11, helper.itemCount)
	}

	@Test
	fun testPageCount() {
		val helper = PaginationHelper<Int>(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24), 10)
		assertEquals(2, helper.pageCount)
	}

	@Test
	fun testPageItemCount() {
		val helper = PaginationHelper<Int>(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24), 10)
		assertEquals(-1, helper.pageItemCount(3))
	}

	@Test
	fun testPageIndex() {
		val helper = PaginationHelper<Int>(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24), 10)
		assertEquals(-1, helper.pageIndex(-1))
	}

	@Test
	fun addition_isCorrect() {
		assertEquals("", validateJoinToString(beneficiary = null, customerName = null))
		assertEquals("", validateJoinToString(beneficiary = "", customerName = ""))
		assertEquals("14106445001", validateJoinToString(beneficiary = "14106445001", customerName = null))
		assertEquals("14106445001", validateJoinToString(beneficiary = "14106445001", customerName = ""))
		assertEquals("Kelvin Kioko", validateJoinToString(beneficiary = null, customerName = "Kelvin Kioko"))
		assertEquals("Kelvin Kioko", validateJoinToString(beneficiary = "", customerName = "Kelvin Kioko"))
		assertEquals("14106445001 • Kelvin Kioko", validateJoinToString(beneficiary = "14106445001", customerName = "Kelvin Kioko"))
	}

	@Test
	fun `validate the user details`() {
		assertEquals("", validateJoinToString(beneficiary = null, customerName = null))
	}

	@Test
	fun `convert Hex to RGB`() {
		assertEquals(RGB(r = 255, g = 153, b = 51), hexStringToRGB("#FF9933"))
		assertEquals(RGB(r = 190, g = 173, b = 237), hexStringToRGB("#beaded"))
		assertEquals(RGB(r = 0, g = 0, b = 0), hexStringToRGB("#000000"))
		assertEquals(RGB(r = 17, g = 17, b = 17), hexStringToRGB("#111111"))
		assertEquals(RGB(r = 250, g = 52, b = 86), hexStringToRGB("#Fa3456"))
	}
}

class ValidateHVTTest {
	@Test
	fun addition_isCorrect() {
		assertEquals(false, validateHVT(controlID = false, oneTimeID = false))
		assertEquals(false, validateHVT(controlID = true, oneTimeID = false))
		assertEquals(true, validateHVT(controlID = true, oneTimeID = true))
	}
}

class TestRanges {
	@Test
	fun validateOpeningAmountLogic() {
		assertEquals(false, validateOpeningAmount(amount = 23000.0f, minimumAmount = 25000.0f, maximumAmount = 1000000.0f))
		assertEquals(true, validateOpeningAmount(amount = 25000.0f, minimumAmount = 25000.0f, maximumAmount = 1000000.0f))
		assertEquals(true, validateOpeningAmount(amount = 1000000.0f, minimumAmount = 25000.0f, maximumAmount = 1000000.0f))
		assertEquals(true, validateOpeningAmount(amount = 50000.0f, minimumAmount = 25000.0f, maximumAmount = 1000000.0f))
//		assertEquals(true, validateRecurrent(controlID = 2, oneTimeID = 1))
//		assertEquals(true, validateRecurrent(controlID = 3, oneTimeID = 1))
//		assertEquals(true, validateRecurrent(controlID = 4, oneTimeID = 1))
	}
}

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