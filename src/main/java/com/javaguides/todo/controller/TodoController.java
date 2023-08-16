//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.javaguides.todo.controller;

import com.javaguides.todo.dto.TodoDto;
import com.javaguides.todo.service.TodoService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin({"*"})
@RestController
@RequestMapping({"/api/todo"})
public class TodoController {
    private TodoService todoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping({"/create"})
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto) {
        TodoDto result = this.todoService.createTodo(todoDto);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping({"/update/{id}"})
    public ResponseEntity<TodoDto> updateTodo(@PathVariable("id") Long id, @RequestBody TodoDto todoDto) {
        TodoDto result = this.todoService.updateTodo(id, todoDto);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping({"/{id}"})
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long id) {
        TodoDto result = this.todoService.getTodo(id);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping({"/delete/{id}"})
    public ResponseEntity<TodoDto> deleteTodo(@PathVariable("id") Long id) {
        TodoDto result = this.todoService.deleteTodo(id);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping({"/all"})
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> result = this.todoService.getAllTodos();
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping({"/{id}/complete"})
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long id) {
        TodoDto result = this.todoService.completeTodo(id);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping({"/{id}/uncomplete"})
    public ResponseEntity<TodoDto> unCompleteTodo(@PathVariable("id") Long id) {
        TodoDto result = this.todoService.unCompleteTodo(id);
        return ResponseEntity.ok(result);
    }

    public TodoController(final TodoService todoService) {
        this.todoService = todoService;
    }
}
