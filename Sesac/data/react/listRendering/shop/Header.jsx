import React from "react";
import { getImgUrl } from "./utils";

export default function Header() {
  const path = "apparel.png";

  return (
    <header className="flex">
      <nav className="flex">
        <a href="#">
          <h1 className="logo">APPERAL SHOP</h1>
        </a>
        <ul className="menu flex">
          <li>
            <a href="#">HOME</a>
          </li>
          <li>
            <a href="#">PRODUCTS</a>
          </li>
        </ul>
      </nav>
      <img src={getImgUrl(path)} alt="" />
    </header>
  );
}
