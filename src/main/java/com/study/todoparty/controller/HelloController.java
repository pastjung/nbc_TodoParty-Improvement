package com.study.todoparty.controller;

import com.study.todoparty.dto.responseDto.TodoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    // Postman 으로 서버 연결 확인용 메서드
    @GetMapping("/hello")
    public ResponseEntity<TodoResponseDto> hello() {
        return ResponseEntity.ok().body(null);
    }
}