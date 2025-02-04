// XOR
let money = true;
let car = true;

if (money) {
  if (car) {
    console.log('False');
  } else {
    console.log('True');
  }
} else {
  if (car) {
    console.log('True');
  } else {
    console.log('False');
  }
}

console.log((money || car) && !(money && car));

console.log('======================================');

// 점수에 따른 등급
const score = 100;

if (score >= 90) {
  console.log('A');

  if (score == 100) {
    console.log('교수님의 총애');
  }
} else if (score >= 80) {
  console.log('B');
} else {
  console.log('C');

  if (score < 40) {
    console.log('학사경고');
  }
}

console.log('======================================');

// 일과시간
const isWeekend = true;
const nowTime = 19;

if (9 <= nowTime && nowTime <= 18) {
  if (isWeekend) {
    console.log('휴식');
  } else {
    console.log('일');
  }
} else {
  if (isWeekend) {
    console.log('자기개발');
  } else {
    console.log('휴식');
  }
}

let isWorkingHour = 9 <= nowTime && nowTime <= 18 ? true : false;

if (isWeekend) {
  if (isWorkingHour) {
    console.log('휴식');
  } else {
    console.log('자기개발');
  }
} else {
  if (isWorkingHour) {
    console.log('일');
  } else {
    console.log('휴식');
  }
}
