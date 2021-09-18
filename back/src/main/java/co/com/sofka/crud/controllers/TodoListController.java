package co.com.sofka.crud.controllers;

import co.com.sofka.crud.DTO.TodoDTO;
import co.com.sofka.crud.DTO.TodoListDTO;
import co.com.sofka.crud.entities.Todo;
import co.com.sofka.crud.entities.TodoList;
import co.com.sofka.crud.services.TodoListService;
import co.com.sofka.crud.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TodoListController {
    @Autowired
    private TodoListService serviceList;

    @GetMapping(value = "api/todolist")
    public Iterable<TodoListDTO> getlist(){
        return serviceList.getAllList();
    }

    @GetMapping(value = "api/{listid}/todolist")
    public List<Todo> getTodoByListId(@PathVariable("listid") Long listId){
        return serviceList.getAllTodoByIdList(listId);
    }

    @PostMapping(value = "api/todolist")
    public TodoListDTO savelist(@RequestBody TodoListDTO todoListDTO){
        return serviceList.save(todoListDTO);
    }

    //no se usa
    @PutMapping(value = "api/todolist")
    public TodoListDTO updatelist(@RequestBody TodoListDTO todoListDTO){
        if(todoListDTO.getListId() != null){
            return serviceList.save(todoListDTO);
        }
        throw new RuntimeException("No existe el id para actualziar");
    }

    @DeleteMapping(value = "api/{listid}/todolist")
    public void deleteList(@PathVariable("listid")Long listId){
        serviceList.deleteList(listId);
    }

    //not used
    @GetMapping(value = "api/{listid}/todolistbyid")
    public TodoList getListById(@PathVariable("listid") Long listId){
        return serviceList.get(listId);
    }

    //should go in the Todo controller
    @PutMapping(value = "api/{listid}/todolist")
    public TodoDTO updateTodoByList(@PathVariable("listid") Long listId, @RequestBody TodoDTO todoDTO){
        if (todoDTO.getId() != null) return serviceList.updateTodoByListId(listId, todoDTO);

        throw new RuntimeException("The ID does not exist!");
    }


    @PostMapping(value = "api/{listid}/todolist")
    public TodoDTO saveTodoByListId(@PathVariable("listid")  Long listId, @RequestBody TodoDTO todoDTO){
        return serviceList.saveTodoByListId(listId,todoDTO);
    }


    @DeleteMapping(value = "api/{id}/todotodo")
    public void delete(@PathVariable("id")Long id){

        serviceList.deleteTodoById(id);
    }


}
