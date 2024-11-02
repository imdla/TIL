// 새로운 배열
const arr = [1, 3, 5, -3, 9, 10, 23, -6, 44, 22, -10, 5, 20];

const newArr = arr.filter((el) => {
  return el > 0 && el % 2 === 0;
});

console.log(newArr);
