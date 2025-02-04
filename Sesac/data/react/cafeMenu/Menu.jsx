import React from "react";

function makeMenuItem(Items) {
  return Items.map((item) => {
    return (
      <li>
        <p className="itemName">{item.name}</p>
        <p className="itemPrice">{item.price}</p>
      </li>
    );
  });
}

export default function Menu({ menuName, menuItems }) {
  return (
    <div>
      <h2>{menuName}</h2>
      <ul>{makeMenuItem(menuItems)}</ul>
    </div>
  );
}
