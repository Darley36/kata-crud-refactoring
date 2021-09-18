import React, {useContext,useEffect} from "react";
import Store from "./CompStore";

const HOST_API = "http://localhost:8080/api";
const TypeList = () => {
    const { dispatch, state: { todoList, todo } } = useContext(Store);
    const currentList = todoList.list;
  
    useEffect(() => {
      fetch(HOST_API + "/todolist")
        .then(response => response.json())
        .then((list) => {
          dispatch({ type: "get-alllist", list })
        })
    }, [dispatch]);
  
  
    const onDelete = (listId) => {
      fetch(HOST_API + "/" + listId + "/todolist", {
        method: "DELETE"
      }).then((list) => {
        dispatch({ type: "delete-onelist", listId })
      })
    };
  
    const decorationDone = {
      textDecoration: 'line-through'
    };
    return <div>
      <div>
        {todoList.list.map((element) => {
          return <div key={element.listId}>
          <fieldset>
            <legend>
              <input type="text" disabled={true} value={element.listName}/>
              <button onClick={() => onDelete(element.listId)}>Eliminar</button>
            </legend>
          </fieldset>
          </div>
        })}
      </div>
    </div>
  }

  export default TypeList;