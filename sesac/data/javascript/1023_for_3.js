// 10. 구구단이 들어있는 2차원 배열
let n = 2;
let m = 3;

let arrayNM = [];

for (let i = 1; i <= n; i++) {
  row = [];
  for (let j = 1; j <= m; j++) {
    let num = i * j;
    row.push(num);
  }
  arrayNM.push(row);
}
console.log(arrayNM);
