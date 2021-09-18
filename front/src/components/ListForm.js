import React, {useState, useContext, useRef} from 'react';
import Store from './CompStore';

const HOST_API = "http://localhost:8080/api";

const ListForm = () => {
    const formRef = useRef(null);
    const { dispatch, state: { todoList } } = useContext(Store);
    const item = todoList.item;
    const [state, setState] = useState(item);
  
    const onAdd = (event) => {
      event.preventDefault();
  
      const request = {
        listName: state.listName,
        listId: null
      };
  
  
      fetch(HOST_API + "/todolist", {
        method: "POST",
        body: JSON.stringify(request),
        headers: {
          'Content-Type': 'application/json'
        }
      })
        .then(response => response.json())
        .then((todo) => {
          dispatch({ type: "create-todolist", item: todoList });
          setState({ name: "" });
          formRef.current.reset();
        });
    }
  
    return <form ref={formRef}>
      <input
        type="text"
        name="listName"
        placeholder="Ingresa la categoria de las tareas"
        defaultValue={item.name}
        onChange={(event) => {
          setState({...state, name: event.target.value })
        }}  ></input>
      {<button onClick={onAdd}>Crear nueva categoria</button>}
    </form>
  }
  export default ListForm