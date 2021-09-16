package co.com.sofka.crud.controllers;

import co.com.sofka.crud.entities.Todo;
import co.com.sofka.crud.entities.TodoList;
import co.com.sofka.crud.services.TodoListService;
import co.com.sofka.crud.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TodoListController {
    @Autowired
    private TodoListService serviceList;

    @GetMapping(value = "api/todolist")
    public Iterable<TodoList> list(){
        return serviceList.list();
    }

    @PostMapping(value = "api/todolist")
    public TodoList save(@RequestBody TodoList todoList){
        return serviceList.save(todoList);
    }

}
