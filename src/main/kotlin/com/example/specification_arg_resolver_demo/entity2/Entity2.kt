package com.example.specification_arg_resolver_demo.entity2

import com.example.specification_arg_resolver_demo.entity1.Entity1
import com.example.specification_arg_resolver_demo.shared.entity.IdEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table
class Entity2(
    @ManyToOne(cascade = [CascadeType.ALL])
    val entity1: Entity1,

    val name: String
): IdEntity<UUID>()