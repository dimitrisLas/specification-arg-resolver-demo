package com.example.specification_arg_resolver_demo.controller

import com.example.specification_arg_resolver_demo.entity2.Entity2
import com.example.specification_arg_resolver_demo.repository.Entity2Repository
import net.kaczmarzyk.spring.data.jpa.domain.Equal
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase
import net.kaczmarzyk.spring.data.jpa.web.annotation.And
import net.kaczmarzyk.spring.data.jpa.web.annotation.OnTypeMismatch
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
@RequestMapping("entity2")
class Entity2Controller(
    private val entity2Repository: Entity2Repository
) {

    @GetMapping
    fun getEntity2(
        pageable: Pageable,
        @And(
            Spec(path="entity1.id", onTypeMismatch = OnTypeMismatch.EXCEPTION, spec = Equal::class),
            Spec(path = "name", spec = LikeIgnoreCase::class)
        )
        searchSpecification: Specification<Entity2>?
    ): ResponseEntity<Page<Entity2>> =
        ResponseEntity.ok(entity2Repository.findAll(searchSpecification, pageable))

    @PostMapping
    fun createEntity2(
        @RequestBody entity2: Entity2
    ):ResponseEntity<Entity2>{
        val savedEntity2 = entity2Repository.save(entity2)
        return ResponseEntity.ok(savedEntity2)
    }
}