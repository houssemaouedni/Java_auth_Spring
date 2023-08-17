package com.javaguides.todo.service;

import com.javaguides.todo.dto.TodoDto;
import java.util.List;

public interface TodoService {
    TodoDto createTodo(TodoDto todoDto);

    TodoDto updateTodo(Long id, TodoDto todoDto);

    TodoDto getTodo(Long id);

    TodoDto deleteTodo(Long id);

    List<TodoDto> getAllTodos();

    TodoDto completeTodo(Long id);

    TodoDto unCompleteTodo(Long id);
}
