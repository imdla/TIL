import React from "react";

// and 연산자
export default function LoginAnd({ isLogin }) {
  return <div>{!isLogin && "로그인이 필요합니다!"}</div>;
}

// if 연산자
// export default function LoginAnd({ isLogin }) {
//   let result;

//   if (isLogin) {
//    result = null; 
//   }
//    result = "로그인이 필요합니다"; 

//   return <div>{result}</div>;
// }
