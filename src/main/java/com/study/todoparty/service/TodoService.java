package com.study.todoparty.service;

import com.study.todoparty.dto.requestDto.CreateTodoRequestDto;
import com.study.todoparty.dto.requestDto.UpdateTodoRequestDto;
import com.study.todoparty.dto.responseDto.TodoResponseDto;
import com.study.todoparty.entity.User;
import java.util.List;
import java.util.Map;

public interface TodoService {
    /**
     * 할일 생성
     * @param request 생성 요청 정보
     * @param user 생성 요청자
     * @return 생성된 할일 정보
     */
    TodoResponseDto createTodo(CreateTodoRequestDto request, User user);

    /**
     * 특정 할일 조회
     * @param todoId 조회할 할일의 ID
     * @return 조회된 할일 정보
     */
    TodoResponseDto getTodo(Long todoId);

    /**
     * 사용자별 할일 목록 조회
     * @return 사용자별 할일 목록 정보
     */
    Map<String, List<TodoResponseDto>> listTodosByUser();

    /**
     * 할일 수정
     * @param request 수정 요청 정보
     * @param user 수정 요청자
     * @return 수정된 할일 정보
     */
    TodoResponseDto updateTodo(UpdateTodoRequestDto request, User user);

    /**
     * 할일 완료 처리
     * @param todoId 완료 처리할 할일의 ID
     * @param user 완료 처리 요청자
     */
    void completeTodo(Long todoId, User user);
}