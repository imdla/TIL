// interface
type Score = "A" | "B" | "C" | "D";

interface User {
  name: string;
  age: number;
  gender?: string;
  readonly birthYear: number;
  [grade: number]: Score;
}

let user: User = {
  name: "user",
  age: 10,
  birthYear: 2000,
  1: "A",
  2: "B",
};

user.gender = "male";

console.log(user.age);

// 함수
interface Add {
  (num1: number, num2: number): number;
}

const add: Add = function (x, y) {
  return x + y;
};

add(10, 20);

interface IsAdult {
  (age: number): boolean;
}

const ad: IsAdult = (age) => {
  return age > 19;
};

// 클래스
interface Car {
  color: string;
  wheels: number;
  start(): void;
}

interface Benz extends Car {
  door: number;
  stop(): void;
}

const benz: Benz = {
  color: "black",
  wheels: 5,
  door: 5,
  start() {
    console.log("Go");
  },
  stop() {
    console.log("Stop");
  },
};

class Bmw implements Car {
  color;
  wheels = 4;
  constructor(c: string) {
    this.color = c;
  }

  start() {
    console.log("Go");
  }
}

const car3 = new Bmw("green");
console.log(car3);
car3.start();

// implements
interface Toy {
  name: string;
}

interface ToyCar extends Car, Toy {
  price: number;
}
