// 배열 원소의 곱
const arr = [1, 3, 5, -3, 9, 10, 23, -6, 44, 22, -10, 5, 20];

let doubleArr = arr.reduce((acc, cur) => acc * cur);
console.log(doubleArr);
