import React from "react";
import NovelGenreDetailContentBox from "../component/NovelGenreDetailContentBox";
import { useLocation } from "react-router-dom";

export default function NovelGenreDetailContent() {
  const location = useLocation();
  const { genre } = location.state;

  return (
    <>
      <NovelGenreDetailContentBox
        novelTypeTitle={`웹 소설 ${genre.title} 랭킹`}
      ></NovelGenreDetailContentBox>
      <NovelGenreDetailContentBox
        novelTypeTitle={`연령별 ${genre.title} 랭킹`}
      ></NovelGenreDetailContentBox>
    </>
  );
}
