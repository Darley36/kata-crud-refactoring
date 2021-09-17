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

    //traer todas las listas
    public Iterable<TodoListDTO> getAllList(){
        Iterable<TodoList> todoList = repository.findAll();
        List<TodoListDTO> todoListDTO = new ArrayList<>();
        for (var item: todoList){
            TodoListDTO listDTO = mapper.map(item, TodoListDTO.class);
            todoListDTO.add(listDTO);
        }
        return todoListDTO;
    }

    //falta colocar el DTO
    //es para traer la lista de los todos
    public List<Todo> getAllTodoByIdList(Long idGroupList){
        List<Todo> todos = todoRepository.getByGroupListId(idGroupList);
        return todos;
    }

    //guardar una nueva lista
    public TodoListDTO save(TodoListDTO todoListDTO){
        TodoList todoList = mapper.map(todoListDTO,TodoList.class);
        Long idList = repository.save(todoList).getListId();
        todoListDTO.setListId(idList);
        return todoListDTO;
    }

    //Agregar nuevas tareas en cierta lista
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


    // actualizar un todo de cierta lista
    public TodoDTO updateTodoByListId(Long idList, TodoDTO todoDTO){
        TodoList todoList = get(idList);
        //TodoList todoList = mapper.map(todoListDTO, TodoList.class);

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

    //Eliminar la lista
    public void deleteList(Long listId){

        repository.delete(repository.findById(listId).orElseThrow());
    }

    //para traer una sola lista por su id
    public TodoList get(Long listId){
        TodoListDTO todoListDTO = new TodoListDTO();
        TodoList todoList = repository.findById(listId).orElseThrow();
        /*todoListDTO.setListId(todoList.getListId());
        todoListDTO.setListName(todoList.getListName());
        todoListDTO.setTodoList(todoList.getTodoList());*/
        return todoList;
    }

    //Eliminar Todo
    public void deleteTodoById(Long id){
        var todo = todoRepository.findById(id).orElseThrow();
        todoRepository.delete(todo);
    }
}
