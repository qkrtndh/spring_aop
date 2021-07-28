package com.cos.aop.domain;

import lombok.Data;

//username, password만 넘기면 id가 널인 User값이 오가기때문에 위험
@Data
public class UpdateRequestDto {
	private String password;
	private String phone;
}
