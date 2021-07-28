package com.cos.aop.domain;
//username, password만 넘기면 id가 널인 User값이 오가기때문에 위험

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class JoinRequestDto {
	
	@NotNull(message="no key value for username")
	@NotBlank(message="username need")
	@Size(max=20,message = "over password length")
	private String username;
	
	@NotNull(message="no password")
	private String password;
	private String phone;
}
