// API 베이스 URL
const URL = 'http://localhost:3000/todos';

// DOMContentLoaded : HTML 문서 로딩이 끝나면 실행되는 이벤트
// 페이지가 로드되면 Todo 목록 초기화 함수를 실행한다.
document.addEventListener('DOMContentLoaded', initTodos);

async function initTodos() {
  let getTodos = await readTodos();

  for (let getTodo of getTodos) {
    makeTodoList(getTodo);
  }
}

// todo list 추가
function addTodoList() {
  const addTodo = document.querySelector('#add-todo');
  const todoInput = document.querySelector('#todo-input');

  addTodo.addEventListener('click', async () => {
    // todo 생성 (POST)
    await createTodo();

    // todo POST한 todo data 가져오기
    let getTodos = await readTodos();
    let id = getTodos.length;
    let getTodo = await readTodos(id);

    // todo data를 리스트로 만들기
    makeTodoList(getTodo);

    todoInput.value = '';
  });
}

// todo data를 list로 만들기 + 완료/삭제 버튼 클릭 이벤트
function makeTodoList(getTodo) {
  const todoList = document.querySelector('#todo-list');
  const inputContent = getTodo.content;

  const liTag = document.createElement('li');

  const content = document.createElement('p');
  content.textContent = inputContent;

  // 완료 버튼 생성 및 버튼 클릭 이벤트
  const completedBtn = document.createElement('button');
  completedBtn.classList.add('btn', 'btn-primary', 'completeBtn');
  completedBtn.textContent = '완료';

  completedBtn.addEventListener('click', (event) =>
    completeBtnFunc(event, content, getTodo)
  );

  // 삭제 버튼 생성 및 버튼 클릭 이벤트
  const deleteBtn = document.createElement('button');
  deleteBtn.classList.add('btn', 'btn-danger', 'deleteBtn');
  deleteBtn.textContent = '삭제';

  deleteBtn.addEventListener('click', (event) => {
    event.target.parentElement.remove();
    deleteBtnFunc(getTodo.id);
  });

  liTag.append(content, completedBtn, deleteBtn);
  todoList.append(liTag);

  // 첫 페이지 로딩 시 completed 확인 -> 버튼 스타일 설정
  if (getTodo.completed) {
    content.classList.add('completed');
    completedBtn.classList.add('completed');
  }
}

// 완료 버튼 작동
async function completeBtnFunc(event, content, getTodo) {
  let completedVal = !getTodo.completed;
  let id = getTodo.id;

  if (!event.target.classList.contains('completed')) {
    event.target.classList.add('completed');
    content.classList.add('completed');
    content.style.textDecoration = 'line-through';
  } else {
    event.target.classList.remove('completed');
    content.classList.remove('completed');
    content.style.textDecoration = 'none';
  }
  await updateTodo(id, completedVal);
}

// 삭제 버튼 작동 (DELETE)
async function deleteBtnFunc(id) {
  const response = await fetch(`${URL}/${id}`, {
    method: 'DELETE',
  });
  if (response.ok) {
    console.log(`ID ${id} 삭제 완료`);
  }
}

// todo 수정 (PATCH)
async function updateTodo(id, completedVal) {
  let todo = {
    completed: completedVal,
  };

  const response = await fetch(`${URL}/${id}`, {
    method: 'PATCH',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(todo),
  });
  const updatedTodo = await response.json();
  return updatedTodo;
}

// todos 가져오기 (GET)
async function readTodos(id = null) {
  let path = `/${id}`;
  const url = id === null ? `${URL}` : `${URL}${path}`;
  const response = await fetch(url);
  const todos = await response.json();
  return todos;
}

// todo 생성 (POST, data 보내기)
async function createTodo() {
  const todoInputVal = document.querySelector('#todo-input').value;
  let newTodo = {
    content: todoInputVal,
    completed: false,
  };

  const response = await fetch(`${URL}`, {
    method: 'POST',
    body: JSON.stringify(newTodo),
    headers: {
      'Content-type': 'application/json; charset=UTF-8',
    },
  });
  const todo = await response.json();
  return todo;
}

addTodoList();
