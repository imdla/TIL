import React from "react";

export default function Greet() {
  const imgSrc = "https://i.imgur.com/7vQD0fPs.jpg";

  return (
    <div className="container">
      <h1>환영합니다!</h1>
      <img src={imgSrc}></img>
      <input type="text" name="username" id="username" />
      <label htmlFor="username">사용자 이름</label>
    </div>
  );
}
