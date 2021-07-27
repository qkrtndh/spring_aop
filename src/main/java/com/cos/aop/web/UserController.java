package com.cos.aop.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.aop.domain.User;
import com.cos.aop.domain.UserRepository;

@RestController
public class UserController {

	private UserRepository userRepository;

	// DI = 의존성 주입
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// http://localhost:8080/user
	@GetMapping("/user")
	public List<User> findAll() {
		System.out.println("findAll");
		return userRepository.findAll();//메세지 컨버터가 자바오브젝트를 json 문자열로 변환해줌
	}

	// http://localhost:8080/user/id
	@GetMapping("/user/{id}")
	public User findById(@PathVariable int id) {

		userRepository.findAll();
		System.out.println("findById :"+id );
		return userRepository.findById(id);
	}

	// http://localhost:8080/user
	@PostMapping("/user")
	public void save(String username, String password, String phone) {
		System.out.println("save");
	}

	@DeleteMapping("/user/{id}")
	public void delete(@PathVariable int id) {
		System.out.println("delete");
	}

	@PutMapping("/user/{id}")
	public void update(@PathVariable int id, String password, String phone) {
		System.out.println("update");
	}
}
