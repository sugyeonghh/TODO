package com.singleproject.todo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

public class TodoDto {
	@Getter
	public static class Post {
		@NotBlank
		private String title;

		private int todoOrder;

		private boolean completed;
	}

	@Getter
	@Setter
	public static class Patch {
		private int todoId;

		private String title;

		@Positive
		private int todoOrder;

		private boolean completed;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class Response {
		private int todoId;

		private String title;

		private int todoOrder;

		private boolean completed;

		private LocalDateTime createdAt;

		private LocalDateTime modifiedAt;
	}
}
