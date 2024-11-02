// 변수와 상수
console.log('변수와 상수');
let let_variable = 'Hello World';
const const_variable = 'Hello World';

console.log(`let_variable : ${let_variable}`);
console.log(`const_variable : ${const_variable}`);

console.log(
  '========================================================================'
);

// 숫자형 변수와 문자열 변수
console.log('숫자형 변수와 문자열 변수');

let number_variable = 1;
let string_variable = '1';
console.log(
  `number_variable : ${number_variable}, typeof : ${typeof number_variable}`
);
console.log(
  `string_variable : ${string_variable}, typeof : ${typeof string_variable}`
);

console.log(
  '========================================================================'
);

// 상수형 변수
console.log('상수형 변수');

let variable = '상수형 변수에는 재할당 할 수 없다.';
variable = '재할당';
console.log(variable);

console.log(
  '========================================================================'
);

// 템플릿 리터럴
console.log('템플릿 리터럴');

let name = '임수빈';
let hello = `안녕하세요. ${name} 입니다.`;
console.log(hello);

console.log(
  '========================================================================'
);

// 사칙연산
console.log('사칙연산');

let number1 = 100;
let number2 = 200;

console.log(`덧셈 : ${number1 + number2}`);
console.log(`뺄셈 : ${number1 - number2}`);
console.log(`곱셈 : ${number1 * number2}`);
console.log(`나눗셈 : ${number1 / number2}`);

console.log(
  '========================================================================'
);

// 나머지
console.log('나머지');

let evenNum = 2;
let oddNum = 3;

console.log(`evenNum을 2로 나눈 나머지 : ${evenNum % 2}`);
console.log(`oddNum을 2로 나눈 나머지 : ${oddNum % 2}`);

console.log(
  '========================================================================'
);

// 배열 생성과 인덱싱
console.log('배열 생성과 인덱싱');

const number_arr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
console.log(number_arr[0]);
console.log(number_arr[2]);
console.log(number_arr[8]);
console.log(number_arr[9]);

console.log(
  '========================================================================'
);

// 빈 배열과 원소 추가
console.log('빈 배열과 원소 추가');

const arr = [];
arr.push(10);
arr.push(20);
arr.push(30);
arr.push(40);
arr.push(50);

console.log(arr);

console.log(
  '========================================================================'
);

// 객체 값 접근
console.log('객체 값 접근');

let todo = {
  userId: 1,
  id: 1,
  title: 'delectus aut autem',
  completed: false,
};

console.log(todo.userId);
console.log(todo.id);
console.log(todo.title);
console.log(todo.completed);

console.log(
  '========================================================================'
);

// 객체 생성
console.log('객체 생성');

const person = {
  name: '임수빈',
  age: 26,
  city: '서울',
  location: '동대문',
  language: ['HTML', 'CSS', 'JS'],
};

console.log(person);
