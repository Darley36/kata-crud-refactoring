import React, { createContext } from "react";


const initialState = {
    todo: { list: [], item: {} }
  };
  const Store = createContext(initialState)
  export const Store = createContext(initialState)
  export default Store;