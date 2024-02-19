package dev.hoon.deepdive.heavytraffic.flitter.domain

import com.github.f4b6a3.ulid.UlidCreator
import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PostLoad
import jakarta.persistence.PostPersist
import jakarta.persistence.Transient
import org.hibernate.annotations.Comment
import org.hibernate.proxy.HibernateProxy
import org.springframework.data.domain.Persistable
import java.util.*

@MappedSuperclass
abstract class UUIDPrimaryKey : Persistable<UUID> {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    @Comment("아이디")
    private var id: UUID = UlidCreator.getMonotonicUlid().toUuid()

    @Transient
    private var _isNew = true

    override fun getId(): UUID = id

    override fun isNew(): Boolean = _isNew

    override fun equals(other: Any?): Boolean {
        if (other == null) return false

        if (other !is HibernateProxy && this::class != other::class) return false

        return id == getIdentifier(other)
    }

    private fun getIdentifier(obj: Any): Any {
        return if (obj is HibernateProxy) {
            obj.hibernateLazyInitializer.identifier
        } else {
            (obj as UUIDPrimaryKey).id
        }
    }

    override fun hashCode(): Int = Objects.hashCode(id)

    @PostLoad
    @PostPersist
    protected fun load() {
        _isNew = false
    }

    fun setId(id: UUID?) {
        this.id = id ?: UlidCreator.getMonotonicUlid().toUuid()
        this._isNew = id == null
    }
}
