import React from "react";
import { useParams, useLocation } from "react-router-dom";
import { Outlet } from "react-router-dom";

export default function WebtoonDayDetail() {
  const { dayId } = useParams();

  const location = useLocation();
  const { dayWebtoon } = location.state;
  const { title, content } = dayWebtoon;

  return (
    <main>
      <h3>{title}</h3>
      <p gray>{content}</p>
      <Outlet></Outlet>
    </main>
  );
}
