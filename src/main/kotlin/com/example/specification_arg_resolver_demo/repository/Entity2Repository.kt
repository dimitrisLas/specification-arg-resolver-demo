package com.example.specification_arg_resolver_demo.repository

import com.example.specification_arg_resolver_demo.entity2.Entity2
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface Entity2Repository: JpaRepository<Entity2, Long>, JpaSpecificationExecutor<Entity2>