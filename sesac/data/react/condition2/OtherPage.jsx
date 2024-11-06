import React from "react";
import AdminPage from "./AdminPage";
import UserPage from "./UserPage";
import ManagerPage from "./ManagerPage";

export default function OtherPage({ userType }) {
  let result;

  if (userType === "admin") {
    result = <AdminPage></AdminPage>;
  } else if (userType === "manager") {
    result = <ManagerPage></ManagerPage>;
  } else if (userType === "user") {
    result = <UserPage></UserPage>;
  }

  return (
    <div className="page">
      <h1>{userType}'s 앱</h1>
      {result}
    </div>
  );
}
