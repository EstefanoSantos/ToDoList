package com.example.toDoList.controller;

import com.example.toDoList.entity.Todo;
import com.example.toDoList.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    @PostMapping
    List<Todo> create(@RequestBody @Valid Todo todo) {
        return todoService.create(todo);
    }
    @GetMapping
    List<Todo> list() {
        return todoService.list();
    }
    @PutMapping
    List<Todo> update(@RequestBody @Valid Todo todo) {
        return todoService.update(todo);
    }
    @DeleteMapping("{id}")
    List<Todo> delete(@PathVariable("id") Long id) {
        return todoService.delete(id);
    }

}
