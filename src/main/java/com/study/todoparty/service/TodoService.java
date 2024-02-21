package com.study.todoparty.service;

import com.study.todoparty.dto.requestDto.CreateTodoRequestDto;
import com.study.todoparty.dto.requestDto.UpdateTodoRequestDto;
import com.study.todoparty.dto.responseDto.TodoResponseDto;
import com.study.todoparty.entity.Todo;
import com.study.todoparty.entity.User;
import com.study.todoparty.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoResponseDto createTodo(CreateTodoRequestDto request, User user){
        Todo todo = new Todo(request);
        todo.setUser(user);

        todoRepository.save(todo);

        return new TodoResponseDto(todo);
    }

    public TodoResponseDto getTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 할일 카드 ID 입니다")
        );
        return new TodoResponseDto(todo);
    }

    public Map<String, List<TodoResponseDto>> listTodosByUser() {
        List<Todo> todos = todoRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        Map<String, List<TodoResponseDto>> todosByUser = new HashMap<>();

        for (Todo todo : todos) {
            String username = todo.getUser().getUsername();

            List<TodoResponseDto> userTodos = todosByUser.getOrDefault(username, new ArrayList<>());

            userTodos.add(new TodoResponseDto(todo));

            todosByUser.put(username, userTodos);
        }
        return todosByUser;
    }

    public TodoResponseDto updateTodo(Long todoId, UpdateTodoRequestDto request, User user) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 할일 카드 ID 입니다")
        );

        if (!todo.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }

        todo.setTitle(request.getTitle());
        todo.setContent(request.getContent());

        todoRepository.save(todo);

        return new TodoResponseDto(todo);
    }

    public void completeTodo(Long todoId, User user) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 할일 카드 ID 입니다")
        );

        if (!todo.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("작성자만 완료할 수 있습니다.");
        }

        todo.setCompleted(true);

        todoRepository.save(todo);
    }
}