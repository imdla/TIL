// x보다 큰 값
const x = 5;
console.log(x);

const arr = [1, 3, 5, -3, 9, 10, 23, -6, 44, 22, -10, 5, 20];

const newArr = arr.filter((el) => {
  return el > 5;
});

console.log(newArr);
