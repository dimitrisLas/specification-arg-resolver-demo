package com.example.specification_arg_resolver_demo

import com.example.specification_arg_resolver_demo.entity1.Entity1
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpecificationArgResolverDemoApplication

fun main(args: Array<String>) {
	runApplication<SpecificationArgResolverDemoApplication>(*args)
}
