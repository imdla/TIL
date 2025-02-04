import React from "react";
import { Link } from "react-router-dom";

export default function WebtoonList({ flag }) {
  const daysWebtoon = [
    {
      id: "mon",
      title: "월요 웹툰",
      content: "월요일 웹툰의 웹툰들",
    },
    {
      id: "tue",
      title: "화요 웹툰",
      content: "화요일 웹툰의 웹툰들",
    },
    {
      id: "wed",
      title: "수요 웹툰",
      content: "수요일 웹툰의 웹툰들",
    },
    {
      id: "thu",
      title: "목요 웹툰",
      content: "목요일 웹툰의 웹툰들",
    },
    {
      id: "fri",
      title: "금요 웹툰",
      content: "금요일 웹툰의 웹툰들",
    },
    {
      id: "sat",
      title: "토요 웹툰",
      content: "토요일 웹툰의 웹툰들",
    },
    {
      id: "sun",
      title: "일요 웹툰",
      content: "일요일 웹툰의 웹툰들",
    },
  ];

  const daysWebtoonList = daysWebtoon.map((dayWebtoon) => {
    const { id, title } = dayWebtoon;

    return (
      <li key={id} className=" relative">
        <Link to={`/webtoon/days/${id}`} state={{ dayWebtoon }}>
          {flag ? <p>{title}</p> : <h3>{title}</h3>}
        </Link>
      </li>
    );
  });

  return daysWebtoonList;
}
