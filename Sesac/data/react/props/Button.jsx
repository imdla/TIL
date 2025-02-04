import React from "react";

export default function Button({ backgroundColor, name }) {
  
  return (
    <>
      <button
        style={{
          backgroundColor
        }}
      >
        {name}
      </button>
    </>
  );
}
