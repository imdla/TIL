// 기본 데이터 타입
let car: string = "bmw";

let car2 = "bmw";

let age: number = 30;
let isAdult: boolean = true;
let a: number[] = [1, 2, 3];

let week1: string[] = ["월", "화", "수", "목", "금"];

// 튜플 (Tuple)
let b: [string, number];

b = ["z", 1];
b[0].toLowerCase();

// void, never
function greet(): void {
  console.log("hello");
}

function showError(): never {
  throw new Error();
}

// enum
enum Os {
  Window = "win",
  Ios = "ios",
  Android = "and",
}

let myOs: Os;

myOs = Os.Window;

// null, undefined
let c: null = null;
let d: undefined = undefined;
