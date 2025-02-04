import React from "react";

export default function Button({ backgroundColor, text }) {
  return (
    <button
      style={{
        backgroundColor,
      }}
    >
      {text}
    </button>
  );
}
