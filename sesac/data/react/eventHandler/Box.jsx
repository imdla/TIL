import React from "react";

export default function Box() {
  function onMouse(event) {
    event.target.style.borderColor = "#333";
  }

  function leaveMouse(event) {
    event.target.style.borderColor = "red";
  }

  return (
    <div
      onMouseOver={onMouse}
      onMouseOut={leaveMouse}
      style={{
        width: "100px",
        height: "100px",
        border: "solid 2px red",
      }}
    >
      Box
    </div>
  );
}
