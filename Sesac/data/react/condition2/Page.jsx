import React from "react";
import AdminPage from "./AdminPage";
import UserPage from "./UserPage";

// if
export default function Page({ userType }) {
  let result;

  if (userType === "admin") {
    result = <AdminPage></AdminPage>;
  } else {
    result = <UserPage></UserPage>;
  }

  return (
    <div className="page">
      <h1>{userType}'s 앱</h1>
      {result}
    </div>
  );
}

// 삼항연산자
// export default function Page({ userType }) {
//   return (
//     <div className="page">
//       <h1>나의 앱</h1>
//       {userType === "admin" ? <AdminPage></AdminPage> : <UserPage></UserPage>}
//     </div>
//   );
// }
