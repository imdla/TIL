import React from "react";

export default function Input() {
  function inputHandle(event) {
    const text = event.target;
    console.log(text.value);
  }

  return (
    <>
      <input type="text" onChange={inputHandle} />
    </>
  );
}
