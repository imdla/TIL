// 1. 배열을 활용해 새 배열 만들기
const arr1 = [1, 2, 3, 4, 5];

// map
const newArr1 = arr1.map((num) => num * num);
console.log(newArr1);

// for
const newArr1For = [];

for (let num of arr1) {
  num *= num;
  newArr1For.push(num);
}
console.log(newArr1For);

console.log(
  '-----------------------------------------------------------------------------------'
);
// 2.
const arr2 = ['1', '2', '3', '4', '5'];

// map
const newArr2 = arr2.map((num) => Number(num));
console.log(newArr2);

// for
const newArr2For = [];

for (let num of arr2) {
  num = Number(num);
  newArr2For.push(num);
}
console.log(newArr2For);

console.log(
  '-----------------------------------------------------------------------------------'
);
// 3.
const arr3 = [
  {
    name: 'jun',
    age: 18,
  },
  {
    name: 'alex',
    age: 28,
  },
  {
    name: 'ken',
    age: 38,
  },
];

// map
const newArr3 = arr3.map((person) => {
  return person.age;
});
console.log(newArr3);

// for
const newArr3For = [];

for (let person of arr3) {
  let age = person.age;
  newArr3For.push(age);
}
console.log(newArr3For);

console.log(
  '-----------------------------------------------------------------------------------'
);
// 4.
const arr4 = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

// 4. 짝수만 들어있는 배열

// filter
const arrEven = arr4.filter((num) => {
  return num % 2 === 0;
});
console.log(arrEven);

// for
const arrEvenFor = [];

for (let num of arr4) {
  if (num % 2 === 0) {
    arrEvenFor.push(num);
  }
}
console.log(arrEvenFor);

console.log(
  '-----------------------------------------------------------------------------------'
);

// 4. 짝수이면서 5 이상인 숫자만 들어있는 배열

// filter
const arrEven5More = arr4.filter((num) => {
  return num % 2 === 0 && num >= 5;
});
console.log(arrEven5More);

// for
const arrEven5MoreFor = [];
for (let num of arr4) {
  if (num % 2 === 0 && num >= 5) {
    arrEven5MoreFor.push(num);
  }
}
console.log(arrEven5MoreFor);

console.log(
  '-----------------------------------------------------------------------------------'
);
// 5.
const arr5 = [
  {
    name: 'jun',
    age: 18,
  },
  {
    name: 'alex',
    age: 28,
  },
  {
    name: 'ken',
    age: 38,
  },
  {
    name: 'beemo',
    age: 48,
  },
  {
    name: 'lynda',
    age: 8,
  },
];

// 5. age가 30이하인 사람들의 정보 배열

// filter
const age30DownArr = arr5.filter((person) => {
  return person.age <= 30;
});
console.log(age30DownArr);

// for
const age30DownArrFor = [];

for (let person of arr5) {
  if (person.age <= 30) {
    age30DownArrFor.push(person);
  }
}
console.log(age30DownArrFor);

console.log(
  '-----------------------------------------------------------------------------------'
);

// 5. age가 30이하인 사람들의 이름이 담긴 배열

// filter & map
const age30DownArrName = arr5
  .filter((person) => {
    return person.age <= 30;
  })
  .map((person) => {
    return person.name;
  });
console.log(age30DownArrName);

// for
const age30DownArrNameFor = [];

for (let person of arr5) {
  if (person.age <= 30) {
    age30DownArrNameFor.push(person.name);
  }
}
console.log(age30DownArrNameFor);
