package com.example.library2kotlin.controller

import com.example.library2kotlin.exception.NoEntityException
import com.example.library2kotlin.model.dto.exception.ExceptionDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerAdvisor {
    @ExceptionHandler(NoEntityException::class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    fun handleAuthorException(ex: NoEntityException): ExceptionDTO {
        return ExceptionDTO(ex.message!!)
    }
}