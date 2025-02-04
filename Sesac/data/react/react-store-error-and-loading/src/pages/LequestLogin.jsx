import React from "react";
import { useNavigate } from "react-router-dom";

export default function LequestLogin() {
  const navigate = useNavigate();
  function handleClick() {
    navigate("..", { relative: "path" });
  }

  return (
    <div>
      <h2>Request Login</h2>
      <p>로그인이 필요한 페이지입니다.</p>

      <button onClick={handleClick}>이전 페이지</button>
    </div>
  );
}
