import React from "react";

export default function WebtoonDayDetailContentBoxList({
  id,
  webtoonName,
  author,
}) {
  return (
    <li key={id}>
      <div className="box">웹툰 이미지</div>
      <p className="bold">{webtoonName}</p>
      <p className="gray">{author}</p>
    </li>
  );
}
