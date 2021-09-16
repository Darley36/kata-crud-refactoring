package co.com.sofka.crud.repository;

import co.com.sofka.crud.entities.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
    List<Todo> getByGroupListId(Long groupListId);
}
