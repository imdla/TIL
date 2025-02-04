import React from "react";

export default function Button({ backgroundColor, children }) {
  return (
    <button
      style={{
        backgroundColor,
      }}
    >
      {children}
    </button>
  );
}
