package com.codility.codewar

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import java.io.Closeable
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.regex.Pattern
import kotlin.math.ceil

class PreviousTasks {
}



fun validateJoinToString(beneficiary: String?, customerName: String?) : String {
    val label = mutableListOf<String>()

    label.apply {
        add(beneficiary ?: "")
        add(customerName ?: "")
        removeAll(listOf("", null))
    }

    println("I need to understand this 1 -${label.joinToString(" • ")}-")

    return label.joinToString(" • ")
}

fun validateHVT(controlID: Boolean, oneTimeID: Boolean) : Boolean {
    println("validate HVT $controlID")
    println("validate HVT $oneTimeID")
    println("validate HVT ${controlID && oneTimeID}")
    println("validate HVT ===============================")
    return controlID && oneTimeID
}

fun validateRecurrent(controlID: Int, oneTimeID: Int) = controlID != oneTimeID

fun regexURL(dirtyUrl: String, newInstance: String) : String {
    val urlRegex = "\\w{4,5}://\\w{1,20}\\.\\w{5}\\.\\w{3}/\\w{3}/".toRegex(setOf(RegexOption.IGNORE_CASE))
    val urlWHyphenRegex = "\\w{4,5}://\\w{1,20}-\\w{1,20}\\.\\w{5}\\.\\w{3}/\\w{3}/".toRegex(setOf(RegexOption.IGNORE_CASE))
    val clearedURL =  dirtyUrl.replace(if (dirtyUrl.contains(urlRegex)) urlRegex else urlWHyphenRegex, "")
    println("Cleared URL $clearedURL")
    return clearedURL
}

fun maskAccountNumber(mask: String): String {
    val firstChars = mask.take(2)
    val lastChars = mask.takeLast(2)

    val max = (1..mask.length - 4).joinToString("") { "x" }

    return "$firstChars$max$lastChars"
}

fun testSingle() {
    val countries = mutableListOf<CountryDetails>()

    countries.add(CountryDetails(oldName = "Kenya", code = "KE"))
    countries.add(CountryDetails(oldName = "Uganda", code = "UG"))
    countries.add(CountryDetails(oldName = "Tanzania", code = "TZ"))

    var destinationCountry: CountryDetails? = null

    destinationCountry = countries.single { it.code == "SS" }

}

fun whiteSpaceTestSolution(S: String) : String {
    val ret = S.trim().replace("\\s+".toRegex(), " ")
    println("$S - $ret")
    return ret
}

fun imageSolution(S: String): String {
    val S = "photo.jpg, Warsaw, 2013-09-05 14:08:15" +
            "john.png, London, 2015-06-20 15:13:22" +
            "myFriends.png, Warsaw, 2013-09-05 14:07:13" +
            "Eiffel.jpg, Paris, 2015-07-23 08:03:02" +
            "pisatower.jpg, Paris, 2015-07-22 23:59:59" +
            "BOB.jpg, London, 2015-08-05 00:02:03" +
            "notredame.png, Paris, 2015-09-01 12:00:00" +
            "me.jpg, Warsaw, 2013-09-06 15:40:22" +
            "a.png, Warsaw, 2016-02-13 13:33:50" +
            "b.jpg, Warsaw, 2016-01-02 15:12:22" +
            "c.jpg, Warsaw, 2016-01-02 14:34:30" +
            "d.jpg, Warsaw, 2016-01-02 15:15:01" +
            "e.png, Warsaw, 2016-01-02 09:49:09" +
            "f.png, Warsaw, 2016-01-02 10:55:32" +
            "g.jpg, Warsaw, 2016-02-29 22:13:11"

    val regex = "\\w{1,20}\\.\\w{3,4}, \\w{1,20}, \\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}".toRegex(setOf(RegexOption.IGNORE_CASE))
    val splitString = regex.findAll(S)

    val splitString2 = mutableListOf<String>()
    splitString.forEach {
        splitString2.add(it.value)
    }
    val photoDetails = mutableListOf<PhotoDetails>()

    splitString2.forEach { photo ->
        val splitPhoto = photo.split(",")

        val locationList = splitString2.filter { it.split(",")[1].equals(splitPhoto[1]) }
        val locationCount = splitString2.count { it.split(",")[1].equals(splitPhoto[1]) }

        val locationPhoto = mutableListOf<LocationPhoto>()
        locationList.forEach {
            locationPhoto.add(
                LocationPhoto(
                    it.split(",")[0],
                    getDateFromString(stringDate = it.split(",")[2])
                )
            )
        }

        locationPhoto.sortBy { it.dateTime }

        var locate = 0
        for (i in locationPhoto.indices) {
            if (locationPhoto[i].equals(LocationPhoto(splitPhoto[0], getDateFromString(stringDate = splitPhoto[2])))) {
                locate = i + 1
                break
            }
        }

        val count = if (locationCount > 9 && locate <= 9) "0$locate" else if (locationCount > 9 && locate > 9) locate else locate

        photoDetails.add(
            PhotoDetails(
                oldName = splitPhoto[0].split(".")[0],
                extension = splitPhoto[0].split(".")[1],
                newName = "${splitPhoto[1]}$count.${splitPhoto[0].split(".")[1]}",
                location = splitPhoto[1],
                dateTime = getDateFromString(stringDate = splitPhoto[2])
            )
        )
    }

    val finalString = mutableListOf<String>()

    photoDetails.forEach {
        finalString.add(it.newName.trim())
    }

    return finalString.joinToString(System.getProperty("line.separator"))
}

