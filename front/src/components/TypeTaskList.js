import React, {useContext,useEffect} from "react";
import Store from "./CompStore";

const HOST_API = "http://localhost:8080/api";
const TypeList = () => {
    const { dispatch, state: { todolist, todo } } = useContext(Store);
    const currentList = todolist.list;
  
    useEffect(() => {
      fetch(HOST_API + "/todolist")
        .then(response => response.json())
        .then((list) => {
          dispatch({ type: "update-list", list })
        })
    }, [dispatch]);
  
  
    const onDelete = (listid) => {
      fetch(HOST_API + "/" + listid + "/todo", {
        method: "DELETE"
      }).then((list) => {
        dispatch({ type: "delete-item", listid })
      })
    };
  
    const decorationDone = {
      textDecoration: 'line-through'
    };
    return <div>
      <div>
        {list.elements.map((Element) => {
          <fieldset>
            <legend>
              {Element.listName}
              <button onClick={() => onDelete(Element.listId)}>Eliminar</button>
            </legend>
          </fieldset>
        })}
      </div>
    </div>
  }

  export default TypeList;