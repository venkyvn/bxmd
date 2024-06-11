package com.digi.bxmd.exception


import com.digi.bxmd.constant.MessageKey
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.dao.ConcurrencyFailureException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.AuthenticationException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolationException

@RestControllerAdvice
@Slf4j
class RestExceptionHandler {

    companion object {
        private const val SPRING_VALIDATOR_ADAPTER = "SpringValidatorAdapter\$ResolvableAttribute"
    }

    @Autowired
    private lateinit var messageSource: MessageSource

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessError(request: HttpServletRequest, e: BusinessException): ResponseEntity<Any> {
        val exInfo = ErrorInfo(
            HttpStatus.BAD_REQUEST.value(),
            e.message,
            messageSource.getMessage(e.message, e.args, Locale.getDefault()),
            null
        )
        return ResponseEntity(exInfo, HttpHeaders(), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleGlobalError(request: HttpServletRequest, ex: Exception): ResponseEntity<Any> {
//        log.error("Exception occurred : ${request.requestURL}", ex)
        val errorInfo = ErrorInfo(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            MessageKey.SERVER_ERROR,
            messageSource.getMessage(MessageKey.SERVER_ERROR, null, Locale.getDefault()),
            null
        )
        return ResponseEntity(errorInfo, HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(ConcurrencyFailureException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleConcurrencyError(ex: ConcurrencyFailureException, request: HttpServletRequest): ResponseEntity<Any> {
//        log.error("ConcurrencyFailureException occurred : ${request.requestURL}", ex)
        val errorInfo = ErrorInfo(
            HttpStatus.CONFLICT.value(),
            MessageKey.ALREADY_UPDATE_MESSAGE,
            messageSource.getMessage(MessageKey.ALREADY_UPDATE_MESSAGE, null, Locale.getDefault()),
            null
        )
        return ResponseEntity(errorInfo, HttpHeaders(), HttpStatus.CONFLICT)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleValidationError(request: HttpServletRequest, e: IllegalArgumentException): ErrorInfo {
        if (e.cause != null && e.cause!!.cause is BusinessException) {
            val be = e.cause!!.cause as BusinessException
//            log.error("BusinessException occurred : ${request.requestURL}", be)
            return ErrorInfo(
                HttpStatus.BAD_REQUEST.value(),
                be.message,
                messageSource.getMessage(be.message, be.args, Locale.getDefault()),
                null
            )
        }
//        log.error("Exception occurred : ${request.requestURL}", e)
        return ErrorInfo(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            MessageKey.SERVER_ERROR,
            messageSource.getMessage(MessageKey.SERVER_ERROR, null, Locale.getDefault()),
            null
        )
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationError(request: HttpServletRequest, e: ConstraintViolationException): ErrorInfo {
        if (e.cause != null && e.cause!!.cause is BusinessException) {
            val be = e.cause!!.cause as BusinessException
//            log.error("ConstraintViolationException occurred : ${request.requestURL}", be)
            return ErrorInfo(
                HttpStatus.BAD_REQUEST.value(),
                be.message,
                messageSource.getMessage(be.message, be.args, Locale.getDefault()),
                null
            )
        }
//        log.error("ConstraintViolationException occurred : ${request.requestURL}", e)
        return ErrorInfo(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            MessageKey.SERVER_ERROR,
            messageSource.getMessage(MessageKey.SERVER_ERROR, null, Locale.getDefault()),
            null
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationError(ex: MethodArgumentNotValidException): ErrorInfo {
        val result = ex.bindingResult
        val fieldErrors = result.fieldErrors
        return handleFieldErrors(fieldErrors)
    }

    @ExceptionHandler(AuthenticationException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleUnAuthorizedError(ex: AuthenticationException, request: HttpServletRequest): ResponseEntity<Any> {
//        log.error("AuthenticationException occurred : ${request.requestURL}", ex)
        val errorInfo = ErrorInfo(
            HttpStatus.UNAUTHORIZED.value(),
            MessageKey.UNAUTHORIZED,
            messageSource.getMessage(MessageKey.UNAUTHORIZED, null, Locale.getDefault()),
            null
        )
        return ResponseEntity(errorInfo, HttpHeaders(), HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(AccessDeniedException::class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    fun handleAccessDeniedError(ex: AccessDeniedException, request: HttpServletRequest): ResponseEntity<Any> {
//        log.error("AccessDeniedException occurred : ${request.requestURL}", ex)
        val errorInfo = ErrorInfo(
            HttpStatus.FORBIDDEN.value(),
            MessageKey.FORBIDDEN,
            messageSource.getMessage(MessageKey.FORBIDDEN, null, Locale.getDefault()),
            null
        )
        return ResponseEntity(errorInfo, HttpHeaders(), HttpStatus.FORBIDDEN)
    }

    @ExceptionHandler(BadCredentialsException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBadCredentialsError(ex: BadCredentialsException, request: HttpServletRequest): ResponseEntity<Any> {
//        log.error("BadCredentialsException occurred : ${request.requestURL}", ex)
        val errorInfo = ErrorInfo(
            HttpStatus.BAD_REQUEST.value(),
            MessageKey.BAD_CREDENTIAL,
            messageSource.getMessage(MessageKey.BAD_CREDENTIAL, null, Locale.getDefault()),
            null
        )
        return ResponseEntity(errorInfo, HttpHeaders(), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleHttpMessageNotReadableException(
        request: HttpServletRequest,
        ex: HttpMessageNotReadableException,
    ): ResponseEntity<Any> {
//        log.error("HttpMessageNotReadableException : ${request.requestURL}", ex)
        val errorInfo = ErrorInfo(HttpStatus.BAD_REQUEST.value(), null, ex.message, null)
        return ResponseEntity(errorInfo, HttpStatus.BAD_REQUEST)
    }

    private fun handleFieldErrors(fieldErrors: List<FieldError>): ErrorInfo {
        val error = ErrorInfo()
        val validationsError = mutableListOf<FieldValidation>()
        for (fieldError in fieldErrors) {
            val fieldValidation = FieldValidation().apply {
                field = fieldError.field
                messageKey = fieldError.defaultMessage
                message = messageSource.getMessage(fieldError.defaultMessage?:"", getArgsVariable(fieldError.arguments), Locale.getDefault())
            }
            validationsError.add(fieldValidation)
        }
        error.apply {
            httpStatus = HttpStatus.BAD_REQUEST.value()
            messageKey = MessageKey.VALIDATION_ERR
            message = messageSource.getMessage(MessageKey.VALIDATION_ERR, null, Locale.getDefault())
            fieldError = validationsError
        }
        return error
    }

    private fun getArgsVariable(args: Array<Any>?): Array<Any>? {
        if (args == null || args.size <= 1) {
            return null
        }

        val argsReturn = getPattern(args)
        if (argsReturn != null) {
            return argsReturn
        }

        val list = mutableListOf<Any>()
        for (i in args.indices.reversed()) {
            if (args[i].toString() == "0") {
                continue
            }
            list.add(args[i])
        }
        return if (list.isNotEmpty()) list.toTypedArray() else null
    }

    private fun getPattern(args: Array<Any>): Array<Any>? {
        for (i in args.indices.reversed()) {
            if (args[i].javaClass.name.contains(SPRING_VALIDATOR_ADAPTER)) {
                return arrayOf(args[i])
            }
        }
        return null
    }

    @ExceptionHandler(UnauthorizedException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleUnauthorizedExceptionError(ex: UnauthorizedException, request: HttpServletRequest): ResponseEntity<Any> {
//        log.error("UnauthorizedException occurred : ${request.requestURL}", ex)
        val errorInfo = ErrorInfo(
            HttpStatus.UNAUTHORIZED.value(),
            MessageKey.UNAUTHORIZED,
            messageSource.getMessage(MessageKey.UNAUTHORIZED, null, Locale.getDefault()),
            null
        )
        return ResponseEntity(errorInfo, HttpHeaders(), HttpStatus.UNAUTHORIZED)
    }
}
