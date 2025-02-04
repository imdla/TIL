import React from "react";
import { useParams, useLocation } from "react-router-dom";
import { Outlet } from "react-router-dom";

export default function NovelGenreDetail() {
  const { dayId } = useParams();

  const location = useLocation();
  const { genre } = location.state;
  const { title, content } = genre;

  return (
    <main>
      <h3>{title}</h3>
      <p>{content}</p>
      <Outlet></Outlet>
    </main>
  );
}
