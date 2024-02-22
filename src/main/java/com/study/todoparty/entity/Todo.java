package com.study.todoparty.entity;

import com.study.todoparty.dto.requestDto.CreateTodoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "todo")
public class Todo extends TimeStamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private Boolean completed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "todo")
    private List<Comment> comments;

    public Todo(CreateTodoRequestDto request) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.completed = false;
        comments = new ArrayList<>();
    }

    public void setUser(User user){
        this.user = user;
        user.getTodo().add(this);
    }
}