import React, { useContext, useReducer, useEffect, useRef, useState, createContext } from 'react';
import Form from './components/Form';
import TaskList from './components/TaskList';
import { StoreProvider } from './components/StoreProvider';
import ListForm from './components/ListForm';

const HOST_API = "http://localhost:8080/api";

function App() {
  return <StoreProvider>
    <h3>Tasks</h3>
    {/*<Form />*/}
    {/*<TaskList >*/}

    <ListForm/>
  </StoreProvider>
}

export default App;
