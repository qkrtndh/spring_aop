package com.cos.aop.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		users.add(new User(1, "ssar", "1234", "11111111111"));
		users.add(new User(2, "ggar", "1234", "11111111111"));
		users.add(new User(3, "jjar", "1234", "11111111111"));
		return users;
	}

	public User findById(int id) {
		
		return new User(1, "ssar", "1234", "11111111111");
	}
	
	public void save(JoinRequestDto dto) {
		System.out.println("insert");
	}
	
	public void delete(int id) {
		System.out.println("delete");
	}
	
	public void update(int id, UpdateRequestDto dto) {
		System.out.println("update");
	}
}
