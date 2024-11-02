// 주어진 테이블의 데이터를 "people" 변수에 저장하시오.
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

const objectPeople = {};

for (const person of people) {
  const name = person.name;
  objectPeople[name] = person;
}

// const person = people[0];

// const name = person.name;

// objectPeople[name] = person;
