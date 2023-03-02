package com.singleproject.todo.service;


import com.singleproject.exception.BusinessLogicException;
import com.singleproject.exception.ExceptionCode;
import com.singleproject.todo.entity.Todos;
import com.singleproject.todo.repository.TodoRepository;
import com.singleproject.utils.CustomBeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {
	private final TodoRepository todoRepository;
	private final CustomBeanUtils<Todos> beanUtils;

	public Todos createTodo(Todos todos) {
		return todoRepository.save(todos);
	}

	public Todos updateTodo(Todos todos) {
		Todos findTodo = findVerifiedTodo(todos.getTodoId());

		Todos updateTodo = beanUtils.copyNonNullProperties(todos, findTodo);
		return findTodo;
	}

	@Transactional(readOnly = true)
	public Todos findTodo(int id) {
		return findVerifiedTodo(id);
	}

	@Transactional(readOnly = true)
	public Page<Todos> findAllTodo(int page, int size, String sortDir, String sortBy) {
		return todoRepository.findAll(PageRequest.of(page - 1, size, Sort.Direction.valueOf(sortDir), sortBy));
	}

	public void deleteTodo(int id) {
		findVerifiedTodo(id);
		todoRepository.deleteById(id);
	}

	public void deleteAllTodo() {
		todoRepository.deleteAll();
	}

	@Transactional(readOnly = true)
	private Todos findVerifiedTodo(int id) {
		Optional<Todos> optionalTodos = todoRepository.findById(id);

		Todos todo = optionalTodos.orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODO_NOT_FOUND));
		return todo;
	}
}
