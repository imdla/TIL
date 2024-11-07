import React from "react";

export default function User() {
  const users = [
    { id: 1, name: "김철수", age: 25, hobby: "독서" },
    { id: 2, name: "이영희", age: 28, hobby: "요리" },
    { id: 3, name: "박민수", age: 23, hobby: "게임" },
  ];

  const userShow = users.map((user) => {
    const { name, age, hobby } = user;

    return (
      <ul>
        <li>Name : {name}</li>
        <li>Age : {age}</li>
        <li>Hobby : {hobby}</li>
      </ul>
    );
  });

  return (
    <div>
      <h2>User Page</h2>
      {userShow}
    </div>
  );
}
