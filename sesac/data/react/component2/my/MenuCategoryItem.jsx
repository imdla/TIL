import React from "react";

export default function MenuCategoryItem({ name, price }) {
  return (
    <li>
      <div>{name}</div>
      <div>{price}</div>
    </li>
  );
}
