// minValue를 reduce
const numbers = [5, 8, 2, 5, 9, 4];

const minValue = numbers.reduce(
  (min, cur) => (min < cur ? min : cur),
  Infinity
);
console.log(minValue);
