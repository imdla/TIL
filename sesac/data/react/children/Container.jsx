import React, { Children } from "react";

export default function Container({ children }) {
  return (
    <div
      style={{
        border: "2px solid #888",
        borderRadius: "12px",
        width: "200px",
        height: "200px",
        display: "flex",
        alignItems: "center",
        justifyContent: "space-around",
        flexDirection: "column",
        marginBottom: "1rem",
      }}
    >
      {children}
    </div>
  );
}
