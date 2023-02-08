package com.singleproject.todo.service;


import com.singleproject.todo.entity.Todos;
import com.singleproject.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
	private final TodoRepository todoRepository;

	public Todos createTodo(Todos todos) {
		return todoRepository.save(todos);
	}

	public Todos updateTodo(Todos todos) {
		Todos findTodo = findVerifiedTodo(todos.getId());

		Optional.ofNullable(todos.getTodoOrder()).ifPresent(findTodo::setTodoOrder);
		Optional.ofNullable(todos.getTitle()).ifPresent(findTodo::setTitle);
		Optional.ofNullable(todos.isCompleted()).ifPresent(findTodo::setCompleted);

		return todoRepository.save(findTodo);
	}

	public Todos findTodo(int id) {
		return findVerifiedTodo(id);
	}

	public List<Todos> findAllTodo() {
		return todoRepository.findAll();
	}

	public void deleteTodo(int id) {
		todoRepository.delete(findVerifiedTodo(id));
	}

	public void deleteAllTodo() {
		List<Todos> todoList = todoRepository.findAll();

		for (Todos todo : todoList) {
			todoRepository.delete(todo);
		}
	}

	private Todos findVerifiedTodo(int id) {
		Optional<Todos> optionalTodos = todoRepository.findById(id);

		Todos todo = optionalTodos.orElseThrow();
		return todo;
	}
}
