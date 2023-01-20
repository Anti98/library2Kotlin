package com.example.library2kotlin.service

import com.example.library2kotlin.exception.NoEntityException
import com.example.library2kotlin.mapper.AuthorMapper
import com.example.library2kotlin.model.dto.author.AuthorDTO
import com.example.library2kotlin.model.dto.author.AuthorListDTO
import com.example.library2kotlin.model.dto.author.NewAuthorDTO
import com.example.library2kotlin.model.entity.AuthorEntity
import com.example.library2kotlin.repository.AuthorRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class AuthorService(private val authorRepository: AuthorRepository) {
    fun getAuthorById(id: Long): AuthorDTO =
        authorRepository.findById(id).orElseThrow { NoEntityException("No author with id=$id") }
            .let { AuthorMapper.entityToDto(it) }

    fun postAuthor(newAuthorDTO: NewAuthorDTO) =
        authorRepository.save(AuthorMapper.postDtoToEntity(newAuthorDTO)).let { AuthorMapper.entityToDto(it) }

    fun getAllAuthors() = AuthorListDTO(
        authorRepository.findAll().stream()
            .map(AuthorMapper::entityToDto)
            .collect(Collectors.toList())
    )

    fun deleteAuthorById(id: Long): AuthorDTO {
        val author = authorRepository.findById(id).orElseThrow { NoEntityException("No author with id=$id") }
        authorRepository.deleteById(id)
        return AuthorMapper.entityToDto(author)
    }

    fun putAuthor(id: Long, newAuthorDTO: NewAuthorDTO): AuthorDTO {
        val authorOld: AuthorEntity =
            authorRepository.findById(id).orElseThrow { NoEntityException("No author with id=$id") }
        val authorNew: AuthorEntity = AuthorMapper.postDtoToEntity(newAuthorDTO)
        authorNew.id = id
        authorNew.books = authorOld.books
        return AuthorMapper.entityToDto(authorRepository.save(authorNew))
    }
}

