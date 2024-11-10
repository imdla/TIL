import React from "react";

export default function Button({ children }) {
  function handleClick() {
    alert("버튼 클릭");
  }

  return (
    <button onClick={handleClick} style={{ backgroundColor: "tomato" }}>
      {children}
    </button>
  );
}
