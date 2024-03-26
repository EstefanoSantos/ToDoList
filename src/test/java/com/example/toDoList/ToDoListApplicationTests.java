package com.example.toDoList;

import com.example.toDoList.entity.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Random;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ToDoListApplicationTests {

	@Autowired
	WebTestClient webTestClient;

	@Test
	void testCreateTodoSuccess() {
		var todo = new Todo("natação", "não morrer afogado", false, 2);

		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(todo)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(1)
				.jsonPath("$[0].nome").isEqualTo(todo.getNome())
				.jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
				.jsonPath("$[0].realizado").isEqualTo(todo.isRealizado())
				.jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());
	}

	@Test
	void testCreateTodoFailure() {
		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(
						new Todo("", "", false, 1)
				)
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Test
	void testUpdateTodoSuccess() {

		var todo = new Todo("natação", "agora sei nadar", false, 2);

		webTestClient
				.put()
				.uri("/todos")
				.bodyValue(todo)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(1)
				.jsonPath("$[0].nome").isEqualTo(todo.getNome())
				.jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
				.jsonPath("$[0].realizado").isEqualTo(todo.isRealizado())
				.jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());
	}

	@Test
	void testUpdateTodoFailure() {
		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(
						new Todo("", "", false, 1)
				)
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Test
	void testDeleteTodoSuccess() {
		Long id = 1L;

		webTestClient
				.delete()
				.uri("/todos/{id}", id)
				.exchange()
				.expectStatus().isOk();
	}



}
