package co.com.sofka.crud.services;

import co.com.sofka.crud.DTO.TodoListDTO;
import co.com.sofka.crud.entities.Todo;
import co.com.sofka.crud.entities.TodoList;
import co.com.sofka.crud.repository.TodoListRepository;
import co.com.sofka.crud.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListService {
    @Autowired
    private TodoListRepository repository;
    @Autowired
    private TodoRepository todoRepository;

    public Iterable<TodoList> list(){
        return repository.findAll();
    }

    private List<Todo> getTodo(Long idGroupList){
        List<Todo> todos = todoRepository.getByGroupListId(idGroupList);
        return todos;
    }

    public TodoList save(TodoListDTO todolistDTO){
        TodoList todolist = new TodoList();
        todolist.setListName(todolistDTO.getListName());
        //todolist.setListId(todolistDTO.getListId());
        Long idList = repository.save(todolist).getListId();
        todolist.setListId(idList);
        return todolist;
    }

    public void delete(Long id){
        repository.delete(get(id));
    }

    public TodoList get(Long id){
        return repository.findById(id).orElseThrow();
    }
}
