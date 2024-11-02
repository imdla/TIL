// 글로벌 환경 변수 선언 -> 블럭 사용

const const_1 = 'const_1';
let let_1 = 'let_1';

{
  console.log(const_1);
  console.log(let_1);
}

console.log();
// 블럭에서 변수 선언 -> 글로벌

{
  const const_2 = 'const_2';
  let let_2 = 'let_2';
}

// 불가능
// console.log(const_2);
// console.log(let_2);

console.log();
// 글로벌 환경 변수 선언 -> 블럭 선언

const const_3 = 'const_3_outer';
let let_3 = 'let_3_outer';

{
  const const_3 = 'const_3_inner';
  let let_3 = 'let_3_inner';
}

console.log(const_3);
console.log(let_3);

console.log();
// 글로벌 환경 변수 선언 -> 블럭 할당

let let_4 = 'let_4_outer';

{
  let_4 = 'let_4_inner';
}

console.log(let_4);
