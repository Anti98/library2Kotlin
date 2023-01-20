package com.example.library2kotlin.controller

import com.example.library2kotlin.model.dto.author.AuthorDTO
import com.example.library2kotlin.model.dto.author.AuthorListDTO
import com.example.library2kotlin.model.dto.author.NewAuthorDTO
import com.example.library2kotlin.service.AuthorService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/author")
@Tag(name = "Авторы", description = "API для работы с авторами")
class AuthorController(private val authorService: AuthorService) {
    @GetMapping("/{id}")
    @Operation(summary = "Найти автора", description = "Найти автора по id")
    fun getAuthor(@PathVariable id: Long) = authorService.getAuthorById(id)

    @PostMapping
    @Operation(summary = "Добавить автора", description = "Добавить автора")
    fun postAuthor(@RequestBody newAuthorDTO: NewAuthorDTO): AuthorDTO = authorService.postAuthor(newAuthorDTO)

    @GetMapping
    @Operation(summary = "Найти всех авторов", description = "Найти всех авторов")
    fun getAllAuthors(): AuthorListDTO = authorService.getAllAuthors()

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить автора", description = "Удалить автора по id")
    fun deleteById(@PathVariable id: Long): AuthorDTO = authorService.deleteAuthorById(id)

    @PutMapping("/{id}")
    @Operation(summary = "Обновить автора", description = "Обновить автора по id")
    fun updateById(@PathVariable id: Long, @RequestBody newAuthorDTO: NewAuthorDTO): AuthorDTO =
        authorService.putAuthor(id, newAuthorDTO)
}