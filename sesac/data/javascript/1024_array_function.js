// 배열 생성
const arr = [1, 3, 5, -3, 9, 10, 23, -6, 44, 22, -10, 5, 20];

// 양수 출력
arr.forEach((num) => {
  if (num > 0) {
    console.log(num);
  }
});

console.log();
// 곱하기 2
const doubleNum = arr.map((num) => num * 2);
console.log(doubleNum);

console.log();
// 양수 홀수 배열
const plusOddNum = arr.filter((num) => {
  return num > 0 && num % 2;
});
console.log(plusOddNum);

console.log();
// 첫번째 5 찾기
const five = arr.findIndex((num) => num === 5);
console.log(five);

console.log();
// 배열 내 객체 출력하기
const todos = [
  {
    todoId: 1,
    content: '예습하기',
    isCompleted: false,
  },
  {
    todoId: 2,
    content: '강의듣기',
    isCompleted: true,
  },
  {
    todoId: 3,
    content: '복습하기',
    isCompleted: true,
  },
];

todos.forEach((obj) => console.log(obj));

console.log();
// 배열 내 객체 출력하기2

todos.filter((obj) => {
  if (obj.isCompleted === true) {
    console.log(obj);
  }
});

console.log();
// 이차원 배열 출력

const matrix = [
  [4, 2],
  [3, 2],
  [5, 7],
  [10, 1],
];

matrix.forEach((arr) => {
  console.log(arr[1]);
});

console.log();
// 이차원 배열의 합

const arrSumEven = matrix.filter((arr) => {
  let sum = arr.reduce((acu, cur) => acu + cur);
  if (sum % 2 === 0) {
    return true
  }
});
console.log(arrSumEven);