package com.piseth.java.school.schoolManagement.dto;

import lombok.Data;

@Data
public class StudentDTO {
	private Long id;
	private String name;
	private String gender;
	private Integer grade;
	private String className;
}
