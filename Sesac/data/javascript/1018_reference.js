let fruits = ['사과', '바나나', '딸기'];
console.log(fruits);

let numbers = [1, 2, '3'];
console.log(numbers);

console.log(fruits[0]);

numbers.push(4);
console.log(numbers);

let pop_number = numbers.pop();
console.log(pop_number);

numbers.unshift('0');
console.log(numbers);

let number = numbers.shift();

console.log(number);
console.log(numbers);

let slice_numbers = numbers.slice(0, 2);
console.log(slice_numbers);
console.log(numbers);

let person = {
  name: '홍길동',
  age: 30,
  job: '개발자',
};

console.log(person.name);
console.log(person['job']);
person.age = 40;
console.log(person);

let setValue = new Set([1, 2, 3, 4]);
console.log(setValue);

setValue.add(5);
setValue.add(7);
console.log(setValue);

console.log(setValue.has(3));
console.log(setValue.has(9));

setValue.delete(3);
console.log(setValue);
