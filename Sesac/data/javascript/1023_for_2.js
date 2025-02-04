// 문제 9번
const nums = [
  [1, 2, 3],
  [4, 5, 6],
  [7, 8, 9],
];

// 순서대로 출력
// for (let numArr of nums) {
//   for (let num of numArr) {
//     console.log(num);
//   }
// }

const n = nums.length;
const m = nums[0].length;

for (let i = 0; i < n; i++) {
  for (let j = 0; j < m; j++) {
    console.log(nums[i][j]);
  }
}

console.log();
// 9-1. 행들의 합의 값을 리스트로 만들기

let arrRow = [];

for (let i = 0; i < n; i++) {
  let rowSum = 0;
  for (let j = 0; j < m; j++) {
    rowSum += nums[i][j];
  }
  arrRow.push(rowSum);
}
console.log(arrRow);

console.log();
// 9-2. 모든 원소들의 합

let allSum = 0;

for (let i = 0; i < n; i++) {
  for (let j = 0; j < m; j++) {
    allSum += nums[i][j];
  }
}
console.log(allSum);

console.log();
// 9-3. 열들의 합의 값으로 리스트 만들기

let arryCol = [];

for (let j = 0; j < m; j++) {
  let colSum = 0;
  for (let i = 0; i < n; i++) {
    colSum += nums[i][j];
  }
  arryCol.push(colSum);
}
console.log(arryCol);

console.log();
var _ = require('lodash');
console.log(_.sum([1, 2, 3]));
