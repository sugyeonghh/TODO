package com.singleproject.todo.controller;

import com.singleproject.todo.dto.TodoPatchDto;
import com.singleproject.todo.dto.TodoPostDto;
import com.singleproject.todo.entity.Todos;
import com.singleproject.todo.mapper.TodoMapper;
import com.singleproject.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TodoController {
	private final TodoMapper mapper;
	private final TodoService todoService;

	@PostMapping
	public ResponseEntity postTodo(@RequestBody TodoPostDto todoPostDto) {
		Todos todo = todoService.createTodo(mapper.TodoPostDtoToTodo(todoPostDto));

		return new ResponseEntity<>(todo, HttpStatus.CREATED);
	}

	@PatchMapping("/{todo-id}")
	public ResponseEntity patchTodo(@PathVariable("todo-id") int id,
	                                @RequestBody TodoPatchDto todoPatchDto) {
		todoPatchDto.setId(id);
		Todos todo = todoService.updateTodo(mapper.TodoPatchDtoToTodo(todoPatchDto));

		return new ResponseEntity<>(todo, HttpStatus.OK);
	}

	@GetMapping("/{todo-id}")
	public ResponseEntity getTodo(@PathVariable("todo-id") int id) {
		Todos todo = todoService.findTodo(id);

		return new ResponseEntity<>(todo, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity getAllTodo() {
		List<Todos> todoList = todoService.findAllTodo();

		return new ResponseEntity<>(todoList, HttpStatus.OK);
	}

	@DeleteMapping("/{todo-id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTodo(@PathVariable("todo-id") int id) {
		todoService.deleteTodo(id);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAllTodo() {
		todoService.deleteAllTodo();
	}
}
