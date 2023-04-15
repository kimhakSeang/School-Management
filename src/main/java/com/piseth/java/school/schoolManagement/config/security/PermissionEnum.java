package com.piseth.java.school.schoolManagement.config.security;

import lombok.Getter;

@Getter
public enum PermissionEnum {
	MS_R ("MONTHLY_SCORE:READ"), 
	MS_W ("MONTHLY_SCORE:WRITE"),
	
	STU_R ("STUDENT:READ"),
	STU_W ("STUDENT:WRITE"),
	
	SUB_R ("SUBJECT:READ"),
	SUB_W ("SUBJECT:WRITE");
	
	private String descript;
	private PermissionEnum(String descript) {
		this.descript = descript;
	}
}