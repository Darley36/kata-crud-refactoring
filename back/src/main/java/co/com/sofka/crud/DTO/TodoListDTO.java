package co.com.sofka.crud.DTO;

import co.com.sofka.crud.entities.Todo;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;

public class TodoListDTO {
    private Long listId;
    private String listName;

    private List<Todo> todoListDTO;

    public List<Todo> getTodoList() {
        return todoListDTO;
    }

    public void setTodoList(List<Todo> todoListDTO) {
        this.todoListDTO = todoListDTO;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }
}
