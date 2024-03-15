package com.study.todoparty.controller;

import com.study.todoparty.config.jwt.UserDetailsImpl;
import com.study.todoparty.dto.requestDto.CreateCommentRequestDto;
import com.study.todoparty.dto.requestDto.UpdateCommentRequestDto;
import com.study.todoparty.dto.responseDto.CommentResponseDto;
import com.study.todoparty.dto.responseDto.CommonResponseDto;
import com.study.todoparty.service.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class CommentController {
    private final CommentServiceImpl commentServiceImpl;

    // 댓글 작성 API : 선택한 할일이 있다면 댓글을 등록
    // 조건 : 토큰을 검사하여, 유요한 토큰일 경우에만 댓글 작성 가능
    // 조건2 : 선택한 할일의 DB 저장 유무 확인
    // 반환 정보 : 등록된 댓글
    @PostMapping("/{todoId}/comments/create")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long todoId, @RequestBody CreateCommentRequestDto request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponseDto response = commentServiceImpl.createComment(todoId, request, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 댓글 수정 API
    // 조건 : 토큰을 검사하여, 유요한 토큰이면서 해당 사용자가 작성한 댓글만 수정 가능
    // 조건2 : 선택한 할일의 DB 저장 유무 확인
    // 반환 정보 : 수정된 댓글

    @PutMapping("/{todoId}/comments/update")
    public ResponseEntity<CommonResponseDto> updateComment(@PathVariable Long todoId, @RequestBody UpdateCommentRequestDto request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponseDto response = commentServiceImpl.updateComment(todoId, request, userDetails.getUser());
        return ResponseEntity.ok().body(response);
    }

    // 댓글 삭제 API
    // 조건 : 토큰을 검사하여, 유요한 토큰이면서 해당 사용자가 작성한 댓글만 삭제 가능
    // 조건2 : 선택한 할일의 DB 저장 유무 확인
    // 반환 정보 : 성공 메시지, 상태코드 = ResponseEntity<CommonResponse>
    @DeleteMapping("/{todoId}/comments/delete/{commentId}")
    public ResponseEntity<CommonResponseDto> deleteComment(@PathVariable Long todoId, @PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentServiceImpl.deleteComment(todoId, commentId, userDetails.getUser());
        return ResponseEntity.ok().body(new CommonResponseDto("댓글이 삭제되었습니다.", HttpStatus.OK.value()));
    }
}
