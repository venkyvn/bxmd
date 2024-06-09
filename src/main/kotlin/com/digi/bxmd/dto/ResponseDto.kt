package com.digi.bxmd.dto

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.io.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResponseDto(
    var transactionTime: String? = null,
    var status: String? = null,
    var data: Any? = null,
) : Serializable {

    companion object {
        fun ok(data: Any?): ResponseEntity<ResponseDto> {
            return ResponseEntity.ok(
                ResponseDto(
                    status = HttpStatus.OK.name,
                    data = data,
                    transactionTime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                )
            )
        }

        fun build(result: HttpStatus, data: Any?): ResponseEntity<ResponseDto> {
            return ResponseEntity.status(result).body(
                ResponseDto(
                    status = result.name,
                    data = data,
                    transactionTime = LocalDateTime.now().format(
                        DateTimeFormatter.ISO_LOCAL_DATE_TIME
                    )
                )
            )
        }

        fun getMessage(data: HttpStatus): String {
            return if (HttpStatus.OK == data) "SUCCESS" else "FAIL"
        }
    }
}
