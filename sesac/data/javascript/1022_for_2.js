// 1. 객체와 배열1
let todos = [];
todos.push(
  {
    todoId: 1,
    content: '예습하기',
    isCompleted: false,
  },
  {
    todoId: 2,
    content: '강의듣기',
    isCompleted: false,
  },
  {
    todoId: 3,
    content: '복습하기',
    isCompleted: false,
  }
);
console.log(todos);

console.log();
// 2. 객체와 배열2
for (let obj of todos) {
  let con = obj.content;
  let isCom = obj.isCompleted;
  console.log(`content: ${con}, isCompleted: ${isCom}`);
}

console.log();
// 3. 객체와 배열3
for (let obj of todos) {
  let id = obj.todoId;
  if (id === 2) {
    console.log(obj);
  }
}

console.log();
// 4. 객체와 배열4
for (let obj of todos) {
  let id = obj.todoId;
  if (id === 2) {
    obj.isCompleted = true;
  }
}
console.log(todos);

console.log();
// 5. 객체와 배열5
