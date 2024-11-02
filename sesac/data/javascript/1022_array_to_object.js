let people = [
  {
    name: 'jun',
    age: 15,
    gender: 'M',
  },
  {
    name: 'ken',
    age: 36,
    gender: 'F',
  },
  {
    name: 'alex',
    age: 37,
    gender: null,
  },
];

// 사람에 대한 정보
for (let person of people) {
  for (let key in person) {
    let value = person[key];
    console.log(key, value);
  }
  console.log();
}

// 이름이 alex인 사람 정보
for (let person of people) {
  let name = person.name;
  if (name === 'alex') {
    console.log(person);
  }
}

// alex의 나이
for (let person of people) {
  let name = person.name;
  if (name === 'alex') {
    let age = person.age;
    console.log(age);
  }
}

// // 변수 수정
// let persons = {};

// // 1. 객체에 삽입 방법
// // persons.keys = {1, 2, 3, 4};

// // 2. key 설정할 이름 추출하기
// // (keys 만들기)
// let names = [];
// for (let person of people) {
//   let name = person.name;
//   names.push(name);
// }
// console.log(names);

// // 3. object로 만들기
// // (persons.keys = {1, 2, 3, 4};) 형태 만들기
// for (let i = 0; i < names.length; i++) {
//   persons[names[i]] = people[i];
// }
// console.log(persons);

const persons = {};

for (let person of people) {
  let name = person.name;
  persons[name] = person;
}

console.log(persons);