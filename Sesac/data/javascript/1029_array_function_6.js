// 마지막 홀수
const arr = [1, 3, 5, -3, 9, 10, 23, -6, 44, 22, -10, 5, 20];

for (let i = arr.length - 1; i >= 0; i--) {
  if (arr[i] % 2) {
    console.log(arr[i]);
    break;
  }
}
