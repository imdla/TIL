import React from "react";

export default function Item({ item, isPacked }) {
  return (
    <li className="item">
      {item} {isPacked && "✅"}
    </li>
  );
}
