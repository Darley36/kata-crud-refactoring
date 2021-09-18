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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TodoListService {
    private ModelMapper mapper;
    @Autowired
    private TodoListRepository repository;
    @Autowired
    private TodoRepository todoRepository;

    public TodoListService(ModelMapper mapper, TodoListRepository repository, TodoRepository todoRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.todoRepository = todoRepository;
    }


    public Iterable<TodoListDTO> getAllList(){
        Iterable<TodoList> todoList = repository.findAll();
        List<TodoListDTO> todoListDTO = new ArrayList<>();
        for (var item: todoList){
            TodoListDTO listDTO = mapper.map(item, TodoListDTO.class);
            todoListDTO.add(listDTO);
        }
        return todoListDTO;
    }


    public List<Todo> getAllTodoByIdList(Long idGroupList){
        List<Todo> todos = todoRepository.getByGroupListId(idGroupList);
        return todos;
    }


    public TodoListDTO save(TodoListDTO todoListDTO){
        TodoList todoList = mapper.map(todoListDTO,TodoList.class);
        Long idList = repository.save(todoList).getListId();
        todoListDTO.setListId(idList);
        return todoListDTO;
    }


    public TodoDTO saveTodoByListId(Long idList, TodoDTO todoDTO){
        TodoList todoList = get(idList);

        Todo todo = mapper.map(todoDTO, Todo.class);

        todoList.getTodoList().add(todo);
        TodoList lastList = repository.save(todoList);
        var internalTodo = lastList.getTodoList().stream()
                .max(Comparator.comparingInt(item -> item.getId().intValue()))
                .orElseThrow();
        
        todoDTO.setId(internalTodo.getId());
        todoDTO.setGroupListId(idList);
        return todoDTO;
    }



    public TodoDTO updateTodoByListId(Long idList, TodoDTO todoDTO){
        TodoList todoList = get(idList);


        List<Todo> fields = todoList.getTodoList();

        for (Todo field: fields){
            if (field.getId() == todoDTO.getId()) {
                field.setId(todoDTO.getId());
                field.setName(todoDTO.getName());
                field.setCompleted(todoDTO.isCompleted());
            }
        }

        repository.save(todoList);
        return todoDTO;
    }


    public void deleteList(Long listId){

        repository.delete(repository.findById(listId).orElseThrow());
    }


    public TodoList get(Long listId){
        TodoListDTO todoListDTO = new TodoListDTO();
        TodoList todoList = repository.findById(listId).orElseThrow();
        return todoList;
    }


    public void deleteTodoById(Long id){
        var todo = todoRepository.findById(id).orElseThrow();
        todoRepository.delete(todo);
    }
}
