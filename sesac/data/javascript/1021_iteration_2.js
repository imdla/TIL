// nums의 구구단 3단
const nums = [1, 2, 3, 4, 5, 6, 7, 8, 9];

// 풀이 1
for (let i in nums) {
  let num = nums[i] * 3;
  console.log(num);
}

// 풀이2
for (let i of nums) {
  console.log(i * 3);
}

console.log();

// 출석 번호
const names = ['jun', 'beemo', 'ken', 'lynda'];

for (let i in names) {
  let name = names[i];
  i++;
  console.log(`${i}번 ${name}`);
}

console.log();

// 이름과 나이 출력
const names2 = ['jun', 'beemo', 'ken'];
const ages = [18, 28, 37];

for (let i in names2) {
  let name = names2[i];
  let age = ages[i];
  console.log(`${age}살 ${name}`);
}

console.log();

// 주어진 리스트의 길이
const names3 = ['jun', 'beemo', 'ken', 'lynda'];

// 풀이 1
console.log(names3.length);

// 풀이 2
let count = 0;
for (_ of names3) {
  count++;
}
console.log(count);

console.log();

// 주어진 리스트의 합계
const nums2 = [1, 2, 3, 4, 5, 6, 7, 8, 9];

let sum = 0;
for (let i of nums2) {
  sum += i;
}
console.log(sum);

console.log();

// 주어진 리스트에서 최소값
const nums3 = [10, 6, 8, 5, 4, 2, 3, 11];

let min = 999;
for (let i of nums3) {
  if (min > i) {
    min = i;
  }
}
console.log(min);

console.log();

// 주어진 리스트의 순서 뒤집기
const nums4 = [10, 6, 8, 5, 4];

// 풀이 1
const reversedNum = [];
for (let i of nums4) {
  reversedNum.unshift(i);
}

console.log(reversedNum);

// 풀이 2
const reversedNum2 = [];
for (let i in nums4) {
  let index = (nums4.length-1) - i;
  let num = nums4[index];
  reversedNum2.push(num);
}

console.log(reversedNum2);
