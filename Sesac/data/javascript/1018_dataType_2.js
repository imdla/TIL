// 숫자 크기 비교 1 1
console.log('숫자 크기 비교 1');

let number_1 = 5;
console.log(number_1);

if (number_1 < 10) {
  console.log('number_1은 10보다 작다.');
}

console.log('==============================================================');

// 짝수 2
console.log('짝수 확인');

let number_2 = 2;
console.log(number_2);

if (number_2 % 2 === 0) {
  console.log('짝수');
}

console.log('==============================================================');

// 자료형 확인 3
console.log('자료형 확인');

let variable = 5;
console.log(variable);

if (typeof variable === 'number') {
  console.log('숫자형');
}

console.log('==============================================================');

// 숫자 크기 비교 2 4
console.log('숫자 크기 비교 2');

let num1 = 5;
console.log(num1);

if (num1 < 10) {
  console.log('num1은 10보다 작다.');
} else if (num1 > 10) {
  console.log('num1은 10보다 크다.');
}

console.log('==============================================================');

// 합격 / 불합격 5
console.log('합격 / 불합격');

let score = 50;
console.log(score);

if (score >= 60) {
  console.log('합격');
} else {
  console.log('불합격');
}

console.log('==============================================================');

// 숫자 크기 비교 3 6
console.log('숫자 크기 비교 3');

let number1 = 11;
console.log(number1);

if (number1 < 10) {
  console.log('number1은 10보다 작다.');
} else if (number1 > 10) {
  console.log('number1은 10보다 크다');
} else {
  console.log('number1은 10이다');
}

console.log('==============================================================');

// 숫자 크기 비교 4 7 
console.log('숫자 크기 비교 4');

let number2 = 'hello';
console.log(number2);

if (typeof number2 === 'number') {
  if (number2 < 10) {
    console.log('number2는 10보다 작다');
  } else if (number2 > 10) {
    console.log('number2는 10보다 크다');
  } else {
    console.log('number2는 10이다');
  }
} else {
  console.log('숫자가 아니다');
}
console.log('==============================================================');

// 짝수 / 홀수 8
console.log('짝수 / 홀수');

let num2 = 0;
console.log(num2);

if (num2 === 0) {
  console.log('영');
} else if (num2 % 2 === 0) {
  console.log('짝수');
} else {
  console.log('홀수');
}

console.log('==============================================================');

// 아이디 / 비밀번호 확인 9
console.log('아이디 / 비밀번호 확인');

let username = 'sesac';
let password = 'sesac1234';

console.log(username);
console.log(password);

if (username === 'sesac' && password === 'sesac1234') {
  console.log('로그인 성공');
} else {
  console.log('로그인 실패');
}

console.log('==============================================================');

// 두 수 비교 10
console.log('두 수 비교');

let num3 = 5;
let num4 = 10;

console.log(num3);
console.log(num4);

if (num3 > num4) {
  console.log(num3);
} else if (num3 < num4) {
  console.log(num4);
} else {
  console.log('같다');
}

console.log('==============================================================');

// 2와 3의 배수 11
console.log('2와 3의 배수');

let num5 = 6;
console.log(num5);

if (num5 % 2 === 0 && num5 % 3 === 0) {
  console.log('6의 배수');
} else if (num5 % 2 === 0) {
  console.log('2의 배수');
} else if (num5 % 3 === 0) {
  console.log('3의 배수');
} else {
  console.log('2의 배수도 아니고, 3의 배수도 아니다.');
}
