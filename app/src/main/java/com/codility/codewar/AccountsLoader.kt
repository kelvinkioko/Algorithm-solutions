package com.codility.codewar

fun main(args: Array<String>) {
    println("@@@ Before sorting the accounts")
    for (account in accounts) {
        println("@@@ $account")
    }

    // order by currency starting with local currency
    val firstAccountsOrder = accounts.sortedWith(compareBy { it.accountCurrency })
    println("@@@ --------------------------------------")
    println("@@@ After sorting the accounts by currency")
    for (account in firstAccountsOrder) {
        println("@@@ $account")
    }

    // order by currency starting with local currency
    val comparatorThree = ComparatorThree(localCurrency = "KES")
    val secondAccountsOrder = accounts.sortedWith(compareBy<AccountsLoader> { it.status }.then(comparatorThree))
    println("@@@ --------------------------------------")
    println("@@@ After sorting the accounts by currency")
    for (account in secondAccountsOrder) {
        println("@@@ $account")
    }

    // order by currency starting with local currency
    val thirdAccountsOrder = accounts.sortedWith(compareBy<AccountsLoader> { it.status }.then(comparatorThree).thenBy { it.accountNumber })
    println("@@@ --------------------------------------")
    println("@@@ After sorting the accounts by currency")
    for (account in thirdAccountsOrder) {
        println("@@@ $account")
    }

//    println("@@@ --------------------------------------")
//    println("@@@ Before")
//    for (account in currencies) {
//        println("@@@ $account")
//    }
//    println("@@@ After")
//    val comparatorOne = ComparatorOne(localCurrency = "")
//    val sorted = currencies.sortedWith(comparatorOne)
//    println("@@@ --------------------------------------")
//    for (account in sorted) {
//        println("@@@ $account")
//    }
//
//    println("@@@ --------------------------------------")
//    println("@@@ After")
//    val comparatorTwo = ComparatorTwo(localCurrency = "KES")
//    val sortedCurrency = dataCurrencies.sortedWith(comparatorTwo)
//    println("@@@ --------------------------------------")
//    for (account in sortedCurrency) {
//        println("@@@ $account")
//    }
}

data class Currency(
    val accountCurrency: String = ""
)

private val dataCurrencies: List<Currency> = arrayListOf(
    Currency(accountCurrency = "EUR"),
    Currency(accountCurrency = "KES"),
    Currency(accountCurrency = "USD"),
    Currency(accountCurrency = "KES"),
    Currency(accountCurrency = "GBP"),
    Currency(accountCurrency = "KES")
)

private val currencies: List<String> = arrayListOf(
    "EUR",
    "KES",
    "USD",
    "KES",
    "GBP",
    "KES"
)

// A comparator to compare first names of Name
class ComparatorOne(val localCurrency: String = ""): Comparator<String>{
    override fun compare(o1: String?, o2: String?): Int {
        if(o1 == null || o2 == null){
            return 0
        }

        if (o1 == localCurrency) {
            val resp = o1.compareTo(o2)
            return if (resp > 0) {
                0 - resp
            } else {
                resp
            }
        } else if (o2 == localCurrency) {
            val resp = o1.compareTo(o2)
            return if (resp < 0) {
                resp * -1
            } else {
                resp
            }
        } else {
            return o1.compareTo(o2)
        }
    }
}

// A comparator to compare first names of Name
class ComparatorTwo(private val localCurrency: String = ""): Comparator<Currency>{
    override fun compare(o1: Currency?, o2: Currency?): Int {
        if(o1 == null || o2 == null){
            return 0
        }

        val resp = o1.accountCurrency.compareTo(o2.accountCurrency)

        return if (o1.accountCurrency == localCurrency)
            if (resp > 0) { 0 - resp } else { resp }
        else if (o2.accountCurrency == localCurrency)
            if (resp < 0) { resp * -1 } else { resp }
        else
            resp
    }
}

// A comparator to compare first names of Name
class ComparatorThree(private val localCurrency: String = ""): Comparator<AccountsLoader>{
    override fun compare(o1: AccountsLoader?, o2: AccountsLoader?): Int {
        if(o1 == null || o2 == null){
            return 0
        }

        val resp = o1.accountCurrency.compareTo(o2.accountCurrency)

        return if (o1.accountCurrency == localCurrency)
            if (resp > 0) { 0 - resp } else { resp }
        else if (o2.accountCurrency == localCurrency)
            if (resp < 0) { resp * -1 } else { resp }
        else
            resp
    }
}

data class AccountsLoader(
    val orderID: String = "",
    val accountCurrency: String = "",
    val accountName: String = "",
    val accountNumber: String = "",
    val accountType: String = "",
    val openDate: String = "",
    val status: String = ""
)

private val accounts: List<AccountsLoader> = arrayListOf(
    AccountsLoader(orderID = "1", accountCurrency="EUR", accountName="Jack Doe", accountNumber="50526168", accountType="Savings", openDate="2021-11-19T10:48:14.6451847", status="Active"),
    AccountsLoader(orderID = "2", accountCurrency="KES", accountName="John Doe", accountNumber="18494226", accountType="Savings", openDate="2021-11-19T10:48:14.6452389", status="Active"),
    AccountsLoader(orderID = "3", accountCurrency="USD", accountName="Jane Doe", accountNumber="99296398", accountType="Savings", openDate="2021-11-19T10:48:14.6452759", status="Active"),
    AccountsLoader(orderID = "4", accountCurrency="KES", accountName="Judy Doe", accountNumber="10471118", accountType="Savings", openDate="2021-11-19T10:48:14.6453225", status="Active"),
    AccountsLoader(orderID = "5", accountCurrency="GBP", accountName="Jake Doe", accountNumber="00660630", accountType="Savings", openDate="2022-05-05T11:31:02.6031581", status="Active"),
    AccountsLoader(orderID = "6", accountCurrency="KES", accountName="Jody Doe", accountNumber="66145890", accountType="Savings", openDate="2022-05-05T11:44:04.5763822", status="InActive")
)
