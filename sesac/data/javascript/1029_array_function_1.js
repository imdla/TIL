// 1. 3가지 방법으로 배열 원소 출력
const arr = [1, 3, 5, -3, 9, 10, 23, -6, 44, 22, -10, 5, 20];

function fstPrint() {
  for (let i = 0; i < arr.length; i++) {
    console.log(arr[i]);
  }
}

fstPrint();

console.log();
function sndPrint() {
  for (let el of arr) {
    console.log(el);
  }
}

sndPrint();

console.log();
function trdPrint(arr) {
  arr.forEach((el) => console.log(el));
}

trdPrint(arr);