data class CountryDetails(
    var oldName: String,
    var code: String
)

data class PhotoDetails(
    var oldName: String,
    var extension: String,
    var newName: String,
    var location: String,
    var dateTime: Date
)

data class LocationPhoto(
    var oldName: String,
    var dateTime: Date
)

fun getDateFromString(stringDate: String): Date {
    val finalFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    var date: Date? = null
    try {
        date = finalFormat.parse(stringDate)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return date!!
}

fun transactionSolution(A: IntArray, D: Array<String>) : Int {
    val totalBalance = A.sum()
    var months = 12

    var jan = 0
    var janSpending = 0
    var feb = 0
    var febSpending = 0
    var mar = 0
    var marSpending = 0
    var apr = 0
    var aprSpending = 0
    var may = 0
    var maySpending = 0
    var jun = 0
    var junSpending = 0
    var jul = 0
    var julSpending = 0
    var aug = 0
    var augSpending = 0
    var sep = 0
    var sepSpending = 0
    var oct = 0
    var octSpending = 0
    var nov = 0
    var novSpending = 0
    var dec = 0
    var decSpending = 0

    for (i in D.indices) {
        var month = D[i].split("-")
        if (A[i] < 0) {
            when {
                month[1].equals("01", ignoreCase = true) -> {
                    jan++
                    janSpending += A[i]
                    if (jan >= 3 && janSpending < -99) months--
                }
                month[1].equals("02", ignoreCase = true) -> {
                    feb++
                    febSpending += A[i]
                    if (feb >= 3 && febSpending < -99) months--
                }
                month[1].equals("03", ignoreCase = true) -> {
                    mar++
                    marSpending += A[i]
                    if (mar >= 3 && marSpending < -99) months--
                }
                month[1].equals("04", ignoreCase = true) -> {
                    apr++
                    aprSpending += A[i]
                    if (apr >= 3 && aprSpending < -99) months--
                }
                month[1].equals("05", ignoreCase = true) -> {
                    may++
                    maySpending += A[i]
                    if (may >= 3 && maySpending < -99) months--
                }
                month[1].equals("06", ignoreCase = true) -> {
                    jun++
                    junSpending += A[i]
                    if (jun >= 3 && junSpending < -99) months--
                }
                month[1].equals("07", ignoreCase = true) -> {
                    jul++
                    julSpending += A[i]
                    if (jul >= 3 && julSpending < -99) months--
                }
                month[1].equals("08", ignoreCase = true) -> {
                    aug++
                    augSpending += A[i]
                    if (aug >= 3 && augSpending < -99) months--
                }
                month[1].equals("09", ignoreCase = true) -> {
                    sep++
                    sepSpending += A[i]
                    if (sep >= 3 && sepSpending < -99) months--
                }
                month[1].equals("10", ignoreCase = true) -> {
                    oct++
                    octSpending += A[i]
                    if (oct >= 3 && octSpending < -99) months--
                }
                month[1].equals("11", ignoreCase = true) -> {
                    nov++
                    novSpending += A[i]
                    if (nov >= 3 && novSpending < -99) months--
                }
                month[1].equals("12", ignoreCase = true) -> {
                    dec++
                    decSpending += A[i]
                    if (dec >= 3 && decSpending < -99) months--
                }
            }
        }
    }

    return totalBalance - (5 * months)
}

fun stringSolution(S: String): Int {
    val fullStopArray = mutableListOf<String>()
    val questionArray = mutableListOf<String>()
    val exclamationArray = mutableListOf<String>()
    var largestNumber = 0

    if (S.isNotEmpty()) {
        when {
            S.contains(".") -> {
                fullStopArray.addAll(S.split("."))

                fullStopArray.forEach { fullStop ->
                    if (fullStop.contains("?")) {
                        questionArray.addAll(fullStop.split("?"))

                        questionArray.forEach { question ->
                            if (question.contains("!")) {
                                exclamationArray.addAll(question.split("!"))

                                exclamationArray.forEach {
                                    val strings = it.trim().split(" ").filter { it != "" }
                                    if (strings.size > largestNumber)
                                        largestNumber = strings.size
                                }
                            } else {
                                val strings = question.trim().split(" ").filter { it != "" }
                                if (strings.size > largestNumber)
                                    largestNumber = strings.size
                            }
                        }
                    } else if (fullStop.contains("!")) {
                        exclamationArray.addAll(fullStop.split("!"))

                        exclamationArray.forEach {
                            val strings = it.trim().split(" ").filter { it != "" }
                            if (strings.size > largestNumber)
                                largestNumber = strings.size
                        }
                    } else {
                        val strings = fullStop.trim().split(" ").filter { it != "" }
                        if (strings.size > largestNumber)
                            largestNumber = strings.size
                    }
                }
            }
            S.contains("?") -> {
                questionArray.addAll(S.split("?"))

                questionArray.forEach { question ->
                    if (question.contains("!")) {
                        exclamationArray.addAll(question.split("!"))

                        exclamationArray.forEach {
                            val strings = it.trim().split(" ").filter { it != "" }
                            if (strings.size > largestNumber)
                                largestNumber = strings.size
                        }
                    } else {
                        val strings = question.trim().split(" ").filter { it != "" }
                        if (strings.size > largestNumber)
                            largestNumber = strings.size
                    }
                }
            }
            S.contains("!") -> {
                exclamationArray.addAll(S.split("!"))

                exclamationArray.forEach {
                    val strings = it.trim().split(" ").filter { it != "" }
                    if (strings.size > largestNumber)
                        largestNumber = strings.size
                }
            }
            else -> {
                val strings = S.split(" ").filter { it != "" }
                if (strings.size > largestNumber)
                    largestNumber = strings.size
            }
        }
    } else {
        largestNumber = 0
    }

    return largestNumber
}

class AggregateUserDataUseCase(
    private val resolveCurrentUser: suspend () -> UserEntity,
    private val fetchUserComments: suspend (UserId) -> List<CommentEntity>,
    private val fetchSuggestedFriends: suspend (UserId) -> List<FriendEntity>
) : Closeable {

    private lateinit var userEntity: UserEntity
    private val userComments = mutableListOf<CommentEntity>()
    private val friendsList = mutableListOf<FriendEntity>()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    val scope = CoroutineScope(SupervisorJob())

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("AggregateUserDataUseCase exception $exception")
    }

    lateinit var fetchData: Job

    suspend fun aggregateDataForCurrentUser(): AggregatedData {
        val uiScope = CoroutineScope(SupervisorJob())

        coroutineScope.launch(exceptionHandler) {
            val userEntity = resolveCurrentUser.invoke()
            try {
                withTimeout(2000) {
                    userComments.addAll(fetchUserComments(userEntity.id))
                }
            } catch (e: TimeoutCancellationException) {
            }
            try {
                withTimeout(2000) {
                    friendsList.addAll(fetchSuggestedFriends(userEntity.id))
                }
            } catch (e: TimeoutCancellationException) {
            }
        }
        userEntity = resolveCurrentUser.invoke()
        fetchData = coroutineScope.async(exceptionHandler) {
            userEntity = resolveCurrentUser.invoke()
            if (::userEntity.isInitialized) {
                try {
                    withTimeout(2000) {
                        userComments.addAll(fetchUserComments(userEntity.id))
                    }
                } catch (e: TimeoutCancellationException) {
                }
                try {
                    withTimeout(2000) {
                        friendsList.addAll(fetchSuggestedFriends(userEntity.id))
                    }
                } catch (e: TimeoutCancellationException) {
                }
            }
        }

        if (::userEntity.isInitialized) {
            return AggregatedData(
                user = userEntity,
                comments = userComments,
                suggestedFriends = friendsList
            )
        } else {
            throw Exception("User exception")
        }
    }

    override fun close() {
        fetchUserComments
    }
}

