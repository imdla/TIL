import React from "react";
import { Link } from "react-router-dom";

export default function WebtoonList({ flag }) {
  const daysWebtoon = [
    {
      id: 1,
      title: "월요일 웹툰",
      content: "월요일 웹툰의 웹툰들",
    },
    {
      id: 2,
      title: "화요일 웹툰",
      content: "화요일 웹툰의 웹툰들",
    },
    {
      id: 3,
      title: "수요일 웹툰",
      content: "수요일 웹툰의 웹툰들",
    },
    {
      id: 4,
      title: "목요일 웹툰",
      content: "목요일 웹툰의 웹툰들",
    },
    {
      id: 5,
      title: "금요일 웹툰",
      content: "금요일 웹툰의 웹툰들",
    },
    {
      id: 6,
      title: "토요일 웹툰",
      content: "토요일 웹툰의 웹툰들",
    },
    {
      id: 7,
      title: "일요일 웹툰",
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
