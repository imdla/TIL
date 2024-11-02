// if (condition) {
//   // condition이 true일 때
// }

// if (condition) {
//   // condition이 true일 때
// } else {
//   // condition이 false일 때
// }

// 온도
let temperature = 20;

if (temperature < 22) {
  console.log('보일러 틀어줘');
} else {
  console.log('에어컨 틀어줘');
}


// 점수
let score = 75;

if (score >= 90) {
  console.log('A');
} else if (score >= 80) {
  console.log('B');
} else if (score >= 70) {
  console.log('C');
} else {
  console.log('D');
}


// 미세먼지
let dust = 168;

if (dust >= 251) {
  console.log('매우 나쁨');
} else if (dust >= 101) {
  console.log('나쁨');
} else if (dust >= 51) {
  console.log('보통');
} else {
  console.log('좋음');
}


// 초미세먼지
let ultraDust = 100;

if (ultraDust >= 76) {
  console.log('매우 나쁨');
} else if (ultraDust >= 36) {
  console.log('나쁨');
} else if (ultraDust >= 16) {
  console.log('보통');
} else {
  console.log('좋음');
}

// 미세먼지 + 초미세먼지
if (dust >= 251 && ultraDust >= 76) {
  console.log('매우 나쁨');
} else {
  console.log('soso');
}