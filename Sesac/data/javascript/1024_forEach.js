const numbers = [1, 2, 3, 4, 5];

// forEach
numbers.forEach((num) => {
  console.log(num); // conLog
});

console.log();
// forEach 내부 분해
const conLog = (num) => {
  console.log(num);
};

numbers.forEach(conLog);

console.log();
// forEach를 분해
function myForEach(arr, func) {
  for (let value of arr) {
    func(value);
  }
}

myForEach(numbers, conLog);

