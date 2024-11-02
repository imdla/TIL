// map 이용해 str -> num 으로 타입 변환
const arrStrNum = ['1', '2', '3', '4'];

const arrNum = arrStrNum.map((num) => {
  return parseInt(num);
});

console.log(arrNum);

// forEach
const arrNum2 = [];

arrStrNum.forEach((num) => {
  let number = parseInt(num);
  console.log(number);

  arrNum2.push(number);
});

console.log(arrNum2);
