// 문자열 처리
let str = 'JavaScript';
console.log(str.charAt('4'));

console.log(str.includes('S'));

console.log();
let sentence = 'Hello world! How are you?';
const words = sentence.split(' ');
console.log(words);

const reSentence = words.join(' ');
console.log(reSentence);

const upSentence = sentence.toUpperCase();
console.log(upSentence);

const lowSentence = sentence.toLowerCase();
console.log(lowSentence);

console.log();
const text = '   Hello World   ';
console.log(text.trim());

console.log();
// 숫자 처리 함수
console.log(parseInt('123456'));

let num = 3.14159;
console.log(num.toFixed(2));

console.log(Math.random());
