// API 베이스 URL
const URL = 'http://localhost:3000/todos';

// DOMContentLoaded : HTML 문서 로딩이 끝나면 실행되는 이벤트
// 페이지가 로드되면 Todo 목록 초기화 함수를 실행한다.
document.addEventListener('DOMContentLoaded', initTodos);

async function initTodos() {
  try {
    let todos = await getTodos();

    for (let todo of todos) {
      makeTodoItem(todo);
    }
  } catch (error) {
    alert('todo 조회 오류입니다.');
  }

  addTodoItem();
}

// todo list 추가
function addTodoItem() {
  const addTodoBtn = document.querySelector('#add-todo');
  const todoInput = document.querySelector('#todo-input');

  addTodoBtn.addEventListener('click', async () => {
    // todo 생성 (POST)
    let todo = await createTodo(todoInput);

    // todo data를 리스트로 만들기
    makeTodoItem(todo);

    todoInput.value = '';
  });
}

// todo data를 list로 만들기 + 완료/삭제 버튼 클릭 이벤트
function makeTodoItem(todo) {
  const todoList = document.querySelector('#todo-list');
  const { id, content, completed } = todo;

  const liTag = document.createElement('li');

  const pTag = document.createElement('p');
  pTag.textContent = content;

  // 완료 버튼 생성 및 버튼 클릭 이벤트
  const completedBtn = document.createElement('button');
  completedBtn.classList.add('btn', 'btn-primary', 'completeBtn');
  completedBtn.textContent = '완료';

  completedBtn.addEventListener('click', (event) => {
    completeBtnClick(event, pTag, todo);
  });

  // 삭제 버튼 생성 및 버튼 클릭 이벤트
  const deleteBtn = document.createElement('button');
  deleteBtn.classList.add('btn', 'btn-danger', 'deleteBtn');
  deleteBtn.textContent = '삭제';

  deleteBtn.addEventListener('click', async (event) => {
    try {
      await deleteBtnClick(id);

      event.target.parentElement.remove();
    } catch (error) {
      alert('삭제 버튼 오류입니다.');
    }
  });

  liTag.append(pTag, completedBtn, deleteBtn);
  todoList.append(liTag);

  // 첫 페이지 로딩 시 completed 확인 -> 버튼 스타일 설정
  if (completed) {
    pTag.classList.add('completed');
    completedBtn.classList.add('completed');
  }
}

// 완료 버튼 작동
async function completeBtnClick(event, pTag, todo) {
  let completedVal = !todo.completed;
  let id = todo.id;

  try {
    await updateTodo(id, completedVal);

    if (!completedVal) {
      event.target.classList.add('completed');
      pTag.classList.add('completed');
    } else {
      event.target.classList.remove('completed');
      pTag.classList.remove('completed');
    }
  } catch (error) {
    alert('완료 버튼 오류입니다.');
  }
}

// 삭제 버튼 작동 (DELETE)
async function deleteBtnClick(id) {
  const response = await fetch(`${URL}/${id}`, {
    method: 'DELETE',
  });
  if (response.ok) {
    console.log(`ID ${id} 삭제 완료`);
  }
}

// todo 수정 (PATCH)
async function updateTodo(id, completedVal) {
  let modifyTodo = {
    completed: completedVal,
  };

  const response = await fetch(`${URL}/${id}`, {
    method: 'PATCH',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(modifyTodo),
  });
  const updatedTodo = await response.json();
  return updatedTodo;
}

// todos 가져오기 (GET)
async function getTodos() {
  const response = await fetch(URL);
  const todos = await response.json();
  return todos;
}

// todo 생성 (POST, data 보내기)
async function createTodo(todoInput) {
  const todoInputVal = todoInput.value;

  let newTodo = {
    content: todoInputVal,
    completed: false,
  };

  try {
    const response = await fetch(`${URL}`, {
      method: 'POST',
      body: JSON.stringify(newTodo),
      headers: {
        'Content-type': 'application/json; charset=UTF-8',
      },
    });
    const todo = await response.json();
    return todo;
  } catch (error) {
    alert('todo 생성 오류입니다.');
  }
}
