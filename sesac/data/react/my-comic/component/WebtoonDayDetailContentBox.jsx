import React from "react";
import WebtoonDayDetailContentBoxList from "./WebtoonDayDetailContentBoxList";

export default function WebtoonDayDetailContentBox({ webtoontypeTitle }) {
  const webtoonDayContents = [
    {
      id: 1,
      webtoonName: "웹툰 제목1",
      author: "작가 이름1",
    },
    {
      id: 2,
      webtoonName: "웹툰 제목2",
      author: "작가 이름2",
    },
    {
      id: 3,
      webtoonName: "웹툰 제목3",
      author: "작가 이름3",
    },
    {
      id: 4,
      webtoonName: "웹툰 제목4",
      author: "작가 이름4",
    },
    {
      id: 5,
      webtoonName: "웹툰 제목5",
      author: "작가 이름5",
    },
  ];

  const webtoonDayContentList = webtoonDayContents.map((webtoonDayContent) => {
    const { id, webtoonName, author } = webtoonDayContent;
    return (
      <WebtoonDayDetailContentBoxList
        id={id}
        webtoonName={webtoonName}
        author={author}
      ></WebtoonDayDetailContentBoxList>
    );
  });

  return (
    <section>
      <h4>{webtoontypeTitle}</h4>
      <ul className="contentUl">{webtoonDayContentList}</ul>
    </section>
  );
}
