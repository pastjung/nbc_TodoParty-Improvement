package com.study.todoparty.service;

import com.study.todoparty.config.exception.customException.CommentNotFoundException;
import com.study.todoparty.config.exception.customException.TodoNotFoundException;
import com.study.todoparty.dto.requestDto.CreateCommentRequestDto;
import com.study.todoparty.dto.requestDto.UpdateCommentRequestDto;
import com.study.todoparty.dto.responseDto.CommentResponseDto;
import com.study.todoparty.entity.Comment;
import com.study.todoparty.entity.Todo;
import com.study.todoparty.entity.User;
import com.study.todoparty.repository.CommentRepository;
import com.study.todoparty.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;

    @Override
    public CommentResponseDto createComment(Long todoId, CreateCommentRequestDto request, User user) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new TodoNotFoundException("존재하지 않는 할일 카드 ID 입니다")
        );

        System.out.println("request.getContent() = " + request.getContent());

        Comment comment = new Comment(request);
        comment.setUser(user);
        comment.setTodo(todo);

        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    @Override
    @Transactional
    public CommentResponseDto updateComment(Long todoId, UpdateCommentRequestDto request, User user) {
        todoRepository.findById(todoId).orElseThrow(
                () -> new TodoNotFoundException("존재하지 않는 할일 카드 ID 입니다")
        );

        Comment comment = commentRepository.findById(request.getId()).orElseThrow(
                () -> new CommentNotFoundException("존재하지 않는 댓글 ID 입니다")
        );

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }

        comment.setContent(request.getContent());

        return new CommentResponseDto(comment);
    }

    @Override
    public void deleteComment(Long todoId, Long commentId, User user) {
        todoRepository.findById(todoId).orElseThrow(
                () -> new TodoNotFoundException("존재하지 않는 할일 카드 ID 입니다")
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new CommentNotFoundException("존재하지 않는 댓글 ID 입니다")
        );

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }

        commentRepository.delete(comment);
    }
}