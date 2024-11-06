import React from "react";

// 삼항연산자
export default function Login({ isLogin }) {
  return (
    <>
      <div>{isLogin ? "환영합니다!" : "로그인이 필요합니다!"}</div>
    </>
  );
}

// IF
// export default function Login({ isLogin }) {
//   let result = null;

//   if (isLogin) {
//     result = "환영합니다"
//   } else {
//     result = "로그인이 필요합니다"
//   }

//   return (
//     <>
//       <div>{result}</div>
//     </>
//   );
// }
