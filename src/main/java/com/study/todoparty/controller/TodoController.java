package com.study.todoparty.controller;

import com.study.todoparty.config.jwt.UserDetails.UserDetailsImpl;
import com.study.todoparty.dto.requestDto.CreateTodoRequestDto;
import com.study.todoparty.dto.requestDto.UpdateTodoRequestDto;
import com.study.todoparty.dto.responseDto.CommonResponseDto;
import com.study.todoparty.dto.responseDto.TodoResponseDto;
import com.study.todoparty.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    // 할일카드 작성 기능 API : 할일 제목,할일 내용, 작성일을 저장
    // 조건 : 토큰을 검사하여, 유효한 토큰일 경우에만 할일 작성 가능
    // 반환 정보 : ResponseEntity<CommonResponse>
    @PostMapping("/create")
    public ResponseEntity<TodoResponseDto> createTodo(@RequestBody CreateTodoRequestDto request, @AuthenticationPrincipal UserDetailsImpl userDetails){
        TodoResponseDto response = todoService.createTodo(request, userDetails.getUser());

        return ResponseEntity.ok().body(response);
    }

    // 선택한 할일카드 조회 기능 API : 선택한 할일의 정보를 조회\
    // 조건 : X
    // 반환 정보 : 할일 정보(할일 제목,할일 내용, 작성자 , 작성일)
    @GetMapping("/find/{todoId}")
    public ResponseEntity<CommonResponseDto> findTodo(@PathVariable Long todoId){
        try{
            TodoResponseDto response = todoService.getTodo(todoId);
            return ResponseEntity.ok().body(response);
        } catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    // 할일카드 목록 조회 기능 API : 회원별로 각각 나누어서 할일 목록이 조회
    // 조건 : 조회된 할일 목록은 작성일 기준 내림차순으로 정렬
    // 반환 정보 : 회원별 할일(할일 제목, 작성자, 작성일, 완료 여부) 목록
    @GetMapping("/list")
    public ResponseEntity<Map<String, List<TodoResponseDto>>> listTodosByUser() {
        Map<String, List<TodoResponseDto>> todosByUser = todoService.listTodosByUser();
        return ResponseEntity.ok().body(todosByUser);
    }


    // 선택한 할일카드 수정 기능 API : 할일카드의 제목, 작성 내용을 수정 가능
    // 조건 : 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 할일카드 만 수정 가능
    // 반환 정보 : 수정된 할일 정보(제목, 내용)
    @PutMapping("/update")
    public ResponseEntity<CommonResponseDto> updateTodo(@RequestBody UpdateTodoRequestDto request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try{
            TodoResponseDto response = todoService.updateTodo(request, userDetails.getUser());
            return ResponseEntity.ok().body(response);
        } catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    // 할일카드 완료 기능 API : 완료처리한 할일 카드는 목록조회시 완료 여부 필드가 TRUE 로 내려감 (기본값은 False)
    // 조건 : 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 할일카드 만 완료 가능
    // 반환 정보 : ResponseEntity<CommonResponse>
    @PatchMapping("/complete/{todoId}") // Put : 전체 수정, Patch : 부분 수정
    public ResponseEntity<CommonResponseDto> completeTodo(@PathVariable Long todoId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try{
            todoService.completeTodo(todoId, userDetails.getUser());
            return ResponseEntity.ok().body(new CommonResponseDto("할일이 완료되었습니다.", HttpStatus.OK.value()));
        } catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }
}