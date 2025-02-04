import React from "react";
import { Link } from "react-router-dom";

export default function Header() {
  return (
    <header>
      <ul>
        <li>
          <Link to="/">HOME으로</Link>
        </li>
        <li>
          <Link to="/posts">게시글로</Link>
        </li>
      </ul>
    </header>
  );
}
