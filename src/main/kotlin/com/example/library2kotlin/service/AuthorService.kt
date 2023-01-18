package com.example.library2kotlin.service

import com.example.library2kotlin.exception.NoEntityException
import com.example.library2kotlin.mapper.AuthorMapper
import com.example.library2kotlin.model.dto.author.AuthorDTO
import com.example.library2kotlin.model.dto.author.AuthorListDTO
import com.example.library2kotlin.model.dto.author.NewAuthorDTO
import com.example.library2kotlin.repository.AuthorRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class AuthorService(val authorRepository: AuthorRepository) {
    fun getAuthorById(id: Long): AuthorDTO = AuthorMapper.entityToDto(
        authorRepository.findById(id).orElseThrow { NoEntityException("No author with that id") })

    fun postAuthor(newAuthorDTO: NewAuthorDTO) =
        AuthorMapper.entityToDto(authorRepository.save(AuthorMapper.postDtoToEntity(newAuthorDTO)))

    fun getAllAuthors() = AuthorListDTO(
        authorRepository.findAll().stream()
            .map(AuthorMapper::entityToDto)
            .collect(Collectors.toList())
    )

    fun deleteAuthorById(id: Long): AuthorDTO {
        var author = authorRepository.findById(id).orElseThrow { NoEntityException("No author with that id") }
        authorRepository.deleteById(id)
        return AuthorMapper.entityToDto(author)
    }
}

