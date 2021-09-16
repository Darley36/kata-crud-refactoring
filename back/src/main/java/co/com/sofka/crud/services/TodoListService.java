package co.com.sofka.crud.services;

import co.com.sofka.crud.DTO.TodoDTO;
import co.com.sofka.crud.DTO.TodoListDTO;
import co.com.sofka.crud.entities.Todo;
import co.com.sofka.crud.entities.TodoList;
import co.com.sofka.crud.repository.TodoListRepository;
import co.com.sofka.crud.repository.TodoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoListService {
    private ModelMapper mapper;
    @Autowired
    private TodoListRepository repository;
    @Autowired
    private TodoRepository todoRepository;

    public Iterable<TodoListDTO> getAllList(){
        Iterable<TodoList> todoList = repository.findAll();
        List<TodoListDTO> todoListDTO = new ArrayList<>();
        todoListDTO.add(mapper.map(todoList, TodoListDTO.class));

        return todoListDTO;
    }

    //falta colocar el DTO
    public List<Todo> getTodo(Long idGroupList){
        List<Todo> todos = todoRepository.getByGroupListId(idGroupList);
        return todos;
    }

    public TodoListDTO save(TodoListDTO todolistDTO){
        TodoList todolist = new TodoList();
        todolist.setListName(todolistDTO.getListName());
        //todolist.setListId(todolistDTO.getListId());
        Long idList = repository.save(todolist).getListId();
        todolistDTO.setListId(idList);
        return todolistDTO;
    }

    public void deleteList(Long listId){

        repository.delete(repository.findById(listId).orElseThrow());
    }

    public TodoListDTO get(Long listId){
        TodoListDTO todoListDTO = new TodoListDTO();
        TodoList todoList = repository.findById(listId).orElseThrow();
        todoListDTO.setListId(todoList.getListId());
        todoListDTO.setListName(todoList.getListName());
        //todoListDTO.setTodoList(todoList.getTodoList());
        return todoListDTO;
    }
}
