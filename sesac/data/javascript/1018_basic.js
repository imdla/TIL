// or 상황
let water = true;
let milk = true;

console.log(water === true || milk === true);
console.log(water === true || milk === false);
console.log(water === false || milk === true);
console.log(water === false || milk === false);

console.log('=========================================');
// and 상황

let money = 10000;
let hungry = 'o';

console.log(money > 1000 && hungry === 'o');
console.log(money > 1000 && hungry !== 'o');
console.log(money < 1000 && hungry === 'o');
console.log(money < 1000 && hungry !== 'o');

console.log('=========================================');
// 변수

let age = 25;
console.log(age);

let greet = 'Hello!';
console.log(greet);

let isOnline = true;
console.log(isOnline);
console.log(typeof isOnline);

let message = `${greet} 는 미국 인사입니다.`;
console.log(message);

console.log('=========================================');
// 산술 연산자

let a = 5;
let b = 3;
console.log(a + b);
console.log(a - b);
console.log(a * b);
console.log(a / b);

console.log('=========================================');
// 추가 연산자

a = 5;
b = 3;

console.log(b % 3);
console.log(a ** b);
console.log(a++);
console.log(++a);
console.log(b--);
console.log(--b);

console.log('=========================================');
// 할당 연산자

a = 5;
b = 3;
console.log((a += 5));
console.log((b /= 4));
console.log((a *= 5));
console.log(10 % a);
console.log(5 === 4);
console.log(10 > 5 || 10 >= 5);
console.log(Math.floor(7 / 2));

console.log('=========================================');

console.log('||');
console.log(true || false);
console.log(false || true);
console.log(false || false);
console.log(true || true);

console.log('=========================================');

console.log('&&');
console.log(true && true);
console.log(true && false);
console.log(false && true);
console.log(false && false);

console.log('=========================================');

console.log(typeof 'apple');
console.log(typeof 20);
console.log(typeof true);
