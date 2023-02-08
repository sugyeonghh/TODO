package com.singleproject.todo.mapper;

import com.singleproject.todo.dto.TodoPatchDto;
import com.singleproject.todo.dto.TodoPostDto;
import com.singleproject.todo.entity.Todos;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodoMapper {
	Todos TodoPostDtoToTodo(TodoPostDto todoPostDto);

	Todos TodoPatchDtoToTodo(TodoPatchDto todoPatchDto);

}
