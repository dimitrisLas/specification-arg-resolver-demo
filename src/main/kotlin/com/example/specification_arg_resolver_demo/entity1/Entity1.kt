package com.example.specification_arg_resolver_demo.entity1

import com.example.specification_arg_resolver_demo.shared.entity.IdEntity
import jakarta.persistence.Entity

@Entity
class Entity1 (
    var name: String
): IdEntity<Long>()

