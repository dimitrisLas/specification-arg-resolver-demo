package com.example.specification_arg_resolver_demo.controller

import com.example.specification_arg_resolver_demo.entity1.Entity1
import com.example.specification_arg_resolver_demo.repository.Entity1Repository
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase
import net.kaczmarzyk.spring.data.jpa.web.annotation.And
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("entity1")
class Entity1Controller(
    private val entity1Repository: Entity1Repository
) {

    @GetMapping
    fun getEntity1(
        pageable: Pageable,
        @And(
            Spec(path = "name", spec = LikeIgnoreCase::class)
        )
        searchSpecification: Specification<Entity1>?
    ): ResponseEntity<Page<Entity1>> =
        ResponseEntity.ok(entity1Repository.findAll(searchSpecification, pageable))

    @PostMapping
    fun createEntity1(
        @RequestBody entity1: Entity1
    ): ResponseEntity<Entity1>{
        val savedEntity1 = entity1Repository.save(entity1)
        return ResponseEntity.ok(savedEntity1)
    }
}