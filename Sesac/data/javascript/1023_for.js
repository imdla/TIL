// 1. 더하기 함수

function add(x1, x2) {
  return x1 + x2;
}

let a = 1;
let b = 2;
console.log(a);
console.log(b);

let addNum = add(a, b);
console.log(addNum);

console.log();
// 크기 비교 함수

function compare(x1, x2) {
  if (x1 > x2) {
    return x1;
  } else if (x1 < x2) {
    return x2;
  } else {
    return null;
  }
}

console.log(a);
console.log(b);

let bigNum = compare(a, b);
console.log(bigNum);

console.log();
// 양수, 음수, 0

function determine(x) {
  if (x > 0) {
    return 1;
  } else if (x < 0) {
    return -1;
  } else {
    return 0;
  }
}

let num = 10;
console.log(num);

let checkPlusOrMinus = determine(num);
console.log(checkPlusOrMinus);

console.log();
// 가장 큰 값

function maxNumber(arr) {
  let max = -Infinity;
  for (let num of arr) {
    if (num > max) {
      max = num;
    }
  }

  return max;
}

let arrNum = [10, 25, 36, 99, 58];
console.log(arrNum);

let maxNum = maxNumber(arrNum);
console.log(maxNum);

console.log();
// 가장 작은 값

function minNumber(arr) {
  let min = Infinity;
  for (let num of arr) {
    if (num < min) {
      min = num;
    }
  }

  return min;
}

console.log(arrNum);

let minNum = minNumber(arrNum);
console.log(minNum);

console.log();
// 짝수

function evenNumber(arr) {
  let new_arr = [];
  for (let num of arr) {
    if (num % 2 === 0) {
      new_arr.push(num);
    }
  }

  return new_arr;
}

console.log(arrNum);

let evenNum = evenNumber(arrNum);
console.log(evenNum);
