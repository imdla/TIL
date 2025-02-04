// 1. 2를 곱하는 함수

function multiply(x) {
  return x * 2;
}

console.log(multiply(5, 4));

// 2. 소수 판별하는 함수

function checkPrime(n) {
  let isPrime = true;

  for (let i = 2; i < n; i++) {
    if (n % i === 0) {
      isPrime = false;
      return isPrime;
    }
  }

  return isPrime;
}

console.log(checkPrime(5));

// 3. 구구단 n단을 return하는 함수

function gugudan(n) {
  let arrNN = [];

  for (let i = 1; i <= n; i++) {
    row = [];
    for (let j = 1; j <= n; j++) {
      let num = i * j;
      row.push(num);
    }
    arrNN.push(row);
  }

  return arrNN;
}

console.log(gugudan(5));

function nDan(n) {
  row = [];

  for (let j = 1; j <= 9; j++) {
    let num = n * j;
    row.push(num);
  }

  return row;
}
