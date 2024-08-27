package com.example.specification_arg_resolver_demo.repository

import com.example.specification_arg_resolver_demo.entity1.Entity1
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface Entity1Repository: JpaRepository<Entity1, Long>, JpaSpecificationExecutor<Entity1>