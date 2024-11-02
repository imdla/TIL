console.log('1번 ------------------------------------------------------------');
// 1. 40 이하의 3의 배수를 출력하시오(3단의 확장).
for (let i = 3; i < 40; i += 3) {
  console.log(i);
}

console.log('2번 ------------------------------------------------------------');
// 2. 1 ~ 100 중에 7의 배수의 개수를 출력하세요.
let count = 0;
for (let i = 7; i < 101; i += 7) {
  count++;
}
console.log(count);

console.log('3번 ------------------------------------------------------------');
// 3. `const arr = [1, 2, 4, 3, 3, 5, 5, 6, 9, 7]` 일 때,
// 원소가  1, 4, 5, 7인 경우를 제외하고 출력하세요
const arr = [1, 2, 4, 3, 3, 5, 5, 6, 9, 7];

console.log('----- 풀이 1');
// 풀이 1
for (let i = 0; i < arr.length; i++) {
  let num = arr[i];

  if (num === 1 || num == 4 || num === 5 || num === 7) {
    continue;
  }

  console.log(num);
}

console.log('----- 풀이 2');
// 풀이 2
const notLog = [1, 4, 5, 7];

for (let num of arr) {
  let isInNotLog = false;

  for (let notLogNum of notLog) {
    if (num === notLogNum) {
      isInNotLog = true;
      break;
    }
  }

  if (isInNotLog) continue;
  console.log(num);
}

console.log('4번 ------------------------------------------------------------');
// 4. 1 ~ 9 까지의 자연수 중 제곱한 수가 10 이상 50 이하인 자연수의 리스트를 출력해보세요.
let squareList = [];

for (let i = 1; i < 10; i++) {
  let squareNum = i ** 2;
  if (squareNum >= 10 && squareNum <= 50) {
    squareList.push(i);
  }
}
console.log(squareList);

console.log('5번 ------------------------------------------------------------');
// 5. 두개의 숫자에 대해. 두 수 사이에 속한 모든 정수의 합을 구하시오. (ex. 2와 5를 입력받는다면 2 + 3 + 4 + 5 = 14)
let num1 = 2;
let num2 = 5;

if (num1 > num2) {
  let tmp = num1;
  num1 = num2;
  num2 = tmp;
}

let sum = 0;
for (let i = num1; i < num2 + 1; i++) {
  sum += i;
}
console.log(sum);

console.log('6번 ------------------------------------------------------------');
// 6. 2의 배수 혹은 3의 배수를 제외하고 1~30까지 숫자를 출력하시오.
//  `1 5 7 11 13 17 19 23 25 29`

// 풀이 1
for (let i = 1; i < 31; i++) {
  if (!(i % 2) || !(i % 3)) {
    continue;
  }
  console.log(i);
}

// 풀이 2
for (let i = 1; i <= 30; i++) {
  if (i % 2 && i % 3) {
    console.log(i);
  }
}

console.log('------------------------------------------------------------');
// 7. 자연수 n이 주어졌을 때, n이 소수인지 판단하시오.
let n = 10;
let result = '소수';

if (n === 1) {
  result = '소수 아님';
} else {
  for (let i = 2; i < n; i++) {
    if (n % i === 0) {
      result = '소수 아님';
      break;
    }
  }
}
console.log(result);

console.log('8번 ------------------------------------------------------------');
// 8. 다음 문제를 푸시오

const people = {
  names: ['jun', 'ken', 'alex'],
  ages: [15, 26, 37],
  genders: ['M', 'F', null],
};

console.log('----- 각 사람 정보 출력');
for (let i = 0; i < 3; i++) {
  console.log(`${i + 1}번`);

  for (let key in people) {
    let val = people[key];
    console.log(`${key}: ${val[i]}`);
  }
}

console.log('----- alex 정보 출력');
let alexIdx;

for (let key in people) {
  let info = people[key];
  for (let i = 0; i < 3; i++) {
    if (info[i] === 'alex') {
      alexIdx = i;
    }
  }
  console.log(`${key}: ${info[alexIdx]}`);
}

console.log('----- alex 나이 출력');
let alexAge = people.ages[alexIdx];
console.log(alexAge);

console.log('----- 이름을 활용한 각 사람 데이터 쉬운 접근');
const person = {
  jun: [15, 'M'],
  ken: [26, 'F'],
  alex: [37, null],
};
const info = ['나이', '성별'];

for (let key in person) {
  console.log(key);
  for (let i = 0; i < 2; i++) {
    console.log(`${info[i]} : ${person[key][i]}`);
  }
}

console.log('8-1번 ------------------------------------------------------------');
// 8-1. 원래 리스트에서 객체로 변경
let people2 = [
  {
    name: 'jun',
    age: 15,
    gender: 'M',
  },
  {
    name: 'ken',
    age: 26,
    gender: 'F',
  },
  {
    name: 'alex',
    age: 37,
    gender: null,
  }
];
