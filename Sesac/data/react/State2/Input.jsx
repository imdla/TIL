import React from "react";

export default function Input({ onChange, children }) {
  return (
    <div>
      <label htmlFor="text">{children}</label>
      <input onChange={onChange} type="text" name="text" id="text" />
    </div>
  );
}