data class AggregatedData(
    val user: UserEntity,
    val comments: List<CommentEntity>,
    val suggestedFriends: List<FriendEntity>
)

typealias UserId = String

data class UserEntity(val id: UserId, val name: String)

data class CommentEntity(val id: String, val content: String)

data class FriendEntity(val id: String, val name: String)




fun hexStringToRGB(hexString: String): RGB{
    return RGB(
        r = hexString.hexToString(s = 0, e = 2),
        g = hexString.hexToString(s = 2, e = 4),
        b = hexString.hexToString(s = 4, e = 6)
    )
}

private fun String.hexToString(s: Int, e: Int)
        = this.replace("#", "").substring(s, e).toInt(16)

data class RGB(
    val r: Int,
    val g: Int,
    val b: Int
)


class PaginationHelper<T>(val collection: List<T>, val itemsPerPage: Int) {

    private val fullPages = ceil(collection.size.toDouble() / itemsPerPage).toInt()
    private val pageGrouping = collection.withIndex().groupBy { it.index / itemsPerPage }.map { it.value.map { it.value } }
    /**
     * returns the number of items within the entire collection
     */
    val itemCount: Int
        get() {
            return collection.size
        }

    /**
     * returns the number of pages
     */
    val pageCount: Int
        get() {
            return fullPages
        }


