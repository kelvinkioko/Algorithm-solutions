package com.codility.codewar

import android.os.Build
import com.auth0.android.jwt.JWT
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import android.util.Base64 as AndroidBase64
import java.util.Base64 as JavaBase64

fun main(args: Array<String>) {
    val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiMjQ4IiwiY3VzdG9tZXJfbm8iOiI2IiwiY3VzdG9tZXJfbmFtZSI6IkEtWiBNVVJFUkEiLCJzYWxlc19hcmVhcyI6W3siY29kZSI6IjUwMiIsImFyZWEiOiIgTElNVVJVICJ9XSwiY3JlZGl0X2FyZWFzIjpbXSwidXNlcl90eXBlIjoiVVNFUiIsInVzZXJuYW1lIjoiNiIsInBpbiI6IjAiLCJmaXJzdF9uYW1lIjoiQWRtaW4iLCJsYXN0X25hbWUiOiJBZG1pbiIsImVtYWlsIjoiYWRtaW5AYWRtaW4uY29tIiwibW9iaWxlbm8iOiItIiwidXNlcl9zdGF0dXMiOiJBY3RpdmUiLCJleHBpcmF0aW9uIjoxNjc0NjQ2ODg0LCJpc3N1ZV90aW1lIjoxNjc0NjQwODg0LCJpc3N1ZWRfYnkiOiJQUkVTSEFNQSJ9.bD60IZ0PfOtrpEP0I377NHPwcoFw4SYTOrZrNXUwAzQ"

//    val ceck = decodeToken(jwt = token)
    randomTest(bodyJWT = token)

//    println("@@@ Check: $ceck")
}

private fun decodeToken(jwt: String): String {
    val parts = jwt.split(".")
    val charset = charset("UTF-8")

    val header = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        String(JavaBase64.getUrlDecoder().decode(parts[0].toByteArray(charset)), charset)
    } else {
        androidBase(bodyJWT = parts[0])
    }

    val payload = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        String(JavaBase64.getUrlDecoder().decode(parts[1].toByteArray(charset)), charset)
    } else {
        AndroidBase64.decode(parts[0], AndroidBase64.DEFAULT).decodeToString()
    }

    println("@@@ Header: $header")
    println("@@@ Payload: $payload")
    val load: TokenPayload = Gson().fromJson(payload, TokenPayload::class.java)
    println("@@@ Data class: $load")
    return try {
        payload
    } catch (e: Exception) {
        "Error parsing JWT: $e"
    }
}

private fun androidBase(bodyJWT: String): String {
    var content = ""
    runBlocking {
        content = AndroidBase64.decode(bodyJWT, AndroidBase64.DEFAULT).decodeToString()
    }
    return content
}

private fun randomTest(bodyJWT: String) {
    val jwt = JWT(bodyJWT)
    println("@@@ ${jwt.header}")
}

data class TokenPayload(
    val user_id: String,
    val customer_no: String,
    val customer_name: String,
    val sales_areas: List<SalesArea> = emptyList(),
    val credit_areas: List<Any> = emptyList(),
    val user_type: String,
    val username: String,
    val pin: String,
    val first_name: String,
    val last_name: String,
    val email: String,
    val mobileno: String,
    val user_status: String,
    val expiration: String,
    val issue_time: String,
    val issued_by: String
)

data class SalesArea(
    val code: String,
    val area: String
)
