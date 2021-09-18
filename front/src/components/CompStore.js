import React, { createContext, useReducer } from "react";
import { reducer } from "./Reducer";


const initialState = {
    todo: { list: [], item: {} },
    todoList:{list:[], item: {} }
  };
  const Store = createContext(initialState)
  //export default Store;

  export const StoreProvider = ({ children }) => {
    const [state, dispatch] = useReducer(reducer, initialState);
  
    return <Store.Provider value={{ state, dispatch }}>
      {children}
    </Store.Provider>
  
  }
  export default Store