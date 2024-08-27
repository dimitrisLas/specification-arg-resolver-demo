package com.example.specification_arg_resolver_demo

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class SpecificationArgResolverDemoApplicationTests {


	@Autowired
	protected lateinit var mockMvc: MockMvc

	@BeforeEach
	fun setUp(){
		val entity2RequestBody = """
			{
				"entity1": {
					"name": "entity-1-test"
				},
				"name": "entity-2-test"
			}
			"""
		createEntity2(entity2RequestBody, "/entity2")
	}

	@Test
	fun setUpTest() {
		// This test will always pass unless an exception is thrown in the setUp method
	}


	@Test
	fun getEntity2WithSpecificationTest() {
		mockMvc.perform(
			MockMvcRequestBuilders.get("/entity2?entity1.id=1")
		).andExpect(MockMvcResultMatchers.status().isOk).andReturn()
	}

	private fun createEntity2(content: String, path: String){
		mockMvc.perform(
			MockMvcRequestBuilders.post(path)
				.contentType(MediaType.APPLICATION_JSON)
				.content(content)
		).andExpect(MockMvcResultMatchers.status().isOk).andReturn()
	}

}
