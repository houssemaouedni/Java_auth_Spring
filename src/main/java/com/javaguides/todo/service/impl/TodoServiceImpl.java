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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {
    private TodoRepository todoRepository;
    private ModelMapper modelMapper;

    public TodoDto createTodo(TodoDto todoDto) {
        Todo todo = (Todo)this.modelMapper.map(todoDto, Todo.class);
        Todo savedTodo = (Todo)this.todoRepository.save(todo);
        return (TodoDto)this.modelMapper.map(savedTodo, TodoDto.class);
    }

    public TodoDto updateTodo(Long id, TodoDto todoDto) {
        Todo todo = (Todo)this.todoRepository.findById(id).get();
        todo.setId(todoDto.getId() != null ? todoDto.getId() : todo.getId());
        todo.setTitle(todoDto.getTitle() != null ? todoDto.getTitle() : todo.getTitle());
        todo.setDescription(todoDto.getDescription() != null ? todoDto.getDescription() : todo.getDescription());
        if (todoDto.isCompleted() != todo.isCompleted()) {
            todo.setCompleted(todoDto.isCompleted());
        } else {
            todo.setCompleted(todo.isCompleted());
        }

        return (TodoDto)this.modelMapper.map(this.todoRepository.save(todo), TodoDto.class);
    }

    public TodoDto getTodo(Long id) {
        Todo todo = (Todo)this.todoRepository.findById(id).get();
        return (TodoDto)this.modelMapper.map(todo, TodoDto.class);
    }

    public TodoDto deleteTodo(Long id) {
        Todo todo = (Todo)this.todoRepository.findById(id).get();
        this.todoRepository.delete(todo);
        return (TodoDto)this.modelMapper.map(todo, TodoDto.class);
    }

    public List<TodoDto> getAllTodos() {
        List<Todo> todo = this.todoRepository.findAll();
        return (List)todo.stream().map((todo1) -> {
            return (TodoDto)this.modelMapper.map(todo1, TodoDto.class);
        }).collect(Collectors.toList());
    }

    public TodoDto completeTodo(Long id) {
        Todo todo = (Todo)this.todoRepository.findById(id).orElseThrow();
        todo.setCompleted(true);
        return (TodoDto)this.modelMapper.map(this.todoRepository.save(todo), TodoDto.class);
    }

    public TodoDto unCompleteTodo(Long id) {
        Todo todo = (Todo)this.todoRepository.findById(id).orElseThrow();
        todo.setCompleted(false);
        return (TodoDto)this.modelMapper.map(this.todoRepository.save(todo), TodoDto.class);
    }

    public TodoServiceImpl(final TodoRepository todoRepository, final ModelMapper modelMapper) {
        this.todoRepository = todoRepository;
        this.modelMapper = modelMapper;
    }
}
