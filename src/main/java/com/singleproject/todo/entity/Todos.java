package com.singleproject.todo.entity;

import com.singleproject.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Todos extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int todoId;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private Integer todoOrder;

	@Column(nullable = false)
	private Boolean completed;
}
