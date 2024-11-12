import React from "react";
import { Link, Outlet } from "react-router-dom";

export default function MainLayout() {
  return (
    <div id="mainLayout">
      <h1>My Comic Page</h1>
      <ul className="ulTag">
        <li>
          <Link to="/">HOME</Link>
        </li>
        <li>
          <Link to="/webtoon">WEBTOON</Link>
        </li>
        <li>
          <Link to="/novel">NOVEL</Link>
        </li>
      </ul>
      <Outlet></Outlet>
    </div>
  );
}
