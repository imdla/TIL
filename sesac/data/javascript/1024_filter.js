// filter가지고 소수만 모아보기
const nums = [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15];

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

const primeNum = nums.filter((num) => checkPrime(num));

console.log(primeNum);
