package com.example.library2kotlin.service

import com.example.library2kotlin.exception.NoEntityException
import com.example.library2kotlin.mapper.AuthorMapper
import com.example.library2kotlin.model.dto.author.AuthorDTO
import com.example.library2kotlin.model.dto.author.AuthorListDTO
import com.example.library2kotlin.model.dto.author.AuthorUpdateDTO
import com.example.library2kotlin.model.dto.author.NewAuthorDTO
import com.example.library2kotlin.model.entity.AuthorEntity
import com.example.library2kotlin.repository.AuthorRepository
import org.springframework.stereotype.Service

@Service
class AuthorService(private val authorRepository: AuthorRepository) {
    fun getAuthorById(id: Long): AuthorDTO =
        authorRepository.findById(id).orElseThrow { NoEntityException("No author with id=$id") }
            .let { AuthorMapper.entityToDto(it) }

    fun postAuthor(newAuthorDTO: NewAuthorDTO) {
        val authorEntity: AuthorEntity = newAuthorDTO.let { AuthorMapper.postDtoToEntity(it) }
        return authorRepository.save(authorEntity).let { AuthorMapper.entityToDto(it) }
    }

    fun getAllAuthors() = AuthorListDTO(
        authorRepository.findAll().map(AuthorMapper::entityToDto)
    )

    fun deleteAuthorById(id: Long): AuthorDTO {
        val author: AuthorEntity =
            authorRepository.findById(id).orElseThrow { NoEntityException("No author with id=$id") }
        authorRepository.deleteById(id)
        return author.let { AuthorMapper.entityToDto(it) }
    }

    fun putAuthor(id: Long, authorUpdateDTO: AuthorUpdateDTO): AuthorDTO {
        val authorOld: AuthorEntity =
            authorRepository.findById(id).orElseThrow { NoEntityException("No author with id=$id") }
        val authorNew: AuthorEntity = authorUpdateDTO.let { AuthorMapper.updateDtoToEntity(it) }
        authorNew.id = id
        authorNew.books = authorOld.books
        return authorRepository.save(authorNew).let { AuthorMapper.entityToDto(it) }
    }
}

