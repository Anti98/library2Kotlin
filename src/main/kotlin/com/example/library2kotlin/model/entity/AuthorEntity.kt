package com.example.library2kotlin.model.entity

import org.hibernate.Hibernate
import java.time.LocalDate
import javax.persistence.*

@Entity
data class AuthorEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long?,
    val name: String,
    val lastName: String,
    val secondName: String,
    val birthDate: LocalDate,
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author")
    var books: Set<BookEntity>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as AuthorEntity
        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}
