package com.study.todoparty.dto.responseDto;

import com.study.todoparty.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto extends CommonResponseDto {
    private Long id;
    private Long todoId;
    private String username;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment){
        this.id = comment.getId();
        this.todoId = comment.getTodo().getId();
        this.username = comment.getUser().getUsername();
        this.content = comment.getContent();
        this.createAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
