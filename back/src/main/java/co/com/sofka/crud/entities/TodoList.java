package co.com.sofka.crud.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class TodoList {
    @Id
    @GeneratedValue
    private Long listId;
    private String listName;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Todo> todoList;

    public List<Todo> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
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
