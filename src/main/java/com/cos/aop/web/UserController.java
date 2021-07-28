package com.cos.aop.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.aop.domain.CommonDto;
import com.cos.aop.domain.JoinRequestDto;
import com.cos.aop.domain.UpdateRequestDto;
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
	public CommonDto<List<User>> findAll() {
		System.out.println("findAll");
		//return userRepository.findAll();//메세지 컨버터가 자바오브젝트를 json 문자열로 변환해줌
		return new CommonDto<>(HttpStatus.OK.value(),userRepository.findAll());
	}

	// http://localhost:8080/user/id
	@GetMapping("/user/{id}")
	public CommonDto<User> findById(@PathVariable int id) {
		System.out.println("findById :"+id );
		return new CommonDto<>(HttpStatus.OK.value(),userRepository.findById(id));
	}

	@CrossOrigin
	// http://localhost:8080/user
	@PostMapping("/user")
	public CommonDto<String> save(@RequestBody JoinRequestDto dto) {
		System.out.println("save");
		userRepository.save(dto);
		
		return new CommonDto<>(HttpStatus.CREATED.value(),"ok");
	}

	@DeleteMapping("/user/{id}")
	public CommonDto delete(@PathVariable int id) {
		System.out.println("delete");
		userRepository.delete(id);
		return new CommonDto<>(HttpStatus.OK.value());
	}

	@PutMapping("/user/{id}")
	public void CommonDto(@PathVariable int id, @RequestBody UpdateRequestDto dto) {
		System.out.println("update");
		userRepository.update(id, dto);
		return new CommonDto<>(HttpStatus.OK.value());
	}
}
