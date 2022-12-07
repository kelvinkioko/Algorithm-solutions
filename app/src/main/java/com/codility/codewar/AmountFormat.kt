package com.codility.codewar

import java.text.DecimalFormat

fun main(args: Array<String>) {
    println((2500.00F).toNewEditable())
}

fun Float.formatAmountText(): String {
    if (this.toString() == "0.0") return "0.00"
    val pattern = "#,###,###,###.00"
    val decFormat = DecimalFormat(pattern)
    val amt = decFormat.format(this)
    if (amt.startsWith(",") or amt.startsWith(".")) return "0$amt"
    return amt
}

fun String.formatAmount(): String {
    val pattern = "#,###,###,###.00"
    val decFormat = DecimalFormat(pattern)
    if (this.isEmpty() || this == "0.0") return "0.00"
    val amt = decFormat.format(this.toDouble())
    if (amt.startsWith(",") or amt.startsWith(".")) return "0$amt"
    return amt
}

fun Float.toEditableText(decimalPoints: Int = 4) : String = "%.${decimalPoints}f".format(this)

fun Float.toNewEditable(decimalPoints: Int = 8): String {
    if (this == 0.0f) return "0"
    val pattern = "#,###,###,###"
    val decimal = if (decimalPoints > 0) ".${"0".repeat(decimalPoints)}" else ""
    return DecimalFormat(pattern + decimal).format(this)
}
