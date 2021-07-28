package com.cos.aop.domain;
//username, password만 넘기면 id가 널인 User값이 오가기때문에 위험

import lombok.Data;

@Data
public class JoinRequestDto {
	private String username;
	private String password;
	private String phone;
}
