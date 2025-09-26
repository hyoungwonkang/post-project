package com.example.post_project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 마이바티스 매퍼 인터페이스를 자동으로 스캔해서 스프링 빈으로 등록
@MapperScan("com.example.post_project.mapper")
public class PostProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostProjectApplication.class, args);
	}

}
