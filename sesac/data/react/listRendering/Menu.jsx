import React from "react";

export default function Menu({ menus }) {
  return (
    <ul className="menuBox">
      {menus.map((menu) => {
        return (
          <li className="menuItem flex">
            <p>{menu.name}</p>
            <p>{menu.price}</p>
          </li>
        );
      })}
    </ul>
  );
}
