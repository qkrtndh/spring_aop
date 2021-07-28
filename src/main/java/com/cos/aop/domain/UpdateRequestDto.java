package com.cos.aop.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

//username, password만 넘기면 id가 널인 User값이 오가기때문에 위험
@Data
public class UpdateRequestDto {
	
	@NotNull
	@NotBlank
	private String password;
	private String phone;
}
