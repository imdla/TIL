// for와 while문 만들기

// 1번 - nums의 구구단 3단 출력 =========================================
const nums = [1, 2, 3, 4, 5, 6, 7, 8, 9];

console.log('-- while --');
// while
let i = 0;

while (i < nums.length) {
  console.log(`3 * ${i + 1} = ${nums[i] * 3}`);
  i++;
}

console.log('-- for --');
// for
for (let i = 0; i < nums.length; i++) {
  console.log(`3 * ${i + 1} = ${nums[i] * 3}`);
}

console.log('------------------------------');
// 2번 - 출석번호 =========================================

const names = ['jun', 'beemo', 'ken', 'lynda'];

console.log('-- while --');
// while
i = 0;

while (i < names.length) {
  let num = i+1;
  let name = names[i]
  console.log(`${num}번 ${name}`);
  i++;
}

console.log('-- for --');
// for
for (let i = 0; i < names.length; i++) {
  let num = i+1;
  let name = names[i]
  console.log(`${num}번 ${name}`);
}

console.log('------------------------------');
// 3번 - names와 ages =========================================

const names2 = ['jun', 'beemo', 'ken'];
const ages = [18, 28, 37];

console.log('-- while --');
// while
i = 0;
while (i < names2.length) {
  let age = ages[i]
  let name = names2[i]
  console.log(`${age}살 ${name}`);
  i++;
}

console.log('-- for --');
// for
for (let i = 0; i < names2.length; i++) {
  let age = ages[i];
  let name = names2[i];
  console.log(`${age}살 ${name}`);
}

console.log('------------------------------');
// 4번 - 리스트의 합계 =========================================

const nums2 = [1, 2, 3, 4, 5, 6, 7, 8, 9];

console.log('-- while --');
// while
i = 0;
let sum = 0;

while (i < nums2.length) {
  sum += nums2[i];
  i++;
}
console.log(sum);

console.log('-- for --');
// for

sum = 0;

for (let i = 0; i < nums2.length; i++) {
  sum += nums[i];
}
console.log(sum);