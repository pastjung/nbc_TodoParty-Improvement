package com.study.todoparty.service;

import com.study.todoparty.dto.requestDto.CreateCommentRequestDto;
import com.study.todoparty.dto.requestDto.UpdateCommentRequestDto;
import com.study.todoparty.dto.responseDto.CommentResponseDto;
import com.study.todoparty.entity.User;

public interface CommentService {
    /**
     * 댓글 생성
     * @param todoId 할일 카드 ID
     * @param request 요청 정보
     * @param user 요청자 정보
     * @return 생성된 댓글 응답 정보
     */
    CommentResponseDto createComment(Long todoId, CreateCommentRequestDto request, User user);

    /**
     * 댓글 수정
     * @param todoId 할일 카드 ID
     * @param request 요청 정보
     * @param user 요청자 정보
     * @return 수정된 댓글 응답 정보
     */
    CommentResponseDto updateComment(Long todoId, UpdateCommentRequestDto request, User user);

    /**
     * 댓글 삭제
     * @param todoId 할일 카드 ID
     * @param commentId 댓글 ID
     * @param user 요청자 정보
     */
    void deleteComment(Long todoId, Long commentId, User user);
}