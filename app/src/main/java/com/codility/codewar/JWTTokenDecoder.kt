package com.codility.codewar

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import android.util.Base64 as AndroidBase64
import java.util.Base64 as JavaBase64

fun main(args: Array<String>) {
    val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiMjQ4IiwiY3VzdG9tZXJfbm8iOiI2IiwiY3VzdG9tZXJfbmFtZSI6IkEtWiBNVVJFUkEiLCJzYWxlc19hcmVhcyI6W3siY29kZSI6IjUwMiIsImFyZWEiOiIgTElNVVJVICJ9XSwiY3JlZGl0X2FyZWFzIjpbXSwidXNlcl90eXBlIjoiVVNFUiIsInVzZXJuYW1lIjoiNiIsInBpbiI6IjAiLCJmaXJzdF9uYW1lIjoiQWRtaW4iLCJsYXN0X25hbWUiOiJBZG1pbiIsImVtYWlsIjoiYWRtaW5AYWRtaW4uY29tIiwibW9iaWxlbm8iOiItIiwidXNlcl9zdGF0dXMiOiJBY3RpdmUiLCJleHBpcmF0aW9uIjoxNjc0NjQ2ODg0LCJpc3N1ZV90aW1lIjoxNjc0NjQwODg0LCJpc3N1ZWRfYnkiOiJQUkVTSEFNQSJ9.bD60IZ0PfOtrpEP0I377NHPwcoFw4SYTOrZrNXUwAzQ"

    val ceck = decodeToken(token = token)
}

private fun decodeToken(token: String): Any {
    val parts = token.split(".")

    val header = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        javaBase64Decoder(content = parts[0])
    else
        androidBaseDecoder(content = parts[0])

    val payload = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        javaBase64Decoder(content = parts[1])
    else
        androidBaseDecoder(content = parts[1])

    return try {
        Gson().fromJson(payload, TokenPayloadModel::class.java)
    } catch (e: Exception) {
        "Error parsing JWT: $e"
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun javaBase64Decoder(content: String): String {
    val charset = charset("UTF-8")
    return String(JavaBase64.getUrlDecoder().decode(content.toByteArray(charset)), charset)
}

private fun androidBaseDecoder(content: String): String {
    var decodedString: String
    runBlocking {
        decodedString = AndroidBase64.decode(content, AndroidBase64.DEFAULT).decodeToString()
    }
    return decodedString
}

data class TokenPayloadModel(
    val user_id: String,
    val customer_no: String,
    val customer_name: String,
    val sales_areas: List<SalesAreaModel> = emptyList(),
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

data class SalesAreaModel(
    val code: String,
    val area: String
)
