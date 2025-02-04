import React from "react";

export default function NovelGenreDetailContentBoxList({
  id,
  novelName,
  author,
}) {
  return (
    <>
      <li key={id}>
        <div className="box relative">
          <div>소설 이미지</div>
          <p>
            <span className="bold">{id}</span>
          </p>
        </div>
        <div>
          <p className="bold">{novelName}</p>
          <p className="gray">{author}</p>
        </div>
      </li>
    </>
  );
}
