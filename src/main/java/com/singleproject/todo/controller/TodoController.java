package com.singleproject.todo.controller;

import com.singleproject.response.MultiResponseDto;
import com.singleproject.response.SingleResponseDto;
import com.singleproject.todo.dto.TodoDto;
import com.singleproject.todo.entity.Todos;
import com.singleproject.todo.mapper.TodoMapper;
import com.singleproject.todo.service.TodoService;
import com.singleproject.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
@Validated
public class TodoController {
	private static final String TODO_DEFAULT_URL = "/todo";
	private final TodoMapper mapper;
	private final TodoService todoService;

	@PostMapping
	public ResponseEntity postTodo(@Valid @RequestBody TodoDto.Post todoPostDto) {
		Todos todo = todoService.createTodo(mapper.TodoPostDtoToTodo(todoPostDto));
		URI location = UriCreator.createUri(TODO_DEFAULT_URL, todo.getTodoId());

		return ResponseEntity.created(location).build();
	}

	@PatchMapping("/{todo-id}")
	public ResponseEntity patchTodo(@PathVariable("todo-id") @Positive int todoId,
	                                @RequestBody TodoDto.Patch todoPatchDto) {
		todoPatchDto.setTodoId(todoId);
		Todos todo = todoService.updateTodo(mapper.TodoPatchDtoToTodo(todoPatchDto));

		return ResponseEntity.ok(new SingleResponseDto<>(mapper.TodoToTodoResponseDto(todo)));
	}

	@GetMapping("/{todo-id}")
	public ResponseEntity getTodo(@PathVariable("todo-id") @Positive int todoId) {
		Todos todo = todoService.findTodo(todoId);

		return ResponseEntity.ok(new SingleResponseDto<>(mapper.TodoToTodoResponseDto(todo)));
	}

	@GetMapping
	public ResponseEntity getAllTodo(@PathParam("page") int page,
	                                 @PathParam("size") int size,
	                                 @PathParam("sortDir") String sortDir,
	                                 @PathParam("sortBy") String sortBy) {
		Page<Todos> todosPage = todoService.findAllTodo(page, size, sortDir, sortBy);
		List<Todos> todos = todosPage.getContent();

		return ResponseEntity.ok(new MultiResponseDto<>(mapper.TodosToTodoResponseDtos(todos), todosPage));
	}

	@DeleteMapping("/{todo-id}")
	public ResponseEntity deleteTodo(@PathVariable("todo-id") @Positive int todoId) {
		todoService.deleteTodo(todoId);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping
	public ResponseEntity deleteAllTodo() {
		todoService.deleteAllTodo();

		return ResponseEntity.noContent().build();
	}
}
