// 배열의 반복문 1
const arr = [1, 2, 3, 4, 5];

for (let v of arr) {
  console.log(v);
}

console.log('---------------------');

// 배열의 반복문 2
for (let v of arr) {
  console.log(v ** 2);
}

console.log('---------------------');

// 배열의 반복문 3
const arrs = [0, 1, 2, 3, 4, 5];

for (let v of arrs) {
  if (v > 0 && v % 2 === 0) {
    console.log(v);
  }
}

console.log('---------------------');

// 객체의 반복문 1
let person = {
  name: '홍길동',
  age: 30,
  job: '개발자',
};

for (let i in person) {
  let value = person[i];
  console.log(`${i}, ${value}`);
}
