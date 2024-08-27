package com.example.specification_arg_resolver_demo.shared.entity


import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.AUTO
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Version
import org.hibernate.Hibernate
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.io.Serializable
import java.time.LocalDateTime
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty1

@MappedSuperclass
abstract class AbstractIdEntity<T : Serializable> {
    abstract val id: T?

    @Version
    var version: Int = 0

    @CreationTimestamp
    val createdAt: LocalDateTime? = null

    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false

        return id == (other as? AbstractIdEntity<*>)?.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "${this::class.simpleName}(id=$id)"
    }
}

fun <ONE1 : AbstractIdEntity<*>, ONE2 : AbstractIdEntity<*>> ONE1.linkOneToOne(entity: ONE2, one1ToOne2: KMutableProperty1<ONE1, ONE2?>, one2ToOne1: KMutableProperty1<ONE2, ONE1?>) {
    one1ToOne2.set(this, entity)
    one2ToOne1.set(entity, this)
}

fun <ONE : AbstractIdEntity<*>, MANY : AbstractIdEntity<*>> ONE.linkOneToMany(entity: MANY, oneToMany: KProperty1<ONE, MutableSet<MANY>>, manyToOne: KMutableProperty1<MANY, ONE?>) {
    oneToMany.get(this).add(entity)
    manyToOne.set(entity, this)
}

fun <ONE : AbstractIdEntity<*>, MANY : AbstractIdEntity<*>> MANY.linkManyToOne(entity: ONE, manyToOne: KMutableProperty1<MANY, ONE?>, oneToMany: KProperty1<ONE, MutableSet<MANY>>) {
    oneToMany.get(entity).add(this)
    manyToOne.set(this, entity)
}

fun <ONE : AbstractIdEntity<*>, MANY : AbstractIdEntity<*>> ONE.removeOneToMany(entity: MANY, oneToMany: KProperty1<ONE, MutableSet<MANY>>, manyToOne: KMutableProperty1<MANY, ONE?>) {
    oneToMany.get(this).remove(entity)
    manyToOne.set(entity, null)
}

fun <ONE : AbstractIdEntity<*>, MANY : AbstractIdEntity<*>> MANY.removeManyToOne(entity: ONE, manyToOne: KMutableProperty1<MANY, ONE?>, oneToMany: KProperty1<ONE, MutableSet<MANY>>? = null) {
    oneToMany?.get(entity)?.remove(this)
    manyToOne.set(this, null)
}

@MappedSuperclass
abstract class IdEntity<T : Serializable> : AbstractIdEntity<T>() {

    @Id
    @GeneratedValue(strategy = AUTO)
    override var id: T? = null
}

@MappedSuperclass
abstract class ImmutableIdEntity<T : Serializable> : AbstractIdEntity<T>() {
    abstract override val id: T
}
