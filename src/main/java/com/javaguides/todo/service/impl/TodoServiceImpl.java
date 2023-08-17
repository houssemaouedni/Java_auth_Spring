//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.javaguides.todo.service.impl;

import com.javaguides.todo.dto.TodoDto;
import com.javaguides.todo.entity.Todo;
import com.javaguides.todo.repository.TodoRepository;
import com.javaguides.todo.service.TodoService;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {
    private TodoRepository todoRepository;
    private ModelMapper modelMapper;

    public TodoDto createTodo(TodoDto todoDto) {
        Todo todo = modelMapper.map(todoDto, Todo.class);
        Todo savedTodo = todoRepository.save(todo);
        return modelMapper.map(savedTodo, TodoDto.class);
    }

    public TodoDto updateTodo(Long id, TodoDto todoDto) {
        Todo todo = todoRepository.findById(id).orElseThrow();
        todo.setId(todoDto.getId() != null ? todoDto.getId() : todo.getId());
        todo.setTitle(todoDto.getTitle() != null ? todoDto.getTitle() : todo.getTitle());
        todo.setDescription(todoDto.getDescription() != null ? todoDto.getDescription() : todo.getDescription());
        if (todoDto.isCompleted() != todo.isCompleted()) {
            todo.setCompleted(todoDto.isCompleted());
        } else {
            todo.setCompleted(todo.isCompleted());
        }

        return modelMapper.map(todoRepository.save(todo), TodoDto.class);
    }

    public TodoDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow();
        return modelMapper.map(todo, TodoDto.class);
    }

    public TodoDto deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow();
        todoRepository.delete(todo);
        return modelMapper.map(todo, TodoDto.class);
    }

    public List<TodoDto> getAllTodos() {
        List<Todo> todo = todoRepository.findAll();
        return todo.stream().map((todo1) ->
           modelMapper.map(todo1, TodoDto.class)
        ).collect(Collectors.toList());
    }

    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow();
        todo.setCompleted(true);
        return modelMapper.map(todoRepository.save(todo), TodoDto.class);
    }

    public TodoDto unCompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow();
        todo.setCompleted(false);
        return modelMapper.map(todoRepository.save(todo), TodoDto.class);
    }
}
