import React from "react";
import { Link } from "react-router-dom";
import WebtoonList from "./WebtoonList";

export default function WebtoonHeader() {
  return (
    <header>
      <h2>Webtoon Header</h2>
      <p>웹툰의 헤더입니다</p>
      <ul className="ulTag">
        <li>
          <Link to="/webtoon">웹툰</Link>
        </li>
        <li>
          <Link to="/webtoon/days">요일별 웹툰</Link>
        </li>
      </ul>
      <ul className="categoryUl">
        <WebtoonList flag={true}></WebtoonList>
      </ul>
    </header>
  );
}
