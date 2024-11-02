// 2, 4, 6, 8, 10 출력
let num = 0;

while (num <= 8) {
  num += 2;
  console.log(num);
}

console.log('----------------------------');

// arr가 빌 때까지 el 출력
const arr = [2, 3, 5, 1, 3];

// 1. 인덱스 하나씩 선택해
// 2. 빼주고
// 3. 뺀 것을 출력

for (let i = 0; i < 5; i++) {
  let el = arr.shift();
  console.log(el);
}

while (arr.length) {
  el = arr.pop();
  console.log(el);
}
