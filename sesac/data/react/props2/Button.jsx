import React from "react";

export default function Button(props) {
  const { backgroundColor, text, children } = props;
  console.log(props);

  return (
    <div>
      {children}
      <button
        style={{
          backgroundColor,
        }}
      >
        {text}
      </button>
    </div>
  );
}
