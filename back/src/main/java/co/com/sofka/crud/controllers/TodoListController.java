package co.com.sofka.crud.controllers;

import co.com.sofka.crud.DTO.TodoListDTO;
import co.com.sofka.crud.entities.Todo;
import co.com.sofka.crud.entities.TodoList;
import co.com.sofka.crud.services.TodoListService;
import co.com.sofka.crud.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoListController {
    @Autowired
    private TodoListService serviceList;

    @GetMapping(value = "api/todolist")
    public Iterable<TodoListDTO> getlist(){
        return serviceList.getAllList();
    }

    @GetMapping(value = "api/{listid}/todo")
    public List<Todo> getTodoByListId(@PathVariable("listid") Long listId){
        return serviceList.getTodo(listId);
    }

    @PostMapping(value = "api/todolist")
    public TodoListDTO savelist(@RequestBody TodoListDTO todoListDTO){
        return serviceList.save(todoListDTO);
    }

    @PutMapping(value = "api/todolist")
    public TodoListDTO updatelist(@RequestBody TodoListDTO todoListDTO){
        if(todoListDTO.getListId() != null){
            return serviceList.save(todoListDTO);
        }
        throw new RuntimeException("No existe el id para actualziar");
    }

    @DeleteMapping(value = "api/{listid}/todolist")
    public void delete(@PathVariable("listid")Long listId){
        serviceList.deleteList(listId);
    }

    @GetMapping(value = "api/{listid}/todolist")
    public TodoListDTO getListById(@PathVariable("listid") Long listId){
        return serviceList.get(listId);
    }

}
