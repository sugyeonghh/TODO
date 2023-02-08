package com.singleproject.todo.repository;

import com.singleproject.todo.entity.Todos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todos, Integer> {
}
