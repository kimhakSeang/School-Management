package com.piseth.java.school.schoolManagement.spec;

import java.util.List;

import lombok.Data;

@Data
public class MonthlyScoreFilter {
	private List<Long> studentIds;
	private List<Long> subjectIds;
	private Long  studentId;
	private Integer year;
	private Integer month;
	private Integer grade;
	private String className;
}
