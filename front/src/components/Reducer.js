


export function reducer(state, action) {
    switch (action.type) {
      case 'update-item':
        const todoUpItem = state.todo;
        const listUpdateEdit = todoUpItem.list.map((item) => {
          if (item.id === action.item.id) {
            return action.item;
          }
          return item;
        });
        todoUpItem.list = listUpdateEdit;
        todoUpItem.item = {};
        return { ...state, todo: todoUpItem }
      case 'delete-item':
        const todoUpDelete = state.todo;
        const listUpdate = todoUpDelete.list.filter((item) => {
          return item.id !== action.id;
        });
        todoUpDelete.list = listUpdate;
        return { ...state, todo: todoUpDelete }
      case 'update-list':
        const todoUpList = state.todo;
        todoUpList.list = action.list;
        return { ...state, todo: todoUpList }
      case 'edit-item':
        const todoUpEdit = state.todo;
        todoUpEdit.item = action.item;
        return { ...state, todo: todoUpEdit }
      case 'add-item':
        const todoUp = state.todo.list;
        todoUp.push(action.item);
        return { ...state, todo: {list: todoUp, item: {}} }
      case 'create-todolist':
          const savetodolist = state.todoList.list;
          savetodolist.push(action.item);
          return{...state, todoList:{list:savetodolist}}
      case 'get-alllist':
          const todoList = state.todoList;
          console.log(todoList);
          todoList.list =action.list;
          return{...state,todoList:todoList}
      case 'delete-onelist':
          /*const deletelist = state.todoList;
          const listUpdate = deletelist.list.filter((elemento) => {
              return elemento.id !== action.listId;
          });
          deletelist.list = listUpdate;
          return{...state, todoList:deletelist}*/
          const list =state.todoList.elements.filter((element) => {
              return element.id !== action.listId;
          });
          return{...state, todoList:{element:list}}
      default:
        return state;
    }
  }
  //export default reducer
