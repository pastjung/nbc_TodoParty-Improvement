package com.study.todoparty.dto.requestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTodoRequestDto {
    private String title;
    private String content;
}
