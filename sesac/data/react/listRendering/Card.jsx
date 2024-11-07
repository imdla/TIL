import React from "react";

export default function Card({ styles, backgroundColor, title, content }) {
  return (
    <div
      className="card flex"
      style={{
        styles,
        backgroundColor,
      }}
    >
      <h1>{title}</h1>
      <p>{content}</p>
    </div>
  );
}
