package com.singleproject.todo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoPatchDto {
	private int id;

	private String title;

	private int todoOrder;

	private boolean completed;
}
