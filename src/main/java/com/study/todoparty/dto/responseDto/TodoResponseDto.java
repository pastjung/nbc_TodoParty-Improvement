package com.study.todoparty.dto.responseDto;

import com.study.todoparty.entity.Todo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class TodoResponseDto extends CommonResponseDto{
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.createAt = todo.getCreatedAt();
        this.modifiedAt = todo.getModifiedAt();
    }
}