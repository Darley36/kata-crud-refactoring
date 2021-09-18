import React, { useContext, useReducer, useEffect, useRef, useState, createContext } from 'react';
import ListForm from './components/ListForm';
import TypeList from './components/TypeTaskList';
import { StoreProvider } from './components/CompStore';

const HOST_API = "http://localhost:8080/api";

function App() {
  return <StoreProvider>
    <blockquote className="blockquote text-center">
      <h3>Tasks</h3>
    </blockquote>
    
    <div className="container">
      <br/>
      <div>
        <h2>Category</h2>
        <ListForm/>
      </div>
      <br/>
    </div>
    <div className="container">
      <TypeList/>
    </div>
  </StoreProvider>
}

export default App;
