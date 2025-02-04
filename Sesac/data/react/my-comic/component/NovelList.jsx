import React from "react";
import { Link } from "react-router-dom";

export default function NovelList({ flag }) {
  const genres = [
    {
      id: 1,
      title: "로맨스",
      content: "로맨스 웹 소설들",
    },
    {
      id: 2,
      title: "무협",
      content: "무협 웹 소설들",
    },
    {
      id: 3,
      title: "판타지",
      content: "판타지 웹 소설들",
    },
    {
      id: 4,
      title: "추리",
      content: "추리 웹 소설들",
    },
    {
      id: 5,
      title: "스릴러",
      content: "스릴러 웹 소설들",
    },
    {
      id: 6,
      title: "과학",
      content: "과학 웹 소설들",
    },
    {
      id: 7,
      title: "게임",
      content: "게임 웹 소설들",
    },
  ];

  const genresList = genres.map((genre) => {
    const { id, title } = genre;
    return (
      <li key={id} className=" relative">
        <Link to={`/novel/genre/${id}`} state={{ genre }}>
          {flag ? <p>{title}</p> : <h3>{title}</h3>}
        </Link>
      </li>
    );
  });

  return genresList;
}
