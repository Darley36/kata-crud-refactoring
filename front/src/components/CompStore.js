import React, { createContext, useReducer } from "react";
import { reducer } from "./Reducer";
import { StoreProvider } from "./StoreProvider";


export const initialState = {
    todo: { list: [], item: {} },
    todoList:{list:[], item: {} }
  };
  export const Store = createContext(initialState)
  export default Store;
