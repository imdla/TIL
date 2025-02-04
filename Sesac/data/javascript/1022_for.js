// 1. 1부터 N까지 출력1
let N = 5;
console.log(5);

for (let i = 1; i <= N; i++) {
  console.log(i);
}

console.log();
// 2. 1부터 N까지 출력2
let i = 1;

while (i <= N) {
  console.log(i);
  i++;
}

console.log();
// 3. 1부터 N까지의 합
let total = 0;

for (let i = 1; i <= N; i++) {
  total += i;
}
console.log(total);

console.log();
// 4. 짝수의 합
total = 0;

for (let i = 1; i <= N; i++) {
  if (i % 2) {
    continue;
  }
  total += i;
}
console.log(total);

console.log();
// 5. 짝수의 개수
let count = 0;

for (let i = 1; i <= N; i++) {
  if (i % 2) {
    continue;
  }
  count++;
}
console.log(count);

console.log();
// 6. 반복문 제어
let arr = [1, 9, 3, 11, 4, 5, 2, 7];

for (let val of arr) {
  if (!(val % 2)) {
    break;
  }
  console.log(val);
}