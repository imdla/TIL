import React from "react";
import NovelGenreDetailContentBoxList from "./NovelGenreDetailContentBoxList";

export default function NovelGenreDetailContentBox({ novelTypeTitle }) {
  const novelGenreContents = [
    {
      id: 1,
      novelName: "소설 제목1",
      author: "작가 이름1",
    },
    {
      id: 2,
      novelName: "소설 제목2",
      author: "작가 이름2",
    },
    {
      id: 3,
      novelName: "소설 제목3",
      author: "작가 이름3",
    },
    {
      id: 4,
      novelName: "소설 제목4",
      author: "작가 이름4",
    },
    {
      id: 5,
      novelName: "소설 제목5",
      author: "작가 이름5",
    },
    {
      id: 6,
      novelName: "소설 제목6",
      author: "작가 이름6",
    },
    {
      id: 7,
      novelName: "소설 제목7",
      author: "작가 이름7",
    },
    {
      id: 8,
      novelName: "소설 제목8",
      author: "작가 이름8",
    },
    {
      id: 9,
      novelName: "소설 제목9",
      author: "작가 이름9",
    },
    {
      id: 10,
      novelName: "소설 제목10",
      author: "작가 이름10",
    },
  ];

  const novelGenreContentList = novelGenreContents.map((novelGenreContent) => {
    const { id, novelName, author } = novelGenreContent;
    return (
      <NovelGenreDetailContentBoxList
        id={id}
        novelName={novelName}
        author={author}
      ></NovelGenreDetailContentBoxList>
    );
  });

  return (
    <>
      <section>
        <h4>{novelTypeTitle}</h4>
        <ul className="rankUl">{novelGenreContentList}</ul>
      </section>
    </>
  );
}
