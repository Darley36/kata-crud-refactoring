package co.com.sofka.crud.services;

import co.com.sofka.crud.repository.TodoRepository;
import co.com.sofka.crud.entities.Todo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private ModelMapper mapper;

    @Autowired
    private TodoRepository repository;

    public Iterable<Todo> list(){
        return repository.findAll();
    }

    public Todo save(Todo todo){
        return repository.save(todo);
    }

    public void delete(Long id){
        repository.delete(get(id));
    }

    public Todo get(Long id){
         return repository.findById(id).orElseThrow();
    }

    public List<Todo> getByIdList(Long id) {return repository.getByGroupListId(id);}

}
