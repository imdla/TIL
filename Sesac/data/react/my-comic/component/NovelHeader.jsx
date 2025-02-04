import React from "react";
import { Link } from "react-router-dom";
import NovelList from "./NovelList";

export default function NovelHeader() {
  return (
    <header>
      <h2>Novel Header</h2>
      <p>소설의 헤더입니다</p>

      <ul className="ulTag">
        <li>
          <Link to="/novel">소설</Link>
        </li>
        <li>
          <Link to="/novel/genre">장르</Link>
        </li>
      </ul>
      <ul className="categoryUl">
        <NovelList flag={true}></NovelList>
      </ul>
    </header>
  );
}
