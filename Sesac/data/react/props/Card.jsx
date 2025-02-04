import React from "react";

export default function Card({ size, title, content }) {
  return (
    <div className="card" style={size}>
      <div>{title}</div>
      <div>{content}</div>
    </div>
  );
}
