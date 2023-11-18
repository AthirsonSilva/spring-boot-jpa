package com.app.core.user.dto;

import lombok.Data;

@Data
public class UserDto {
	private String name;
	private Integer age;
	private RoleDto role;
}