    /**
     * returns the number of items on the current page. page_index is zero based.
     * this method should return -1 for pageIndex values that are out of range
     */
    fun pageItemCount(pageIndex: Int): Int {
        return if (pageIndex >= pageGrouping.size) -1 else pageGrouping[pageIndex].size
    }


    /**
     * determines what page an item is on. Zero based indexes
     * this method should return -1 for itemIndex values that are out of range
     */
    fun pageIndex(itemIndex: Int): Int {
        if (collection.isEmpty() || collection.size <= itemIndex || itemIndex < 0)
            return -1
        else {
            val item = collection[itemIndex]
            for (x in pageGrouping.indices) {
                val page = pageGrouping[x]

                if (item in page) {
                    return x
                }
            }
            return -1
        }
    }
}



fun phone(strng: String, num: String): String {
    val phoneBook = strng.lines()
    val occurrence = phoneBook.count { "+$num" in it }

    val phoneNumberRegex = "\\+\\d{1,2}-\\d{3}-\\d{3}-\\d{4}".toRegex(setOf(RegexOption.IGNORE_CASE))
    val nameRegex = "<(.+?)>".toRegex(setOf(RegexOption.IGNORE_CASE))
    val cleanRegex = "[/:;!\$*,]".toRegex(setOf(RegexOption.IGNORE_CASE))
    val spacesRegex = "\\s+".toRegex(setOf(RegexOption.IGNORE_CASE))

    return when {
        occurrence == 0 -> "Error => Not found: $num"
        occurrence > 1 -> "Error => Too many people: $num"
        else -> {
            val contact = phoneBook.find { "+$num" in it }
            val name = contact?.substring(startIndex = contact.indexOf("<") + 1, endIndex = contact.indexOf(">")) ?: ""
            val phoneMatcher = Pattern.compile("\\+\\d{1,2}-\\d{3}-\\d{3}-\\d{4}").matcher(contact ?: "")
            var phoneNumber = ""
            while (phoneMatcher.find()) { phoneNumber = phoneMatcher.group().replace("+", "") }
            val address = contact?.replace("_", " ")?.replace(phoneNumberRegex, "")?.replace(nameRegex, "")?.replace(cleanRegex, " ")?.replace(spacesRegex, " ")?.trim()
            "Phone => $phoneNumber, Name => $name, Address => $address"
        }
    }
}
