package com.study.todoparty.entity;

import com.study.todoparty.dto.requestDto.CreateCommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Comment extends TimeStamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;

    public Comment(CreateCommentRequestDto request){
        this.content = request.getContent();
    }

    public void setTodo(Todo todo){
        this.todo = todo;
        todo.getComments().add(this);
    }
}
