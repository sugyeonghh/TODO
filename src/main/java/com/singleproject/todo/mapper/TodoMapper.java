package com.singleproject.todo.mapper;

import com.singleproject.todo.dto.TodoDto;
import com.singleproject.todo.entity.Todos;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {
	Todos TodoPostDtoToTodo(TodoDto.Post todoPostDto);

	Todos TodoPatchDtoToTodo(TodoDto.Patch todoPatchDto);

	TodoDto.Response TodoToTodoResponseDto(Todos todo);

	List<TodoDto.Response> TodosToTodoResponseDtos(List<Todos> todo);
}
